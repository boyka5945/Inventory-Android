package com.example.yello.inventory_mvc.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.adapter.RequisitionDetailAdapter;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.model.Requisition_Record;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HP on 1/25/2018.
 */

public class DetailRequisitionFragment extends Fragment {

    private ListView listView;

    public DetailRequisitionFragment() {
        // Required empty public constructor
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_requisition, container, false);
        listView = view.findViewById(R.id.listView1);

        String reqNo = null;

        Bundle bundle = this.getArguments(); // Get bundle from starting activity
        if (bundle != null) {
            reqNo = bundle.getString(Key.BUNDLE_REQUISITION);
        }


        final Activity containerActivity = this.getActivity(); // get starting activity

        new AsyncTask<String, Void, List<Requisition_Detail>>()
        {
            @Override
            protected List<Requisition_Detail> doInBackground(String... strings)
            {
                return Requisition_Detail.getDetailsByReqNo(strings[0]);
            }

            @Override
            protected void onPostExecute(List<Requisition_Detail> result)
            {
                RequisitionDetailAdapter adapter = new RequisitionDetailAdapter(getActivity().getApplicationContext(),R.layout.edit_requisition_row,result);

                listView.setAdapter(adapter);

                //displayDetail(result);
            }
        }.execute(reqNo);





        //}.execute(url);

        return view;
    }
}