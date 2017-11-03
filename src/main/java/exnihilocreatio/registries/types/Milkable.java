package exnihilocreatio.registries.types;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Milkable {

    @Getter
    private String entityOnTop;

    @Getter
    private String result;

    @Getter
    private int amount;

    @Getter
    private int coolDown;

    public Milkable(String entityOnTop, String result, int amount, int coolDown){
        this.entityOnTop = entityOnTop;
        this.result = result;
        this.amount = amount;
        this.coolDown = coolDown;
    }
}
