package com.example.yello.inventory_mvc.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yello.inventory_mvc.R;

public class ManageRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_request);
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    public void onBackPressed() {

        finish();
    }

}
