package exnihilocreatio.util;

import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

public class ItemInfo implements StackInfo {

    public static final ItemInfo EMPTY = new ItemInfo(ItemStack.EMPTY);

    @Getter
    @Nonnull
    private Item item;

    private int meta = 0;

    @Getter
    private NBTTagCompound nbt = new NBTTagCompound();

    @Getter
    private boolean isWildcard = false;

    public ItemInfo(@Nonnull Item item) {
        this.item = item;
        checkWildcard();
    }

    public ItemInfo(@Nonnull Item item, int meta) {
        this.item = item;
        if (meta == -1 || meta == OreDictionary.WILDCARD_VALUE) {
            this.isWildcard = true;
        }
        else {
            this.meta = meta;
            checkWildcard();
        }
    }

    public ItemInfo(@Nonnull ItemStack stack) {
        this.item = stack.getItem();
        this.meta = stack.getItemDamage();
        this.nbt = stack.getTagCompound();
        checkWildcard();
    }

    public ItemInfo(@Nonnull Block block) {
        this.item = Item.getItemFromBlock(block);
        checkWildcard();
    }

    public ItemInfo(@Nonnull Block block, int blockMeta) {
        this(block, blockMeta, new NBTTagCompound());
    }

    public ItemInfo(@Nonnull Block block, int blockMeta, @Nonnull NBTTagCompound tag) {
        this(Item.getItemFromBlock(block), blockMeta, tag);
    }

    public ItemInfo(@Nonnull Item item, int meta, @Nonnull NBTTagCompound tag) {
        this.item = item;
        this.nbt = tag.copy();
        if (this.item == Items.AIR){
            this.isWildcard = true;
        }
        else {
            if (meta == -1 || meta == OreDictionary.WILDCARD_VALUE) {
                this.isWildcard = true;
            } else {
                this.meta = meta;
                checkWildcard();
            }
        }
    }

    public ItemInfo(@Nonnull Item item, int meta, @Nonnull String tag) {
        this.item = item;
        if (this.item == Items.AIR){
            this.isWildcard = true;
        }
        else {
            if (meta == -1 || meta == OreDictionary.WILDCARD_VALUE) {
                this.isWildcard = true;
            } else {
                this.meta = meta;
                checkWildcard();
            }
        }
        try{
            this.nbt = JsonToNBT.getTagFromJson(tag);
        } catch (NBTException e){
            LogUtil.error("Could not parse NBTTag: " + tag);
            this.nbt = new NBTTagCompound();
        }
    }

    public ItemInfo(@Nonnull String string) {
        if (string.isEmpty() || string.length() < 2) {
            this.item = Items.AIR;
            this.isWildcard = true;
            return;
        }
        String[] split = string.split(":");

        Item item = null;
        int meta = 0;

        switch (split.length) {
            case 1:
                item = Item.getByNameOrId("minecraft:" + split[0]);
                break;
            case 2:
                try {
                    meta = split[1].equals("*") ? -1 : Integer.parseInt(split[1]);
                    item = Item.getByNameOrId("minecraft:" + split[0]);
                } catch (NumberFormatException e) {
                    this.isWildcard = true;
                    meta = 0;
                    item = Item.getByNameOrId(split[0] + ":" + split[1]);
                }
                break;
            case 3:
                try {
                    meta = split[2].equals("*") ? -1 : Integer.parseInt(split[2]);
                    item = Item.getByNameOrId(split[0] + ":" + split[1]);
                } catch (NumberFormatException e) {
                    meta = 0;
                    this.isWildcard = true;
                }
                break;
            default:
                this.item = Items.AIR;
                this.isWildcard = true;
                return;
        }

        if (item == null){
            this.item = Items.AIR;
            this.isWildcard = true;
        }
        else {
            this.item = item;
            if (meta == -1 || meta == OreDictionary.WILDCARD_VALUE) {
                this.isWildcard = true;
            } else {
                this.meta = meta;
                checkWildcard();
            }
        }
    }

    public ItemInfo(@Nonnull IBlockState state) {
        item = Item.getItemFromBlock(state.getBlock());
        meta = state.getBlock().getMetaFromState(state);
    }

