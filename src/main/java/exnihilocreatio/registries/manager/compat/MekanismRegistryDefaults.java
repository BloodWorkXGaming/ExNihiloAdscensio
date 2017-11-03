package exnihilocreatio.registries.manager.compat;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.blocks.BlockSieve.MeshType;
import exnihilocreatio.registries.registries.FluidBlockTransformerRegistry;
import exnihilocreatio.registries.registries.HammerRegistry;
import exnihilocreatio.registries.registries.OreRegistry;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.ItemInfo;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

public class MekanismRegistryDefaults implements ICompatRegistryDefaults {
    @Getter
    public String MODID = "mekanism";

    public MekanismRegistryDefaults(){

    }

    public void registerRecipeDefaults(OreRegistry registry) {
        // Osmium
        registry.register("osmium", new Color("BBDDFF"), null);
    }
}
