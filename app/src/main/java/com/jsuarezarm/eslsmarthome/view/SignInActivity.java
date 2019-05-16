package com.jsuarezarm.eslsmarthome.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jsuarezarm.eslsmarthome.R;
import com.jsuarezarm.eslsmarthome.controller.User;

public class SignInActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signin;
    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.sign_in_email);
        password = findViewById(R.id.sign_in_password);
        signin = findViewById(R.id.sign_in_login);
        signup = findViewById(R.id.sign_in_signup);

        // Sign in
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                // Check values introduced by the user
                if(User.isValidEmail(email.getText().toString()) && User.isValidPassword(password.getText().toString())) {
                    // Log in
                    User.logIn(SignInActivity.this, email.getText().toString(), password.getText().toString());

                } else if(!User.isValidEmail(email.getText().toString())) { // Email bad formatted
                    Toast.makeText(SignInActivity.this, getString(R.string.email_bad_formatted), Toast.LENGTH_SHORT).show();

                } else if(!User.isValidPassword(password.getText().toString())) { // Password bad formatted
                    Toast.makeText(SignInActivity.this, getString(R.string.password_bad_formatted), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sign up
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }
}
