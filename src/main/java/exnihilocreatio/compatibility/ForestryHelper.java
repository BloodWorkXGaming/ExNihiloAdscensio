package exnihilocreatio.compatibility;

import exnihilocreatio.util.ItemInfo;
import exnihilocreatio.util.LogUtil;
import exnihilocreatio.util.StackInfo;
import forestry.api.apiculture.*;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import net.minecraft.item.ItemStack;

public class ForestryHelper {

    public static StackInfo getDroneInfo(String species){
        return new ItemInfo(getBeeStack(species, EnumBeeType.DRONE, false));
    }

    public static StackInfo getPristineInfo(String species){
        return new ItemInfo(getBeeStack(species, EnumBeeType.PRINCESS, true));
    }

    public static StackInfo getIgnobleInfo(String species){
        return new ItemInfo(getBeeStack(species, EnumBeeType.PRINCESS, false));
    }

    public static ItemStack getBeeStack(String speciesName, EnumBeeType beeType, boolean isPristine){
        IAlleleBeeSpecies species = null;
        for(String uid : AlleleManager.alleleRegistry.getRegisteredAlleles().keySet()){
            if(uid.equals(speciesName)){
                IAllele allele = AlleleManager.alleleRegistry.getAllele(uid);
                if(allele instanceof IAlleleBeeSpecies){
                    species = (IAlleleBeeSpecies) allele;
                    break;
                }
            }
        }
        if(species == null){
            for (IAllele allele : AlleleManager.alleleRegistry.getRegisteredAlleles().values()){
                if(allele instanceof IAlleleBeeSpecies && allele.getAlleleName().equals(speciesName)){
                    species = (IAlleleBeeSpecies) allele;
                    break;
                }
            }
        }
        if(species == null){
            LogUtil.error("Could not find bee species: "+speciesName);
            return ItemStack.EMPTY;
        }
        IAllele[] template = BeeManager.beeRoot.getTemplate(species);
        IBeeGenome genome = BeeManager.beeRoot.templateAsGenome(template);
        IBee bee = BeeManager.beeRoot.getBee(genome);

        if(beeType.equals(EnumBeeType.PRINCESS)){
            bee.setIsNatural(isPristine);
        }

        return BeeManager.beeRoot.getMemberStack(bee, beeType);
    }
}
