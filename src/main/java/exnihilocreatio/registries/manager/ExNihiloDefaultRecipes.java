package exnihilocreatio.registries.manager;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.ModFluids;
import exnihilocreatio.ModItems;
import exnihilocreatio.blocks.BlockSieve.MeshType;
import exnihilocreatio.items.EnumPebbleSubtype;
import exnihilocreatio.items.ItemPebble;
import exnihilocreatio.items.ItemResource;
import exnihilocreatio.items.ore.ItemOre;
import exnihilocreatio.items.seeds.ItemSeedBase;
import exnihilocreatio.registries.manager.compat.CompatRegistryDefaults;
import exnihilocreatio.registries.registries.*;
import exnihilocreatio.registries.types.Meltable;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.ItemInfo;
import exnihilocreatio.util.Util;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;


public class ExNihiloDefaultRecipes {
    private static CompatRegistryDefaults compat = new CompatRegistryDefaults();


    public static void registerDefaults() {
        ExNihiloRegistryManager.registerSieveDefaultRecipeHandler(new SieveDefaults());
        ExNihiloRegistryManager.registerHammerDefaultRecipeHandler(new HammerDefaults());
        ExNihiloRegistryManager.registerCompostDefaultRecipeHandler(new CompostDefaults());
        ExNihiloRegistryManager.registerCrookDefaultRecipeHandler(new CrookDefaults());
        ExNihiloRegistryManager.registerHeatDefaultRecipeHandler(new HeatDefaults());
        ExNihiloRegistryManager.registerOreDefaultRecipeHandler(new OreDefaults());
        ExNihiloRegistryManager.registerBarrelLiquidBlacklistDefaultHandler(new BarrelLiquidBlacklistDefaults());
        ExNihiloRegistryManager.registerFluidOnTopDefaultRecipeHandler(new FluidOnTopDefaults());
        ExNihiloRegistryManager.registerFluidTransformDefaultRecipeHandler(new FluidTransformDefaults());
        ExNihiloRegistryManager.registerFluidBlockDefaultRecipeHandler(new FluidBlockTransformDefaults());
        ExNihiloRegistryManager.registerFluidItemFluidDefaultHandler(new FluidItemFluidDefaults());
        ExNihiloRegistryManager.registerCrucibleStoneDefaultRecipeHandler(new CrucibleStoneDefaults());
        ExNihiloRegistryManager.registerCrucibleWoodDefaultRecipeHandler(new CrucibleWoodDefaults());
        ExNihiloRegistryManager.registerMilkEntityDefaultRecipeHandler(new MilkEntityDefaults());
    }

    private static class CompostDefaults implements ICompostDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(CompostRegistry registry) {
            IBlockState dirtState = Blocks.DIRT.getDefaultState();

            registry.register(Items.ROTTEN_FLESH, 0, 0.1f, dirtState, new Color("C45631"));

            registry.register(Blocks.SAPLING, 0, 0.125f, dirtState, new Color("35A82A"));
            registry.register(Blocks.SAPLING, 1, 0.125f, dirtState, new Color("2E8042"));
            registry.register(Blocks.SAPLING, 2, 0.125f, dirtState, new Color("6CC449"));
            registry.register(Blocks.SAPLING, 3, 0.125f, dirtState, new Color("22A116"));
            registry.register(Blocks.SAPLING, 4, 0.125f, dirtState, new Color("B8C754"));
            registry.register(Blocks.SAPLING, 5, 0.125f, dirtState, new Color("378030"));

            registry.register(Blocks.LEAVES, 0, 0.125f, dirtState, new Color("35A82A"));
            registry.register(Blocks.LEAVES, 1, 0.125f, dirtState, new Color("2E8042"));
            registry.register(Blocks.LEAVES, 2, 0.125f, dirtState, new Color("6CC449"));
            registry.register(Blocks.LEAVES, 3, 0.125f, dirtState, new Color("22A116"));
            registry.register(Blocks.LEAVES2, 0, 0.125f, dirtState, new Color("B8C754"));
            registry.register(Blocks.LEAVES2, 1, 0.125f, dirtState, new Color("378030"));

            registry.register(Items.SPIDER_EYE, 0, 0.08f, dirtState, new Color("963E44"));

