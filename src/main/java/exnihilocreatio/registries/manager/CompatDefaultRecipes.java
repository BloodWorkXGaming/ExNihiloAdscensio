package exnihilocreatio.registries.manager;

import exnihilocreatio.recipes.defaults.*;
import exnihilocreatio.registries.registries.*;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.List;

public class CompatDefaultRecipes {
    private static List<IRecipeDefaults> MODS = new ArrayList<IRecipeDefaults>();
    static {
        // TODO use config options to dynamically add mod support
        // TODO consider making the default Ex Nihilo recipes use this same programatic method.
        MODS.add(new AppliedEnergistics2());
        MODS.add(new IntegratedDynamics());
        MODS.add(new Mekanism());
        MODS.add(new ExtremeReactors());
        MODS.add(new ActuallyAdditions());
    }

    public void registerRecipeDefaults(CompostRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(CrookRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(SieveRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(HammerRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(HeatRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(BarrelLiquidBlacklistRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(FluidOnTopRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(OreRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(FluidTransformRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(FluidBlockTransformerRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaults(FluidItemFluidRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }

    public void registerRecipeDefaultsStone(CrucibleRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaultsStone(registry);
            }
        }
    }

    public void registerRecipeDefaultsWood(CrucibleRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaultsWood(registry);
            }
        }

    }

    public void registerRecipeDefaults(MilkEntityRegistry registry) {
        for(IRecipeDefaults mod : MODS){
            if(Loader.isModLoaded(mod.getMODID())){
                mod.registerRecipeDefaults(registry);
            }
        }
    }
}
