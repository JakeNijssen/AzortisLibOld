/*
 * Copyright (C) 2019  Azortis
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

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CommandBuilder {
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private Plugin plugin;

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

    public AlCommand build(){
        return new AlCommand(name, description, usage, aliases, plugin);
    }
}
