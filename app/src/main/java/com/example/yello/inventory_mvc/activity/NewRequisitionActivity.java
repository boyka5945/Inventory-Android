package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.fragment.NewRequisitionFragment;
import com.example.yello.inventory_mvc.utility.Key;

public class NewRequisitionActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition);
    
        Intent intent = this.getIntent();
    
        if(intent.hasExtra(Key.BUNDLE_STATIONERY))
        {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Key.BUNDLE_STATIONERY, intent.getSerializableExtra(Key.BUNDLE_STATIONERY));
        
            Fragment fragment = new NewRequisitionFragment();
            fragment.setArguments(bundle);
        
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout, fragment)
                    .commit();
        } 
    }
}
