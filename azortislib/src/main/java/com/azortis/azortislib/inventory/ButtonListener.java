package com.azortis.azortislib.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ButtonListener implements Listener {
    public ButtonListener(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory().getHolder() instanceof PaginatedGUI) {
            ((PaginatedGUI) e.getInventory().getHolder()).getButton(e.getSlot()).click(e);
        }
    }

}
