/*
 * Copyright (C) 2019 Azortis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.azortis.azortislib.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public class PaginatedGUI implements InventoryHolder, Comparable, Cloneable {
    private Inventory inv;
    private Number page;
    private Map<Integer, Button> buttonMap = new HashMap<>();

    public void setInventory(Inventory i) {
        inv = i;
    }

    public Button getButton(int slot) {
        return buttonMap.get(slot);
    }

    public void addButton(int slot, Button b) {
        inv.addItem(b);
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
        if (o instanceof PaginatedGUI && page != null) {
            return Double.compare(((PaginatedGUI) o).getPage().doubleValue(), page.doubleValue());
        }
        return -1;
    }

    @Override
    public PaginatedGUI clone() throws CloneNotSupportedException {
        PaginatedGUI gui = (PaginatedGUI) super.clone();
        gui.setInventory(inv);
        gui.setPage(page);
        for(int i : buttonMap.keySet()) {
            gui.addButton(i, buttonMap.get(i).clone());
        }
        return gui;
    }
}