            registry.register(Items.WHEAT, 0, 0.08f, dirtState, new Color("E3E162"));
            registry.register(Items.WHEAT_SEEDS, 0, 0.08f, dirtState, new Color("35A82A"));
            registry.register(Items.BREAD, 0, 0.16f, dirtState, new Color("D1AF60"));

            registry.register(Blocks.YELLOW_FLOWER, 0, 0.10f, dirtState, new Color("FFF461"));
            registry.register(Blocks.RED_FLOWER, 0, 0.10f, dirtState, new Color("FF1212"));
            registry.register(Blocks.RED_FLOWER, 1, 0.10f, dirtState, new Color("33CFFF"));
            registry.register(Blocks.RED_FLOWER, 2, 0.10f, dirtState, new Color("F59DFA"));
            registry.register(Blocks.RED_FLOWER, 3, 0.10f, dirtState, new Color("E3E3E3"));
            registry.register(Blocks.RED_FLOWER, 4, 0.10f, dirtState, new Color("FF3D12"));
            registry.register(Blocks.RED_FLOWER, 5, 0.10f, dirtState, new Color("FF7E29"));
            registry.register(Blocks.RED_FLOWER, 6, 0.10f, dirtState, new Color("FFFFFF"));
            registry.register(Blocks.RED_FLOWER, 7, 0.10f, dirtState, new Color("F5C4FF"));
            registry.register(Blocks.RED_FLOWER, 8, 0.10f, dirtState, new Color("E9E9E9"));

            registry.register(Blocks.DOUBLE_PLANT, 0, 0.10f, dirtState, new Color("FFDD00"));
            registry.register(Blocks.DOUBLE_PLANT, 1, 0.10f, dirtState, new Color("FCC7F0"));
            registry.register(Blocks.DOUBLE_PLANT, 4, 0.10f, dirtState, new Color("FF1212"));
            registry.register(Blocks.DOUBLE_PLANT, 5, 0.10f, dirtState, new Color("F3D2FC"));

            registry.register(Blocks.BROWN_MUSHROOM, 0, 0.10f, dirtState, new Color("CFBFB6"));
            registry.register(Blocks.RED_MUSHROOM, 0, 0.10f, dirtState, new Color("D6A8A5"));

            registry.register(Items.PUMPKIN_PIE, 0, 0.16f, dirtState, new Color("E39A6D"));

            registry.register(Items.PORKCHOP, 0, 0.2f, dirtState, new Color("FFA091"));
            registry.register(Items.COOKED_PORKCHOP, 0, 0.2f, dirtState, new Color("FFFDBD"));

            registry.register(Items.BEEF, 0, 0.2f, dirtState, new Color("FF4242"));
            registry.register(Items.COOKED_BEEF, 0, 0.2f, dirtState, new Color("80543D"));

            registry.register(Items.CHICKEN, 0, 0.2f, dirtState, new Color("FFE8E8"));
            registry.register(Items.COOKED_CHICKEN, 0, 0.2f, dirtState, new Color("FA955F"));

            registry.register(Items.FISH, 0, 0.15f, dirtState, new Color("6DCFB3"));
            registry.register(Items.COOKED_FISH, 0, 0.15f, dirtState, new Color("D8EBE5"));

            registry.register(Items.FISH, 1, 0.15f, dirtState, new Color("FF2E4A"));
            registry.register(Items.COOKED_FISH, 1, 0.15f, dirtState, new Color("E87A3F"));

            registry.register(Items.FISH, 2, 0.15f, dirtState, new Color("FF771C"));
            registry.register(Items.FISH, 3, 0.15f, dirtState, new Color("DBFAFF"));

            registry.register(ModItems.resources, ItemResource.getMetaFromName(ItemResource.SILKWORM), 0.04f, dirtState, new Color("ff9966"));
            registry.register(ModItems.cookedSilkworm, 0, 0.04f, dirtState, new Color("cc6600"));

            registry.register(Items.APPLE, 0, 0.10f, dirtState, new Color("FFF68F"));
            registry.register(Items.MELON, 0, 0.04f, dirtState, new Color("FF443B"));
            registry.register(Blocks.MELON_BLOCK, 0, 1.0f / 6, dirtState, new Color("FF443B"));
            registry.register(Blocks.PUMPKIN, 0, 1.0f / 6, dirtState, new Color("FFDB66"));
            registry.register(Blocks.LIT_PUMPKIN, 0, 1.0f / 6, dirtState, new Color("FFDB66"));

