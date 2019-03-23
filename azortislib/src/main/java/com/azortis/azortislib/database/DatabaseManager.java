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

package com.azortis.azortislib.database;

import com.azortis.azortislib.AzortisLib;
import com.azortis.azortislib.Callback;
import com.zaxxer.hikari.HikariConfig;

import java.util.HashMap;

public class DatabaseManager {

    private AzortisLib al;
    private HashMap<String, IDatabase> databases = new HashMap<>();

    public DatabaseManager(AzortisLib al){
        this.al = al;
    }

    public IDatabase getDatabase(String name){
        return databases.get(name);
    }

    public IDatabase openMySQLDatabase(String name, HikariConfig config, Callback callback){
        if(databases.containsKey(name)){
            al.getLogger().warning("Database: " + name + " already exists!");
            return null;
        }
        MySQL database = new MySQL(al, name, config, callback);
        databases.put(name, database);
        return database;
    }

    public IDatabase openSQLiteDatabase(String name, String filePath, Callback callback){
        if(databases.containsKey(name)){
            al.getLogger().warning("Database: " + name + " already exists!");
            return null;
        }
        SQLite database = new SQLite(al, name, filePath, callback);
        databases.put(name, database);
        return database;
    }

}
