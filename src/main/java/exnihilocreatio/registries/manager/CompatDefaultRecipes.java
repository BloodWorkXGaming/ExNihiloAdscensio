package exnihilocreatio.registries.manager;

import exnihilocreatio.recipes.defaults.*;
import exnihilocreatio.registries.registries.*;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.List;

public class CompatDefaultRecipes {
    private static final List<IRecipeDefaults> MODS = new ArrayList<>();

    static {
        // TODO use config options to dynamically add mod support
        MODS.add(new ExNihilo()); // Not exactly a "cross" mod support ¯\_(ツ)_/¯
        MODS.add(new TinkersConstruct());
        MODS.add(new AppliedEnergistics2());
        MODS.add(new IntegratedDynamics());
        MODS.add(new Mekanism());
        MODS.add(new BigReactors());
        MODS.add(new ActuallyAdditions());
        MODS.add(new EnderIO());
        MODS.add(new DraconicEvolution());
        MODS.add(new ThermalFoundation());
        MODS.add(new Forestry());
        MODS.add(new MoreBees());
        MODS.add(new ExtraBees());
        MODS.add(new MagicBees());
        MODS.add(new BinniesBotany());

    }

    public void registerCompost(CompostRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerCompost(registry));
    }

    public void registerCrook(CrookRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerCrook(registry));
    }

    public void registerSieve(SieveRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerSieve(registry));
    }

    public void registerHammer(HammerRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerHammer(registry));
    }

    public void registerHeat(HeatRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerHeat(registry));
    }

    public void registerBarrel(BarrelLiquidBlacklistRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerBarrelLiquidBlacklist(registry));
    }

    public void registerFluidOnTop(FluidOnTopRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerFluidOnTop(registry));
    }

    public void registerOreChunks(OreRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerOreChunks(registry));
    }

    public void registerFluidTransform(FluidTransformRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerFluidTransform(registry));
    }

    public void registerFluidBlockTransform(FluidBlockTransformerRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerFluidBlockTransform(registry));
    }

    public void registerFluidItemFluid(FluidItemFluidRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerFluidItemFluid(registry));
    }

    public void registerCrucibleStone(CrucibleRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerCrucibleStone(registry));
    }

    public void registerCrucibleWood(CrucibleRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerCrucibleWood(registry));
    }

    public void registerMilk(MilkEntityRegistry registry) {
        MODS.stream().filter(mod -> Loader.isModLoaded(mod.getMODID()))
                .forEach(mod -> mod.registerMilk(registry));
    }
}
