package exnihilocreatio.blocks;

import exnihilocreatio.barrel.IBarrelMode;
import exnihilocreatio.barrel.modes.block.BarrelModeBlock;
import exnihilocreatio.barrel.modes.compost.BarrelModeCompost;
import exnihilocreatio.barrel.modes.fluid.BarrelModeFluid;
import exnihilocreatio.barrel.modes.transform.BarrelModeFluidTransform;
import exnihilocreatio.compatibility.ITOPInfoProvider;
import exnihilocreatio.config.ModConfig;
import exnihilocreatio.tiles.TileBarrel;
import exnihilocreatio.util.Util;
import lombok.Getter;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class BlockBarrel extends BlockBase implements ITileEntityProvider, ITOPInfoProvider {

    private final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0625f, 0, 0.0625f, 0.9375f, 1f, 0.9375f);
    @Getter
    private int tier;

    public BlockBarrel(int tier, Material material) {
        super(material, "block_barrel" + tier);
        this.tier = tier;
        this.setHardness(2.0f);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null && te instanceof TileBarrel) {
            TileBarrel barrel = (TileBarrel) te;

            if (barrel.getMode() != null && barrel.getMode().getName().equals("block")) {
                IItemHandler barrelCap = barrel.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                if (barrelCap != null) {
                    ItemStack stack = barrelCap.getStackInSlot(0);
                    if (!stack.isEmpty())
                        Util.dropItemInWorld(te, null, stack, 0);
                }
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getLightValue(@Nonnull IBlockState state, IBlockAccess world, @Nonnull BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);

        if (te != null && te instanceof TileBarrel) {
            TileBarrel tile = (TileBarrel) te;
            if (tile.getMode() instanceof BarrelModeBlock) {
                BarrelModeBlock mode = (BarrelModeBlock) tile.getMode();

                if (mode.getBlock() != null) {
                    return Block.getBlockFromItem(mode.getBlock().getItem()).getStateFromMeta(mode.getBlock().getMeta()).getLightValue();
                }
            } else if (tile.getMode() instanceof BarrelModeFluid) {
                BarrelModeFluid mode = (BarrelModeFluid) tile.getMode();

                if (mode.getFluidHandler(tile).getFluidAmount() > 0) {
                    return Util.getLightValue(mode.getFluidHandler(tile).getFluid());
                }
            } else if (ModConfig.misc.enableBarrelTransformLighting) {
                if (tile.getMode() instanceof BarrelModeCompost) {
                    BarrelModeCompost mode = (BarrelModeCompost) tile.getMode();

                    if (mode.getCompostState() != null) {
                        int value = mode.getCompostState().getLightValue() / 2;

                        return Math.round(Util.weightedAverage((float) value / 2, value, mode.getProgress()));
                    }
                } else if (tile.getMode() instanceof BarrelModeFluidTransform) {
                    BarrelModeFluidTransform mode = (BarrelModeFluidTransform) tile.getMode();

                    int inputValue = Util.getLightValue(mode.getInputStack());
                    int outputValue = Util.getLightValue(mode.getOutputStack());

                    return Math.round(Util.weightedAverage(outputValue, inputValue, mode.getProgress()));
                }
            }
        }

        return super.getLightValue(state, world, pos);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote || worldIn.getTileEntity(pos) == null)
            return true;

        return ((TileBarrel) worldIn.getTileEntity(pos)).onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    @Deprecated
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileBarrel(this);
    }

    @Override
    @Nonnull
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return boundingBox;
    }

    @Override
    public boolean isTopSolid(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world,
                             IBlockState blockState, IProbeHitData data) {
        TileBarrel barrel = (TileBarrel) world.getTileEntity(data.getPos());
        if (barrel == null)
            return;

        if (mode == ProbeMode.EXTENDED)
            probeInfo.text(TextFormatting.GREEN + "Mode: " + StringUtils.capitalize(barrel.getMode().getName()));

        IBarrelMode barrelMode = barrel.getMode();
        if (barrelMode != null) {
            List<String> tooltips = barrelMode.getWailaTooltip(barrel, new ArrayList<>());
            for (String tooltip : tooltips) {
                probeInfo.text(tooltip);
            }
        }
    }

    // Barrels will attempt to milk entities
    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn){
        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null && te instanceof TileBarrel) {
            ((TileBarrel) te).entityOnTop(worldIn, entityIn);
        }
    }


}
