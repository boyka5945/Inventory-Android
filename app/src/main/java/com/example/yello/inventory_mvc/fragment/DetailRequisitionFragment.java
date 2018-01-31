package com.example.yello.inventory_mvc.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.activity.DetailRequisitionActivity;
import com.example.yello.inventory_mvc.activity.NewRequisitionFormActivity;
import com.example.yello.inventory_mvc.adapter.RequisitionDetailAdapter;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.model.Requisition_Record;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.util.HashMap;
import java.util.List;

import static com.example.yello.inventory_mvc.utility.UrlString.host;
import static java.nio.file.Paths.get;

/**
 * Created by HP on 1/25/2018.
 */

public class DetailRequisitionFragment extends Fragment {

    private ListView listView;

    private Button confirm;
    private String url;
    private String reqNO;
    private String quantity;
    private String p;

    public DetailRequisitionFragment() {
        // Required empty public constructor
        super();
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_requisition, container, false);
        listView = view.findViewById(R.id.listView1);


        Bundle bundle = this.getArguments(); // Get bundle from starting activity
        if (bundle != null) {
            reqNO = bundle.getString(Key.BUNDLE_REQUISITION);
        }


        final Button remove = (Button) view.findViewById(R.id.remove_button);




        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, String>()
                {
                    @Override
                    protected  String doInBackground(String... strings)
                    {
                        //return Requisition_Detail.getDetailsByReqNo(strings[0]);
                        List<Requisition_Detail> rl = Requisition_Detail.getDetailsByReqNo(reqNO);
                        String urls = UrlString.removePendingRequisition + "/" + reqNO ;
                        Requisition_Detail.removePendingRequisition(urls);

                        return "0";
                    }

                    @Override
                    protected void onPostExecute(String result)
                    {
                        Toast.makeText(getActivity(), R.string.remove_pending_requisition, Toast.LENGTH_LONG).show();
                    }
                }.execute(reqNO);
            }
        });



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

                Requisition_Detail detail = result.get(0);
                if (!detail.get(Key.REQUISITION_DETAIL_12_STATUS).equals("Pending Approval"))
                {

                    remove.setVisibility(View.GONE);
                }

            }
        }.execute(reqNO);

        return view;
    }



}