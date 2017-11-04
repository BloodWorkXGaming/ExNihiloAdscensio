package exnihilocreatio.tiles;

import exnihilocreatio.config.ModConfig;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.util.ItemInfo;
import exnihilocreatio.util.TankUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TileCrucibleWood extends TileCrucibleBase {

    public TileCrucibleWood() {
        super(ExNihiloRegistryManager.CRUCIBLE_WOOD_REGISTRY);
    }

    @Override
    public void update() {
        if (getWorld().isRemote)
            return;

        ticksSinceLast++;

        if (ticksSinceLast >= 10) {
            ticksSinceLast = 0;

            int heatRate = getHeatRate();

            if (heatRate <= 0)
                return;

            if (solidAmount <= 0) {
                if (!itemHandler.getStackInSlot(0).isEmpty()) {
                    currentItem = new ItemInfo(itemHandler.getStackInSlot(0));
                    itemHandler.getStackInSlot(0).shrink(1);

                    if (itemHandler.getStackInSlot(0).isEmpty()) {
                        itemHandler.setStackInSlot(0, ItemStack.EMPTY);
                    }

                    solidAmount = crucibleRegistry.getMeltable(currentItem).getAmount();
                } else {
                    if (currentItem != null) {
                        currentItem = null;

                        markDirtyClient();
                    }

                    return;
                }
            }

            if (!itemHandler.getStackInSlot(0).isEmpty() && itemHandler.getStackInSlot(0).isItemEqual(currentItem.getItemStack())) {
                // For meltables with a really small "amount"
                while (heatRate > solidAmount && !itemHandler.getStackInSlot(0).isEmpty()) {
                    solidAmount += crucibleRegistry.getMeltable(currentItem).getAmount();
                    itemHandler.getStackInSlot(0).shrink(1);

                    if (itemHandler.getStackInSlot(0).isEmpty()) {
                        itemHandler.setStackInSlot(0, ItemStack.EMPTY);
                    }
                }
            }

            // Never take more than we have left
            if (heatRate > solidAmount) {
                heatRate = solidAmount;
            }

            if (heatRate > 0 && currentItem != null && crucibleRegistry.canBeMelted(currentItem)) {
                FluidStack toFill = new FluidStack(FluidRegistry.getFluid(crucibleRegistry.getMeltable(currentItem).getFluid()), heatRate);
                int filled = tank.fillInternal(toFill, true);
                solidAmount -= filled;

                if (filled > 0) {
                    markDirtyClient();
                }
            }
        }
    }

    @Override
    public int getHeatRate() {
        return ModConfig.crucible.woodenCrucibleSpeed;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ, IFluidHandler handler) {
        if (TankUtil.drainWaterIntoBottle(this, player, tank))
            return true;

        return super.onBlockActivated(world, pos, state, player, hand, side, hitX, hitY, hitZ, handler);
    }
}
