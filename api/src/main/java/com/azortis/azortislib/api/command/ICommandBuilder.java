package com.azortis.azortislib.api.command;

import org.bukkit.plugin.Plugin;

import java.util.List;

public interface ICommandBuilder {

    void setName(String name);

    void setDescription(String description);

    void setUsage(String usage);

    void addAliases(List<String> aliases);

    void addAlias(List<String> alias);

    void setPlugin(Plugin plugin);

    IBaseCommand build();
}
