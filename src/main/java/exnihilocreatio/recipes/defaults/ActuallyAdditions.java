package exnihilocreatio.recipes.defaults;

import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.registries.registries.SieveRegistry;
import lombok.Getter;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

public class ActuallyAdditions implements IRecipeDefaults {
    @Getter
    public String MODID = "actuallyadditions";

    @Nullable
    @GameRegistry.ObjectHolder("actuallyadditions:item_misc")
    // 5 = Black Quartz
    public static final Item AA_ITEM_MISC = null;

    public ActuallyAdditions(){

    }

    public void registerRecipeDefaults(SieveRegistry registry) {
        // Actually Additions crashes if these are actually registered
        registry.register(Blocks.SAND.getDefaultState(), new ItemStack(AA_ITEM_MISC, 1, 5), 0.02f, BlockSieve.MeshType.IRON.getID());
        registry.register(Blocks.SAND.getDefaultState(), new ItemStack(AA_ITEM_MISC, 1, 5), 0.05f, BlockSieve.MeshType.DIAMOND.getID());
    }
}
