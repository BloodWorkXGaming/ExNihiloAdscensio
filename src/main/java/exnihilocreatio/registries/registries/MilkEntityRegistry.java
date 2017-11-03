package exnihilocreatio.registries.registries;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exnihilocreatio.json.CustomBlockInfoJson;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.registries.prefab.BaseRegistryList;
import exnihilocreatio.registries.types.Milkable;
import exnihilocreatio.util.BlockInfo;
import net.minecraft.entity.Entity;
import net.minecraftforge.fluids.Fluid;

import java.io.FileReader;
import java.util.List;

public class MilkEntityRegistry extends BaseRegistryList<Milkable> {
    public MilkEntityRegistry() {
        super(
                new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(BlockInfo.class, new CustomBlockInfoJson())
                    .create(),
                ExNihiloRegistryManager.MILK_ENTITY_DEFAULT_REGISTRY_PROVIDERS
        );
    }

    public void register(Entity entityOnTop, Fluid result, int amount, int coolDown){
        registry.add(new Milkable(entityOnTop.getName(), result.getName(), amount, coolDown));
    }

    public void register(String entityOnTop, String result, int amount, int coolDown){
        registry.add(new Milkable(entityOnTop, result, amount, coolDown));
    }

    public boolean isValidRecipe(Entity entityOnTop){
        if(entityOnTop == null){
            return false;
        }
        return this.isValidRecipe(entityOnTop.getName());
    }

    public boolean isValidRecipe(String entityOnTop){
        if(entityOnTop == null){
            return false;
        }
        for(Milkable milk : registry){
            if(milk.getEntityOnTop().equals(entityOnTop)){
                return true;
            }
        }
        return false;
    }

    public Milkable getMilkable(Entity entityOnTop){
        // Returns the entire milkable object instead of having use multiple functions
        if(entityOnTop == null){
            return null;
        }
        for(Milkable milk : registry){
            if(milk.getEntityOnTop().equals(entityOnTop.getName())){
                return milk;
            }
        }
        return null;
    }

    public String getResult(Entity entityOnTop){
        for(Milkable milk : registry){
            if(milk.getEntityOnTop().equals(entityOnTop.getName())){
                return milk.getResult();
            }
        }
        return null;
    }

    public int getAmount(Entity entityOnTop){
        for(Milkable milk : registry){
            if(milk.getEntityOnTop().equals(entityOnTop.getName())){
                return milk.getAmount();
            }
        }
        return 0;
    }

    public int getCoolDown(Entity entityOnTop){
        for(Milkable milk : registry){
            if(milk.getEntityOnTop().equals(entityOnTop.getName())){
                return milk.getCoolDown();
            }
        }
        return 0;
    }

    @Override
    public void registerEntriesFromJSON(FileReader fr) {
        List<Milkable> gsonInput = gson.fromJson(fr, new TypeToken<List<Milkable>>() {
        }.getType());
        registry.addAll(gsonInput);
    }
}
