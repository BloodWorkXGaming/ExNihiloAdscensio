package exnihilocreatio.registries.manager.compat;

import exnihilocreatio.registries.registries.*;

public interface ICompatRegistryDefaults {
    public String getMODID();

    default public void registerRecipeDefaults(CompostRegistry registry){};
    default public void registerRecipeDefaults(CrookRegistry registry){};
    default public void registerRecipeDefaults(SieveRegistry registry){};
    default public void registerRecipeDefaults(HammerRegistry registry){};
    default public void registerRecipeDefaults(HeatRegistry registry){};
    default public void registerRecipeDefaults(BarrelLiquidBlacklistRegistry registry){};
    default public void registerRecipeDefaults(FluidOnTopRegistry registry){};
    default public void registerRecipeDefaults(OreRegistry registry){};
    default public void registerRecipeDefaults(FluidTransformRegistry registry){};
    default public void registerRecipeDefaults(FluidBlockTransformerRegistry registry){};
    default public void registerRecipeDefaults(FluidItemFluidRegistry registry){};
    default public void registerRecipeDefaultsStone(CrucibleRegistry registry){};
    default public void registerRecipeDefaultsWood(CrucibleRegistry registry){};
    default public void registerRecipeDefaults(MilkEntityRegistry registry){};

}
