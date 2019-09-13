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

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandBuilder {
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private Plugin plugin;

    public CommandBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CommandBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandBuilder setUsage(String usage) {
        this.usage = usage;
        return this;
    }

    public CommandBuilder addAliases(List<String> aliases) {
        if(this.aliases == null)this.aliases = new ArrayList<String>();
        this.aliases.addAll(aliases);
        return this;
    }

    public CommandBuilder addAliases(String... aliases){
        if(this.aliases == null)this.aliases = new ArrayList<String>();
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    public CommandBuilder addAlias(String alias) {
        if(aliases == null)aliases = new ArrayList<String>();
        this.aliases.add(alias);
        return this;
    }

    public CommandBuilder setPlugin(Plugin plugin){
        this.plugin = plugin;
        return this;
    }

    public Command build(){
        return new Command(name, description, usage, aliases, plugin);
    }
}
