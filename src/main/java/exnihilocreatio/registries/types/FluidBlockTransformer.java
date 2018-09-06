package exnihilocreatio.registries.types;

import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.EntityInfo;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.minecraft.item.crafting.Ingredient;

@AllArgsConstructor
@EqualsAndHashCode
public class FluidBlockTransformer {

    @Getter
    private String fluidName;

    @Getter
    private Ingredient input;

    @Getter
    private BlockInfo output;

    @Getter
    private EntityInfo toSpawn;

    @Getter
    private int spawnCount;

    @Getter
    private int spawnRange;

    public FluidBlockTransformer(String fluidName, Ingredient input, BlockInfo output){
        this(fluidName, input, output, (String) null, 0, 0);
    }

    public FluidBlockTransformer(String fluidName, Ingredient input, BlockInfo output, String entityName){
        this(fluidName, input, output, entityName, 4, 4);
    }

    public FluidBlockTransformer(String fluidName, Ingredient input, BlockInfo output, String entityName, int spawnCount, int spawnRange){
        this.fluidName = fluidName;
        this.input = input;
        this.output = output;
        this.toSpawn = entityName == null ? EntityInfo.EMPTY : new EntityInfo(entityName);
        this.spawnCount = spawnCount;
        this.spawnRange = spawnRange;
    }

}
