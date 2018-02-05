package com.example.yello.inventory_mvc.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.fragment.StationeryCatalogueFragment;
import com.example.yello.inventory_mvc.model.Category;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.util.List;

public class BrowseCatalogueActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Spinner categorySpinner;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_catalogue);
        
        // set up go to form button
        if(this.findViewById(R.id.button_go_to_form) != null)
        {
            Button goToForm = (Button) this.findViewById(R.id.button_go_to_form);
            goToForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(BrowseCatalogueActivity.this, NewRequisitionFormActivity.class));
                }
            });
        }
        
        
        // set up spinner
        categorySpinner = (Spinner) findViewById(R.id.spinner_category);
        new AsyncTask<String, Void, List<Category>>()
        {
            @Override
            protected List<Category> doInBackground(String... strings)
            {
                return Category.ListCategories(strings[0]);
            }
            
            @Override
            protected void onPostExecute(List<Category> result)
            {
                SimpleAdapter adapter = new SimpleAdapter(BrowseCatalogueActivity.this,
                                                          result,
                                                          android.R.layout.simple_list_item_1,
                                                          new String[]{Key.CATEGORY_2_NAME},
                                                          new int[]{android.R.id.text1});
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                categorySpinner.setAdapter(adapter);

            }
        }.execute(UrlString.getAllCategories);
        categorySpinner.setOnItemSelectedListener(this);
        
        // Set up toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        this.setSupportActionBar(myToolbar);
        
        // Handle search query
        Intent intent = this.getIntent();
        handleIntent(intent);
    }
    
    @Override
    protected void onNewIntent(Intent intent)
    {
        // Handle search query in the same activity
        handleIntent(intent);
    }
    
    private void handleIntent(Intent intent)
    {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) // if action_search come back this activity
        {
            // retrieve selected category
            Category c = (Category) categorySpinner.getSelectedItem();
            String selectedCategory = c.get(Key.CATEGORY_2_NAME);
 
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(selectedCategory, query); // perform search
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
    public boolean onSearchRequested()
    {
        Bundle selectedCategory = new Bundle();
        Category c = (Category) categorySpinner.getSelectedItem();
        String selected = c.get(Key.CATEGORY_2_NAME);
        
        selectedCategory.putString(Key.BUNDLE_CATEGORY, selected);
        startSearch(null, false, selectedCategory, false);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Category c = (Category) parent.getItemAtPosition(position);
        String selectedCategory = c.get(Key.CATEGORY_2_NAME);
        
        String url = UrlString.getStationeryByCategory + selectedCategory;
        startListFragment(url);
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        startListFragment(UrlString.getAllStationeries);
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
    
    // TODO - Change whether in pos or land - pos need to implement the button to go to form
}
