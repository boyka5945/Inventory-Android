package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.fragment.ViewRequisitionRecordsFragment;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

/**
 * Created by HP on 1/24/2018.
 */

public class ViewRequisitionRecordsActivity extends AppCompatActivity
{


    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_records_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        this.setSupportActionBar(myToolbar);

        Intent intent = this.getIntent();
        startListFragment(UrlString.getRequisitionRecordsByRequesterID);

    }


    protected void startListFragment(String url)
    {
        final String TAG = "LIST_FRAGMENT";

        Bundle bundle = new Bundle();
        bundle.putString(Key.BUNDLE_URL, url);

        Fragment listRequisitionFragment = new ViewRequisitionRecordsFragment();
        listRequisitionFragment.setArguments(bundle);

        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (manager.findFragmentByTag(TAG) == null)
        {
            transaction.add(R.id.frameLayoutList, listRequisitionFragment, TAG);
        }
        else
        {
            transaction.replace(R.id.frameLayoutList, listRequisitionFragment, TAG);
        }

        transaction.commit();
    }
}
