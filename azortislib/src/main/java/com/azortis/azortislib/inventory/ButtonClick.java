package com.azortis.azortislib.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface ButtonClick {
    /*
     * The method called when a button is clicked.
     * @param InventoryClickEvent
     */
    void buttonClick(InventoryClickEvent e);
}
