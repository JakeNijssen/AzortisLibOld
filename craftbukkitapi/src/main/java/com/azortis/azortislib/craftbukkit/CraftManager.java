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

package com.azortis.azortislib.craftbukkit;

public class CraftManager {

    private IAlCraftServer craftServer;
    private IAlCraftPlayer craftPlayer;

    public CraftManager(String minecraftVersion){
        try{
            String packageName = "com.azortis.azortislib.craftbukkit." + minecraftVersion + ".";
            craftServer = (IAlCraftServer) Class.forName(packageName + "AlCraftServer").getConstructor().newInstance();
            craftPlayer = (IAlCraftPlayer) Class.forName(packageName + "AlCraftPlayer").getConstructor().newInstance();
        }catch (Exception exception){ //Since there are so many exception that can be thrown we use Exception and not specifics.
            //TODO log error trough logger
            exception.printStackTrace();
        }
    }

    public IAlCraftServer getServer(){
        return craftServer;
    }

    public IAlCraftPlayer getCraftPlayer() {
        return craftPlayer;
    }
}
