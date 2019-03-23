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
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQL implements IDatabase{

    private AzortisLib al;
    private String name;
    private HikariDataSource dataSource;

    public MySQL(AzortisLib al, String name, HikariConfig config, Callback callback){
        this.al = al;
        this.name = name;
        dataSource = new HikariDataSource(config);
        callback.onCallBack();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Connection getConnection() {
        try{
            return dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}