package com.jsuarezarm.eslsmarthome.model;

import com.google.firebase.database.PropertyName;

public class Action {

    @PropertyName("name")
    public String name;

    @PropertyName("status")
    public int status;

    public Action() {}

}
