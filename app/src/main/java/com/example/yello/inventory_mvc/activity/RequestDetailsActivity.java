package com.example.yello.inventory_mvc.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.fragment.DetailRequisitionFragment;
import com.example.yello.inventory_mvc.fragment.RequestDetailsFragment;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.HashMap;

public class RequestDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        Intent intent = this.getIntent();

        if(intent.hasExtra(Key.BUNDLE_REQUESITION_RECORD))
        {
            Bundle bundle = new Bundle();
            //bundle.putString(Key.BUNDLE_REQUESITION_RECORD, intent.getStringExtra(Key.BUNDLE_REQUESITION_RECORD));
            bundle.putSerializable(Key.BUNDLE_REQUESITION_RECORD, intent.getSerializableExtra(Key.BUNDLE_REQUESITION_RECORD));
            Fragment fragment = new RequestDetailsFragment();
            fragment.setArguments(bundle);

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.requestDetailsFrame2, fragment)
                    .commit();
        }

    }
}
