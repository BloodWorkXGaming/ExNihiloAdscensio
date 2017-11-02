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


    public static final BlockBaseFalling dust;
    public static final BlockBaseFalling netherrackCrushed;
    public static final BlockBaseFalling endstoneCrushed;
    public static BlockBaseFalling skystoneCrushed = null;
    public static final BlockBarrel barrelWood;
    public static final BlockBarrel barrelStone;
    public static final BlockInfestedLeaves infestedLeaves;
    public static final BlockSieve sieve;

    public static final BlockCrucibleStone crucibleStone;
    public static final BlockCrucibleWood CRUCIBLE_WOOD;

    public static final BlockWaterwheel watermill;
    public static final BlockStoneAxle axle_stone;

    public static final BlockGrinder grinder;
    public static final BlockAutoSifter autoSifter;

    static {
        dust = new BlockBaseFalling(SoundType.CLOTH, "block_dust");
        dust.setCreativeTab(ExNihiloCreatio.tabExNihilo);
        dust.setHardness(0.7F);

        netherrackCrushed = new BlockBaseFalling(SoundType.GROUND, "block_netherrack_crushed");
        netherrackCrushed.setCreativeTab(ExNihiloCreatio.tabExNihilo);
        netherrackCrushed.setHardness(0.7F);

        endstoneCrushed = new BlockBaseFalling(SoundType.GROUND, "block_endstone_crushed");
        endstoneCrushed.setCreativeTab(ExNihiloCreatio.tabExNihilo);
        endstoneCrushed.setHardness(0.7F);

        if(Loader.isModLoaded("appliedenergistics2")){
            skystoneCrushed = new BlockBaseFalling(SoundType.GROUND, "block_skystone_crushed");
            skystoneCrushed.setCreativeTab(ExNihiloCreatio.tabExNihilo);
            skystoneCrushed.setHardness(0.7F);
        }

        barrelWood = new BlockBarrel(0, Material.WOOD);
        barrelWood.setCreativeTab(ExNihiloCreatio.tabExNihilo);

        barrelStone = new BlockBarrel(1, Material.ROCK);
        barrelStone.setCreativeTab(ExNihiloCreatio.tabExNihilo);
        GameRegistry.registerTileEntity(TileBarrel.class, "block_barrel");

        infestedLeaves = new BlockInfestedLeaves();
        GameRegistry.registerTileEntity(TileInfestedLeaves.class, "block_infested_leaves");
        infestedLeaves.setCreativeTab(ExNihiloCreatio.tabExNihilo);

        crucibleStone = new BlockCrucibleStone();
        GameRegistry.registerTileEntity(TileCrucibleStone.class, "block_crucible");

        CRUCIBLE_WOOD = new BlockCrucibleWood();
        GameRegistry.registerTileEntity(TileCrucibleWood.class, "block_crucible_wood");

        sieve = new BlockSieve();
        sieve.setCreativeTab(ExNihiloCreatio.tabExNihilo);
        GameRegistry.registerTileEntity(TileSieve.class, "block_sieve");


        watermill = new BlockWaterwheel();
        GameRegistry.registerTileEntity(TileWaterwheel.class, "block_waterwheel");

        axle_stone = new BlockStoneAxle();
        GameRegistry.registerTileEntity(TileStoneAxle.class, "block_axle_stone");

        grinder = new BlockGrinder();
        GameRegistry.registerTileEntity(TileGrinder.class, "block_grinder");

        autoSifter = new BlockAutoSifter();
        GameRegistry.registerTileEntity(TileAutoSifter.class, "block_auto_sifter");

    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        for (Block block : Data.BLOCKS) {
            registry.register(block);
        }
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
