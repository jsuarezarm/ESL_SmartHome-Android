package com.jsuarezarm.eslsmarthome.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsuarezarm.eslsmarthome.R;
import com.jsuarezarm.eslsmarthome.controller.User;

public class DeleteUserDialog extends AlertDialog.Builder {
    public DeleteUserDialog(final Context context, final Activity activity) {
        super(context);

        this.setCancelable(false);
        this.setTitle(context.getString(R.string.delete_user_dialog_title));
        this.setMessage(context.getString(R.string.delete_user_dialog_message));

        // Create layout
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);

        float scale = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int) (context.getResources().getDimension(R.dimen.delete_user_dialog_padding));
        layout.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);

        // Create elements
        TextView tvPassword = new TextView(activity);
        tvPassword.setText(context.getString(R.string.delete_user_dialog_password));
        final EditText inputPassword = new EditText(activity);
        inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        layout.addView(tvPassword);
        layout.addView(inputPassword);
        this.setView(layout);

        this.setNegativeButton(context.getString(R.string.delete_user_dialog_negative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing
            }
        });

        this.setPositiveButton(context.getString(R.string.delete_user_dialog_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(User.isValidPassword(inputPassword.getText().toString())) {
                    // Delete user account
                    User.delete(activity, inputPassword.getText().toString());
                } else {
                    Toast.makeText(activity, context.getString(R.string.password_bad_formatted), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
