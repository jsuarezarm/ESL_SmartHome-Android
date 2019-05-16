package com.jsuarezarm.eslsmarthome.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsuarezarm.eslsmarthome.R;
import com.jsuarezarm.eslsmarthome.dialog.ActionDialog;
import com.jsuarezarm.eslsmarthome.model.ActionItem;

import java.util.ArrayList;

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.ActionViewHolder> {

    private ArrayList<ActionItem> actionsList;

    public ActionAdapter(ArrayList<ActionItem> items) {
        actionsList = items;
    }

    @NonNull
    @Override
    public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;

        Log.d("Position", String.valueOf(i));

        if(i == 0) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.action_item, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.action_item_1, viewGroup, false);
        }

        ActionViewHolder holder = new ActionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ActionViewHolder actionViewHolder, int i) {
        final ActionItem action = actionsList.get(i);
        actionViewHolder.deviceName.setText(action.getDeviceName());
        actionViewHolder.actionName.setText(action.getActionName());

        actionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionDialog actionDialog = new ActionDialog(actionViewHolder.itemView.getContext(), action);
                actionDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return actionsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? 0 : 1;
    }

    public static class ActionViewHolder extends RecyclerView.ViewHolder {

        TextView deviceName;
        TextView actionName;

        public ActionViewHolder(View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.item_device_name);
            actionName = itemView.findViewById(R.id.item_action_name);
        }
    }

}
