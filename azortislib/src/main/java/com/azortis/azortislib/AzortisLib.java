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
import com.azortis.azortislib.database.DatabaseManager;
import com.azortis.azortislib.file.FileManager;
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
    private DatabaseManager databaseManager;
    private FileManager fileManager;

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
            minecraftVersionString = rawMinecraftVersionString.substring(0, rawMinecraftVersionString.length() - ".AlAlCraftServer".length());
            if(minecraftVersionString.equals("v1_13_R2")){
                minecraftVersion = MinecraftVersion.v1_13_R2;
                return minecraftVersion;
            }else if(minecraftVersionString.equals("v1_14_R1")){
                minecraftVersion = MinecraftVersion.v1_14_R1;
                return minecraftVersion;
            }
        }
        return minecraftVersion;
    }

    public Plugin getPlugin() { return plugin; }

    public Logger getLogger(){
        return logger;
    }

    public void close(){
        if(this.databaseManager != null){
            this.databaseManager.closeConnection();
        }
    }

    //Manager getters.

    public CraftManager getCraftManager(){
        if(craftManager == null){
            this.craftManager = new CraftManager(getMinecraftVersion().getVersionString());
        }
        return this.craftManager;
    }

    public CommandManager getCommandManager(){
        if(commandManager == null){
            getCraftManager(); // To make sure it isn't empty
            commandManager = new CommandManager(this);
            return commandManager;
        }
        return commandManager;
    }

    public DatabaseManager getDatabaseManager(){
        if(databaseManager == null){
            databaseManager = new DatabaseManager(this);
            return databaseManager;
        }
        return databaseManager;
    }

    public FileManager getFileManager(){
        if(fileManager == null){
            fileManager = new FileManager(this);
            return fileManager;
        }
        return fileManager;
    }

}
