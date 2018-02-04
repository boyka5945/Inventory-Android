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
import com.example.yello.inventory_mvc.model.AllocationGroupViewModel;
import com.example.yello.inventory_mvc.model.AllocationListViewModel;
import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.ArrayList;
import java.util.List;

public class AllocationGroupedByItemActivity extends AppCompatActivity {

    public void onBackPressed() {
        // Write your code here
        Intent intent = new Intent(getApplicationContext(), AllocationListActivity.class);
        startActivity(intent);

        super.onBackPressed();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_grouped_by_item);

        final String itemCode = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_5_ITEMCODE);
        final String retrieved = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_1_DESCRIPTION);

        final ListView lv = findViewById(R.id.listview1);
        final TextView tvItemCode = (TextView) findViewById(R.id.textView29);
        final TextView TvRetrievedQty = (TextView) findViewById(R.id.textView9) ;
        TextView header = (TextView) findViewById(R.id.textView8);
/*        TextView code = (TextView) findViewById();*/
        TextView itemDescrp = (TextView) findViewById(R.id.textView11);

        itemDescrp.setText(retrieved);
        tvItemCode.setText(itemCode);
        header.setText("Pending Fulfilment " + "(" + itemCode + ")");


        new AsyncTask< String, Void, Retrieval_Item>() {

            @Override
            protected Retrieval_Item doInBackground(String... params) {
                return Retrieval_Item.GetRetrievalForm(params[0]);
            }
            @Override
            protected void onPostExecute(Retrieval_Item result) {

                TvRetrievedQty.setText(result.get(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED));
            }
        }.execute(itemCode);






        new AsyncTask<String, Void, List<AllocationGroupViewModel>>() {

            @Override
            protected List<AllocationGroupViewModel> doInBackground(String... params) {
                return AllocationGroupViewModel.getAllocationListGroupedByItem(params[0]);
            }

            @Override
            protected void onPostExecute(List<AllocationGroupViewModel> result) {


                List<AllocationGroupViewModel>removal = new ArrayList<>();
                for (AllocationGroupViewModel alvm: result){
                    if(Integer.parseInt(alvm.get("qtyUnfulfilled").toString()) == 0){
                        removal.add(alvm);
                    }
                }
                result.removeAll(removal);

                if(result.size()==0){
                    Intent intent = new Intent(getApplicationContext(), AllocationListActivity.class );
                    startActivity(intent);
                }

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

                AllocationGroupViewModel allocationItem = (AllocationGroupViewModel) adapter.getAdapter().getItem(position);

                Intent intent = new Intent(getApplicationContext(), AllocationUpdateActivity.class);
                intent.putExtra("orderNum", allocationItem.get("orderNum"));
                intent.putExtra("qtyUnfulfilled", allocationItem.get("qtyUnfulfilled"));
                intent.putExtra("itemCode", tvItemCode.getText().toString());
                intent.putExtra("departmentCode", allocationItem.get("departmentCode"));
                intent.putExtra("itemDescrp",retrieved);

                startActivity(intent);
            }
        });



    }

}


