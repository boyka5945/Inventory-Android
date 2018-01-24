package com.example.yello.inventory_mvc.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.fragment.StationeryCatalogueFragment;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

public class BrowseCatalogueActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_catalogue);
    
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        this.setSupportActionBar(myToolbar);
        
        Intent intent = this.getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) // if action_search come back this activity
        {
            String query = intent.getStringExtra(SearchManager.QUERY); // get search query
            doSearch("All", query); // TODO: FIX to suit all category
        }
        else
        {
            startListFragment(UrlString.getAllStationeries);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.browse_catalogue_menu, menu);
        
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setQueryRefinementEnabled(true);
        
        return true;
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case (R.id.show_new_requisition_form):
                Intent intent = new Intent(this, NewRequisitionActivity.class);
                this.startActivity(intent);
                return true;
  
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    
    protected void doSearch(String category, String searchString)
    {
        String url = UrlString.getStationeryByCriteria + "%s/%s";
        url = String.format(url, category, searchString);
        startListFragment(url);
    }
    
    protected void startListFragment(String url)
    {
        final String TAG = "LIST_FRAGMENT";
        
        Bundle bundle = new Bundle();
        bundle.putString(Key.BUNDLE_URL, url);
        
        Fragment listStationeryFragment = new StationeryCatalogueFragment();
        listStationeryFragment.setArguments(bundle);
        
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        
        if (manager.findFragmentByTag(TAG) == null)
        {
            transaction.add(R.id.frameLayoutList, listStationeryFragment, TAG);
        }
        else
        {
            transaction.replace(R.id.frameLayoutList, listStationeryFragment, TAG);
        }
        
        transaction.commit();
    }
}
