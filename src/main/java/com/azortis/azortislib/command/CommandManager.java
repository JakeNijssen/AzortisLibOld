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
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.HashMap;

public class CommandManager {

    private HashMap<String, Command> commands = new HashMap<String, Command>();
    private CommandMap commandMap;

    public CommandManager(AzortisLib azortisLib){
        try{
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (CommandMap)commandMapField.get(Bukkit.getServer());
        }catch (NoSuchFieldException | IllegalAccessException ex){
            ex.printStackTrace();
        }
    }

    public void register(String name, Command command){
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
