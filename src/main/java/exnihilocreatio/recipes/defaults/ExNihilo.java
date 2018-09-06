package exnihilocreatio.recipes.defaults;

import exnihilocreatio.ExNihiloCreatio;
import exnihilocreatio.ModBlocks;
import exnihilocreatio.ModFluids;
import exnihilocreatio.ModItems;
import exnihilocreatio.blocks.BlockSieve.MeshType;
import exnihilocreatio.config.ModConfig;
import exnihilocreatio.items.ItemResource;
import exnihilocreatio.items.ore.ItemOre;
import exnihilocreatio.items.seeds.ItemSeedBase;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.*;
import exnihilocreatio.registries.types.Meltable;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.ItemInfo;
import exnihilocreatio.util.Util;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExNihilo implements IRecipeDefaults {
    @Getter
    public String MODID = ExNihiloCreatio.MODID;


    @Override
    public void registerCompost(CompostRegistry registry) {
        BlockInfo dirtState = new BlockInfo(Blocks.DIRT);

        registry.register("treeSapling", 0.125f, dirtState);
        registry.register("treeLeaves", 0.125f, dirtState);
        registry.register("flower", 0.1f, dirtState);
        registry.register("fish", 0.15f, dirtState);
        registry.register("listAllmeatcooked", 0.20f, dirtState);

        registry.register(new ItemInfo(Items.ROTTEN_FLESH), 0.1f, dirtState, new Color("C45631"));

        registry.register(new ItemInfo(Items.SPIDER_EYE), 0.08f, dirtState, new Color("963E44"));

        registry.register(new ItemInfo(Items.WHEAT), 0.08f, dirtState, new Color("E3E162"));
        registry.register(new ItemInfo(Items.WHEAT_SEEDS), 0.08f, dirtState, new Color("35A82A"));
        registry.register(new ItemInfo(Items.BREAD), 0.16f, dirtState, new Color("D1AF60"));

        registry.register(new BlockInfo(Blocks.BROWN_MUSHROOM), 0.10f, dirtState, new Color("CFBFB6"));
        registry.register(new BlockInfo(Blocks.RED_MUSHROOM), 0.10f, dirtState, new Color("D6A8A5"));

        registry.register(new ItemInfo(Items.PUMPKIN_PIE), 0.16f, dirtState, new Color("E39A6D"));

        registry.register(new ItemInfo(Items.PORKCHOP), 0.2f, dirtState, new Color("FFA091"));
        registry.register(new ItemInfo(Items.BEEF), 0.2f, dirtState, new Color("FF4242"));
        registry.register(new ItemInfo(Items.CHICKEN), 0.2f, dirtState, new Color("FFE8E8"));

        registry.register(new ItemInfo(ModItems.resources, ItemResource.getMetaFromName(ItemResource.SILKWORM)), 0.04f, dirtState, new Color("ff9966"));
        registry.register(new ItemInfo(ModItems.cookedSilkworm), 0.04f, dirtState, new Color("cc6600"));

        registry.register(new ItemInfo(Items.APPLE), 0.10f, dirtState, new Color("FFF68F"));
        registry.register(new ItemInfo(Items.MELON), 0.04f, dirtState, new Color("FF443B"));
        registry.register(new BlockInfo(Blocks.MELON_BLOCK), 1.0f / 6, dirtState, new Color("FF443B"));
        registry.register(new BlockInfo(Blocks.PUMPKIN), 1.0f / 6, dirtState, new Color("FFDB66"));
        registry.register(new BlockInfo(Blocks.LIT_PUMPKIN), 1.0f / 6, dirtState, new Color("FFDB66"));

        registry.register(new BlockInfo(Blocks.CACTUS), 0.10f, dirtState, new Color("DEFFB5"));

        registry.register(new ItemInfo(Items.CARROT), 0.08f, dirtState, new Color("FF9B0F"));
        registry.register(new ItemInfo(Items.POTATO), 0.08f, dirtState, new Color("FFF1B5"));
        registry.register(new ItemInfo(Items.BAKED_POTATO), 0.08f, dirtState, new Color("FFF1B5"));
        registry.register(new ItemInfo(Items.POISONOUS_POTATO), 0.08f, dirtState, new Color("E0FF8A"));

        registry.register(new BlockInfo(Blocks.WATERLILY.getDefaultState()), 0.10f, dirtState, new Color("269900"));
        registry.register(new BlockInfo(Blocks.VINE.getDefaultState()), 0.10f, dirtState, new Color("23630E"));
        registry.register(new BlockInfo(Blocks.TALLGRASS, 1), 0.08f, dirtState, new Color("23630E"));
        registry.register(new ItemInfo(Items.EGG), 0.08f, dirtState, new Color("FFFA66"));
        registry.register(new ItemInfo(Items.NETHER_WART), 0.10f, dirtState, new Color("FF2B52"));
        registry.register(new ItemInfo(Items.REEDS), 0.08f, dirtState, new Color("9BFF8A"));
        registry.register(new ItemInfo(Items.STRING), 0.04f, dirtState, Util.whiteColor);

        //Register any missed items
        registry.register("listAllfruit", 0.10f, dirtState, new Color("35A82A"));
        registry.register("listAllveggie", 0.10f, dirtState, new Color("FFF1B5"));
        registry.register("listAllGrain", 0.08f, dirtState, new Color("E3E162"));
        registry.register("listAllseed", 0.08f, dirtState, new Color("35A82A"));
        registry.register("listAllmeatraw", 0.15f, dirtState, new Color("FFA091"));

        // Misc. Modded Items
        registry.register("dustAsh", 0.125f, dirtState, new Color("C0C0C0"));
    }

    @Override
    public void registerCrook(CrookRegistry registry) {
        registry.register("treeLeaves", ItemResource.getResourceStack(ItemResource.SILKWORM), 0.1f, 0f);
    }

    @Override
    public void registerSieve(SieveRegistry registry) {

        //Stone Pebble
        registry.register("dirt", new ItemInfo(ModItems.pebbles), getDropChance(1f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(ModItems.pebbles), getDropChance(1f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(ModItems.pebbles), getDropChance(0.5f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(ModItems.pebbles), getDropChance(0.5f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(ModItems.pebbles), getDropChance(0.1f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(ModItems.pebbles), getDropChance(0.1f), MeshType.STRING.getID());

        //Granite Pebble
        registry.register("dirt", new ItemInfo(ModItems.pebbles, 1), getDropChance(0.5f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(ModItems.pebbles, 1), getDropChance(0.1f), MeshType.STRING.getID());

        //Diorite Pebble
        registry.register("dirt", new ItemInfo(ModItems.pebbles, 2), getDropChance(0.5f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(ModItems.pebbles, 2), getDropChance(0.1f), MeshType.STRING.getID());

        //Andesite Pebble
        registry.register("dirt", new ItemInfo(ModItems.pebbles, 3), getDropChance(0.5f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(ModItems.pebbles, 3), getDropChance(0.1f), MeshType.STRING.getID());

        registry.register("dirt", new ItemInfo(Items.WHEAT_SEEDS), getDropChance(0.7f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(Items.MELON_SEEDS), getDropChance(0.35f), MeshType.STRING.getID());
        registry.register("dirt", new ItemInfo(Items.PUMPKIN_SEEDS), getDropChance(0.35f), MeshType.STRING.getID());

        //Ancient Spores
        registry.register("dirt", new ItemInfo(ModItems.resources, 3), getDropChance(0.05f), MeshType.STRING.getID());
        //Grass Seeds
        registry.register("dirt", new ItemInfo(ModItems.resources, 4), getDropChance(0.05f), MeshType.STRING.getID());


        registry.register("sand", new ItemInfo(Items.DYE, 3), getDropChance(0.03f), MeshType.STRING.getID());
        registry.register("sand", new ItemInfo(Items.PRISMARINE_SHARD), getDropChance(0.02f), MeshType.DIAMOND.getID());

        registry.register("gravel", new ItemInfo(Items.FLINT), getDropChance(0.25f), MeshType.FLINT.getID());
        registry.register("gravel", new ItemInfo(Items.COAL), getDropChance(0.125f), MeshType.FLINT.getID());
        registry.register("gravel", new ItemInfo(Items.DYE, 4), getDropChance(0.05f), MeshType.FLINT.getID());

        registry.register("gravel", new ItemInfo(Items.DIAMOND), getDropChance(0.008f), MeshType.IRON.getID());
        registry.register("gravel", new ItemInfo(Items.EMERALD), getDropChance(0.008f), MeshType.IRON.getID());

        registry.register("gravel", new ItemInfo(Items.DIAMOND), getDropChance(0.016f), MeshType.DIAMOND.getID());
        registry.register("gravel", new ItemInfo(Items.EMERALD), getDropChance(0.016f), MeshType.DIAMOND.getID());


        registry.register(new BlockInfo(Blocks.SOUL_SAND), new ItemInfo(Items.QUARTZ), getDropChance(1f), MeshType.FLINT.getID());
        registry.register(new BlockInfo(Blocks.SOUL_SAND), new ItemInfo(Items.QUARTZ), getDropChance(0.33f), MeshType.FLINT.getID());

        registry.register(new BlockInfo(Blocks.SOUL_SAND), new ItemInfo(Items.NETHER_WART), getDropChance(0.1f), MeshType.STRING.getID());

        registry.register(new BlockInfo(Blocks.SOUL_SAND), new ItemInfo(Items.GHAST_TEAR), getDropChance(0.02f), MeshType.DIAMOND.getID());
        registry.register(new BlockInfo(Blocks.SOUL_SAND), new ItemInfo(Items.QUARTZ), getDropChance(1f), MeshType.DIAMOND.getID());
        registry.register(new BlockInfo(Blocks.SOUL_SAND), new ItemInfo(Items.QUARTZ), getDropChance(0.8f), MeshType.DIAMOND.getID());

        registry.register("dust", new ItemInfo(Items.DYE, 15), getDropChance(0.2f), MeshType.STRING.getID());
        registry.register("dust", new ItemInfo(Items.GUNPOWDER), getDropChance(0.07f), MeshType.STRING.getID());

        registry.register("dust", new ItemInfo(Items.REDSTONE), getDropChance(0.125f), MeshType.IRON.getID());
        registry.register("dust", new ItemInfo(Items.REDSTONE), getDropChance(0.25f), MeshType.DIAMOND.getID());

        registry.register("dust", new ItemInfo(Items.GLOWSTONE_DUST), getDropChance(0.0625f), MeshType.IRON.getID());
        registry.register("dust", new ItemInfo(Items.BLAZE_POWDER), getDropChance(0.05f), MeshType.IRON.getID());

        // Custom Ores for other mods
        OreRegistry oreRegistry = ExNihiloRegistryManager.ORE_REGISTRY;

        // Gold from nether rack
        ItemOre gold = oreRegistry.getOreItem("gold");
        if (gold != null) {
            registry.register(new BlockInfo(ModBlocks.netherrackCrushed), new ItemInfo(gold, 0), getDropChance(0.25f), MeshType.FLINT.getID());
            registry.register(new BlockInfo(ModBlocks.netherrackCrushed), new ItemInfo(gold, 0), getDropChance(0.25f), MeshType.IRON.getID());
            registry.register(new BlockInfo(ModBlocks.netherrackCrushed), new ItemInfo(gold, 0), getDropChance(0.4f), MeshType.DIAMOND.getID());
        }


        // All default Ores
        for (ItemOre ore : oreRegistry.getItemOreRegistry()) {
            if (oreRegistry.getSieveBlackList().contains(ore)) continue;
            if(ore.getOre().getName() == "iron"){
                registry.register("gravel", new ItemInfo(ore), getDropChance(0.1f), MeshType.FLINT.getID());
                registry.register("gravel", new ItemInfo(ore), getDropChance(0.15f), MeshType.IRON.getID());
                registry.register("gravel", new ItemInfo(ore), getDropChance(0.25f), MeshType.DIAMOND.getID());
                registry.register(new BlockInfo(Blocks.SAND,1), new ItemInfo(ore), getDropChance(0.4f), MeshType.DIAMOND.getID());
            }
            else if (ore.getOre().getName() == "uranium") {
                registry.register("gravel", new ItemInfo(ore), getDropChance(0.008f), MeshType.FLINT.getID());
                registry.register("gravel", new ItemInfo(ore), getDropChance(0.009f), MeshType.IRON.getID());
                registry.register("gravel", new ItemInfo(ore), getDropChance(0.01f), MeshType.DIAMOND.getID());
            }
            else{
                registry.register("gravel", new ItemInfo(ore), getDropChance(0.05f), MeshType.FLINT.getID());
                registry.register("gravel", new ItemInfo(ore), getDropChance(0.075f), MeshType.IRON.getID());
                registry.register("gravel", new ItemInfo(ore), getDropChance(0.15f), MeshType.DIAMOND.getID());
            }
        }
        // Seeds
        for (ItemSeedBase seed : ModItems.itemSeeds) {
            registry.register("dirt", new ItemInfo(seed), getDropChance(0.05f), MeshType.STRING.getID());
        }

        getLeavesSapling().forEach((leaves, sapling) -> {
            BlockLeaves blockLeaves = ((BlockLeaves) Block.getBlockFromItem(leaves.getItemStack().getItem()));
            float chance = blockLeaves.getSaplingDropChance(blockLeaves.getDefaultState()) / 100f;

            registry.register(leaves, sapling, Math.min(chance * 1, 1.0f), MeshType.STRING.getID());
            registry.register(leaves, sapling, Math.min(chance * 2, 1.0f), MeshType.FLINT.getID());
            registry.register(leaves, sapling, Math.min(chance * 3, 1.0f), MeshType.IRON.getID());
            registry.register(leaves, sapling, Math.min(chance * 4, 1.0f), MeshType.DIAMOND.getID());

            //Apple
            registry.register(leaves, new ItemInfo(Items.APPLE), 0.05f, MeshType.STRING.getID());
            registry.register(leaves, new ItemInfo(Items.APPLE), 0.10f, MeshType.FLINT.getID());
            registry.register(leaves, new ItemInfo(Items.APPLE), 0.15f, MeshType.IRON.getID());
            registry.register(leaves, new ItemInfo(Items.APPLE), 0.20f, MeshType.DIAMOND.getID());

            //Golden Apple
            registry.register(leaves, new ItemInfo(Items.GOLDEN_APPLE), 0.001f, MeshType.STRING.getID());
            registry.register(leaves, new ItemInfo(Items.GOLDEN_APPLE), 0.003f, MeshType.FLINT.getID());
            registry.register(leaves, new ItemInfo(Items.GOLDEN_APPLE), 0.005f, MeshType.IRON.getID());
            registry.register(leaves, new ItemInfo(Items.GOLDEN_APPLE), 0.01f, MeshType.DIAMOND.getID());

            //Silk Worm
            registry.register(leaves, new ItemInfo(ItemResource.getResourceStack(ItemResource.SILKWORM)), 0.025f, MeshType.STRING.getID());
            registry.register(leaves, new ItemInfo(ItemResource.getResourceStack(ItemResource.SILKWORM)), 0.05f, MeshType.FLINT.getID());
            registry.register(leaves, new ItemInfo(ItemResource.getResourceStack(ItemResource.SILKWORM)), 0.1f, MeshType.IRON.getID());
            registry.register(leaves, new ItemInfo(ItemResource.getResourceStack(ItemResource.SILKWORM)), 0.2f, MeshType.DIAMOND.getID());
        });
    }

    @Override
    public void registerHammer(HammerRegistry registry) {
        registry.register("cobblestone", new ItemStack(Blocks.GRAVEL, 1), 0, 1.0F, 0.0F);
        registry.register("gravel", new ItemStack(Blocks.SAND, 1), 0, 1.0F, 0.0F);
        registry.register("sand", new ItemStack(ModBlocks.dust, 1), 0, 1.0F, 0.0F);
        registry.register("netherrack", new ItemStack(ModBlocks.netherrackCrushed, 1), 0, 1.0F, 0.0F);
        registry.register("endstone", new ItemStack(ModBlocks.endstoneCrushed, 1), 0, 1.0F, 0.0F);

        registry.register("stoneAndesite", new ItemStack(ModBlocks.crushedAndesite), 0, 1f, 0);
        registry.register("stoneGranite", new ItemStack(ModBlocks.crushedGranite), 0, 1f, 0);
        registry.register("stoneDiorite", new ItemStack(ModBlocks.crushedDiorite), 0, 1f, 0);

        registry.register("crushedGranite", new ItemStack(Blocks.SAND, 1, 1), 0, 1.0f, 0.0f);


        // Hammer concrete into concrete powder
        for (int meta = 0; meta < 16; meta++)
            registry.register(BlockInfo.getStateFromMeta(Blocks.CONCRETE, meta), new ItemStack(Blocks.CONCRETE_POWDER, 1, meta), 1, 1.0f, 0.0f);

    }

    @Override
    public void registerHeat(HeatRegistry registry) {
        // Vanilla fluids are weird, the "flowing" variant is simply a temporary state of checking if it can flow.
        // So, once the lava has spread out all the way, it will all actually be "still" lava.
        // Thanks Mojang <3
        registry.register(new BlockInfo(Blocks.FLOWING_LAVA), 3);
        registry.register(new BlockInfo(Blocks.LAVA), 3);
        registry.register(new BlockInfo(Blocks.FIRE), 4);
        registry.register(new BlockInfo(Blocks.TORCH), 1);
    }

    @Override
    public void registerBarrelLiquidBlacklist(BarrelLiquidBlacklistRegistry registry) {
        registry.register(ModBlocks.barrelWood.getTier(), "lava");
        registry.register(ModBlocks.barrelWood.getTier(), "fire_water");
        registry.register(ModBlocks.barrelWood.getTier(), "rocket_fuel");
        registry.register(ModBlocks.barrelWood.getTier(), "pyrotheum");
    }

    @Override
    public void registerFluidOnTop(FluidOnTopRegistry registry) {
        registry.register(FluidRegistry.LAVA, FluidRegistry.WATER, new BlockInfo(Blocks.OBSIDIAN.getDefaultState()));
        registry.register(FluidRegistry.WATER, FluidRegistry.LAVA, new BlockInfo(Blocks.COBBLESTONE.getDefaultState()));
    }

    @Override
    public void registerOreChunks(OreRegistry registry) {
        registry.register("gold", new Color("FFFF00"), new ItemInfo(Items.GOLD_INGOT, 0));
        registry.register("iron", new Color("BF8040"), new ItemInfo(Items.IRON_INGOT, 0));

        //TODO: Better way, will most likely never grab as it is just called before many mods init their oredict
        if (!OreDictionary.getOres("oreCopper").isEmpty()) {
            registry.register("copper", new Color("FF9933"), null);
        }

        if (!OreDictionary.getOres("oreTin").isEmpty()) {
            registry.register("tin", new Color("E6FFF2"), null);
        }

        if (!OreDictionary.getOres("oreAluminium").isEmpty() || !OreDictionary.getOres("oreAluminum").isEmpty()) {
            registry.register("aluminium", new Color("BFBFBF"), null);
        }

        if (!OreDictionary.getOres("oreLead").isEmpty()) {
            registry.register("lead", new Color("330066"), null);
        }

        if (!OreDictionary.getOres("oreSilver").isEmpty()) {
            registry.register("silver", new Color("F2F2F2"), null);
        }

        if (!OreDictionary.getOres("oreNickel").isEmpty()) {
            registry.register("nickel", new Color("FFFFCC"), null);
        }

        if (!OreDictionary.getOres("oreUranium").isEmpty()) {
            registry.register("uranium", new Color("4E5B43"), null);
        }
    }

    @Override
    public void registerFluidTransform(FluidTransformRegistry registry) {
        registry.register("water", "witchwater", 12000, new BlockInfo[]{new BlockInfo(Blocks.MYCELIUM.getDefaultState())}, new BlockInfo[]{new BlockInfo(Blocks.BROWN_MUSHROOM.getDefaultState()), new BlockInfo(Blocks.RED_MUSHROOM.getDefaultState())});
    }

    @Override
    public void registerFluidBlockTransform(FluidBlockTransformerRegistry registry) {
        registry.register(FluidRegistry.WATER, "dust", new ItemInfo(new ItemStack(Blocks.CLAY)));
        registry.register(FluidRegistry.LAVA, "dustRedstone", new ItemInfo(new ItemStack(Blocks.NETHERRACK)));
        registry.register(FluidRegistry.LAVA, "dustGlowstone", new ItemInfo(new ItemStack(Blocks.END_STONE)));
        registry.register(ModFluids.fluidWitchwater, "sand", new ItemInfo(new ItemStack(Blocks.SOUL_SAND)));

        if (FluidRegistry.isFluidRegistered("milk")){
            registry.register(FluidRegistry.getFluid("milk"), new ItemInfo(new ItemStack(Blocks.BROWN_MUSHROOM)), new ItemInfo(new ItemStack(Blocks.SLIME_BLOCK)), "Slime");
            registry.register(FluidRegistry.getFluid("milk"), new ItemInfo(new ItemStack(Blocks.RED_MUSHROOM)), new ItemInfo(new ItemStack(Blocks.SLIME_BLOCK)), "Slime");
        }

        // Vanilla Concrete
        for (int meta = 0; meta < 16; meta++)
            registry.register(FluidRegistry.WATER, new ItemInfo(new ItemStack(Blocks.CONCRETE_POWDER, 1, meta)), new ItemInfo(new ItemStack(Blocks.CONCRETE, 1, meta)));
    }

    @Override
    public void registerFluidItemFluid(FluidItemFluidRegistry registry) {
        registry.register(FluidRegistry.WATER, new ItemInfo(ItemResource.getResourceStack(ItemResource.ANCIENT_SPORES)), ModFluids.fluidWitchwater);
    }

    @Override
    public void registerCrucibleStone(CrucibleRegistry registry) {
        registry.register("cobblestone", FluidRegistry.LAVA, 250);
        registry.register("stone", FluidRegistry.LAVA, 250);
        registry.register("gravel", FluidRegistry.LAVA, 200);
        registry.register("sand", FluidRegistry.LAVA, 100);
        registry.register(new BlockInfo(ModBlocks.dust.getDefaultState()), FluidRegistry.LAVA, 50);

        // 1:1 Back to lava
        registry.register("netherrack", FluidRegistry.LAVA, 1000);
        registry.register("obsidian", FluidRegistry.LAVA, 1000);
    }

    @Override
    public void registerCrucibleWood(CrucibleRegistry registry) {
        Meltable water = new Meltable(FluidRegistry.WATER.getName(), 250, new BlockInfo(Blocks.LEAVES, 0));
        registry.register("treeLeaves", FluidRegistry.WATER, 250);
        registry.register("treeSapling", water);
        registry.register("listAllfruit", water);
        registry.register("listAllveggie", water);

        registry.register(new ItemInfo(Blocks.SAPLING, 0), water);
        registry.register(new ItemInfo(Blocks.SAPLING, 1), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES, 1)));
        registry.register(new ItemInfo(Blocks.SAPLING, 2), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES, 2)));
        registry.register(new ItemInfo(Blocks.SAPLING, 3), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES, 3)));
        registry.register(new ItemInfo(Blocks.SAPLING, 4), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES2, 0)));
        registry.register(new ItemInfo(Blocks.SAPLING, 5), water.copy().setTextureOverride(new BlockInfo(Blocks.LEAVES2, 1)));
        registry.register(new ItemInfo(Items.APPLE), water);
    }

    @Override
    public void registerMilk(MilkEntityRegistry registry) {
        registry.register("Cow", "milk", 10, 20);
    }

    private float getDropChance(float chance) {
        if (ModConfig.world.isSkyWorld)
            return chance;
        else return chance / 100f * (float) ModConfig.world.normalDropPercent;
    }

    public static Map<BlockInfo, BlockInfo> getLeavesSapling(){
        Map<BlockInfo, BlockInfo> saplingMap = new LinkedHashMap<>();
        saplingMap.put(new BlockInfo(Blocks.LEAVES, 0), new BlockInfo(Blocks.SAPLING, 0));
        saplingMap.put(new BlockInfo(Blocks.LEAVES, 1), new BlockInfo(Blocks.SAPLING, 1));
        saplingMap.put(new BlockInfo(Blocks.LEAVES, 2), new BlockInfo(Blocks.SAPLING, 2));
        saplingMap.put(new BlockInfo(Blocks.LEAVES, 3), new BlockInfo(Blocks.SAPLING, 3));
        saplingMap.put(new BlockInfo(Blocks.LEAVES2, 0), new BlockInfo(Blocks.SAPLING, 4));
        saplingMap.put(new BlockInfo(Blocks.LEAVES2, 1), new BlockInfo(Blocks.SAPLING, 5));

        return saplingMap;
    }
}
