package exnihilocreatio.config;

import exnihilocreatio.ExNihiloCreatio;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ExNihiloCreatio.MODID, name = "exnihilocreatio/ExNihiloCreatio", category = "exnihilocreatio")
public class ModConfig {
    /**
     * All Config Variables
     */
    @Config.Comment("These configs can be changed ClientSided without making problems with connecting to a server")
    public static final Client client = new Client();
    public static final Mechanics mechanics = new Mechanics();
    public static final Composting composting = new Composting();
    public static final InfestedLeaves infested_leaves = new InfestedLeaves();
    public static final Crooking crooking = new Crooking();
    public static final Misc misc = new Misc();
    public static final Sieve sieve = new Sieve();
    public static final Compatibility compatibility = new Compatibility();
    public static final Crucible crucible = new Crucible();
    public static final World world = new World();
    public static final Ore ore = new Ore();

    /**
     * All Config Classes
     */
    public static class Client {
        public boolean clientFancyAutoSieveAnimations = true;
        @Config.Comment("This enables the thin Sieve model which is similar to the one in the 1.7 ex nihilo")
        public boolean thinCrucibleModel = false;
    }

    public static class Mechanics {
        public boolean enableBarrels = true;
        public boolean enableCrucible = true;
        public boolean shouldBarrelsFillWithRain = true;
        public boolean fakePlayersCanSieve = false;
    }

    public static class Composting {
        public int ticksToFormDirt = 600;
    }

    public static class InfestedLeaves {
        public int ticksToTransform = 600;
        @Config.Comment("How many ticks to wait before getting ticked again, already fully infested leaves spread much slower due to waiting for world ticks.")
        public int leavesUpdateFrequency = 5;
        @Config.Comment("Minimum percentage to spread")
        public int leavesSpreadPercent = 15;
        @Config.Comment("Chance to spread if it got ticked")
        public float leavesSpreadChanceFloat = 0.5f;
    }

    public static class Crooking {
        public double stringChance = 1.0;
        public double stringFortuneChance = 1.0;
        public int numberOfTimesToTestVanillaDrops = 3;
        @Config.Comment("Disable the ExNihilo Crooks, useful if another mod adds compatible crooks")
        public boolean disableCrookCrafting = Loader.isModLoaded("inspirations");
    }

    public static class Misc {
        public boolean enableBarrelTransformLighting = true; // maybe move to client?

        @Config.RequiresMcRestart
        @Config.Comment({"Enable this to to load the JSON files.",
                "Keeping this on false will only load recipes registered in code",
                "This is recommended to enable for Packmakers",
                "For players just playing with this without wanting to configure something this is recommended to keep disabled"})
        public boolean enableJSONLoading = false;

        public boolean oredictVanillaItems = true;

        public boolean oredictExNihiloSeeds = true;

    }

    public static class Sieve {
        public int sieveSimilarRadius = 2;
        public int autoSieveRadius = 2;
        public boolean setFireToMacroUsers = false;
        public int meshMaxStackSize = 16;
    }

    public static class Compatibility {
        @Config.RequiresMcRestart
        public TinkersConstructCompat tinkers_construct_compat = new TinkersConstructCompat();

        @Config.Comment("Prevents unidict from merging the ore chunks into normal ore.")
        public boolean preventUnidict = true;
        public boolean addYelloriteOreDict = true;
        public boolean dankNullIntegration = true;
        public boolean generalItemHandlerCompat = true;

        public static class TinkersConstructCompat {
            public boolean doTinkersConstructCompat = true;
            public boolean addModifer = true;
            public boolean addMeltingOfChunks = true;
            public double ingotsPerChunkWhenMelting = 2.0;

            public boolean addMeltingOfDust = true;
            public double ingotsPerDustWhenMelting = 1.0;

        }
    }

    public static class Crucible {
        public int woodenCrucibleSpeed = 4;
    }

    public static class World {
        public boolean isSkyWorld = true;
        public int normalDropPercent = 100;
    }

    public static class Ore {
        public String chunkBaseOreDictName = "ore";
        public String pieceBaseOreDictName = "piece";
        public String dustBaseOreDictName  = "dust";
        public String ingotBaseOreDictName = "ingot";
    }

    /**
     * Inner Class to handle Reloading of the recipes
     */
    @Mod.EventBusSubscriber
    static class ConfigurationHolder {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(ExNihiloCreatio.MODID)) {
                ConfigManager.load(ExNihiloCreatio.MODID, Config.Type.INSTANCE);
            }
        }
    }

}
