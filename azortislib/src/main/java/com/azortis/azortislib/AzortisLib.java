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

package com.azortis.azortislib;

import com.azortis.azortislib.command.CommandManager;
import com.azortis.azortislib.craftbukkit.CraftManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class AzortisLib {

    private Plugin plugin;
    private String pluginName;
    private Logger logger;

    private MinecraftVersion minecraftVersion;

    //Managers
    private CraftManager craftManager;
    private CommandManager commandManager;

    public AzortisLib(Plugin plugin, String pluginName, String loggerPrefix){
        this.plugin = plugin;
        this.pluginName = pluginName;
        this.logger = new Logger(loggerPrefix);
    }

    public MinecraftVersion getMinecraftVersion(){
        if(minecraftVersion == null){
            String rawMinecraftVersionString = Bukkit.getServer().getClass().getName();
            String minecraftVersionString;
            rawMinecraftVersionString = rawMinecraftVersionString.substring("org.bukkit.craftbukkit.".length());
            minecraftVersionString = rawMinecraftVersionString.substring(0, rawMinecraftVersionString.length() - ".CraftServer".length());
            if(minecraftVersionString.equals("v1_13_R2")){
                minecraftVersion = MinecraftVersion.v1_13_R2;
            }else if(minecraftVersionString.equals("v1_14_R1")){
                //TODO add 1.14 support once it releases
            }
        }
        return minecraftVersion;
    }

    public Logger getLogger(){
        return logger;
    }

    //Manager getters.

    public CraftManager getCraftManager(){
        if(craftManager == null){
            craftManager = new CraftManager(getMinecraftVersion().getVersionString());
        }
        return craftManager;
    }

    public CommandManager getCommandManager(){
        if(commandManager == null){
            if(craftManager == null){
                craftManager = new CraftManager(getMinecraftVersion().getVersionString()); // Required to fetch commandMap
            }
            commandManager = new CommandManager(this);
            return commandManager;
        }
        return commandManager;
    }



}
