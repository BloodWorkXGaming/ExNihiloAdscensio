package exnihilocreatio.modules.tconstruct.tools;

import exnihilocreatio.ExNihiloCreatio;
import exnihilocreatio.items.tools.ICrook;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.StringUtils;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.AoeToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;

import javax.annotation.Nullable;
import java.util.List;

public class TiCrook extends AoeToolCore implements ICrook {
    public TiCrook() {
        this(PartMaterialType.handle(TinkerTools.toolRod),
                PartMaterialType.head(TinkerTools.toolRod),
                PartMaterialType.extra(TinkerTools.toolRod),
                PartMaterialType.handle(TinkerTools.toolRod));

    }

    public TiCrook(PartMaterialType... requiredComponents) {
        super(requiredComponents);

        addCategory(Category.HARVEST);

        this.setHarvestLevel("crook", 0);

        this.setRegistryName("crook_tconstruct");
        this.setTranslationKey(ExNihiloCreatio.MODID+".crook_tconstruct");
    }

    @Override
    public float damagePotential() {
        return 0.5f;
    }

    @Override
    public double attackSpeed() {
        return 3f;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState state) {
        if(StringUtils.isNullOrEmpty(toolClass) || state == null || ToolHelper.isBroken(stack))
            return -1;
        if(isEffective(state))
            return ToolHelper.getHarvestLevelStat(stack);
        return super.getHarvestLevel(stack, toolClass, player, state);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(this.isInCreativeTab(tab)) {
            this.addDefaultSubItems(subItems, null, null, null);
        }
    }


    @Override
    public boolean isEffective(IBlockState state) {
        return ExNihiloRegistryManager.CROOK_REGISTRY.isRegistered(state);
    }

    @Override
    protected ToolNBT buildTagData(List<Material> materials) {
        HandleMaterialStats handle = materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE);
        HeadMaterialStats head = materials.get(1).getStatsOrUnknown(MaterialTypes.HEAD);
        ExtraMaterialStats extra = materials.get(2).getStatsOrUnknown(MaterialTypes.EXTRA);
        HandleMaterialStats handle2 = materials.get(3).getStatsOrUnknown(MaterialTypes.HANDLE);

        ToolNBT data = new ToolNBT();
        data.head(head);
        data.extra(extra);
        data.handle(handle, handle2);

        return data;
    }

    @Override
    public boolean isCrook(ItemStack stack) {
        // Don't consider a broken tool a valid crook.
        return (stack.getItem() instanceof TiCrook) && !ToolHelper.isBroken(stack);
    }
}
