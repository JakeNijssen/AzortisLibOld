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

public class DatabaseManager {
    private AzortisLib al;
    private IDatabase database;
    private MySQL mySQL; //Stored, so it can be closed.

    public DatabaseManager(AzortisLib al){
        this.al = al;
    }

    public void useMySQL(MySQLSettings settings){
        
    }

    public void useSQLite(SQLiteSettings settings){

    }

    public IDatabase getDatabase(){
        return database;
    }

    /**
     * This is only applicable if an MySQL database is used.
     */
    public void closeConnection(){
        if(mySQL != null){
            mySQL.close();
        }
    }

}
