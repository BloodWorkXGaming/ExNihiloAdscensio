package exnihilocreatio.blocks;

import exnihilocreatio.config.ModConfig;
import exnihilocreatio.tiles.TileCrucibleStone;
import exnihilocreatio.util.IHasSpecialRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BlockCrucibleStone extends BlockCrucibleBase implements IHasSpecialRegistry {

    public static final PropertyBool FIRED = PropertyBool.create("fired");

    public BlockCrucibleStone() {
        super("block_crucible", Material.ROCK);

        this.setHardness(2.0f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FIRED, false).withProperty(THIN, ModConfig.client.thinCrucibleModel));
    }

    @Override
    public TileEntity createTileEntity(@Nonnull World worldIn, @Nonnull IBlockState state) {
        if (state.getValue(FIRED))
            return new TileCrucibleStone();

        return null;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return state.getValue(FIRED);
    }

    @Override
    @Nonnull
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FIRED, THIN);
    }

    @Override
    @Nonnull
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FIRED, meta != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FIRED) ? 1 : 0;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        if (itemIn == getCreativeTabToDisplayOn()) {
            items.add(new ItemStack(this, 1, 0));
            items.add(new ItemStack(this, 1, 1));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel(ModelRegistryEvent e) {
        ModelResourceLocation unfired = new ModelResourceLocation(getRegistryName(), "fired=false,thin=" + ModConfig.client.thinCrucibleModel);
        ModelResourceLocation fired = new ModelResourceLocation(getRegistryName(), "fired=true,thin=" + ModConfig.client.thinCrucibleModel);

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, unfired);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 1, fired);
    }
}
