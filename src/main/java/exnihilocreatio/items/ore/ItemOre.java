package exnihilocreatio.items.ore;

import exnihilocreatio.ExNihiloCreatio;
import exnihilocreatio.util.Data;
import exnihilocreatio.util.IHasModel;
import exnihilocreatio.util.IHasSpecialRegistry;
import lombok.Getter;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public class ItemOre extends Item implements IHasModel, IHasSpecialRegistry {

    @Getter
    private boolean registerIngot;

    @Getter
    private Ore ore;

    public ItemOre(Ore ore) {
        super();

        this.ore = ore;
        registerIngot = ore.getResult() == null;
        setUnlocalizedName(ExNihiloCreatio.MODID + ".ore." + ore.getName());
        setRegistryName("item_ore_" + StringUtils.lowerCase(ore.getName()));
        setCreativeTab(ExNihiloCreatio.tabExNihilo);
        setHasSubtypes(true);

        Data.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@Nonnull CreativeTabs tab, NonNullList<ItemStack> list) {
        if (this.isInCreativeTab(tab)) {
            list.add(new ItemStack(this, 1, 0)); //Piece
            list.add(new ItemStack(this, 1, 1)); //Chunk
            list.add(new ItemStack(this, 1, 2)); //Dust
            if (registerIngot)
                list.add(new ItemStack(this, 1, 3)); //Ingot
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel(ModelRegistryEvent e) {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("exnihilocreatio:item_ore", "type=piece"));
        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation("exnihilocreatio:item_ore", "type=hunk"));
        ModelLoader.setCustomModelResourceLocation(this, 2, new ModelResourceLocation("exnihilocreatio:item_ore", "type=dust"));
        if (registerIngot)
            ModelLoader.setCustomModelResourceLocation(this, 3, new ModelResourceLocation("exnihilocreatio:item_ore", "type=ingot"));
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String name = ore.getName();
        String pre = "";
        switch (stack.getItemDamage()) {
            case 0:
                pre = "orepiece";
                break;
            case 1:
                // TODO during the next big version change, fix this typo.
                pre = "orehunk";
                break;
            case 2:
                pre = "oredust";
                break;
            case 3:
                pre = "oreingot";
                break;
            default:
                break;
        }
        return (StringUtils.capitalize(name) + " " + I18n.translateToLocal(pre + ".name")).trim();
    }

}
