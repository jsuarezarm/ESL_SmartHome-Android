package com.jsuarezarm.eslsmarthome.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.jsuarezarm.eslsmarthome.R;
import com.jsuarezarm.eslsmarthome.controller.User;

public class LogOutDialog extends AlertDialog.Builder {

    public LogOutDialog(Context context, final Activity activity) {
        super(context);

        this.setCancelable(false);

        this.setTitle(context.getString(R.string.logout_dialog_title));
        this.setMessage(context.getString(R.string.logout_dialog_message));

        this.setNegativeButton(context.getString(R.string.logout_dialog_negative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing
            }
        });

        this.setPositiveButton(context.getString(R.string.logout_dialog_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                User.logOut(activity);
            }
        });
    }
}
