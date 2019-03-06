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

import org.bukkit.command.CommandSender;

public class AlSubCommand {

    private String name;
    private AlCommand mainCommand;
    private IAlSubCommandExecutor executor;

    public AlSubCommand(String name, AlCommand mainCommand, IAlSubCommandExecutor executor){
        this.name = name;
        this.mainCommand = mainCommand;
        this.executor = executor;
    }

    public boolean execute(CommandSender commandSender, String label, String[] args){
        return executor.onSubCommand(commandSender, this, label, args);
    }

    public String getName() {
        return name;
    }

    public AlCommand getMainCommand() {
        return mainCommand;
    }

    public IAlSubCommandExecutor getExecutor() {
        return executor;
    }
}
