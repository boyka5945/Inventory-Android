package com.example.yello.inventory_mvc.activity;

import android.app.ListActivity;
import android.content.Intent;
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
        final ListView lv = (ListView) findViewById(R.id.listview1);


        new AsyncTask<Void, Void, List<Retrieval_Item>>() {

            @Override
            protected List<Retrieval_Item> doInBackground(Void... params) {
                return Retrieval_Item.ListRetrieval();
            }

            @Override
            protected void onPostExecute(List<Retrieval_Item> result) {

                SimpleAdapter adapter =
                        new SimpleAdapter(getApplicationContext(), result,
                                R.layout.allocation_row,
                                new String[]{Key.RETRIEVAL_ITEM_1_DESCRIPTION, Key.RETRIEVAL_ITEM_2_QTY},
                                new int[]{R.id.textView4, R.id.textView3});

                lv.setAdapter(adapter);
            }
        }.execute();



            lv.setOnItemClickListener(this);




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Retrieval_Item  allocationItem = (Retrieval_Item) parent.getAdapter().getItem(position);

        Intent intent = new Intent(getApplicationContext(),AllocationGroupedByItemActivity.class );
        intent.putExtra(Key.RETRIEVAL_ITEM_5_ITEMCODE, allocationItem.get(Key.RETRIEVAL_ITEM_5_ITEMCODE));
        startActivity(intent);

    }




}
