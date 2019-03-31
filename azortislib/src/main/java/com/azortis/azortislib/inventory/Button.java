package com.azortis.azortislib.inventory;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Button extends ItemStack {
    private ButtonClick clickEvent;

    public Button(Material type) {
        super(type);
    }


    /*
     * Sets the code that is run when clicked in the inventory by using the ButtonClick interface
     * @param ButtonClick
     */
    public void setClick(ButtonClick c) {
        this.clickEvent = c;
    }

    /*
     * Called when the button is clicked
     * @param InventoryClickEvent
     */
    public void click(InventoryClickEvent event) {
        clickEvent.buttonClick(event);
    }

}
