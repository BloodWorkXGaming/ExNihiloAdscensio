package exnihilocreatio.registries.manager.compat;

import exnihilocreatio.blocks.BlockSieve.MeshType;
import exnihilocreatio.registries.registries.SieveRegistry;
import lombok.Getter;
import net.minecraft.block.BlockDirt;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

public class IntegratedDynamicsRegistryDefaults implements ICompatRegistryDefaults {
    @Getter
    public String MODID = "integrateddynamics";

    // Integrated Dynamics Support
    @Nullable
    @GameRegistry.ObjectHolder("integrateddynamics:menril_sapling")
    public static final Item MENRIL_SAPLING = null;

    public IntegratedDynamicsRegistryDefaults(){

    }

    public void registerRecipeDefaults(SieveRegistry registry) {
        // TODO: Consider instead using witch water to mutate a normal sapling into a menril sapling.
        registry.register(Blocks.DIRT.getDefaultState(), new ItemStack(MENRIL_SAPLING, 1, 0), 0.02f, MeshType.DIAMOND.getID());
        registry.register(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT), new ItemStack(MENRIL_SAPLING, 1, 0), 0.1f, MeshType.DIAMOND.getID());
    }
}
