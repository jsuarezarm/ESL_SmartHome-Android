package com.jsuarezarm.eslsmarthome.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jsuarezarm.eslsmarthome.R;
import com.jsuarezarm.eslsmarthome.controller.User;

public class SignUpActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.sign_up_email);
        password = findViewById(R.id.sign_up_password);
        confirmPassword = findViewById(R.id.sign_up_confirm_password);
        signup = findViewById(R.id.sign_up_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                // Check values introduced by the user
                if(User.isValidEmail(email.getText().toString()) &&
                        User.isValidPassword(password.getText().toString()) &&
                        password.getText().toString().equals(confirmPassword.getText().toString())) {

                    // Create new user
                    User.create(SignUpActivity.this, email.getText().toString(), password.getText().toString());

                } else if(!User.isValidEmail(email.getText().toString())) { // Email bad formatted
                    Toast.makeText(SignUpActivity.this, getString(R.string.email_bad_formatted), Toast.LENGTH_SHORT).show();

                } else if(!User.isValidPassword(password.getText().toString())) { // Password bad formatted
                    Toast.makeText(SignUpActivity.this, getString(R.string.password_bad_formatted), Toast.LENGTH_SHORT).show();

                } else if(!password.getText().toString().equals(confirmPassword.getText().toString())) { // Password non equals
                    Toast.makeText(SignUpActivity.this, getString(R.string.passwords_non_equals), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
