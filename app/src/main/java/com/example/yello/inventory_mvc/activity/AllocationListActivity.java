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
import com.example.yello.inventory_mvc.model.AllocationListViewModel;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.ArrayList;
import java.util.List;

public class AllocationListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    
        @Override
    public void onBackPressed() {
        // Write your code here
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_list);
        final ListView lv = (ListView) findViewById(R.id.listViewMain);



        new AsyncTask<Void, Void, List<AllocationListViewModel>>() {

            @Override
            protected List<AllocationListViewModel> doInBackground(Void... params) {
                return AllocationListViewModel.getAllocationList();
            }

            @Override
            protected void onPostExecute(List<AllocationListViewModel> result) {


                List<AllocationListViewModel>removal = new ArrayList<>();
                for (AllocationListViewModel alvm: result){
                    if(Integer.parseInt(alvm.get("AllocateQty").toString()) == 0){
                        removal.add(alvm);
                    }
                }
                result.removeAll(removal);



                SimpleAdapter adapter =
                        new SimpleAdapter(AllocationListActivity.this, result,
                                R.layout.allocation_row,
                                new String[]{"StationeryDescription", "AllocateQty", "qtyRetrieved"},
                                new int[]{R.id.itemNameCol, R.id.totalReqCol, R.id.totalRetCol});

                lv.setAdapter(adapter);
            }
        }.execute();



            lv.setOnItemClickListener(this);




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AllocationListViewModel allocationItem = (AllocationListViewModel) parent.getAdapter().getItem(position);

        Intent intent = new Intent(getApplicationContext(),AllocationGroupedByItemActivity.class );
        intent.putExtra(Key.RETRIEVAL_ITEM_5_ITEMCODE, allocationItem.get(Key.RETRIEVAL_ITEM_5_ITEMCODE));
        intent.putExtra(Key.RETRIEVAL_ITEM_1_DESCRIPTION, allocationItem.get("StationeryDescription"));

        startActivity(intent);



    }




}
