package exnihilocreatio.recipes.defaults;

import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.items.ore.ItemOre;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.OreRegistry;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.texturing.Color;
import lombok.Getter;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Mekanism implements IRecipeDefaults {
    @Getter
    public String MODID = "mekanism";

    public Mekanism(){

    }

    public void registerRecipeDefaults(SieveRegistry registry) {
        ItemOre osmium = ExNihiloRegistryManager.ORE_REGISTRY.getOreItem("osmium");
        if (osmium != null) {
            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemStack(osmium, 1, 0), 0.05f, BlockSieve.MeshType.IRON.getID());
            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemStack(osmium, 1, 0), 0.1f, BlockSieve.MeshType.DIAMOND.getID());
        }
    }

    public void registerRecipeDefaults(OreRegistry registry) {
        // Osmium
        registry.register("osmium", new Color("BBDDFF"), null);
        ItemOre ore = ExNihiloRegistryManager.ORE_REGISTRY.getOreItem("osmium");
        registry.getSieveBlackList().add(ore); //Disables the default sieve recipes
    }
}
