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
import com.google.gson.JsonObject;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    private static AzortisLib al;

    private FileManager() {
    }

    private static File getFile(String s) {
        if(!al.getPlugin().getDataFolder().exists()) al.getPlugin().getDataFolder().mkdirs();
        File f = new File(al.getPlugin().getDataFolder(), s + ".yml");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

    public static YamlConfiguration getConfig(String s) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(getFile(s));
        return config;
    }


    /**
     *  UNTESTED
     * @param src
     * InputStream. Recommended to get stream via getResourceAsStream(...) method.
     * @param destination
     * The absolute path of the new file. "C:/Users/Admin/Desktop/Server/plugins/resource.png"
     */
    public static void copyResource(InputStream src, String destination) {
        try {
            Files.copy(src, Paths.get(destination));
        } catch (IOException ex) {
            al.getLogger().warning("Could not copy resource: ");
            ex.printStackTrace();
        }
    }

    public static FileManager initialize(AzortisLib alvar) {
        al = alvar;
        return new FileManager();
    }

}
