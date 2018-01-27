package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.AllocationViewModel;
import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.List;

public class AllocationGroupedByItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_grouped_by_item);
        final ListView lv = findViewById(R.id.listViewAllocationGroup);
        final String itemCode = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_5_ITEMCODE);


        new AsyncTask<String, Void, List<AllocationViewModel>>() {

            @Override
            protected List<AllocationViewModel> doInBackground(String... params) {
                return AllocationViewModel.getAllocationListGroupedByItem(params[0]);
            }

            @Override
            protected void onPostExecute(List<AllocationViewModel> result) {

                SimpleAdapter adapter =
                        new SimpleAdapter(getApplicationContext(), result,
                                R.layout.allocation_row,
                                new String[]{"orderNum", "departmentCode", "qtyReq"},
                                new int[]{R.id.textView5, R.id.textView6, R.id.textView7});

                lv.setAdapter(adapter);
            }
        }.execute(itemCode);


    }
    }


/*       this.put("orderNum", orderNum);
        this.put("departmentCode", departmentCode);
                this.put("qtyReq", qtyReq);

                */
