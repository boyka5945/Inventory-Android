package com.example.yello.inventory_mvc.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.AllocationViewModel;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.List;

public class AllocationGroupedByItemActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_grouped_by_item);
        final ListView lv = findViewById(R.id.listview1);
        final String itemCode = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_5_ITEMCODE);
        final TextView tvItemCode = (TextView) findViewById(R.id.textView29);
        TextView header = (TextView) findViewById(R.id.textView8);
/*        TextView code = (TextView) findViewById();*/
        TextView itemDescrp = (TextView) findViewById(R.id.textView11);
        String retrieved = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_1_DESCRIPTION);
        itemDescrp.setText(retrieved);
        tvItemCode.setText(itemCode);
        header.setText("Pending Fulfilment" + "(" + itemCode + ")");







        new AsyncTask<String, Void, List<AllocationViewModel>>() {

            @Override
            protected List<AllocationViewModel> doInBackground(String... params) {
                return AllocationViewModel.getAllocationListGroupedByItem(params[0]);
            }

            @Override
            protected void onPostExecute(List<AllocationViewModel> result) {

                SimpleAdapter adapter =
                        new SimpleAdapter(AllocationGroupedByItemActivity.this, result,
                                R.layout.allocation_group_row,
                                new String[]{"orderNum", "departmentCode", "qtyUnfulfilled"}, //CHANGE TO UNFULFILLED
                                new int[]{R.id.textView5, R.id.textView7, R.id.textView6});

                lv.setAdapter(adapter);
            }
        }.execute(itemCode);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                AllocationViewModel allocationItem = (AllocationViewModel) adapter.getAdapter().getItem(position);

                Intent intent = new Intent(getApplicationContext(), AllocationUpdateActivity.class);
                intent.putExtra("orderNum", allocationItem.get("orderNum"));
                intent.putExtra("qtyUnfulfilled", allocationItem.get("qtyUnfulfilled"));
                intent.putExtra("itemCode", tvItemCode.getText().toString());
                intent.putExtra("departmentCode", allocationItem.get("departmentCode"));
                startActivity(intent);
            }
        });



    }

}


