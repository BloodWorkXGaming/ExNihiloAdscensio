package exnihilocreatio.recipes.defaults;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.blocks.BlockSieve.MeshType;
import exnihilocreatio.items.ore.ItemOre;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.OreRegistry;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.ItemInfo;
import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtremeReactors implements IRecipeDefaults {
    @Getter
    public String MODID = "bigreactors"; // Extreme Reactors is a fork of Big Reactors and still uses the same MOD ID

    public void registerSieve(SieveRegistry registry) {
        ItemOre ore = ExNihiloRegistryManager.ORE_REGISTRY.getOreItem("yellorium");
        if(ore !=null){
            registry.register(ModBlocks.dust.getDefaultState(), new ItemStack(ore, 1, 0), 0.01f, MeshType.DIAMOND.getID());
        }
    }

    public void registerOreChunks(OreRegistry registry) {
        // 0 = Yellorium
        Item yellorium = Item.getByNameOrId("bigreactors:ingotmetals");
        if(yellorium != null){
            registry.register("yellorium", new Color("DCF400"), new ItemInfo(yellorium, 0));
            ItemOre ore = ExNihiloRegistryManager.ORE_REGISTRY.getOreItem("yellorium");
            registry.getSieveBlackList().add(ore); //Disables the default sieve recipes
        }
    }
}
