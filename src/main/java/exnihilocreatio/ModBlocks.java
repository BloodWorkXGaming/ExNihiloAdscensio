package exnihilocreatio;

import exnihilocreatio.blocks.*;
import exnihilocreatio.tiles.*;
import exnihilocreatio.util.Data;
import exnihilocreatio.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static final BlockBaseFalling dust = (BlockBaseFalling) new BlockBaseFalling(SoundType.CLOTH, "block_dust").setHardness(0.7F);
    public static final BlockBaseFalling netherrackCrushed = (BlockBaseFalling) new BlockBaseFalling(SoundType.GROUND, "block_netherrack_crushed").setHardness(0.7F);
    public static final BlockBaseFalling endstoneCrushed = (BlockBaseFalling) new BlockBaseFalling(SoundType.GROUND, "block_endstone_crushed").setHardness(0.7F);
    public static final BlockBaseFalling skystoneCrushed = Loader.isModLoaded("appliedenergistics2") ? (BlockBaseFalling) new BlockBaseFalling(SoundType.GROUND, "block_skystone_crushed").setHardness(0.7F) : null;

    public static final BlockBaseFalling crushedAndesite = (BlockBaseFalling) new BlockBaseFalling(SoundType.GROUND, "block_andesite_crushed").setHardness(0.7F);
    public static final BlockBaseFalling crushedDiorite = (BlockBaseFalling) new BlockBaseFalling(SoundType.GROUND, "block_diorite_crushed").setHardness(0.7F);
    public static final BlockBaseFalling crushedGranite = (BlockBaseFalling) new BlockBaseFalling(SoundType.GROUND, "block_granite_crushed").setHardness(0.7F);


    public static final BlockBarrel barrelWood = new BlockBarrel(0, Material.WOOD);
    public static final BlockBarrel barrelStone = new BlockBarrel(1, Material.ROCK);
    public static final BlockInfestingLeaves infestingLeaves = new BlockInfestingLeaves();
    public static final BlockInfestedLeaves infestedLeaves = new BlockInfestedLeaves();
    public static final BlockSieve sieve = new BlockSieve();

    public static final BlockCrucibleStone crucibleStone = new BlockCrucibleStone();
    public static final BlockCrucibleWood crucibleWood = new BlockCrucibleWood();

    public static final BlockWaterwheel watermill = new BlockWaterwheel();
    public static final BlockStoneAxle axle_stone = new BlockStoneAxle();

    public static final BlockGrinder grinder = new BlockGrinder();
    public static final BlockAutoSifter autoSifter = new BlockAutoSifter();

    public static final BlockEndCake endCake = new BlockEndCake();

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        for (Block block : Data.BLOCKS) {
            registry.register(block);
        }

        // TODO: Change in 1.13 as this is world breaking
        GameRegistry.registerTileEntity(TileBarrel.class, "block_barrel");
        GameRegistry.registerTileEntity(TileInfestingLeaves.class, "block_infesting_leaves");
        GameRegistry.registerTileEntity(TileInfestedLeaves.class, "block_infested_leaves");
        GameRegistry.registerTileEntity(TileCrucibleStone.class, "block_crucible");
        GameRegistry.registerTileEntity(TileCrucibleWood.class, "block_crucible_wood");
        GameRegistry.registerTileEntity(TileSieve.class, "block_sieve");
        GameRegistry.registerTileEntity(TileWaterwheel.class, "block_waterwheel");
        GameRegistry.registerTileEntity(TileStoneAxle.class, "block_axle_stone");
        GameRegistry.registerTileEntity(TileGrinder.class, "block_grinder");
        GameRegistry.registerTileEntity(TileAutoSifter.class, "block_auto_sifter");
    }

    @SideOnly(Side.CLIENT)
    public static void initModels(ModelRegistryEvent e) {
        for (Block block : Data.BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).initModel(e);
            }
        }
    }
}
