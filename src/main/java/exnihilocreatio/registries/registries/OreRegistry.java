package exnihilocreatio.registries.registries;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exnihilocreatio.ExNihiloCreatio;
import exnihilocreatio.items.ore.EnumOreSubtype;
import exnihilocreatio.items.ore.ItemOre;
import exnihilocreatio.items.ore.Ore;
import exnihilocreatio.json.CustomBlockInfoJson;
import exnihilocreatio.json.CustomItemInfoJson;
import exnihilocreatio.json.CustomOreJson;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.prefab.BaseRegistryList;
import exnihilocreatio.texturing.Color;
import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.ItemInfo;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OreRegistry extends BaseRegistryList<Ore> {

    @Getter
    private List<ItemOre> itemOreRegistry = new ArrayList<>();

    @Getter
    private Set<ItemOre> sieveBlackList = new HashSet<>(); // A black list of ores to not register

    public OreRegistry() {
        super(
                new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(ItemInfo.class, new CustomItemInfoJson())
                        .registerTypeAdapter(BlockInfo.class, new CustomBlockInfoJson())
                        .registerTypeAdapter(Ore.class, new CustomOreJson())
                        .create(),
                ExNihiloRegistryManager.ORE_DEFAULT_REGISTRY_PROVIDERS
        );
    }

    /**
     * Registers a new custom piece, hunk, dust and potentially ingot to be
     * generated by Ex Nihilo Creatio.
     *
     * @param name  Unique name for ore
     * @param color Color for the pieces
     * @param info  Final result for the process. If null, an ingot is generated.
     *              Otherwise, the hunk will be smelted into this.
     * @return Ore, containing the base Ore object.
     */
    public Ore register(String name, Color color, ItemInfo info) {
        Ore ore = new Ore(name, color, info);
        registry.add(ore);
        itemOreRegistry.add(new ItemOre(ore));

        return ore;
    }

    @Override
    public void register(Ore value) {
        registry.add(value);
        itemOreRegistry.add(new ItemOre(value));
    }

    @Override
    protected void registerEntriesFromJSON(FileReader fr) {
        List<Ore> gsonInput = gson.fromJson(fr, new TypeToken<List<Ore>>() {
        }.getType());
        for (Ore ore : gsonInput) {
            register(ore);
        }
    }

    public void doRecipes() {
        for (ItemOre ore : itemOreRegistry) {
            ResourceLocation group = new ResourceLocation(ExNihiloCreatio.MODID, "exores");
            ResourceLocation baseName = new ResourceLocation(ExNihiloCreatio.MODID, "ore_compression_");

            GameRegistry.addShapedRecipe(new ResourceLocation(baseName.getResourceDomain(), baseName.getResourcePath() + ore.getOre().getName()), group,
                    new ItemStack(ore, 1, EnumOreSubtype.CHUNK.getMeta()),
                    "xx", "xx", 'x', new ItemStack(ore, 1, EnumOreSubtype.PIECE.getMeta()));

            ItemStack smeltingResult;

            if (ore.isRegisterIngot()) {
                smeltingResult = new ItemStack(ore, 1, 3);
                OreDictionary.registerOre("ingot" + StringUtils.capitalize(ore.getOre().getName()), smeltingResult);
                if (ore.getOre().getName().contains("aluminium"))
                    OreDictionary.registerOre("ingotAluminum", smeltingResult);
            } else {
                smeltingResult = ore.getOre().getResult().getItemStack();
            }

            FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(ore, 1, EnumOreSubtype.CHUNK.getMeta()), smeltingResult, 0.7f);
            FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(ore, 1, EnumOreSubtype.DUST.getMeta()), smeltingResult, 0.7f);
        }
    }

    /**
     * Must be called in init or later,
     * in the model event there is no getRenderItem() yet
     */
    @SideOnly(Side.CLIENT)
    public void initModels() {
        final ItemMeshDefinition ORES = stack -> {
            switch (stack.getItemDamage()) {
                case 0:
                    return new ModelResourceLocation("exnihilocreatio:item_ore", "type=piece");
                case 1:
                    return new ModelResourceLocation("exnihilocreatio:item_ore", "type=hunk");
                case 2:
                    return new ModelResourceLocation("exnihilocreatio:item_ore", "type=dust");
                case 3:
                    return new ModelResourceLocation("exnihilocreatio:item_ore", "type=ingot");
                default:
                    return new ModelResourceLocation(stack.getItem().getRegistryName() == null ? new ResourceLocation("exnihilocreatio:item_ore") : stack.getItem().getRegistryName(), "inventory");
            }
        };
        for (ItemOre ore : itemOreRegistry) {
            ModelLoader.setCustomMeshDefinition(ore, ORES);
            ModelBakery.registerItemVariants(ore, new ModelResourceLocation("exnihilocreatio:itemOre", "type=piece"),
                    new ModelResourceLocation("exnihilocreatio:item_ore", "type=hunk"),
                    new ModelResourceLocation("exnihilocreatio:item_ore", "type=dust"),
                    new ModelResourceLocation("exnihilocreatio:item_ore", "type=ingot"));
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ore, ORES);
        }
    }

    public void registerToGameRegistry(IForgeRegistry<Item> itemRegistry) {
        for (ItemOre itemOre : itemOreRegistry) {
            itemRegistry.register(itemOre);
            OreDictionary.registerOre("ore" + StringUtils.capitalize(itemOre.getOre().getName()), new ItemStack(itemOre, 1, EnumOreSubtype.CHUNK.getMeta()));
            OreDictionary.registerOre("dust" + StringUtils.capitalize(itemOre.getOre().getName()), new ItemStack(itemOre, 1, EnumOreSubtype.DUST.getMeta()));
            OreDictionary.registerOre("piece" + StringUtils.capitalize(itemOre.getOre().getName()), new ItemStack(itemOre, 1, EnumOreSubtype.PIECE.getMeta()));

            if (itemOre.isRegisterIngot())
                OreDictionary.registerOre("ingot" + StringUtils.capitalize(itemOre.getOre().getName()), new ItemStack(itemOre, 1, EnumOreSubtype.INGOT.getMeta()));
        }
    }

    public ItemOre getOreItem(String name) {
        for (ItemOre itemOre : itemOreRegistry) {
            if (itemOre.getOre().getName().equals(name)) {
                return itemOre;
            }
        }

        return null;
    }

    public boolean isRegistered(String name) {
        for (Ore ore : registry) {
            if (ore.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
