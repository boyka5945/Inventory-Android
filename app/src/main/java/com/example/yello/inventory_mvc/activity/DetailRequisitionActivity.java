package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.fragment.DetailRequisitionFragment;
import com.example.yello.inventory_mvc.fragment.NewRequisitionFragment;
import com.example.yello.inventory_mvc.utility.Key;

/**
 * Created by HP on 1/24/2018.
 */

public class DetailRequisitionActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_requisition);

        Intent intent = this.getIntent();

        if(intent.hasExtra(Key.BUNDLE_REQUISITION))
        {
            Bundle bundle = new Bundle();
            bundle.putString(Key.BUNDLE_REQUISITION, intent.getStringExtra(Key.BUNDLE_REQUISITION));

            Fragment fragment = new DetailRequisitionFragment();
            fragment.setArguments(bundle);

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout_RD, fragment)
                    .commit();
        }
    }
}


