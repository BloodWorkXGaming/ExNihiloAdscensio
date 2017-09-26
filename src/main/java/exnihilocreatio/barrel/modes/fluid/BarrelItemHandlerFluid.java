package exnihilocreatio.barrel.modes.fluid;

import exnihilocreatio.barrel.modes.mobspawn.BarrelModeMobSpawn;
import exnihilocreatio.items.ItemDoll;
import exnihilocreatio.networking.MessageBarrelModeUpdate;
import exnihilocreatio.networking.PacketHandler;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.tiles.TileBarrel;
import exnihilocreatio.util.ItemInfo;
import lombok.Setter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class BarrelItemHandlerFluid extends ItemStackHandler {

    @Setter
    private TileBarrel barrel;

    public BarrelItemHandlerFluid(TileBarrel barrel) {
        super(1);
        this.barrel = barrel;
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        FluidTank tank = barrel.getTank();

        if (tank.getFluid() == null)
            return stack;

        if (ExNihiloRegistryManager.FLUID_BLOCK_TRANSFORMER_REGISTRY.canBlockBeTransformedWithThisFluid(tank.getFluid().getFluid(), stack) && tank.getFluidAmount() == tank.getCapacity()) {
            ItemInfo info = ExNihiloRegistryManager.FLUID_BLOCK_TRANSFORMER_REGISTRY.getBlockForTransformation(tank.getFluid().getFluid(), stack);

            if (info != null) {
                if (!simulate) {
                    tank.drain(tank.getCapacity(), true);
                    barrel.setMode("block");
                    PacketHandler.sendToAllAround(new MessageBarrelModeUpdate("block", barrel.getPos()), barrel);

                    barrel.getMode().addItem(info.getItemStack(), barrel);
                }

                if (stack.getItem().hasContainerItem(stack)) {

                }

                ItemStack ret = stack.copy();
                ret.shrink(1);

                return ret.isEmpty() ? ItemStack.EMPTY : ret;
            }

        }

        String fluidItemFluidOutput = ExNihiloRegistryManager.FLUID_ITEM_FLUID_REGISTRY.getFluidForTransformation(tank.getFluid().getFluid(), stack);
        if (fluidItemFluidOutput != null && tank.getFluidAmount() == tank.getCapacity()) {
            if (!simulate) {
                int flfAmount = ExNihiloRegistryManager.FLUID_ITEM_FLUID_REGISTRY.getAmountFromTransformation(tank.getFluid().getFluid(), stack);

                tank.drain(tank.getCapacity(), true);
                barrel.setMode("fluid");
                PacketHandler.sendToAllAround(new MessageBarrelModeUpdate("block", barrel.getPos()), barrel);
                tank.fill(FluidRegistry.getFluidStack(fluidItemFluidOutput, flfAmount),true);
                PacketHandler.sendNBTUpdate(barrel);
            }
            ItemStack ret = stack.copy();
            ret.shrink(1);

            return ret.isEmpty() ? ItemStack.EMPTY : ret;
        }

        if (!stack.isEmpty() && tank.getFluidAmount() == tank.getCapacity() && stack.getItem() instanceof ItemDoll
                && ((ItemDoll) stack.getItem()).getSpawnFluid(stack) == tank.getFluid().getFluid()) {
            if (!simulate) {
                barrel.getTank().drain(Fluid.BUCKET_VOLUME, true);
                barrel.setMode("mobspawn");
                ((BarrelModeMobSpawn) barrel.getMode()).setDollStack(stack);
                PacketHandler.sendNBTUpdate(barrel);
            }
            ItemStack ret = stack.copy();
            ret.shrink(1);

            return ret;
        }

        return stack;
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return ItemStack.EMPTY;
    }
}
