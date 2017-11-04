package exnihilocreatio.recipes.defaults;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.blocks.BlockSieve.MeshType;
import exnihilocreatio.items.ore.ItemOre;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.HeatRegistry;
import exnihilocreatio.registries.registries.OreRegistry;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.ItemInfo;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BigReactors implements IRecipeDefaults {
    /*
    Mods Which use this same modid:
    * Big Reactors (the original)
    * Extreme Reactors (the fork)
    If extreme reactors ever stops updating, I bet someone will fork it and use the same modid
     */
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

    public void registerHeat(HeatRegistry registry) {
        // 0 = Yellorium
        // 1 = Cyanite
        // 3 = Blutonium
        Block brBlocks = Block.getBlockFromName("bigreactors:blockmetals");
        if(brBlocks != null){
            registry.register(new BlockInfo(brBlocks, 0), 10);
            registry.register(new BlockInfo(brBlocks, 1), 15);
            registry.register(new BlockInfo(brBlocks, 3), 20);
        }
    }
}
