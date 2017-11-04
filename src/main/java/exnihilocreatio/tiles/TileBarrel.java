package exnihilocreatio.tiles;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.barrel.BarrelFluidHandler;
import exnihilocreatio.barrel.BarrelItemHandler;
import exnihilocreatio.barrel.IBarrelMode;
import exnihilocreatio.blocks.BlockBarrel;
import exnihilocreatio.config.ModConfig;
import exnihilocreatio.networking.MessageBarrelModeUpdate;
import exnihilocreatio.networking.MessageCheckLight;
import exnihilocreatio.networking.PacketHandler;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.BarrelModeRegistry;
import exnihilocreatio.registries.registries.BarrelModeRegistry.TriggerType;
import exnihilocreatio.registries.types.Milkable;
import exnihilocreatio.util.TankUtil;
import lombok.Getter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class TileBarrel extends BaseTileEntity implements ITickable {

    @Getter
    private IBarrelMode mode;
    @Getter
    private BarrelItemHandler itemHandler;
    @Getter
    private BarrelFluidHandler tank;
    @Getter
    private int tier;

    @Getter
    private long entityWalkCooldown; //The time after which the barrel will attempt to milk an Entity. Based on the world clock

    public TileBarrel() {
        this(ModBlocks.barrelWood);
    }

    public TileBarrel(BlockBarrel block) {
        this.tier = block.getTier();
        this.blockType = block;
        itemHandler = new BarrelItemHandler(this);
        tank = new BarrelFluidHandler(this);
        this.entityWalkCooldown = 0;
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (mode == null || mode.getName().equals("fluid")) {
            if (TankUtil.drainWaterFromBottle(this, player, tank))
                return true;

            if (tank != null && TankUtil.drainWaterIntoBottle(this, player, tank))
                return true;


            ItemStack stack = player.getHeldItem(hand);

            IFluidHandler fluidHandler = getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
            boolean result = false;
            if (fluidHandler != null) result = FluidUtil.interactWithFluidHandler(player, hand, fluidHandler);

            if (result) {
                if (!player.isCreative()) {
                    stack.shrink(1);
                }

                PacketHandler.sendNBTUpdate(this);
                markDirty();
                if (getBlockType().getLightValue(state, world, pos) != world.getLight(pos)) {
                    world.checkLight(pos);
                    PacketHandler.sendToAllAround(new MessageCheckLight(pos), this);
                }

                return true;
            }

            //Check for more fluid
            IFluidHandler tank = getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
            FluidStack bucketStack = FluidUtil.getFluidContained(stack);

            if (tank == null) {
                return false;
            }

            FluidStack tankStack = tank.drain(Integer.MAX_VALUE, false);
            if (bucketStack != null && tankStack != null
                    && bucketStack.getFluid() == tankStack.getFluid()
                    && tank.fill(FluidUtil.getFluidContained(stack), false) != 0) {
                tank.drain(Fluid.BUCKET_VOLUME, true);
                result = FluidUtil.interactWithFluidHandler(player, hand, fluidHandler);

                if (result && !player.isCreative()) {
                    stack.shrink(1);
                }

                PacketHandler.sendNBTUpdate(this);
            }
        }

        if (mode == null) {
            if (!player.getHeldItem(hand).isEmpty()) {
                ItemStack stack = player.getHeldItem(hand);
                ArrayList<IBarrelMode> modes = BarrelModeRegistry.getModes(TriggerType.ITEM);
                if (modes == null)
                    return false;
                for (IBarrelMode possibleMode : modes) {
                    if (possibleMode.isTriggerItemStack(stack)) {
                        setMode(possibleMode.getName());
                        PacketHandler.sendToAllAround(new MessageBarrelModeUpdate(mode.getName(), this.pos), this);
                        mode.onBlockActivated(world, this, pos, state, player, hand, side, hitX, hitY, hitZ);
                        this.markDirty();
                        this.getWorld().setBlockState(pos, state);

                        if (getBlockType().getLightValue(state, world, pos) != world.getLight(pos)) {
                            world.checkLight(pos);
                            PacketHandler.sendToAllAround(new MessageCheckLight(pos), this);
                        }

                        return true;
                    }
                }
            }
        } else {
            mode.onBlockActivated(world, this, pos, state, player, hand, side, hitX, hitY, hitZ);
            markDirty();

            if (getBlockType().getLightValue(state, world, pos) != world.getLight(pos)) {
                world.checkLight(pos);
                PacketHandler.sendToAllAround(new MessageCheckLight(pos), this);
            }

            return true;
        }

        return true;
    }

    @Override
    public void update() {
        if (getWorld().isRemote)
            return;

        if (ModConfig.mechanics.shouldBarrelsFillWithRain && (mode == null || mode.getName().equalsIgnoreCase("fluid"))) {
            BlockPos plusY = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            if (getWorld().isRainingAt(plusY)) {
                FluidStack stack = new FluidStack(FluidRegistry.WATER, 2);
                tank.fill(stack, true);
            }
        }
        if (mode != null)
            mode.update(this);

        if (getBlockType().getLightValue(getWorld().getBlockState(pos), getWorld(), pos) != getWorld().getLight(pos)) {
            getWorld().checkLight(pos);
            PacketHandler.sendToAllAround(new MessageCheckLight(pos), this);
        }
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tank.writeToNBT(tag);

        if (mode != null) {
            NBTTagCompound barrelModeTag = new NBTTagCompound();
            mode.writeToNBT(barrelModeTag);
            barrelModeTag.setString("name", mode.getName());
            tag.setTag("mode", barrelModeTag);
        }

        NBTTagCompound handlerTag = itemHandler.serializeNBT();
        tag.setTag("itemHandler", handlerTag);
        tag.setInteger("barrelTier", tier);

        return super.writeToNBT(tag);

    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        tank.readFromNBT(tag);
        if (tag.hasKey("mode")) {
            NBTTagCompound barrelModeTag = (NBTTagCompound) tag.getTag("mode");
            this.setMode(barrelModeTag.getString("name"));
            if (mode != null)
                mode.readFromNBT(barrelModeTag);
        }

        if (tag.hasKey("itemHandler")) {
            itemHandler.deserializeNBT((NBTTagCompound) tag.getTag("itemHandler"));
        }

        if (tag.hasKey("barrelTier")) {
            tier = tag.getInteger("barrelTier");
        }
        super.readFromNBT(tag);
    }

    public void setMode(String modeName) {
        try {
            if (modeName.equals("null"))
                mode = null;
            else
                mode = BarrelModeRegistry.getModeByName(modeName).getClass().newInstance();
            this.markDirty();
        } catch (Exception e) {
            e.printStackTrace(); //Naughty
        }
    }

    public void setMode(IBarrelMode mode) {
        this.mode = mode;
        this.markDirty();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) itemHandler;
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return (T) tank;

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ||
                capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ||
                super.hasCapability(capability, facing);
    }

    //TODO: Add Moo Fluids support if it ever updates
    public void entityOnTop(World world, Entity entityIn){
        long currentTime = world.getTotalWorldTime(); //Get the current time, shouldn't be affected by in-game /time command
        if(currentTime < this.entityWalkCooldown) return; // Cooldown hasn't elapsed, do nothing

        Milkable milk = ExNihiloRegistryManager.MILK_ENTITY_REGISTRY.getMilkable(entityIn);
        if(milk == null) return; // Not a valid recipe

        // Attempt to add the fluid if it is a valid fluid
        Fluid result = FluidRegistry.getFluid(milk.getResult());
        if(result != null)
            this.tank.fill(new FluidStack(result, milk.getAmount()), true);

        //Set the new cooldown time
        this.entityWalkCooldown = currentTime + milk.getCoolDown();
    }
}
