package com.azortis.azortislib.api.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public interface IBaseCommand {

    /**
     * Gets the executor of the command.
     *
     * @return The executor of the command.
     */
    Command getExecutor();

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    String getName();

    /**
     * Gets the description of the command.
     *
     * @return The description of the command.
     */
    String getDescription();

    /**
     * Gets the usage of the command.
     *
     * @return The usage of the command.
     */
    String getUsage();

    /**
     * Gets the aliases of the command.
     *
     * @return The aliases of the command.
     */
    List<String> getAliases();

    /**
     * Gets the plugin of the command.
     *
     * @return The plugin of the command.
     */
    Plugin getPlugin();

    /**
     * Sets the tabCompleter of the command.
     *
     * @param tabCompleter The tabCompleter class instance.
     */
    void setTabCompleter(IAlTabCompleter tabCompleter);

    /**
     * Execute the command.
     *
     * @param sender The commandSender instance.
     * @param label The command label.
     * @param args The command arguments.
     */
    boolean execute(CommandSender sender, String label, String[] args);
}
