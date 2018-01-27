package com.example.yello.inventory_mvc.activity;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.List;

public class AllocationListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_list);
        final ListView lv = (ListView) findViewById(R.id.listView);

        new AsyncTask<Void, Void, List<Requisition_Detail>>() {

            @Override
            protected List<Requisition_Detail> doInBackground(Void... params) {
                return Requisition_Detail.ToAllocate();
            }

            @Override
            protected void onPostExecute(List<Requisition_Detail> result) {

                SimpleAdapter adapter =
                        new SimpleAdapter(getApplicationContext(), result,
                                R.layout.allocation_row,
                                new String[]{Key.REQUISITION_DETAIL_2_ITEM_CODE, Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION},
                                new int[]{android.R.id.text1, android.R.id.text2});

                lv.setAdapter(adapter);
            }
        }.execute();



        lv.setOnItemClickListener(this);




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }




}
