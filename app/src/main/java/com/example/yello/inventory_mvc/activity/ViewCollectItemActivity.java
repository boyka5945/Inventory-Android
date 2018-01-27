package com.example.yello.inventory_mvc.activity;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.adapter.CollectItemAdapter;
import com.example.yello.inventory_mvc.adapter.NewRequisitionFormAdapter;
import com.example.yello.inventory_mvc.model.Disbursement;
import com.example.yello.inventory_mvc.model.RequisitionForm;

import java.util.List;

//public class ViewCollectItemActivity extends AppCompatActivity {
//
//
//    private ListView listView;
//   private String url = "";
//    private CollectItemAdapter adapter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        listView = (ListView) this.findViewById(R.id.listView);
//        adapter = new CollectItemAdapter(this, R.layout.collect_item_row,
//                Disbursement.GetDisbursementList(url));
//        listView.setAdapter(adapter);
//
//        registerForContextMenu(listView);
//
//        new AsyncTask<String, Void, List<Disbursement>>() {
//            @Override
//            protected List<Disbursement> doInBackground(String... params) {
//                return Disbursement.GetDisbursementList(params[0]);
//            }
//            @Override
//            protected void onPostExecute(List<Disbursement> result) {
//               CollectItemAdapter adapter = new CollectItemAdapter(getApplicationContext(),
//                        result, android.R.layout.,
//                        new String[]{"StationeryDescription", "NeedQty","ActualQty"},
//                        new int[]{ android.R.id.text1, android.R.id.text2,android.R.id.});
//                setListAdapter(adapter);
//            }
//        }.execute(url);
//    }
//
//}