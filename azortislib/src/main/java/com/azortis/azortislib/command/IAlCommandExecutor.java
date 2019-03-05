package com.azortis.azortislib.command;

import org.bukkit.command.CommandSender;

public interface IAlCommandExecutor {

    boolean onCommand(CommandSender commandSender, AlCommand command, String alias, String[] args);

}
