package com.azortis.azortislib.command;

import com.azortis.azortislib.api.command.IAlTabCompleter;
import com.azortis.azortislib.api.command.IBaseCommand;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.List;

public abstract class BaseCommand implements IBaseCommand {

    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private Plugin plugin;

    private Command executor;
    private IAlTabCompleter tabCompleter;

    public BaseCommand(String name, String description, String usage, List<String> aliases, Plugin plugin){
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.aliases = aliases;
        this.plugin = plugin;

        executor = new AzPluginCommand(name, plugin, this);
        executor.setDescription(description);
        executor.setUsage(usage);
        executor.setAliases(aliases);
    }

    public BaseCommand(String name, String description, String usage, List<String> aliases){
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.aliases = aliases;

        executor = new AzCommand(name, this);
        executor.setDescription(description);
        executor.setUsage(usage);
        executor.setAliases(aliases);
    }

    private class AzCommand extends Command{
        private BaseCommand parent;

        AzCommand(String name, BaseCommand parent){
            super(name);
            this.parent = parent;
        }

        @Override
        public boolean execute(CommandSender sender, String label, String[] args) {
            return parent.execute(sender, label, args);
        }


        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) {
            if(parent.tabCompleter != null) return parent.tabCompleter.tabComplete(sender,alias,args,location);
            return null;
        }
    }

    private class AzPluginCommand extends Command implements PluginIdentifiableCommand{
        private Plugin plugin;
        private BaseCommand parent;

        AzPluginCommand(String name, Plugin plugin, BaseCommand parent){
            super(name);
            this.plugin = plugin;
            this.parent = parent;
        }

        public Plugin getPlugin() {
            return plugin;
        }

        @Override
        public boolean execute(CommandSender sender, String label, String[] args) {
            return parent.execute(sender,label,args);
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) {
            if(parent.tabCompleter != null) return parent.tabCompleter.tabComplete(sender,alias,args,location);
            return null;
        }
    }

    public Command getExecutor() {
        return executor;
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
        if(plugin != null) return plugin;
        return null;
    }

    public void setTabCompleter(IAlTabCompleter tabCompleter) {
        this.tabCompleter = tabCompleter;
    }

    public abstract boolean execute(CommandSender sender, String label, String[] args);
}
