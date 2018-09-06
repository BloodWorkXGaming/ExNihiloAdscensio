package exnihilocreatio.json;

import com.google.gson.*;
import exnihilocreatio.util.ItemInfo;
import exnihilocreatio.util.LogUtil;
import net.minecraft.item.Item;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Type;

public class CustomItemInfoJson implements JsonDeserializer<ItemInfo>, JsonSerializer<ItemInfo> {
    @Override
    public JsonElement serialize(ItemInfo src, Type typeOfSrc, JsonSerializationContext context) {
        if(src.getNbt() == null || src.getNbt().hasNoTags())
            return new JsonPrimitive(src.getItem().getRegistryName().toString() + ":" + src.getMeta());
        JsonObject json = new JsonObject();
        json.add("name", context.serialize(src.getItem().getRegistryName().toString()));
        json.add("meta", context.serialize((src.getMeta())));
        json.add("nbt", context.serialize((src.getNbt().toString())));

        return json;
    }

    @Override
    public ItemInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
            String name = json.getAsString();
            return new ItemInfo(name);
        } else {
            JsonHelper helper = new JsonHelper(json);

            String name = helper.getString("name");
            int meta = helper.getNullableInteger("meta", 0);

            Item item = Item.getByNameOrId(name);

            NBTTagCompound nbt = new NBTTagCompound();
            if(json.getAsJsonObject().has("nbt")){
                try{
                    nbt = JsonToNBT.getTagFromJson(json.getAsJsonObject().get("nbt").toString());
                } catch (NBTException e){
                    LogUtil.error("Could not convert JSON to NBT: "+json.getAsJsonObject().get("nbt").toString());
                }
            }

            if (item == null) {
                LogUtil.error("Error parsing JSON: Invalid Item: " + json.toString());
                LogUtil.error("This may result in crashing or other undefined behavior");
                return ItemInfo.EMPTY;
            }

            return new ItemInfo(item, meta, nbt);
        }
    }
}
