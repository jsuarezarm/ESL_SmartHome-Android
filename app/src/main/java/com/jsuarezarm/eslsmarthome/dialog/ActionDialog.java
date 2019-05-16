package com.jsuarezarm.eslsmarthome.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.jsuarezarm.eslsmarthome.R;
import com.jsuarezarm.eslsmarthome.controller.Database;
import com.jsuarezarm.eslsmarthome.model.ActionItem;

public class ActionDialog extends AlertDialog.Builder {

    public ActionDialog(Context context, final ActionItem action) {
        super(context);

        this.setCancelable(false);

        this.setTitle(context.getString(R.string.action_dialog_title));
//        this.setMessage(context.getString(R.string.action_dialog_message));
        this.setMessage(action.getDeviceName() + " - " + action.getActionName());

        this.setNegativeButton(context.getString(R.string.action_dialog_negative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database.cancelAction(action.getDeviceId(), action.getActionId());
            }
        });

        this.setPositiveButton(context.getString(R.string.action_dialog_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database.acceptAction(action.getDeviceId(), action.getActionId());
            }
        });
    }
}
