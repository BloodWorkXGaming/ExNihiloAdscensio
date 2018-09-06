package exnihilocreatio.recipes.defaults;

import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.compatibility.ForestryHelper;
import exnihilocreatio.registries.registries.CompostRegistry;
import exnihilocreatio.registries.registries.CrookRegistry;
import exnihilocreatio.registries.registries.CrucibleRegistry;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.ItemInfo;
import lombok.Getter;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.FluidRegistry;

public class Forestry implements IRecipeDefaults {
    @Getter
    public String MODID = "forestry";

    @Override
    public void registerCompost(CompostRegistry registry) {
        BlockInfo dirtState = new BlockInfo(Blocks.DIRT);

        // Compost Drones
        registry.register(new ItemInfo("forestry:bee_drone_ge"), 0.0625f, dirtState, new Color("C45631"));
        registry.register(new ItemInfo("forestry:bee_larvae_ge"), 0.0625f, dirtState, new Color("C45631"));
        registry.register(new ItemInfo("forestry:cocoon_ge"), 0.0625f, dirtState, new Color("C45631"));
        registry.register(new ItemInfo("forestry:caterpillar_ge"), 0.0625f, dirtState, new Color("C45631"));
        // Compost Peat
        registry.register(new ItemInfo("forestry:peat"), 0.5f, dirtState, new Color("AF9F96"));
        // Compost Compost (yo dawg)
        registry.register(new ItemInfo("forestry:fertilizer_bio"), 0.5f, dirtState, new Color("135303"));
        // Compost Mouldy Wheat, Decaying Wheat, Mulch
        registry.register(new ItemInfo("forestry:mouldy_wheat"), 0.0625f, dirtState, new Color("D3D152"));
        registry.register(new ItemInfo("forestry:decaying_wheat"), 0.125f, dirtState, new Color("D3D152"));
        registry.register(new ItemInfo("forestry:mulch"), 0.25f, dirtState, new Color("D3D152"));
        // Compost Wood Pulp
        registry.register(new ItemInfo("forestry:wood_pulp"), 0.125f, dirtState, new Color("FFF1B5"));
        // Compost Pollens
        registry.register(new ItemInfo("forestry:pollen:0"), 0.125f, dirtState, new Color("FFFA66"));
        registry.register(new ItemInfo("forestry:pollen:1"), 0.125f, new BlockInfo(Blocks.SNOW, -1), new Color("709BD3"));
        registry.register(new ItemInfo("forestry:pollen_fertile"), 0.25f, dirtState, new Color("FFFA66"));

        registry.register(new ItemInfo("forestry:propolis:0"), 0.125f, new BlockInfo(Blocks.SLIME_BLOCK), new Color("7B934B"));
        registry.register(new ItemInfo("forestry:propolis:1"), 0.125f, new BlockInfo(Blocks.SLIME_BLOCK), new Color("7B934B"));
        registry.register(new ItemInfo("forestry:propolis:3"), 0.125f, new BlockInfo(Blocks.SLIME_BLOCK), new Color("7B934B"));

    }

    @Override
    public void registerCrook(CrookRegistry registry) {
        // All Leaves for Forest Bees
        registry.register("treeLeaves", ForestryHelper.getDroneInfo("forestry.speciesForest").getItemStack(), 0.02f, 0.2f);
        registry.register("treeLeaves", ForestryHelper.getIgnobleInfo("forestry.speciesForest").getItemStack(), 0.01f, 0.01f);
        registry.register("treeLeaves", ForestryHelper.getPristineInfo("forestry.speciesForest").getItemStack(), 0.005f, 0.5f);
        // Jungle Leaves for Tropical
        registry.register("treeLeaves", ForestryHelper.getDroneInfo("forestry.speciesTropical").getItemStack(), 0.02f, 0.2f);
        registry.register("treeLeaves", ForestryHelper.getIgnobleInfo("forestry.speciesTropical").getItemStack(), 0.01f, 0.01f);
        registry.register("treeLeaves", ForestryHelper.getPristineInfo("forestry.speciesTropical").getItemStack(), 0.005f, 0.5f);
    }

    @Override
    public void registerSieve(SieveRegistry registry) {
        // Sand for Apatite
        registry.register("sand", new ItemInfo("forestry:apatite"), 0.01f, BlockSieve.MeshType.STRING.getID());
        registry.register("sand", new ItemInfo("forestry:apatite"), 0.02f, BlockSieve.MeshType.FLINT.getID());
        registry.register("sand", new ItemInfo("forestry:apatite"), 0.03f, BlockSieve.MeshType.IRON.getID());
        registry.register("sand", new ItemInfo("forestry:apatite"), 0.05f, BlockSieve.MeshType.DIAMOND.getID());

        /*
         BEEEEEEEEEES
         */

        // Leaves for Forest Bees
        registry.register("treeLeaves", ForestryHelper.getDroneInfo("forestry.speciesForest"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register("treeLeaves", ForestryHelper.getIgnobleInfo("forestry.speciesForest"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register("treeLeaves", ForestryHelper.getPristineInfo("forestry.speciesForest"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Jungle for Tropical Bees
        registry.register(new ItemInfo("minecraft:leaves:3"), ForestryHelper.getDroneInfo("forestry.speciesTropical"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(new ItemInfo("minecraft:leaves:3"), ForestryHelper.getIgnobleInfo("forestry.speciesTropical"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(new ItemInfo("minecraft:leaves:3"), ForestryHelper.getPristineInfo("forestry.speciesTropical"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Sand for Modest Bees
        registry.register("sand", ForestryHelper.getDroneInfo("forestry.speciesModest"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register("sand", ForestryHelper.getIgnobleInfo("forestry.speciesModest"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register("sand", ForestryHelper.getPristineInfo("forestry.speciesModest"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Snow for Wintry Bees
        registry.register(Blocks.SNOW, -1, ForestryHelper.getDroneInfo("forestry.speciesWintry"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(Blocks.SNOW, -1, ForestryHelper.getIgnobleInfo("forestry.speciesWintry"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(Blocks.SNOW, -1, ForestryHelper.getPristineInfo("forestry.speciesWintry"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Humus for Marshy Bees
        registry.register(new BlockInfo("forestry:humus"), ForestryHelper.getDroneInfo("forestry.speciesMarshy"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(new BlockInfo("forestry:humus"), ForestryHelper.getIgnobleInfo("forestry.speciesMarshy"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(new BlockInfo("forestry:humus"), ForestryHelper.getPristineInfo("forestry.speciesMarshy"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Crushed End Stone for Ender Bees
        registry.register(new BlockInfo("exnihilocreatio:block_endstone_crushed"), ForestryHelper.getDroneInfo("forestry.speciesEnded"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register(new BlockInfo("exnihilocreatio:block_endstone_crushed"), ForestryHelper.getIgnobleInfo("forestry.speciesEnded"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register(new BlockInfo("exnihilocreatio:block_endstone_crushed"), ForestryHelper.getPristineInfo("forestry.speciesEnded"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
        // Dirt for Meadows
        registry.register("dirt", ForestryHelper.getDroneInfo("forestry.speciesMeadows"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register("dirt", ForestryHelper.getIgnobleInfo("forestry.speciesMeadows"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register("dirt", ForestryHelper.getPristineInfo("forestry.speciesMeadows"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());


    }

    @Override
    public void registerCrucibleStone(CrucibleRegistry registry) {
        // Melt down honey drops
        registry.register("dropHoney", FluidRegistry.getFluid("for.honey"), 10);
    }
}
