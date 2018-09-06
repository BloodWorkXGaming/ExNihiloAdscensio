package exnihilocreatio.recipes.defaults;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.compatibility.ForestryHelper;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.util.BlockInfo;
import lombok.Getter;
import net.minecraft.init.Blocks;

public class ExtraBees implements IRecipeDefaults {
    @Getter
    public String MODID = "extrabees";

    @Override
    public void registerSieve(SieveRegistry registry) {
        /*
         BEEEEEEEEEES
         */
        // Gravel for Rocky Bees
        registry.register("gravel", ForestryHelper.getDroneInfo("extrabees.species.rock"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register("gravel", ForestryHelper.getIgnobleInfo("extrabees.species.rock"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register("gravel", ForestryHelper.getPristineInfo("extrabees.species.rock"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Crushed Diorite for Marbled Bees
        registry.register(new BlockInfo(ModBlocks.crushedDiorite), ForestryHelper.getDroneInfo("extrabees.species.marble"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(new BlockInfo(ModBlocks.crushedDiorite), ForestryHelper.getIgnobleInfo("extrabees.species.marble"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(new BlockInfo(ModBlocks.crushedDiorite), ForestryHelper.getPristineInfo("extrabees.species.marble"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Crushed Netherrack for Embittered Bees
        registry.register(new BlockInfo(ModBlocks.netherrackCrushed), ForestryHelper.getDroneInfo("extrabees.species.basalt"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(new BlockInfo(ModBlocks.netherrackCrushed), ForestryHelper.getIgnobleInfo("extrabees.species.basalt"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(new BlockInfo(ModBlocks.netherrackCrushed), ForestryHelper.getPristineInfo("extrabees.species.basalt"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Clay for Water Bees --- clearly the most balanced of bees
        registry.register(new BlockInfo(Blocks.CLAY), ForestryHelper.getDroneInfo("extrabees.species.water"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(new BlockInfo(Blocks.CLAY), ForestryHelper.getIgnobleInfo("extrabees.species.water"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(new BlockInfo(Blocks.CLAY), ForestryHelper.getPristineInfo("extrabees.species.water"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
    }

}
