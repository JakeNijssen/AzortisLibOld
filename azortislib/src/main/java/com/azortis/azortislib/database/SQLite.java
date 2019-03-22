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

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("all")
public class SQLite implements IDatabase{

    private String name;
    private String path;

    public SQLite(AzortisLib al, String name, String filePath, Callback callback){
        this.name = name;
        File databaseFile = new File(filePath, name + ".db");
        try{
            if(!databaseFile.exists()){
                if(!databaseFile.createNewFile()){
                    al.getLogger().severe("Can't create database file: " + name + ".db");
                }
            }
        }catch (NullPointerException | IOException e){
            e.printStackTrace();
        }
        path = "jdbc:sqlite:" + databaseFile.getPath();
        callback.onCallBack();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Connection getConnection() {
        try{
            return DriverManager.getConnection(path);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
