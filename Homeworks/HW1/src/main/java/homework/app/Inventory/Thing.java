package homework.app.Inventory;

import homework.app.Interface.IInfo;
import homework.app.Interface.IInventory;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс предметов в зоопарке (для будущего из условия)
 */
@Getter
public abstract class Thing implements IInventory, IInfo {
    @Setter
    private int invNumber;
    private final String invName;

    public Thing(int invNumber, String invName) {
        this.invNumber = invNumber;
        this.invName = invName;
    }
}
