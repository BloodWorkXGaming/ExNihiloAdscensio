package exnihilocreatio.recipes.defaults;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.compatibility.ForestryHelper;
import exnihilocreatio.registries.registries.CompostRegistry;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.ItemInfo;
import lombok.Getter;
import net.minecraft.init.Blocks;

public class MagicBees implements IRecipeDefaults {
    @Getter
    public String MODID = "magicbees";

    @Override
    public void registerCompost(CompostRegistry registry) {
        registry.register(new ItemInfo("magicbees:propolis:0"), 0.125f, new BlockInfo(Blocks.SLIME_BLOCK), new Color("7B934B"));
        registry.register(new ItemInfo("magicbees:propolis:1"), 0.125f, new BlockInfo(Blocks.SLIME_BLOCK), new Color("7B934B"));
        registry.register(new ItemInfo("magicbees:propolis:2"), 0.25f, new BlockInfo(Blocks.MAGMA), new Color("9B4314"));
        registry.register(new ItemInfo("magicbees:propolis:3"), 0.125f, new BlockInfo(Blocks.SLIME_BLOCK), new Color("7B934B"));
        registry.register(new ItemInfo("magicbees:propolis:4"), 0.25f, new BlockInfo(Blocks.STONE), new Color("ACAfA5"));
        registry.register(new ItemInfo("magicbees:propolis:5"), 0.125f, new BlockInfo(Blocks.SLIME_BLOCK), new Color("7B934B"));
        registry.register(new ItemInfo("magicbees:propolis:6"), 0.125f, new BlockInfo(Blocks.SLIME_BLOCK), new Color("7B934B"));
    }
    @Override
    public void registerSieve(SieveRegistry registry) {
        /*
         BEEEEEEEEEES
         */
        // Crushed Netherrack for Infernal Bees
        registry.register(new BlockInfo(ModBlocks.netherrackCrushed), ForestryHelper.getDroneInfo("magicbees.speciesInfernal"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(new BlockInfo(ModBlocks.netherrackCrushed), ForestryHelper.getIgnobleInfo("magicbees.speciesInfernal"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(new BlockInfo(ModBlocks.netherrackCrushed), ForestryHelper.getPristineInfo("magicbees.speciesInfernal"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Crushed Endstone for Oblivion Bees
        registry.register(new BlockInfo(ModBlocks.endstoneCrushed), ForestryHelper.getDroneInfo("magicbees.speciesOblivion"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(new BlockInfo(ModBlocks.endstoneCrushed), ForestryHelper.getIgnobleInfo("magicbees.speciesOblivion"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(new BlockInfo(ModBlocks.endstoneCrushed), ForestryHelper.getPristineInfo("magicbees.speciesOblivion"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Dirt for Unusual Bees
        registry.register("dirt", ForestryHelper.getDroneInfo("magicbees.speciesUnusual"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register("dirt", ForestryHelper.getIgnobleInfo("magicbees.speciesUnusual"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register("dirt", ForestryHelper.getPristineInfo("magicbees.speciesUnusual"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Grass for Mystical Bees
        registry.register("grass", ForestryHelper.getDroneInfo("magicbees.speciesMystical"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register("grass", ForestryHelper.getIgnobleInfo("magicbees.speciesMystical"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register("grass", ForestryHelper.getPristineInfo("magicbees.speciesMystical"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Sand for Sorcerous Bees
        registry.register("sand", ForestryHelper.getDroneInfo("magicbees.speciesSorcerous"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register("sand", ForestryHelper.getIgnobleInfo("magicbees.speciesSorcerous"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register("sand", ForestryHelper.getPristineInfo("magicbees.speciesSorcerous"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Crushed Andesite for Attuned Bees
        registry.register(new BlockInfo(ModBlocks.crushedAndesite), ForestryHelper.getDroneInfo("magicbees.speciesAttuned"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(new BlockInfo(ModBlocks.crushedAndesite), ForestryHelper.getIgnobleInfo("magicbees.speciesAttuned"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(new BlockInfo(ModBlocks.crushedAndesite), ForestryHelper.getPristineInfo("magicbees.speciesAttuned"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
    }

}
