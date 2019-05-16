package com.jsuarezarm.eslsmarthome.model;

import com.google.firebase.database.PropertyName;

import java.util.HashMap;

public class Data {

    @PropertyName("devices")
    public HashMap<String, Device> devices;

    public Data() {}

}
