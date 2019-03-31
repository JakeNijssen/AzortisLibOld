package com.azortis.azortislib.inventory;

import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public class PaginatedGUI implements InventoryHolder, Comparable, GUI {
    private Inventory inv;
    private String name;
    private Number page;
    private Map<Integer, Button> buttonMap = new HashMap<>();

    public void setInventory(Inventory i) {
        inv = i;
        name = ChatColor.stripColor(i.getName());
    }

    public Button getButton(int slot) {
        return buttonMap.get(slot);
    }

    public void addButton(int slot, Button b) {
        buttonMap.put(slot, b);
    }

    public Number getPage() {
        return page;
    }

    public void setPage(Number page) {
        this.page = page;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof PaginatedGUI) {
            return ((PaginatedGUI) o).getPage().doubleValue() >= page.doubleValue() ? 1: -1;
        }
        return -1;
    }

    public String getName() {
        return name;
    }
}
