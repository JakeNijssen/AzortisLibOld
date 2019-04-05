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

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MultiGUI implements GUI {
    private List<PaginatedGUI> pages = new ArrayList<>();

    public void addPage(List<PaginatedGUI> guis) {
        pages.addAll(guis);
        sort();
    }

    public void addPage(PaginatedGUI... gui) {
        pages.addAll(Arrays.asList(gui));
        sort();
    }


    public PaginatedGUI search(String name, boolean ignoreCase) {
        for (PaginatedGUI g : pages) {
            if (ignoreCase && g.getName().equalsIgnoreCase(name))
                return g;
            else if (g.getName().equals(name)) return g;
        }
        return null;
    }

    public void openPage(Player p, PaginatedGUI g) {
        p.openInventory(g.getInventory());
    }

    public PaginatedGUI search(Number page) {
        for (PaginatedGUI g : pages) {
            if (g.getPage().doubleValue() == page.doubleValue()) return g;
        }
        return null;
    }

    private void sort() {
        Collections.sort(pages);
    }

}
