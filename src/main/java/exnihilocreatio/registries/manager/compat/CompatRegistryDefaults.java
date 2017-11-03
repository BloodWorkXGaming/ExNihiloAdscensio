package exnihilocreatio.registries.manager.compat;

import exnihilocreatio.registries.registries.*;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.List;

public class CompatRegistryDefaults {
    private static List<ICompatRegistryDefaults> MODS = new ArrayList<ICompatRegistryDefaults>();
    static {
        // TODO use config options to dynamically add mod support
        // TODO consider making the default Ex Nihilo recipes use this same programatic method.
        MODS.add(new AE2RegistryDefaults());
        MODS.add(new IntegratedDynamicsRegistryDefaults());
        MODS.add(new MekanismRegistryDefaults());
    }

    public void registerRecipeDefaults(CompostRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(CrookRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(SieveRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(HammerRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(HeatRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(BarrelLiquidBlacklistRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(FluidOnTopRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(OreRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(FluidTransformRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(FluidBlockTransformerRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(FluidItemFluidRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaultsStone(CrucibleRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaultsStone(registry);
            }
        }
    }

    public void registerRecipeDefaultsWood(CrucibleRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaultsWood(registry);
            }
        }

    }

    public void registerRecipeDefaults(MilkEntityRegistry registry) {
        for(ICompatRegistryDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }
}
