package com.jsuarezarm.eslsmarthome.model;

import com.google.firebase.database.PropertyName;

import java.util.ArrayList;
import java.util.HashMap;

public class Device {

    @PropertyName("id")
    public String id;

    @PropertyName("name")
    public String name;

    @PropertyName("actions")
    public HashMap<String, Action> actions;

    @PropertyName("auth_users")
    public ArrayList<String> authUsers;

    public Device() {}

}
