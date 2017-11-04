package exnihilocreatio.blocks;

import exnihilocreatio.config.ModConfig;
import exnihilocreatio.tiles.TileCrucibleWood;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BlockCrucibleWood extends BlockCrucibleBase {

    public BlockCrucibleWood() {
        super("block_crucible_wood", Material.WOOD);
        setDefaultState(this.blockState.getBaseState().withProperty(THIN, ModConfig.client.thinCrucibleModel));

        this.setHardness(2.0f);
    }

    @Override
    public TileEntity createTileEntity(@Nonnull World worldIn, @Nonnull IBlockState state) {
        return new TileCrucibleWood();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, THIN);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(THIN, ModConfig.client.thinCrucibleModel);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel(ModelRegistryEvent e) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "thin=" + ModConfig.client.thinCrucibleModel));
    }
}
