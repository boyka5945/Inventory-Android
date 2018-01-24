package com.example.yello.inventory_mvc.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.activity.NewRequisitionActivity;
import com.example.yello.inventory_mvc.model.Stationery;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;


import java.util.List;

/**
 * Created by CK Tan on 1/23/2018.
 */

public class StationeryCatalogueFragment extends ListFragment
{
    
    public StationeryCatalogueFragment()
    {
        // Required empty public constructor
        super();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stationery_catalogue, container, false);
        
        String url = null;
        
        Bundle bundle = this.getArguments(); // Get bundle from starting activity
        if(bundle != null)
        {
            url = bundle.getString(Key.BUNDLE_URL);
        }
        
        if(url == null) // If no search query from activity, get all stationery
        {
            url = UrlString.getAllStationeries;
        }
        
        final Activity containerActivity = this.getActivity(); // get starting activity
        
        new AsyncTask<String, Void, List<Stationery>>()
        {
            
            @Override
            protected List<Stationery> doInBackground(String... strings)
            {
                return Stationery.ListStationery(strings[0]);
            }
            
            @Override
            protected void onPostExecute(List<Stationery> result)
            {
                SimpleAdapter adapter = new SimpleAdapter(containerActivity.getApplicationContext(),
                                                          result,
                                                          android.R.layout.simple_list_item_1,
                                                          new String[] {Key.STATIONERY_2_DESCRIPTION},
                                                          new int[] {android.R.id.text1});
                
                StationeryCatalogueFragment.this.setListAdapter(adapter);
            }
        }.execute(url);
        
        return view;
    }
    
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Stationery stationery = (Stationery) l.getAdapter().getItem(position); // get selected stationery
        
        displayDetail(stationery);
    }
    
    protected void displayDetail(Stationery stationery)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Key.BUNDLE_STATIONERY, stationery); // put selected stationery into bundle
        
        if(this.getActivity().findViewById(R.id.frameLayoutStationeryInfo) != null)
        {
            // in landscape mode => put fragment in frame layout
            final String TAG = "NEW_REQUISITION_FRAGMENT";
            
            Fragment fragment = new NewRequisitionFragment(); // initialize fragment
            fragment.setArguments(bundle); // put bundle inside fragment
            
            FragmentManager manager = this.getFragmentManager(); // get fragment manager
            FragmentTransaction transaction = manager.beginTransaction(); // get transaction
            
            if(manager.findFragmentByTag(TAG) == null) // first time
            {
                transaction.add(R.id.frameLayoutStationeryInfo, fragment, TAG); // new
            }
            else // not first time
            {
                transaction.replace(R.id.frameLayoutStationeryInfo, fragment, TAG); // replace old fragmet
            }
            
            transaction.commit(); // commit transaction
        }
        else
        {
            // in portrait mode => start another activity
            Intent intent = new Intent(this.getActivity(), NewRequisitionActivity.class);
            intent.putExtras(bundle);
            
            this.startActivity(intent);
        }
        
    }
}
