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

public class ChangePasswordDialog extends AlertDialog.Builder {
    public ChangePasswordDialog(final Context context, final Activity activity) {
        super(context);

        this.setCancelable(false);
        this.setTitle(context.getString(R.string.change_password_dialog_title));

        // Create layout
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);

        float scale = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int) (context.getResources().getDimension(R.dimen.change_password_dialog_padding));
        layout.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);

        // Create elements
        TextView tvOldPassword = new TextView(activity);
        tvOldPassword.setText(context.getString(R.string.change_password_dialog_old_password));
        TextView tvNewPassword = new TextView(activity);
        tvNewPassword.setText(context.getString(R.string.change_password_dialog_new_password));
        TextView tvCNewPassword = new TextView(activity);
        tvCNewPassword.setText(context.getString(R.string.change_password_dialog_confirm_new_password));
        final EditText inputOldPassword = new EditText(activity);
        inputOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        final EditText inputNewPassword = new EditText(activity);
        inputNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        final EditText inputCNewPassword = new EditText(activity);
        inputCNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        // Add elements to layout
        layout.addView(tvOldPassword);
        layout.addView(inputOldPassword);
        layout.addView(tvNewPassword);
        layout.addView(inputNewPassword);
        layout.addView(tvCNewPassword);
        layout.addView(inputCNewPassword);
        this.setView(layout);

        this.setNegativeButton(context.getString(R.string.change_password_dialog_negative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing
            }
        });

        this.setPositiveButton(context.getString(R.string.change_password_dialog_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Check values introduced by the user
                if(User.isValidPassword(inputOldPassword.getText().toString()) &&
                        User.isValidPassword(inputNewPassword.getText().toString()) &&
                        inputNewPassword.getText().toString().equals(inputCNewPassword.getText().toString())) {

                    // Change password
                    User.changePassword(activity, inputOldPassword.getText().toString(), inputNewPassword.getText().toString());

                } else if(!User.isValidPassword(inputNewPassword.getText().toString())) { // Password bad formatted
                    Toast.makeText(activity, context.getString(R.string.password_bad_formatted), Toast.LENGTH_SHORT).show();

                } else if(!inputNewPassword.getText().toString().equals(inputCNewPassword.getText().toString())) { // Password non equals
                    Toast.makeText(activity, context.getString(R.string.passwords_non_equals), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
