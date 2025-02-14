package homework.app.Inventory;

import homework.app.Interface.IInventory;
import lombok.Getter;
@Getter
public class Thing implements IInventory {
    private final int invNumber;
    private final String invName;

    public Thing(int invNumber, String invName) {
        this.invNumber = invNumber;
        this.invName = invName;
    }
}
