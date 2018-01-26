package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yello.inventory_mvc.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool);
        this.setSupportActionBar(myToolbar);

        Intent intent = this.getIntent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Uri uri;
        Intent i;
        switch (item.getItemId()) {
            case R.id.viewRequisition:
                startActivity(new Intent(this, NewRequisitionActivity.class));
                return true;
            case R.id.stationeryRetrieval:
                startActivity(new Intent(this, RetrievalListActivity.class));
                return true;
            case R.id.disbursementList:
                startActivity(new Intent(this, DisbursementMenuActivity.class));
                return true;
            case R.id.logout:
                startActivity(new Intent(this, LoginActivity                            .class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
