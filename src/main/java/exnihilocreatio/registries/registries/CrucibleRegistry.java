package exnihilocreatio.registries.registries;

import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exnihilocreatio.api.registries.ICrucibleRegistry;
import exnihilocreatio.compatibility.jei.crucible.CrucibleRecipe;
import exnihilocreatio.json.CustomBlockInfoJson;
import exnihilocreatio.json.CustomIngredientJson;
import exnihilocreatio.json.CustomMeltableJson;
import exnihilocreatio.registries.ingredient.IngredientUtil;
import exnihilocreatio.registries.ingredient.OreIngredientStoring;
import exnihilocreatio.registries.manager.IDefaultRecipeProvider;
import exnihilocreatio.registries.registries.prefab.BaseRegistryMap;
import exnihilocreatio.registries.types.Meltable;
import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.LogUtil;
import exnihilocreatio.util.StackInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.io.FileReader;
import java.util.*;

public class CrucibleRegistry extends BaseRegistryMap<Ingredient, Meltable> implements ICrucibleRegistry {
    public CrucibleRegistry(List<? extends IDefaultRecipeProvider> defaultRecipeProviders) {
        super(
                new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(BlockInfo.class, CustomBlockInfoJson.INSTANCE)
                        .registerTypeAdapter(Ingredient.class, CustomIngredientJson.INSTANCE)
                        .registerTypeAdapter(OreIngredientStoring.class, CustomIngredientJson.INSTANCE)
                        .registerTypeAdapter(Meltable.class, CustomMeltableJson.INSTANCE)
                        .enableComplexMapKeySerialization()
                        .create(),
                new TypeToken<Map<Ingredient, Meltable>>() {
                }.getType(),
                defaultRecipeProviders
        );
    }

    public void register(@NotNull StackInfo item, @NotNull Fluid fluid, int amount) {
        register(item.getItemStack(), fluid, amount);
    }

    public void register(@NotNull StackInfo item, @NotNull Meltable meltable) {
        register(item.getItemStack(), meltable);
    }


    @Override
    public void register(@NotNull String name, @NotNull Fluid fluid, int amount, @NotNull BlockInfo block) {
        register(name, new Meltable(fluid.getName(), amount, block));
    }
    public void register(@NotNull ItemStack stack, @NotNull Fluid fluid, int amount) {
        register(stack, new Meltable(fluid.getName(), amount));
    }

    public void register(@NotNull ItemStack stack, @NotNull Meltable meltable) {
        if (stack.isEmpty() || !FluidRegistry.isFluidRegistered(meltable.getFluid())) return;
        if (registry.keySet().stream().anyMatch(ingredient -> ingredient.test(stack)))
            LogUtil.warn("Crucible entry for " + stack.getDisplayName() + " with meta " + stack.getMetadata() + " already exists, skipping.");
        else register(CraftingHelper.getIngredient(stack), meltable);
    }

    public void register(@NotNull String name, @NotNull Fluid fluid, int amount) {
        register(name, new Meltable(fluid.getName(), amount));
    }

    public void register(@NotNull String name, @NotNull Meltable meltable) {
        Ingredient ingredient = new OreIngredientStoring(name);
        if (!FluidRegistry.isFluidRegistered(meltable.getFluid()))
            return;

        if (registry.keySet().stream().anyMatch(entry -> IngredientUtil.ingredientEquals(entry, ingredient)))
            LogUtil.error("Crucible Ore Entry for " + name + " already exists, skipping.");
        else registry.put(ingredient, meltable);
    }

    public boolean canBeMelted(@NotNull ItemStack stack) {
        return registry.keySet().stream().anyMatch(entry -> entry.test(stack));
    }

    public boolean canBeMelted(@NotNull StackInfo info) {
        return canBeMelted(info.getItemStack());
    }

    @Nonnull
    public Meltable getMeltable(@NotNull ItemStack stack) {
        Ingredient ingredient = registry.keySet().stream().filter(entry -> entry.test(stack)).findFirst().orElse(null);

        if (ingredient != null) {
            return registry.get(ingredient);
        } else {
            return Meltable.Companion.getEMPTY();
        }
    }

    @Nonnull
    public Meltable getMeltable(@NotNull StackInfo info) {
        return getMeltable(info.getItemStack());
    }

    @Nonnull
    public Meltable getMeltable(@NotNull Item item, int meta) {
        return getMeltable(new ItemStack(item, meta));
    }

    @Override
    protected void registerEntriesFromJSON(FileReader fr) {
        Map<String, Meltable> gsonInput = gson.fromJson(fr, new TypeToken<Map<String, Meltable>>() {
        }.getType());

        gsonInput.forEach((key, value) -> {
            Ingredient ingredient = IngredientUtil.parseFromString(key);

            if (registry.keySet().stream().anyMatch(entry -> IngredientUtil.ingredientEquals(ingredient, entry)))
                LogUtil.error("Compost JSON Entry for " + Arrays.toString(ingredient.getMatchingStacks()) + " already exists, skipping.");

            registry.put(ingredient, value);
        });
    }

    @Override
    public Map<Ingredient, Meltable> getRegistry() {
        return registry;
    }

    @Override
    public List<CrucibleRecipe> getRecipeList() {
        List<CrucibleRecipe> recipes = Lists.newLinkedList();

        Map<Fluid, List<List<ItemStack>>> outputMap = new HashMap<>();
        for(Map.Entry<Ingredient, Meltable> entry: getRegistry().entrySet()){
            Fluid output = FluidRegistry.getFluid(entry.getValue().getFluid());
            Ingredient ingredient = entry.getKey();
            if(output == null || ingredient == null)
                continue;
            // Initialize new outputs
            if(!outputMap.containsKey(output)){
                List<List<ItemStack>> inputs = new ArrayList<>();
                outputMap.put(output, inputs);
            }
            // Collect all the potential itemstacks which match this ingredient
            List<ItemStack> inputs = new ArrayList<>();
            for(ItemStack match : ingredient.getMatchingStacks()){
                if(match.isEmpty() || match.getItem() == null)
                    continue;
                ItemStack input = match.copy();
                input.setCount((int) Math.max(Math.ceil(Fluid.BUCKET_VOLUME / entry.getValue().getAmount()), 1));
                inputs.add(input);
            }
            // Empty oredicts can result in 0 inputs.
            if(inputs.size() > 0)
                outputMap.get(output).add(inputs);
        }
        // Split the recipe up into "pages"
        for(Map.Entry<Fluid, List<List<ItemStack>>> entry : outputMap.entrySet()){
            for(int i = 0; i < entry.getValue().size(); i+=21){
                recipes.add(new CrucibleRecipe(entry.getKey(),
                        entry.getValue().subList(i,  Math.min(i+21, entry.getValue().size()))));
            }
        }

        return recipes;
    }
}
