package com.example.yello.inventory_mvc.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.activity.RequestDetailsActivity;
import com.example.yello.inventory_mvc.model.LoginUser;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.model.Requisition_Record;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestListFragment extends ListFragment {


    public RequestListFragment() {
        // Required empty public constructor
        super();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_request_list, container, false);

        String url = null;

        Bundle bundle = this.getArguments(); // Get bundle from starting activity
        if(bundle != null)
        {
            url = bundle.getString(Key.BUNDLE_URL);
        }

        if(url == null) // If no search query from activity, get all stationery
        {
            url = UrlString.getRequistionRecordByDept;
        }

        final Activity containerActivity = this.getActivity(); // get starting activity

        new AsyncTask<String, Void, List<Requisition_Record>>()
        {
            @Override
            protected List<Requisition_Record> doInBackground(String... strings)
            {

                return Requisition_Record.getRequistionRecordByDept(LoginUser.deptCode);
            }

            @Override
            protected void onPostExecute(List<Requisition_Record> result)
            {
                SimpleAdapter adapter = new SimpleAdapter(containerActivity.getApplicationContext(),
                        result,
                        android.R.layout.simple_list_item_2,
                        new String[] {Key.REQUISITION_RECORD_1_REQUISITION_NO , Key.REQUISITION_RECORD_4_REQUESTER_ID},
                        new int[] {android.R.id.text1, android.R.id.text2});

                RequestListFragment.this.setListAdapter(adapter);
            }

        }.execute(url);

        return view;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Requisition_Record record = (Requisition_Record) getListAdapter().getItem(position);

        if (getActivity().findViewById(R.id.requestDetailsframe1) == null) {
            // single-pane
            String check=record.get(Key.BUNDLE_REQUESITION_RECORD);
            Intent intent = new Intent(getActivity(), RequestDetailsActivity.class);
            //intent.putExtra(Key.BUNDLE_REQUESITION_RECORD, record.get(Key.REQUISITION_RECORD_1_REQUISITION_NO));
            intent.putExtra(Key.BUNDLE_REQUESITION_RECORD, record);

            startActivity(intent);
        }
        else
            displayDetails(record);


    }

    protected void displayDetails(Requisition_Record rec)
    {
       Bundle bundle=new Bundle();
       bundle.putSerializable(Key.BUNDLE_REQUESITION_RECORD, rec);
        final String TAG = "REQ_DETAIL_FRAG";

        Fragment fragment = new RequestDetailsFragment(); // initialize fragment
        //bundle.putString(Key.BUNDLE_SHOW_BUTTON, "hide"); // to indicate whether to hide back to catalogue button
        fragment.setArguments(bundle); // put bundle inside fragment

        FragmentManager manager = this.getFragmentManager(); // get fragment manager
        FragmentTransaction transaction = manager.beginTransaction(); // get transaction

        if (manager.findFragmentByTag(TAG) == null) // first time
        {
            transaction.add(R.id.requestDetailsframe1, fragment, TAG); // new
        }
        else // not first time
        {
            transaction.replace(R.id.requestDetailsframe1, fragment, TAG); // replace old fragmet
        }

        transaction.commit(); // commit transaction
    }




}
