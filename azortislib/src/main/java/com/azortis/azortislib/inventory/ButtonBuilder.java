package com.azortis.azortislib.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ButtonBuilder {
    private Button stack;


    public ButtonBuilder(Button stack){
        this.stack = stack;
    }


    public static ButtonBuilder start(Material material){
        return new ButtonBuilder(new Button(material));
    }


    public ButtonBuilder name(String name){
        ItemMeta stackMeta = stack.getItemMeta();
        stackMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        stack.setItemMeta(stackMeta);
        return this;
    }


    public ButtonBuilder amount(int amount){
        stack.setAmount(amount);
        return this;
    }


    public ButtonBuilder lore(String... lore){
        for(int i = 0; i < lore.length; i++){
            lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
        }

        ItemMeta stackMeta = stack.getItemMeta();
        stackMeta.setLore(Arrays.asList(lore));
        stack.setItemMeta(stackMeta);
        return this;
    }


    public ButtonBuilder lore(List<String> lore){
        for(int i = 0; i < lore.size(); i++){
            lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }

        ItemMeta stackMeta = stack.getItemMeta();
        stackMeta.setLore(lore);
        stack.setItemMeta(stackMeta);
        return this;
    }


    public ButtonBuilder data(short data){
        stack.setDurability(data);
        return this;
    }


    public ButtonBuilder durability(short durability){
        stack.setDurability(durability);
        return this;
    }

    public Button build(){
        return stack;
    }

}
