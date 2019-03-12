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

import com.azortis.azortislib.AzortisLib;
import org.bukkit.command.CommandMap;

import java.util.HashMap;

public class CommandManager {

    private HashMap<String, AlCommand> commands = new HashMap<String, AlCommand>();
    private CommandMap commandMap;

    public CommandManager(AzortisLib azortisLib){
        commandMap = azortisLib.getCraftManager().getServer().getCommandMap();
    }

    public void register(String name, AlCommand command){
        if(!commands.containsKey(name)){
            commands.put(name, command);
            commandMap.register(name, command.getBukkitCommand());
        }
        //TODO Log error: Same command trying to get registered
    }

    public void unregister(String name){
        if(commands.containsKey(name)){
            commands.remove(name);
            commandMap.getCommand(name).unregister(commandMap);
        }
    }

}
