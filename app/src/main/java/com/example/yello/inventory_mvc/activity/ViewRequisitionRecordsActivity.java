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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_records_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        this.setSupportActionBar(myToolbar);

        Intent intent = this.getIntent();
        /*if (Intent.ACTION_SEARCH.equals(intent.getAction())) // if action_search come back this activity
        {
            String query = intent.getStringExtra(SearchManager.QUERY); // get search query
            doSearch("All", query); // TODO: FIX to suit all category
        }
        else
        {*/
            startListFragment(UrlString.getAllRequisitionRecords);
        //}
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.requisition_records_meun, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setQueryRefinementEnabled(true);

        return true;
    }*/


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case (R.id.category_1_clip):
                startListFragment(UrlString.getStationeryByCriteria + R.string.category_1_clip);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/


    /*protected void doSearch(String category, String searchString)
    {
        String url = UrlString.getStationeryByCriteria + "%s/%s";
        url = String.format(url, category, searchString);
        startListFragment(url);
    }*/

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
