package com.example.yello.inventory_mvc.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.User;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText oldPassword;
    private EditText newPassword;
    private EditText confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPassword = (EditText) findViewById(R.id.oldPassword);
        //populateAutoComplete();


        newPassword = (EditText) findViewById(R.id.newPassword);

        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        newPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                   // attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button action_confirm = (Button) findViewById(R.id.action_confrim);
        action_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // attemptLogin();
            }
        });

        Intent intent = getIntent();

        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");


      //  User currentUser = User.validateUser(email,password);
      //  User currentUserChangePassword = currentUser.setPassword(email,password, newPassword.toString());
        //currentUser.saveInBackground();

    }

}
