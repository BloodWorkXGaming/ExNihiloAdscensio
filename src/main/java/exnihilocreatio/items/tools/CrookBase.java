package exnihilocreatio.items.tools;

import com.google.common.collect.Sets;
import exnihilocreatio.ExNihiloCreatio;
import exnihilocreatio.config.ModConfig;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.util.Data;
import exnihilocreatio.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import javax.annotation.Nullable;

public class CrookBase extends ItemTool implements ICrook, IHasModel {

    public CrookBase(String name, int maxUses, int miningLevel, ToolMaterial mat) {
        super(mat, Sets.newHashSet(new Block[]{}));

        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setMaxDamage(maxUses);
        this.setCreativeTab(ExNihiloCreatio.tabExNihilo);
        this.setHarvestLevel("crook", mat.getHarvestLevel());
        this.attackDamage = mat.getAttackDamage();

        if(!ModConfig.crooking.disableCrookCrafting)
            Data.ITEMS.add(this);
    }

    public CrookBase(String name, int maxUses, int miningLevel) {
        this(name, maxUses, miningLevel, ToolMaterial.WOOD);
    }

    @Override
    public boolean isCrook(ItemStack stack) {
        return true;
    }

    @Override
    public float getDestroySpeed(@Nullable ItemStack stack, IBlockState state) {
        return ExNihiloRegistryManager.CROOK_REGISTRY.isRegistered(state.getBlock()) ? this.efficiency : 1.0F;
    }

}
