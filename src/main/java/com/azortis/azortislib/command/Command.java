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

package com.azortis.azortislib.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("all")
public class Command {

    //Provided via constructor
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private Plugin plugin;

    //Provided via setter methods
    private ICommandExecutor executor;
    private ITabCompleter tabCompleter;
    private org.bukkit.command.Command bukkitCommand;
    private HashMap<String, SubCommand> subCommands;
    private HashMap<String, AliasFunction> aliasFunctions;


    public Command(String name, String description, String usage, List<String> rawAliases, Plugin plugin){
        this.name = name;
        if (description != null) this.description = description;
        if (usage != null) this.usage = usage;
        if(rawAliases != null){
            aliases = new ArrayList<String>();
            for (String alias : rawAliases) {
                if(!alias.contains("-f")){
                    aliases.add(alias);
                    break;
                }
                if(aliasFunctions == null)aliasFunctions = new HashMap<String, AliasFunction>();
                AliasFunction aliasFunction = new AliasFunction(alias);
                aliasFunctions.put(aliasFunction.getAlias(), aliasFunction);
                aliases.add(aliasFunction.getAlias());
            }
        }
        if(plugin != null) {
            this.bukkitCommand = new BukkitPluginCommand(name, this, plugin);
            if (description != null) this.bukkitCommand.setDescription(description);
            if (usage != null) this.bukkitCommand.setUsage(usage);
            if (aliases != null) this.bukkitCommand.setAliases(aliases);
            this.plugin = plugin;
        }else{
            this.bukkitCommand = new BukkitCommand(name, this);
            if(description != null)this.bukkitCommand.setDescription(description);
            if(usage != null)this.bukkitCommand.setUsage(usage);
            if(aliases != null)this.bukkitCommand.setAliases(aliases);
        }
    }

    private class BukkitCommand extends org.bukkit.command.Command {
        private Command parent;

        BukkitCommand(String name, Command parent){
            super(name);
            this.parent = parent;
        }

        @Override
        public boolean execute(CommandSender commandSender, String alias, String[] args) {
            if(parent.subCommands != null) {
                if (parent.subCommands.containsKey(args[0])) {
                    SubCommand subCommand = subCommands.get(args[0]);
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

    private class BukkitPluginCommand extends org.bukkit.command.Command implements PluginIdentifiableCommand {
        private Command parent;
        private Plugin plugin;

        BukkitPluginCommand(String name, Command parent, Plugin plugin){
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
                    SubCommand subCommand = subCommands.get(args[0]);
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

    public org.bukkit.command.Command getBukkitCommand() {
        return bukkitCommand;
    }

    public ICommandExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ICommandExecutor executor) {
        this.executor = executor;
    }

    public ITabCompleter getTabCompleter() {
        return tabCompleter;
    }

    public void setTabCompleter(ITabCompleter tabCompleter) {
        this.tabCompleter = tabCompleter;
    }

    public SubCommand addSubCommand(String name, ISubCommandExecutor executor){
        if(subCommands == null)subCommands = new HashMap<String, SubCommand>();
        SubCommand subCommand = new SubCommand(name, this, executor);
        subCommands.put(name, subCommand);
        return subCommand;
    }

    public void addSubCommand(SubCommand subCommand){
        if(subCommands == null)subCommands = new HashMap<String, SubCommand>();
        subCommands.put(subCommand.getName(), subCommand);
    }

    public void addSubCommands(SubCommand... subCommands){
        if(this.subCommands == null)this.subCommands = new HashMap<String, SubCommand>();
        for (SubCommand subCommand : subCommands) {
            this.subCommands.put(subCommand.getName(), subCommand);
        }
    }

    public List<SubCommand> getSubCommands(){
        List<SubCommand> subCommands = new ArrayList<SubCommand>();
        if(this.subCommands != null)subCommands.addAll(this.subCommands.values());
        return subCommands;
    }

    public boolean isFunction(String alias){
        return aliasFunctions.containsKey(alias);
    }

    public AliasFunction getAliasFunction(String alias){
        if(aliasFunctions.containsKey(alias))return aliasFunctions.get(alias);
        return null;
    }

    public List<AliasFunction> getAliasFunctions(){
        List<AliasFunction> aliasFunctions = new ArrayList<AliasFunction>();
        if(this.aliasFunctions != null)aliasFunctions.addAll(this.aliasFunctions.values());
        return null;
    }

}
