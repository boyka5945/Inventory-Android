package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.List;

public class AllocationListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_list);
        final ListView lv = (ListView) findViewById(R.id.listViewMain);

/*
        new AsyncTask<Void, Void, List<Retrieval_Item>>() {

            @Override
            protected List<Retrieval_Item> doInBackground(Void... params) {
                return Retrieval_Item.ListRetrieval();
            }

            @Override
            protected void onPostExecute(List<Retrieval_Item> result) {

                SimpleAdapter adapter =
                        new SimpleAdapter(AllocationListActivity.this, result,
                                R.layout.allocation_row,
                                new String[]{Key.RETRIEVAL_ITEM_1_DESCRIPTION, Key.RETRIEVAL_ITEM_2_QTY, Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED, },
                                new int[]{R.id.itemNameCol, R.id.totalReqCol, R.id.totalRetCol});

                lv.setAdapter(adapter);
            }
        }.execute();*/



            lv.setOnItemClickListener(this);




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Retrieval_Item allocationItem = (Retrieval_Item) parent.getAdapter().getItem(position);

        Intent intent = new Intent(getApplicationContext(),AllocationGroupedByItemActivity.class );
        intent.putExtra(Key.RETRIEVAL_ITEM_5_ITEMCODE, allocationItem.get(Key.RETRIEVAL_ITEM_5_ITEMCODE));
        intent.putExtra(Key.RETRIEVAL_ITEM_1_DESCRIPTION, allocationItem.get(Key.RETRIEVAL_ITEM_1_DESCRIPTION));
        startActivity(intent);

    }




}
