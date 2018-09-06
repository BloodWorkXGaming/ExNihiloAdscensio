package exnihilocreatio;

import exnihilocreatio.capabilities.ENCapabilities;
import exnihilocreatio.command.CommandReloadConfig;
import exnihilocreatio.compatibility.crafttweaker.CrTIntegration;
import exnihilocreatio.compatibility.tconstruct.CompatTConstruct;
import exnihilocreatio.config.ModConfig;
import exnihilocreatio.entities.ENEntities;
import exnihilocreatio.handlers.HandlerCrook;
import exnihilocreatio.handlers.HandlerHammer;
import exnihilocreatio.networking.PacketHandler;
import exnihilocreatio.proxy.CommonProxy;
import exnihilocreatio.registries.RegistryReloadedEvent;
import exnihilocreatio.registries.manager.ExNihiloDefaultRecipes;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.BarrelModeRegistry;
import exnihilocreatio.util.LogUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Mod(modid = ExNihiloCreatio.MODID, name = "Ex Nihilo Creatio", version = ExNihiloCreatio.VERSION, acceptedMinecraftVersions = "[1.12, 1.13)", dependencies = "after:forestry;after:extrabees;after:morebees;after:magicbees;")
@Mod.EventBusSubscriber
public class ExNihiloCreatio {

    public static final String MODID = "exnihilocreatio";
    public static final String VERSION = "@VERSION@";
    public static final CreativeTabs tabExNihilo = new CreativeTabExNihiloCreatio();
    @SidedProxy(serverSide = "exnihilocreatio.proxy.ServerProxy", clientSide = "exnihilocreatio.proxy.ClientProxy")
    public static CommonProxy proxy;
    @Instance(MODID)
    public static ExNihiloCreatio instance;
    public static File configDirectory;
    public static boolean configsLoaded = false;
    public static boolean crtActionsLoaded = false;

    static {
        FluidRegistry.enableUniversalBucket();
        // registers itself to the needed registries
    }

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

        LogUtil.setup();

        configDirectory = new File(event.getModConfigurationDirectory(), "exnihilocreatio");
        configDirectory.mkdirs();

        ENCapabilities.init();
        ENEntities.init();

        PacketHandler.initPackets();

        MinecraftForge.EVENT_BUS.register(new HandlerHammer());
        MinecraftForge.EVENT_BUS.register(new HandlerCrook());

        if (ModConfig.mechanics.enableBarrels) {
            BarrelModeRegistry.registerDefaults();
        }

        ExNihiloDefaultRecipes.registerDefaults();

    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);

        if (Loader.isModLoaded("tconstruct") && ModConfig.compatibility.tinkers_construct_compat.doTinkersConstructCompat) {
            CompatTConstruct.postInit();
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        proxy.registerModels(event);
    }

    public static void loadConfigs() {
        configsLoaded = true;

        ExNihiloRegistryManager.COMPOST_REGISTRY.loadJson(new File(configDirectory, "CompostRegistry.json"));
        ExNihiloRegistryManager.CROOK_REGISTRY.loadJson(new File(configDirectory, "CrookRegistry.json"));
        ExNihiloRegistryManager.SIEVE_REGISTRY.loadJson(new File(configDirectory, "SieveRegistry.json"));
        ExNihiloRegistryManager.HAMMER_REGISTRY.loadJson(new File(configDirectory, "HammerRegistry.json"));
        ExNihiloRegistryManager.HEAT_REGISTRY.loadJson(new File(configDirectory, "HeatRegistry.json"));
        ExNihiloRegistryManager.BARREL_LIQUID_BLACKLIST_REGISTRY.loadJson(new File(configDirectory, "BarrelLiquidBlacklistRegistry.json"));
        ExNihiloRegistryManager.FLUID_ON_TOP_REGISTRY.loadJson(new File(configDirectory, "FluidOnTopRegistry.json"));
        ExNihiloRegistryManager.FLUID_TRANSFORM_REGISTRY.loadJson(new File(configDirectory, "FluidTransformRegistry.json"));
        ExNihiloRegistryManager.FLUID_BLOCK_TRANSFORMER_REGISTRY.loadJson(new File(configDirectory, "FluidBlockTransformerRegistry.json"));
        ExNihiloRegistryManager.FLUID_ITEM_FLUID_REGISTRY.loadJson(new File(configDirectory, "FluidItemFluidRegistry.json"));
        ExNihiloRegistryManager.CRUCIBLE_STONE_REGISTRY.loadJson(new File(configDirectory, "CrucibleRegistryStone.json"));
        ExNihiloRegistryManager.CRUCIBLE_WOOD_REGISTRY.loadJson(new File(configDirectory, "CrucibleRegistryWood.json"));
        ExNihiloRegistryManager.MILK_ENTITY_REGISTRY.loadJson(new File(configDirectory, "MilkEntityRegistry.json"));

        MinecraftForge.EVENT_BUS.post(new RegistryReloadedEvent());

        if (Loader.isModLoaded("crafttweaker")) {
            CrTIntegration.loadIActions();
            crtActionsLoaded = true;
        }
    }

    @EventHandler
    public static void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandReloadConfig());
    }

    @EventHandler
    public static void modMapping(FMLModIdMappingEvent event) {

    }

}
