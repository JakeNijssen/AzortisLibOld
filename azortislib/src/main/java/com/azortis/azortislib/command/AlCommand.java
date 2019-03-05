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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("all")
public class AlCommand {

    //Provided via constructor
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private Plugin plugin;

    //Provided via setter methods
    private IAlCommandExecutor executor;
    private IAlTabCompleter tabCompleter;
    private Command command;
    private HashMap<String, AlSubCommand> subCommands;


    public AlCommand(String name, String description, String usage, List<String> aliases, Plugin plugin){
        this.name = name;
        if (description != null) this.description = description;
        if (usage != null) this.usage = usage;
        if (aliases != null) this.aliases = aliases;
        if(plugin != null) {
            this.command = new BukkitPluginCommand(name, this, plugin);
            if (description != null) this.command.setDescription(description);
            if (usage != null) this.command.setUsage(usage);
            if (aliases != null) this.command.setAliases(aliases);
            this.plugin = plugin;
        }else{
            this.command = new BukkitCommand(name, this);
            if(description != null)this.command.setDescription(description);
            if(usage != null)this.command.setUsage(usage);
            if(aliases != null)this.command.setAliases(aliases);
        }
    }

    private class BukkitCommand extends Command {
        private AlCommand parent;

        BukkitCommand(String name, AlCommand parent){
            super(name);
            this.parent = parent;
        }

        @Override
        public boolean execute(CommandSender commandSender, String alias, String[] args) {
            if(parent.subCommands != null) {
                if (parent.subCommands.containsKey(args[0])) {
                    AlSubCommand subCommand = subCommands.get(args[0]);
                    List<String> argsList = Arrays.asList(args);
                    argsList.remove(args[0]);
                    String[] subArgs = argsList.toArray(new String[0]);
                    return subCommand.execute(commandSender, alias, subArgs);
                }
            }
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
            if(parent.subCommands != null) {
                if (parent.subCommands.containsKey(args[0])) {
                    AlSubCommand subCommand = subCommands.get(args[0]);
                    List<String> argsList = Arrays.asList(args);
                    argsList.remove(args[0]);
                    String[] subArgs = argsList.toArray(new String[0]);
                    return subCommand.execute(commandSender, alias, subArgs);
                }
            }
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

    public void addSubCommand(String name, IAlSubCommandExecutor executor){
        if(subCommands == null)subCommands = new HashMap<String, AlSubCommand>();
        subCommands.put(name, new AlSubCommand(name, this, executor));
    }

    public void addSubCommand(AlSubCommand subCommand){
        if(subCommands == null)subCommands = new HashMap<String, AlSubCommand>();
        subCommands.put(subCommand.getName(), subCommand);
    }

    public void addSubCommands(AlSubCommand... subCommands){
        if(this.subCommands == null)this.subCommands = new HashMap<String, AlSubCommand>();
        for (AlSubCommand subCommand : subCommands) {
            this.subCommands.put(subCommand.getName(), subCommand);
        }
    }
}
