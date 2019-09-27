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

package com.azortis.azortislib.file.json;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public abstract class SettingsWrapper {

    private JsonConfig config;
    private HashMap<String, Object> settingsMap;

    public SettingsWrapper(JsonConfig config, Object settingsMap){
        this.config = config;
        this.settingsMap = (HashMap<String, Object>)settingsMap;
    }

}
