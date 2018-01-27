package com.example.yello.inventory_mvc.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.fragment.RequestDetailsFragment;

import java.util.HashMap;

public class RequestDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        Intent intent = getIntent();
        if (intent.hasExtra("details")) {
            HashMap<String, String> m = (HashMap<String,String>)intent.getSerializableExtra("details");
            Bundle args = new Bundle();
            args.putSerializable("item", m);
            Fragment f = new RequestDetailsFragment();
            f.setArguments(args);
           // getFragmentManager().beginTransaction()
           //         .add(R.id.requestDetailsFrame2, f)
           //         .commit();
        }

    }
}
