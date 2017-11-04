package exnihilocreatio.recipes.defaults;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.blocks.BlockSieve.MeshType;
import exnihilocreatio.registries.registries.FluidBlockTransformerRegistry;
import exnihilocreatio.registries.registries.HammerRegistry;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.util.ItemInfo;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

public class AppliedEnergistics2 implements IRecipeDefaults {
    @Getter
    public String MODID = "appliedenergistics2";

    @Nullable
    @GameRegistry.ObjectHolder("appliedenergistics2:material")
    // Certus = 0, Charged Certus = 1, Sky stone dust = 45
    public static final Item AE_MATERIAL = null;

    @Nullable
    @GameRegistry.ObjectHolder("appliedenergistics2:crystal_seed")
    // Pure Certus = 0
    public static final Item AE_SEEDS = null;

    @Nullable
    @GameRegistry.ObjectHolder("appliedenergistics2:sky_stone_block")
    public static final Block SKY_STONE = null;

    public AppliedEnergistics2(){

    }

    public void registerRecipeDefaults(SieveRegistry registry) {
        // Sky Stone Dust
        registry.register(ModBlocks.dust.getDefaultState(), new ItemStack(AE_MATERIAL, 1, 45), 0.1f, MeshType.FLINT.getID());
        registry.register(ModBlocks.dust.getDefaultState(), new ItemStack(AE_MATERIAL, 1, 45), 0.2f, MeshType.IRON.getID());
        registry.register(ModBlocks.dust.getDefaultState(), new ItemStack(AE_MATERIAL, 1, 45), 0.3f, MeshType.DIAMOND.getID());

        // Certus Quartz
        ItemStack stack = new ItemStack(AE_MATERIAL, 1, 0);
        registry.register(ModBlocks.skystoneCrushed.getDefaultState(), stack, 0.01f, MeshType.IRON.getID());
        registry.register(ModBlocks.skystoneCrushed.getDefaultState(), stack, 0.02f, MeshType.DIAMOND.getID());

        // Pure Certus Quartz Seed
        stack = new ItemStack(AE_SEEDS, 1, 0);
        registry.register(ModBlocks.skystoneCrushed.getDefaultState(), stack, 0.01f, MeshType.STRING.getID());
        registry.register(ModBlocks.skystoneCrushed.getDefaultState(), stack, 0.01f, MeshType.FLINT.getID());
        registry.register(ModBlocks.skystoneCrushed.getDefaultState(), stack, 0.02f, MeshType.IRON.getID());
        registry.register(ModBlocks.skystoneCrushed.getDefaultState(), stack, 0.02f, MeshType.DIAMOND.getID());

        // Charged Certus Quartz
        stack = new ItemStack(AE_MATERIAL, 1, 1);
        registry.register(ModBlocks.skystoneCrushed.getDefaultState(), stack, 0.001f, MeshType.DIAMOND.getID());
    }
    public void registerRecipeDefaults(HammerRegistry registry) {
        registry.register(SKY_STONE.getDefaultState(), new ItemStack(ModBlocks.skystoneCrushed, 1), 3, 1.0F, 0.0F);
    }

    public void registerRecipeDefaults(FluidBlockTransformerRegistry registry) {
        registry.register(FluidRegistry.LAVA, new ItemInfo(AE_MATERIAL,  45), new ItemInfo(SKY_STONE, 0));
    }
}
