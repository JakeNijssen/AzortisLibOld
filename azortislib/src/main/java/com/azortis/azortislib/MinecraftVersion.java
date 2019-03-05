package com.azortis.azortislib;

public enum MinecraftVersion {
    v1_12_R1("v_12_R1")
    ,v1_13_R1("v1_13_R1")
    ,v1_13_R2("v1_13_R2");

    private final String versionString;

    MinecraftVersion(String versionString){
        this.versionString = versionString;
    }

    public String getVersionString(){
        return versionString;
    }

}
