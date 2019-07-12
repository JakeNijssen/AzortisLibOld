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

package com.azortis.azortislib.file;

import com.azortis.azortislib.AzortisLib;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileManager {
    private static AzortisLib al;

    private FileManager() {
    }

    private static File getFile(String s) {
        return new File(al.getPlugin().getDataFolder(), s + ".yml");
    }

    public static YamlConfiguration getConfig(String s) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(getFile(s));
        config.options().copyDefaults(true);
        return config;
    }

     public static FileManager initialize(AzortisLib alvar) {
        al = alvar;
        return new FileManager();
    }

}
