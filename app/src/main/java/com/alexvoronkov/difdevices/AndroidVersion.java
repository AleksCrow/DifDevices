package com.alexvoronkov.difdevices;

import java.io.Serializable;

public class AndroidVersion implements Serializable{

    private String ver;
    private String name;
    private String api;

    private AndroidVersion[] android;

    public AndroidVersion[] getAndroid() {
        return android;
    }

    public String getVer() {
        return ver;
    }

    public String getName() {
        return name;
    }

    public String getApi() {
        return api;
    }
}
