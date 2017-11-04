package exnihilocreatio.recipes.defaults;

import exnihilocreatio.registries.registries.*;

public interface IRecipeDefaults {
    public String getMODID();

    default public void registerCompost(CompostRegistry registry){};
    default public void registerCrook(CrookRegistry registry){};
    default public void registerSieve(SieveRegistry registry){};
    default public void registerHammer(HammerRegistry registry){};
    default public void registerHeat(HeatRegistry registry){};
    default public void registerBarrelLiquidBlacklist(BarrelLiquidBlacklistRegistry registry){};
    default public void registerFluidOnTop(FluidOnTopRegistry registry){};
    default public void registerOreChunks(OreRegistry registry){};
    default public void registerFluidTransform(FluidTransformRegistry registry){};
    default public void registerFluidBlockTransform(FluidBlockTransformerRegistry registry){};
    default public void registerFluidItemFluid(FluidItemFluidRegistry registry){};
    default public void registerCrucibleStone(CrucibleRegistry registry){};
    default public void registerCrucibleWood(CrucibleRegistry registry){};
    default public void registerMilk(MilkEntityRegistry registry){};

}
