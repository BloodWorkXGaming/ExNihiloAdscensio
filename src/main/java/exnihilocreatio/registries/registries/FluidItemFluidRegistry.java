package exnihilocreatio.registries.registries;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exnihilocreatio.json.CustomItemInfoJson;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.prefab.BaseRegistryList;
import exnihilocreatio.registries.types.FluidItemFluid;
import exnihilocreatio.util.ItemInfo;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import java.io.FileReader;
import java.util.List;

public class FluidItemFluidRegistry extends BaseRegistryList<FluidItemFluid> {

    public FluidItemFluidRegistry() {
        super(
                new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(ItemInfo.class, new CustomItemInfoJson())
                        .create(),
                ExNihiloRegistryManager.FLUID_ITEM_FLUID_DEFAULT_REGISTRY_PROVIDERS
        );
    }

    public void register(String inputFluid, ItemInfo reactant, String outputFluid, int amount) {
        registry.add(new FluidItemFluid(inputFluid, reactant, outputFluid, amount));
    }

    public void register(Fluid inputFluid, ItemInfo reactant, Fluid outputFluid, int amount) {
        registry.add(new FluidItemFluid(inputFluid.getName(), reactant, outputFluid.getName(), amount));
    }

    public boolean canFluidBeTransformedWithThisItem(Fluid fluid, ItemStack stack) {
        ItemInfo info = ItemInfo.getItemInfoFromStack(stack);

        for (FluidItemFluid transformer : registry) {
            if (fluid.getName().equals(transformer.getInputFluid()) && info.equals(transformer.getReactant()))
                return true;
        }
        return false;
    }

    public String getFluidForTransformation(Fluid fluid, ItemStack stack) {
        ItemInfo info = ItemInfo.getItemInfoFromStack(stack);

        for (FluidItemFluid transformer : registry) {
            if (fluid.getName().equals(transformer.getInputFluid()) && info.equals(transformer.getReactant())) {
                return transformer.getOutput();
            }
        }

        return null;
    }

    public int getAmountFromTransformation(Fluid fluid, ItemStack stack) {
        ItemInfo info = ItemInfo.getItemInfoFromStack(stack);

        for (FluidItemFluid transformer : registry) {
            if (fluid.getName().equals(transformer.getInputFluid()) && info.equals(transformer.getReactant())) {
                return transformer.getAmount();
            }
        }

        return 0;
    }

    @Override
    protected void registerEntriesFromJSON(FileReader fr) {
        List<FluidItemFluid> gsonInput = gson.fromJson(fr, new TypeToken<List<FluidItemFluid>>() {
        }.getType());
        registry.addAll(gsonInput);
    }
}
