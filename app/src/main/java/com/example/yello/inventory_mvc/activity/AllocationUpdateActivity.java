package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;

public class AllocationUpdateActivity extends AppCompatActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_update);


        final String itemDescrp = getIntent().getExtras().getString("itemDescrp");
        final String itemCode = getIntent().getExtras().getString("itemCode");
        final String orderNum = getIntent().getExtras().getString("orderNum");
        TextView tvOrderNum = (TextView) findViewById(R.id.textView18);
        TextView tvItemCode = (TextView) findViewById(R.id.textView16);
        final TextView tvDepartment = (TextView) findViewById(R.id.textView20);
        final TextView tvUnfulfilled = (TextView) findViewById(R.id.textView25);
        final TextView tvRetrievedQty = (TextView) findViewById(R.id.textView27);
        final EditText tvAllocatedQty = (EditText) findViewById(R.id.editText2);
        //final TextView tvEmployee = (TextView) findViewById(R.id.textView22);
        Button b = (Button) findViewById(R.id.button4);

        tvOrderNum.setText(orderNum);
        tvItemCode.setText(itemCode);
        tvDepartment.setText(getIntent().getExtras().getString("departmentCode"));
        tvUnfulfilled.setText(getIntent().getExtras().getString("qtyUnfulfilled"));


        new AsyncTask< String, Void, Retrieval_Item>() {

            @Override
            protected Retrieval_Item doInBackground(String... params) {
                return Retrieval_Item.GetRetrievalForm(params[0]);
            }

            @Override
            protected void onPostExecute(Retrieval_Item result) {

                tvRetrievedQty.setText(result.get(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED));


            }
        }.execute(itemCode);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<String, Void, Void>() {

                    @Override
                    protected Void doInBackground(String... params) {
                        Requisition_Detail.updateRequisitionDetails(params[0], params[1], params[2]);

                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result) {
                        finish();
                    }
                }.execute(orderNum, itemCode, tvAllocatedQty.getText().toString());

                Intent intent = new Intent(getApplicationContext(), AllocationGroupedByItemActivity.class);
                intent.putExtra(Key.RETRIEVAL_ITEM_5_ITEMCODE, itemCode );
                intent.putExtra(Key.RETRIEVAL_ITEM_1_DESCRIPTION,itemDescrp);
                startActivity(intent);
            }
        });


    }


}
