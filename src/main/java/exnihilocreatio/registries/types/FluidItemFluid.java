package exnihilocreatio.registries.types;

import exnihilocreatio.util.ItemInfo;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class FluidItemFluid {

    @Getter
    private String inputFluid;

    @Getter
    private ItemInfo reactant;

    @Getter
    private String output;

    @Getter
    private int amount;
}