    public static ItemInfo readFromNBT(NBTTagCompound tag) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("item")));
        int meta = tag.getInteger("meta");
        if(tag.hasKey("nbt")){
            return item == null ? EMPTY : new ItemInfo(item, meta, tag.getCompoundTag("nbt"));
        }
        return item == null ? EMPTY : new ItemInfo(item, meta);
    }


    private void checkWildcard(){
        // This checks if the item has sub items or not.
        // If not, accept any item that matches this, otherwise
        // Only accept items with meta 0
        NonNullList<ItemStack> subItems = NonNullList.create();
        item.getSubItems(item.getCreativeTab() == null ? CreativeTabs.SEARCH : item.getCreativeTab(), subItems);
        if (subItems.size() <= 1)
            this.isWildcard = true;
    }

    //StackInfo

    @Override
    public String toString() {
        return ForgeRegistries.ITEMS.getKey(item) + (meta == 0 ? "" : (":" + meta));
    }

    @Nonnull
    @Override
    public ItemStack getItemStack() {
        if(item == Items.AIR)
            return ItemStack.EMPTY;
        ItemStack stack = new ItemStack(item, 1, meta);
        stack.setTagCompound(nbt);
        return stack;
    }

    @Override
    public boolean hasBlock() {
        return item instanceof ItemBlock;
    }

    @Nonnull
    @Override
    public Block getBlock() {
        return Block.getBlockFromItem(item);
    }

    @Nonnull
    @Override
    public IBlockState getBlockState() {
        if (item == Items.AIR)
            return Blocks.AIR.getDefaultState();
        try {
            return Block.getBlockFromItem(item).getStateFromMeta(meta);
        } catch (Exception e){
            return Block.getBlockFromItem(item).getDefaultState();
        }
    }

    @Override
    public int getMeta() {
        return isWildcard ? -1 : meta;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setString("item", ForgeRegistries.ITEMS.getKey(item) == null ? "" : ForgeRegistries.ITEMS.getKey(item).toString());
        tag.setInteger("meta", meta);
        if(!this.nbt.hasNoTags()){
            tag.setTag("nbt", this.nbt);
        }
        return tag;
    }

    @Override
    public boolean isValid() {
        return this.item != Items.AIR && meta <= OreDictionary.WILDCARD_VALUE;
    }

    @Override
    public int hashCode() {
        return this.item.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (isWildcard){
            if (obj instanceof ItemInfo)
                return ItemStack.areItemsEqualIgnoreDurability(((ItemInfo) obj).getItemStack(), getItemStack());
            else if (obj instanceof ItemStack)
                return ItemStack.areItemsEqualIgnoreDurability((ItemStack) obj, getItemStack());
            else if (obj instanceof BlockInfo)
                return ItemStack.areItemsEqualIgnoreDurability(((BlockInfo) obj).getItemStack(), getItemStack());
            else if (obj instanceof Block) {
                BlockInfo block = new BlockInfo((Block) obj);
                return ItemStack.areItemsEqualIgnoreDurability(block.getItemStack(), getItemStack());
            } else if (obj instanceof Item) {
                ItemInfo item = new ItemInfo((Item) obj);
                return ItemStack.areItemsEqualIgnoreDurability(item.getItemStack(), getItemStack());
            }
        }
        else {
            if (obj instanceof ItemInfo)
                return ItemStack.areItemStacksEqual(((ItemInfo) obj).getItemStack(), getItemStack());
            else if (obj instanceof ItemStack)
                return ItemStack.areItemStacksEqual((ItemStack) obj, getItemStack());
            else if (obj instanceof BlockInfo)
                return ItemStack.areItemStacksEqual(((BlockInfo) obj).getItemStack(), getItemStack());
            else if (obj instanceof Block) {
                BlockInfo block = new BlockInfo((Block) obj);
                return ItemStack.areItemStacksEqual(block.getItemStack(), getItemStack());
            } else if (obj instanceof Item) {
                ItemInfo item = new ItemInfo((Item) obj);
                return ItemStack.areItemStacksEqual(item.getItemStack(), getItemStack());
            }
        }
        return false;
    }
}
