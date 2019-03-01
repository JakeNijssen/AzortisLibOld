package com.azortis.azortislib.api.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface IAlTabCompleter {

    List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location);

}
