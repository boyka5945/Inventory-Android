package com.example.yello.inventory_mvc.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.activity.ManageRequestActivity;
import com.example.yello.inventory_mvc.activity.RequestDetailsActivity;
import com.example.yello.inventory_mvc.adapter.RequisitionDetailAdapter;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.model.Requisition_Record;
import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestDetailsFragment extends ListFragment {

    private TextView reqid;
    private TextView reqname;
    private TextView reqdate;
    private ListView listView;
    private Button approve;
    private Button reject;
    protected HashMap<String, String> rec = null;
    protected Requisition_Record reqRecord;
    public RequestDetailsFragment() {
        // Required empty public constructor
        super();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.request_approval_layout, container, false);
        listView = view.findViewById(android.R.id.list);
        reqid = view.findViewById(R.id.reqid);
        reqname = view.findViewById(R.id.reqname);
        reqdate = view.findViewById(R.id.reqdate);

        String reqNo = null;

        Bundle bundle = this.getArguments(); // Get bundle from starting activity
        if (bundle != null) {
            // String reqNo = bundle.getString(Key.BUNDLE_REQUESITION_RECORD);
            rec = (HashMap<String, String>) bundle.getSerializable(Key.BUNDLE_REQUESITION_RECORD);
            reqNo = rec.get(Key.REQUISITION_RECORD_1_REQUISITION_NO);
        }


        reqid.setText(rec.get(Key.REQUISITION_RECORD_1_REQUISITION_NO));
        reqname.setText(rec.get(Key.REQUISITION_RECORD_5_REQUESTER_NAME));
        reqdate.setText(rec.get(Key.REQUISITION_RECORD_10_REQUEST_DATE));

        final Activity containerActivity = this.getActivity(); // get starting activity
        new AsyncTask<String, Void, List<Requisition_Detail>>() {
            @Override
            protected List<Requisition_Detail> doInBackground(String... strings) {
                return Requisition_Detail.getDetailsByReqNo(strings[0]);
            }

            @Override
            protected void onPostExecute(List<Requisition_Detail> result) {

                SimpleAdapter adapter = new SimpleAdapter(containerActivity.getApplicationContext(),
                        result,
                        android.R.layout.simple_list_item_2,
                        new String[]{Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION, Key.REQUISITION_DETAIL_6_REQUEST_QTY},
                        new int[]{android.R.id.text1, android.R.id.text2});

                listView.setAdapter(adapter);
            }
        }.execute(reqNo);

        approve = view.findViewById(R.id.approve);
        reject = view.findViewById(R.id.reject);

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String reqNumber = rec.get(Key.REQUISITION_RECORD_1_REQUISITION_NO);
               // reqRecord= (Requisition_Record) rec;
                new AsyncTask<String, Void, Void>() {


                    @Override
                    protected Void doInBackground(String... params) {
                        Requisition_Record.UpdateRequisition(params[0], "Approved and Processing", "S1000");

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        Intent intent = new Intent(getActivity(), ManageRequestActivity.class);
                        startActivity(intent);
                        containerActivity.finish();
                    }
                }.execute(reqNumber);
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String reqNumber = rec.get(Key.REQUISITION_RECORD_1_REQUISITION_NO);
                // reqRecord= (Requisition_Record) rec;
                new AsyncTask<String, Void, Void>() {

                    @Override
                    protected Void doInBackground(String... params) {
                        Requisition_Record.UpdateRequisition(params[0], "Rejected", "S1000");

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        Intent intent = new Intent(getActivity(), ManageRequestActivity.class);
                        startActivity(intent);
                        containerActivity.finish();
                    }
                }.execute(reqNumber);
            }
        });
        return view;
    }


}
