/*
 * Copyright (C) 2019  Azortis
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

package com.azortis.azortislib.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.List;

@SuppressWarnings("all")
public class AlCommand {

    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private Plugin plugin;

    private IAlCommandExecutor executor;
    private IAlTabCompleter tabCompleter;
    private Command command;

    public AlCommand(String name, String description, String usage, List<String> aliases){
        this.command = new BukkitCommand(name, this);
        if(description != null)this.command.setDescription(description);
        if(usage != null)this.command.setUsage(usage);
        if(aliases != null)this.command.setAliases(aliases);
        this.name = name;
        if(description != null)this.description = description;
        if(usage != null)this.usage = usage;
        if(aliases != null)this.aliases = aliases;
    }

    public AlCommand(String name, String description, String usage, List<String> aliases, Plugin plugin){
        this.command = new BukkitPluginCommand(name, this, plugin);
        if(description != null)this.command.setDescription(description);
        if(usage != null)this.command.setUsage(usage);
        if(aliases != null)this.command.setAliases(aliases);
        this.name = name;
        if(description != null)this.description = description;
        if(usage != null)this.usage = usage;
        if(aliases != null)this.aliases = aliases;
        this.plugin = plugin;
    }

    private class BukkitCommand extends Command {
        private AlCommand parent;

        BukkitCommand(String name, AlCommand parent){
            super(name);
            this.parent = parent;
        }

        @Override
        public boolean execute(CommandSender commandSender, String alias, String[] args) {
            if(parent.executor != null) return parent.executor.onCommand(commandSender, parent, alias, args);
            //TODO Log no executor error.
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) {
            if(parent.tabCompleter != null) return parent.tabCompleter.tabComplete(sender, alias, args, location);
            return null;
        }

    }

    private class BukkitPluginCommand extends Command implements PluginIdentifiableCommand {
        private AlCommand parent;
        private Plugin plugin;

        BukkitPluginCommand(String name, AlCommand parent, Plugin plugin){
            super(name);
            this.parent = parent;
            this.plugin = plugin;
        }

        public Plugin getPlugin() {
            return plugin;
        }

        @Override
        public boolean execute(CommandSender commandSender, String alias, String[] args) {
            if(parent.executor != null) return parent.executor.onCommand(commandSender, parent, alias, args);
            //TODO Log no executor error.
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
            if(parent.tabCompleter != null) return parent.tabCompleter.tabComplete(sender, alias, args, location);
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public Plugin getPlugin() {
        if(plugin != null)return plugin;
        return null;
    }

    public Command getCommand() {
        return command;
    }

    public IAlCommandExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(IAlCommandExecutor executor) {
        this.executor = executor;
    }

    public IAlTabCompleter getTabCompleter() {
        return tabCompleter;
    }

    public void setTabCompleter(IAlTabCompleter tabCompleter) {
        this.tabCompleter = tabCompleter;
    }
}
