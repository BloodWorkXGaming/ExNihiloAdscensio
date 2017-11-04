package exnihilocreatio.recipes.defaults;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.items.ore.ItemOre;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.OreRegistry;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.ItemInfo;
import lombok.Getter;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TinkersConstruct implements IRecipeDefaults {
    @Getter
    public String MODID = "tconstruct";

    public void registerSieve(SieveRegistry registry){
        OreRegistry oreRegistry = ExNihiloRegistryManager.ORE_REGISTRY;
        ItemOre ardite = oreRegistry.getOreItem("ardite");
        if (ardite != null) {
            registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(ardite, 1, 0), 0.1f, BlockSieve.MeshType.FLINT.getID());
            registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(ardite, 1, 0), 0.2f, BlockSieve.MeshType.IRON.getID());
            registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(ardite, 1, 0), 0.3f, BlockSieve.MeshType.DIAMOND.getID());
        }

        ItemOre cobalt = oreRegistry.getOreItem("cobalt");
        if (cobalt != null) {
            registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(cobalt, 1, 0), 0.1f, BlockSieve.MeshType.FLINT.getID());
            registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(cobalt, 1, 0), 0.2f, BlockSieve.MeshType.IRON.getID());
            registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(cobalt, 1, 0), 0.3f, BlockSieve.MeshType.DIAMOND.getID());
        }
        registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemInfo(Items.BLAZE_POWDER, 0), 0.05f, BlockSieve.MeshType.IRON.getID());
    }

    public void registerOreChunks(OreRegistry registry){
        Item tconstructIngots = Item.getByNameOrId("tconstruct:ingots");
        if (tconstructIngots != null) {
            registry.register("ardite", new Color("FF751A"), new ItemInfo(tconstructIngots, 1));
            registry.getSieveBlackList().add(ExNihiloRegistryManager.ORE_REGISTRY.getOreItem("ardite")); //Disables the default sieve recipes
            registry.register("cobalt", new Color("3333FF"), new ItemInfo(tconstructIngots, 0));
            registry.getSieveBlackList().add(ExNihiloRegistryManager.ORE_REGISTRY.getOreItem("cobalt")); //Disables the default sieve recipes
        }
    }
}
