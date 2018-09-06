package exnihilocreatio.recipes.defaults;

import exnihilocreatio.registries.registries.CompostRegistry;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.ItemInfo;
import lombok.Getter;
import net.minecraft.init.Blocks;

public class BinniesBotany implements IRecipeDefaults {
    @Getter
    public String MODID = "botany";

    @Override
    public void registerCompost(CompostRegistry registry) {
        BlockInfo dirtState = new BlockInfo(Blocks.DIRT);
        // Compost Pollens
        registry.register(new ItemInfo("botany:pollen:0"), 0.125f, dirtState, new Color("FFFA66"));
    }
}
