package com.azortis.azortislib.command;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CommandBuilder {
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private Plugin plugin;
    private IAlCommandExecutor executor;
    private IAlTabCompleter tabCompleter;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void addAliases(List<String> aliases) {
        if(this.aliases == null)this.aliases = new ArrayList<String>();
        this.aliases.addAll(aliases);
    }

    public void addAlias(String alias) {
        if(aliases == null)aliases = new ArrayList<String>();
        this.aliases.add(alias);
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setExecutor(IAlCommandExecutor executor) {
        this.executor = executor;
    }

    public void setTabCompleter(IAlTabCompleter tabCompleter) {
        this.tabCompleter = tabCompleter;
    }

    public AlCommand build(){
        if(plugin != null){
            AlCommand command = new AlCommand(name, description, usage, aliases, plugin);
            if(executor != null)command.setExecutor(executor);
            if(tabCompleter != null)command.setTabCompleter(tabCompleter);
            return command;
        }
        AlCommand command = new AlCommand(name, description, usage, aliases);
        if(executor != null)command.setExecutor(executor);
        if(tabCompleter != null)command.setTabCompleter(tabCompleter);
        return command;
    }
}
