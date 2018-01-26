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
import com.example.yello.inventory_mvc.activity.RequestDetailsActivity;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.model.Requisition_Record;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.io.Serializable;
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

                return Requisition_Record.getRequistionRecordByDept("ENGL");
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
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Requisition_Record reqRecord = (Requisition_Record) l.getAdapter().getItem(position); // get selected request record
        new AsyncTask<String, Void, List<Requisition_Detail>>()
        {
            @Override
            protected List<Requisition_Detail> doInBackground(String... strings)
            {

                return Requisition_Detail.getDetailsByReqNo(Key.REQUISITION_RECORD_1_REQUISITION_NO);
            }

            @Override
            protected void onPostExecute(List<Requisition_Detail> result)
            {
                displayDetail(result);
            }

        }.execute();
        //displayDetail(re);
    }

    protected void displayDetail(List<Requisition_Detail> req_detail)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Key.BUNDLE_REQUISITION_DETAIL, (Serializable) req_detail); // put selected stationery into bundle

        if(this.getActivity().findViewById(R.id.frameLayoutRequestDetails) != null)
        {
            // in landscape mode => put fragment in frame layout
            final String TAG = "REQUEST_DETAIL_FRAGMENT";

            Fragment fragment = new NewRequisitionFragment(); // initialize fragment
            fragment.setArguments(bundle); // put bundle inside fragment

            FragmentManager manager = this.getFragmentManager(); // get fragment manager
            FragmentTransaction transaction = manager.beginTransaction(); // get transaction

            if(manager.findFragmentByTag(TAG) == null) // first time
            {
                transaction.add(R.id.frameLayoutRequestDetails, fragment, TAG); // new
            }
            else // not first time
            {
                transaction.replace(R.id.frameLayoutRequestDetails, fragment, TAG); // replace old fragmet
            }

            transaction.commit(); // commit transaction
        }
        else
        {
            // in portrait mode => start another activity
            Intent intent = new Intent(this.getActivity(), RequestDetailsActivity.class);
            intent.putExtras(bundle);

            this.startActivity(intent);
        }

    }
}
