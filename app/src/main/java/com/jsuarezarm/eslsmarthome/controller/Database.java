package com.jsuarezarm.eslsmarthome.controller;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jsuarezarm.eslsmarthome.adapter.ActionAdapter;
import com.jsuarezarm.eslsmarthome.model.Action;
import com.jsuarezarm.eslsmarthome.model.ActionItem;
import com.jsuarezarm.eslsmarthome.model.Data;
import com.jsuarezarm.eslsmarthome.model.Device;

import java.util.ArrayList;
import java.util.Map;

public class Database {

    public static void updateActions(final ArrayList<ActionItem> list, final ActionAdapter adapter) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                Data data = dataSnapshot.getValue(Data.class);

                for(Map.Entry<String, Device> device : data.devices.entrySet()) {
                    if(device.getValue().authUsers.contains(User.getEmail())) {
                        for(Map.Entry<String, Action> action : device.getValue().actions.entrySet()) {
                            if(action.getValue().status == 0) {
                                list.add(new ActionItem(device.getKey(), device.getValue().name, action.getKey(), action.getValue().name));
                            }
                        }
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void acceptAction(String deviceId, String actionId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("devices/" + deviceId + "/actions/" + actionId);
        ref.child("status").setValue(1);
    }

    public static void cancelAction(String deviceId, String actionId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("devices/" + deviceId + "/actions/" + actionId);
        ref.child("status").setValue(2);
    }

}