            registry.register(Blocks.CACTUS, 0, 0.10f, dirtState, new Color("DEFFB5"));

            registry.register(Items.CARROT, 0, 0.08f, dirtState, new Color("FF9B0F"));
            registry.register(Items.POTATO, 0, 0.08f, dirtState, new Color("FFF1B5"));
            registry.register(Items.BAKED_POTATO, 0, 0.08f, dirtState, new Color("FFF1B5"));
            registry.register(Items.POISONOUS_POTATO, 0, 0.08f, dirtState, new Color("E0FF8A"));

            registry.register(Blocks.WATERLILY, 0, 0.10f, dirtState, new Color("269900"));
            registry.register(Blocks.VINE, 0, 0.10f, dirtState, new Color("23630E"));
            registry.register(Blocks.TALLGRASS, 1, 0.08f, dirtState, new Color("23630E"));
            registry.register(Items.EGG, 0, 0.08f, dirtState, new Color("FFFA66"));
            registry.register(Items.NETHER_WART, 0, 0.10f, dirtState, new Color("FF2B52"));
            registry.register(Items.REEDS, 0, 0.08f, dirtState, new Color("9BFF8A"));
            registry.register(Items.STRING, 0, 0.04f, dirtState, Util.whiteColor);

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class CrookDefaults implements ICrookDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(CrookRegistry registry) {
            registry.register(new BlockInfo(Blocks.LEAVES, -1), ItemResource.getResourceStack(ItemResource.SILKWORM), 0.1f, 0f);
            registry.register(new BlockInfo(Blocks.LEAVES2, -1), ItemResource.getResourceStack(ItemResource.SILKWORM), 0.1f, 0f);

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class SieveDefaults implements ISieveDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(SieveRegistry registry) {
            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("stone"), 1f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("stone"), 1f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("stone"), 0.5f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("stone"), 0.5f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("stone"), 0.1f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("stone"), 0.1f, MeshType.STRING.getID());

            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("granite"), 0.5f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("granite"), 0.1f, MeshType.STRING.getID());

            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("diorite"), 0.5f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("diorite"), 0.1f, MeshType.STRING.getID());

            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("andesite"), 0.5f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), ItemPebble.getPebbleStack("andesite"), 0.1f, MeshType.STRING.getID());

            registry.register(Blocks.DIRT.getDefaultState(), new ItemInfo(Items.WHEAT_SEEDS, 0), 0.7f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), new ItemInfo(Items.MELON_SEEDS, 0), 0.35f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), new ItemInfo(Items.PUMPKIN_SEEDS, 0), 0.35f, MeshType.STRING.getID());

            registry.register(Blocks.SAND.getDefaultState(), new ItemInfo(Items.DYE, 3), 0.03f, MeshType.STRING.getID());
            registry.register(Blocks.SAND.getDefaultState(), new ItemInfo(Items.PRISMARINE_SHARD, 0), 0.02f, MeshType.DIAMOND.getID());

            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemInfo(Items.FLINT, 0), 0.25f, MeshType.FLINT.getID());
            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemInfo(Items.COAL, 0), 0.125f, MeshType.FLINT.getID());
            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemInfo(Items.DYE, 4), 0.05f, MeshType.FLINT.getID());

            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemInfo(Items.DIAMOND, 0), 0.008f, MeshType.IRON.getID());
            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemInfo(Items.EMERALD, 0), 0.008f, MeshType.IRON.getID());

            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemInfo(Items.DIAMOND, 0), 0.016f, MeshType.DIAMOND.getID());
            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemInfo(Items.EMERALD, 0), 0.016f, MeshType.DIAMOND.getID());

            registry.register(Blocks.SOUL_SAND.getDefaultState(), new ItemInfo(Items.QUARTZ, 0), 1f, MeshType.FLINT.getID());
            registry.register(Blocks.SOUL_SAND.getDefaultState(), new ItemInfo(Items.QUARTZ, 0), 0.33f, MeshType.FLINT.getID());

            registry.register(Blocks.SOUL_SAND.getDefaultState(), new ItemInfo(Items.NETHER_WART, 0), 0.1f, MeshType.STRING.getID());

            registry.register(Blocks.SOUL_SAND.getDefaultState(), new ItemInfo(Items.GHAST_TEAR, 0), 0.02f, MeshType.DIAMOND.getID());
            registry.register(Blocks.SOUL_SAND.getDefaultState(), new ItemInfo(Items.QUARTZ, 0), 1f, MeshType.DIAMOND.getID());
            registry.register(Blocks.SOUL_SAND.getDefaultState(), new ItemInfo(Items.QUARTZ, 0), 0.8f, MeshType.DIAMOND.getID());

            registry.register(ModBlocks.dust.getDefaultState(), new ItemInfo(Items.DYE, 15), 0.2f, MeshType.STRING.getID());
            registry.register(ModBlocks.dust.getDefaultState(), new ItemInfo(Items.GUNPOWDER, 0), 0.07f, MeshType.STRING.getID());

            registry.register(ModBlocks.dust.getDefaultState(), new ItemInfo(Items.REDSTONE, 0), 0.125f, MeshType.IRON.getID());
            registry.register(ModBlocks.dust.getDefaultState(), new ItemInfo(Items.REDSTONE, 0), 0.25f, MeshType.DIAMOND.getID());

            registry.register(ModBlocks.dust.getDefaultState(), new ItemInfo(Items.GLOWSTONE_DUST, 0), 0.0625f, MeshType.IRON.getID());
            registry.register(ModBlocks.dust.getDefaultState(), new ItemInfo(Items.BLAZE_POWDER, 0), 0.05f, MeshType.IRON.getID());

            if (Loader.isModLoaded("tconstruct")) {
                Item.getByNameOrId("");
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemInfo(Items.BLAZE_POWDER, 0), 0.05f, MeshType.IRON.getID());
            }

            // Custom Ores for other mods
            OreRegistry oreRegistry = ExNihiloRegistryManager.ORE_REGISTRY;

            // Gold from nether rack
            ItemOre gold = oreRegistry.getOreItem("gold");
            if (gold != null) {
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(gold, 1, 0), 0.25f, MeshType.FLINT.getID());
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(gold, 1, 0), 0.25f, MeshType.IRON.getID());
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(gold, 1, 0), 0.4f, MeshType.DIAMOND.getID());
            }

            // TCon support
            ItemOre ardite = oreRegistry.getOreItem("ardite");
            if (ardite != null) {
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(ardite, 1, 0), 0.1f, MeshType.FLINT.getID());
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(ardite, 1, 0), 0.2f, MeshType.IRON.getID());
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(ardite, 1, 0), 0.3f, MeshType.DIAMOND.getID());
            }

            ItemOre cobalt = oreRegistry.getOreItem("cobalt");
            if (cobalt != null) {
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(cobalt, 1, 0), 0.1f, MeshType.FLINT.getID());
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(cobalt, 1, 0), 0.2f, MeshType.IRON.getID());
                registry.register(ModBlocks.netherrackCrushed.getDefaultState(), new ItemStack(cobalt, 1, 0), 0.3f, MeshType.DIAMOND.getID());
            }

            // Mekanism Support
            ItemOre osmium = oreRegistry.getOreItem("osmium");
            if (osmium != null) {
                registry.register(Blocks.GRAVEL.getDefaultState(), new ItemStack(osmium, 1, 0), 0.05f, MeshType.IRON.getID());
                registry.register(Blocks.GRAVEL.getDefaultState(), new ItemStack(osmium, 1, 0), 0.1f, MeshType.DIAMOND.getID());
            }

            // All default Ores
            for (ItemOre ore : oreRegistry.getItemOreRegistry()) {
                if (ore == ardite || ore == cobalt) continue;

                registry.register(Blocks.GRAVEL.getDefaultState(), new ItemStack(ore, 1, 0), 0.2f, MeshType.FLINT.getID());
                registry.register(Blocks.GRAVEL.getDefaultState(), new ItemStack(ore, 1, 0), 0.2f, MeshType.IRON.getID());
                registry.register(Blocks.GRAVEL.getDefaultState(), new ItemStack(ore, 1, 0), 0.1f, MeshType.DIAMOND.getID());
            }

            // Seeds
            for (ItemSeedBase seed : ModItems.itemSeeds) {
                registry.register(Blocks.DIRT.getDefaultState(), new ItemStack(seed), 0.05f, MeshType.STRING.getID());
            }

            registry.register(Blocks.DIRT.getDefaultState(), ItemResource.getResourceStack(ItemResource.ANCIENT_SPORES), 0.05f, MeshType.STRING.getID());
            registry.register(Blocks.DIRT.getDefaultState(), ItemResource.getResourceStack(ItemResource.GRASS_SEEDS), 0.05f, MeshType.STRING.getID());

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class HammerDefaults implements IHammerDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(HammerRegistry registry) {
            registry.register(Blocks.COBBLESTONE.getDefaultState(), new ItemStack(Blocks.GRAVEL, 1), 0, 1.0F, 0.0F);
            registry.register(Blocks.GRAVEL.getDefaultState(), new ItemStack(Blocks.SAND, 1), 0, 1.0F, 0.0F);
            registry.register(Blocks.SAND.getDefaultState(), new ItemStack(ModBlocks.dust, 1), 0, 1.0F, 0.0F);
            registry.register(Blocks.NETHERRACK.getDefaultState(), new ItemStack(ModBlocks.netherrackCrushed, 1), 0, 1.0F, 0.0F);
            registry.register(Blocks.END_STONE.getDefaultState(), new ItemStack(ModBlocks.endstoneCrushed, 1), 0, 1.0F, 0.0F);

            // Hammering stone into pebbles (no idea why anyone should do that, but hey :P)
            registry.register(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE), new ItemStack(ModItems.pebbles, 1, EnumPebbleSubtype.STONE.getMeta()), 1, 3F, 1.25F);
            registry.register(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), new ItemStack(ModItems.pebbles, 1, EnumPebbleSubtype.ANDESITE.getMeta()), 1, 3F, 1.25F);
            registry.register(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), new ItemStack(ModItems.pebbles, 1, EnumPebbleSubtype.GRANITE.getMeta()), 1, 3F, 1.25F);
            registry.register(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), new ItemStack(ModItems.pebbles, 1, EnumPebbleSubtype.DIORITE.getMeta()), 1, 3F, 1.25F);

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class HeatDefaults implements IHeatDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(HeatRegistry registry) {
            // Vanilla fluids are weird, the "flowing" variant is simply a temporary state of checking if it can flow.
            // So, once the lava has spread out all the way, it will all actually be "still" lava.
            // Thanks Mojang <3
            registry.register(new BlockInfo(Blocks.FLOWING_LAVA, -1), 3);
            registry.register(new BlockInfo(Blocks.LAVA, -1), 3);
            registry.register(new BlockInfo(Blocks.FIRE, -1), 4);
            registry.register(new BlockInfo(Blocks.TORCH, -1), 1);

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class BarrelLiquidBlacklistDefaults implements IBarrelLiquidBlacklistDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(BarrelLiquidBlacklistRegistry registry) {
            registry.register(ModBlocks.barrelWood.getTier(), "lava");
            registry.register(ModBlocks.barrelWood.getTier(), "fire_water");
            registry.register(ModBlocks.barrelWood.getTier(), "rocket_fuel");

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class FluidOnTopDefaults implements IFluidOnTopDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(FluidOnTopRegistry registry) {
            registry.register(FluidRegistry.LAVA, FluidRegistry.WATER, new ItemInfo(Blocks.OBSIDIAN.getDefaultState()));
            registry.register(FluidRegistry.WATER, FluidRegistry.LAVA, new ItemInfo(Blocks.COBBLESTONE.getDefaultState()));

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class OreDefaults implements IOreDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(OreRegistry registry) {
            registry.register("gold", new Color("FFFF00"), new ItemInfo(Items.GOLD_INGOT, 0));
            registry.register("iron", new Color("BF8040"), new ItemInfo(Items.IRON_INGOT, 0));

            //TODO: Better way, will most likely never grab as it is just called before many mods init their oredict
            if (OreDictionary.getOres("oreCopper").size() > 0) {
                registry.register("copper", new Color("FF9933"), null);
            }

            if (OreDictionary.getOres("oreTin").size() > 0) {
                registry.register("tin", new Color("E6FFF2"), null);
            }

            if (OreDictionary.getOres("oreAluminium").size() > 0 || OreDictionary.getOres("oreAluminum").size() > 0) {
                registry.register("aluminium", new Color("BFBFBF"), null);
            }

            if (OreDictionary.getOres("oreLead").size() > 0) {
                registry.register("lead", new Color("330066"), null);
            }

            if (OreDictionary.getOres("oreSilver").size() > 0) {
                registry.register("silver", new Color("F2F2F2"), null);
            }

            if (OreDictionary.getOres("oreNickel").size() > 0) {
                registry.register("nickel", new Color("FFFFCC"), null);
            }

            Item tconstructIngots = Item.getByNameOrId("tconstruct:ingots");

            if (tconstructIngots != null) {
                registry.register("ardite", new Color("FF751A"), new ItemInfo(tconstructIngots, 1));
                registry.register("cobalt", new Color("3333FF"), new ItemInfo(tconstructIngots, 0));
            }

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class FluidTransformDefaults implements IFluidTransformDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(FluidTransformRegistry registry) {
            registry.register("water", "witchwater", 12000, new BlockInfo[]{new BlockInfo(Blocks.MYCELIUM.getDefaultState())}, new BlockInfo[]{new BlockInfo(Blocks.BROWN_MUSHROOM.getDefaultState()), new BlockInfo(Blocks.RED_MUSHROOM.getDefaultState())});

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class FluidBlockTransformDefaults implements IFluidBlockDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(FluidBlockTransformerRegistry registry) {
            registry.register(FluidRegistry.WATER, new ItemInfo(new ItemStack(ModBlocks.dust)), new ItemInfo(new ItemStack(Blocks.CLAY)));
            registry.register(FluidRegistry.LAVA, new ItemInfo(new ItemStack(Items.REDSTONE)), new ItemInfo(new ItemStack(Blocks.NETHERRACK)));
            registry.register(FluidRegistry.LAVA, new ItemInfo(new ItemStack(Items.GLOWSTONE_DUST)), new ItemInfo(new ItemStack(Blocks.END_STONE)));
            registry.register(ModFluids.fluidWitchwater, new ItemInfo(new ItemStack(Blocks.SAND)), new ItemInfo(new ItemStack(Blocks.SOUL_SAND)));

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class FluidItemFluidDefaults implements IFluidItemFluidDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(FluidItemFluidRegistry registry) {
            registry.register(FluidRegistry.WATER, new ItemInfo(ItemResource.getResourceStack(ItemResource.ANCIENT_SPORES)), ModFluids.fluidWitchwater);

            compat.registerRecipeDefaults(registry);
        }
    }

    private static class CrucibleStoneDefaults implements ICrucibleStoneDefaultRegistryProvider {

        @Override
        public void registerRecipeDefaults(CrucibleRegistry registry) {
            registry.register(new ItemStack(Blocks.COBBLESTONE), FluidRegistry.LAVA, 250);

            compat.registerRecipeDefaultsStone(registry);
        }
    }

    private static class CrucibleWoodDefaults implements ICrucibleWoodDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(CrucibleRegistry registry) {
            Meltable water = new Meltable(FluidRegistry.WATER.getName(), 250, new BlockInfo(Blocks.LEAVES, 0));
            registry.register(new ItemStack(Blocks.LEAVES), FluidRegistry.WATER, 250);
            registry.register(new ItemStack(Blocks.LEAVES, 1, 1), FluidRegistry.WATER, 250);
            registry.register(new ItemStack(Blocks.LEAVES, 1, 2), FluidRegistry.WATER, 250);
            registry.register(new ItemStack(Blocks.LEAVES, 1, 3), FluidRegistry.WATER, 250);
            registry.register(new ItemStack(Blocks.LEAVES2, 1, 0), FluidRegistry.WATER, 250);
            registry.register(new ItemStack(Blocks.LEAVES2, 1, 1), FluidRegistry.WATER, 250);

            registry.register(new ItemInfo(Blocks.SAPLING, 0), water);
            registry.register(new ItemInfo(Blocks.SAPLING, 1), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES, 1)));
            registry.register(new ItemInfo(Blocks.SAPLING, 2), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES, 2)));
            registry.register(new ItemInfo(Blocks.SAPLING, 3), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES, 3)));
            registry.register(new ItemInfo(Blocks.SAPLING, 4), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES2, 0)));
            registry.register(new ItemInfo(Blocks.SAPLING, 5), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES2, 1)));

            registry.register(new ItemInfo(Items.APPLE, 0), water);

            compat.registerRecipeDefaultsWood(registry);
        }
    }

    public static class MilkEntityDefaults implements IMilkEntityDefaultRegistryProvider {
        @Override
        public void registerRecipeDefaults(MilkEntityRegistry registry) {
            registry.register("Cow", "milk", 10, 20);

            compat.registerRecipeDefaults(registry);
        }
    }
}
