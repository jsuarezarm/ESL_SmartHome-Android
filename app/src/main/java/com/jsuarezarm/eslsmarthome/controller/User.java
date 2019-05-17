package com.jsuarezarm.eslsmarthome.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jsuarezarm.eslsmarthome.view.MainActivity;
import com.jsuarezarm.eslsmarthome.R;
import com.jsuarezarm.eslsmarthome.view.SignInActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    public static void create(final Activity activity, String email, String password) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            // Send email verification
                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(activity, activity.getString(R.string.verification_email_sent), Toast.LENGTH_SHORT).show();

                                        activity.finish();
                                    } else {
                                        Toast.makeText(activity, activity.getString(R.string.verification_email_failed), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

////                            Log.d("user", "createUserWithEmail:success");
//                            Intent intent = new Intent(activity, MainActivity.class);
//                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK); // Close all before activities
//                            activity.startActivity(intent);
//                            activity.finish();
                        } else {
//                            Log.w("user", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, activity.getString(R.string.email_invalid), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public static void logIn(final Activity activity, String email, String password) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            if(mAuth.getCurrentUser().isEmailVerified()) {
//                                Log.d("user", "logIn:success");
                                activity.startActivity(new Intent(activity, MainActivity.class));
                                activity.finish();
                            } else {
                                Toast.makeText(activity, activity.getString(R.string.verify_email), Toast.LENGTH_SHORT).show();
                            }

                        } else {
//                            Log.w("user", "logIn:failure", task.getException());
                            Toast.makeText(activity, activity.getString(R.string.login_incorrect), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public static void logOut(final Activity activity) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        activity.startActivity(new Intent(activity, SignInActivity.class));
        activity.finish();
    }

    public static void delete(final Activity activity, String password) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getEmail(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
//                                        Log.d("user", "delete:success");
                                        Toast.makeText(activity, activity.getString(R.string.user_deleted), Toast.LENGTH_SHORT).show();
                                        activity.startActivity(new Intent(activity, SignInActivity.class));
                                        activity.finish();
                                    } else {
                                        Toast.makeText(activity, activity.getString(R.string.user_not_deleted), Toast.LENGTH_SHORT).show();
//                                        Log.w("user", "delete:failure", task.getException());
                                    }

                                }
                            });
                        } else {
                            Toast.makeText(activity, activity.getString(R.string.user_not_deleted), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void changePassword(final Activity activity, String oldPassword, final String newPassword) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getEmail(), oldPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
//                                        Log.d("user", "changePassword:success");
                                        Toast.makeText(activity, activity.getString(R.string.changed_password), Toast.LENGTH_SHORT).show();
                                    } else {
//                                        Log.w("user", "changePassword:failure", task.getException());
                                        Toast.makeText(activity, activity.getString(R.string.not_changed_password), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(activity, activity.getString(R.string.not_changed_password), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void sendPasswordResetEmail(final Activity activity, String email) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(activity, activity.getString(R.string.change_password_email_sent), Toast.LENGTH_SHORT).show();

                        activity.finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, activity.getString(R.string.change_password_email_failed), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static String getId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            return user.getUid();
        } else {
            return null;
        }
    }

    public static String getEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            return user.getEmail();
        } else {
            return null;
        }
    }

    public static boolean isValidEmail(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        if(password.length() > 5) {
            return true;
        } else {
            return false;
        }
    }

}
