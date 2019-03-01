package com.azortis.azortislib.api;

/**
 * This class is meant to store the Minecraft version the server is using.
 * Will be used in cases of NMS or CraftBukkit
 */
public enum MinecraftVersion {
    v1_12_R1("v_12_R1")
    ,v1_13_R1("v1_13_R1")
    ,v1_13_R2("v1_13_R2");

    private final String version;

    MinecraftVersion(String version){
        this.version = version;
    }

    public String getVersion(){
        return version;
    }
}
