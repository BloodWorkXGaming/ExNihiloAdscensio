package exnihilocreatio.recipes.defaults;

import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.compatibility.ForestryHelper;
import exnihilocreatio.registries.registries.SieveRegistry;
import lombok.Getter;

public class MoreBees implements IRecipeDefaults {
    @Getter
    public String MODID = "morebees";
    @Override
    public void registerSieve(SieveRegistry registry) {
        /*
         BEEEEEEEEEES
         */
        // Gravel for Rocky Bees
        registry.register("gravel", ForestryHelper.getDroneInfo("morebees.species.rock"), 0.05f, BlockSieve.MeshType.FLINT.getID());
        registry.register("gravel", ForestryHelper.getIgnobleInfo("morebees.species.rock"), 0.05f, BlockSieve.MeshType.IRON.getID());
        registry.register("gravel", ForestryHelper.getPristineInfo("morebees.species.rock"), 0.01f, BlockSieve.MeshType.DIAMOND.getID());
    }

}
