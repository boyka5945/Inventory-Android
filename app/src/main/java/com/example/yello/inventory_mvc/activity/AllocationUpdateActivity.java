package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        final int unFulfilledQty = Integer.parseInt(getIntent().getExtras().getString("qtyUnfulfilled"));

        Button b = (Button) findViewById(R.id.button4);
        tvOrderNum.setText(orderNum);
        tvItemCode.setText(itemCode);
        tvDepartment.setText(getIntent().getExtras().getString("departmentCode"));
        tvUnfulfilled.setText(Integer.toString(unFulfilledQty));




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

                String emptyChecker = tvAllocatedQty.getText().toString();
                if(emptyChecker.equals("")){
                    Toast t = Toast.makeText(AllocationUpdateActivity.this, "No value entered.", Toast.LENGTH_SHORT);
                    t.show();
                }

                else if(Integer.parseInt(tvAllocatedQty.getText().toString()) > Integer.parseInt(tvRetrievedQty.getText().toString())){

                    Toast t = Toast.makeText(AllocationUpdateActivity.this, "Cannot allocate more than the Retrieved Qty", Toast.LENGTH_SHORT);
                    t.show();

                }

                else if (Integer.parseInt(tvAllocatedQty.getText().toString()) > unFulfilledQty ) {

                    Toast t = Toast.makeText(AllocationUpdateActivity.this, "Cannot allocate more than the Unfulfilled Qty", Toast.LENGTH_SHORT);
                    t.show();

                }

                else if(Integer.parseInt(tvAllocatedQty.getText().toString()) ==0 ){
                    Toast t = Toast.makeText(AllocationUpdateActivity.this, "No value entered.", Toast.LENGTH_SHORT);
                    t.show();
                }

                else {
                    try {
                        new AsyncTask<String, Void, Void>() {

                            @Override
                            protected Void doInBackground(String... params) {
                                Requisition_Detail.updateRequisitionDetails(params[0], params[1], params[2]);



                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void result) {

                                StringBuilder sb = new StringBuilder();
                                sb.append("Successfully allocated " + tvAllocatedQty.getText().toString() + " to item: " + itemCode  );

                                Toast t = Toast.makeText(AllocationUpdateActivity.this, sb.toString(), Toast.LENGTH_SHORT);
                                t.show();
                                finish();
                            }
                        }.execute(orderNum, itemCode, tvAllocatedQty.getText().toString());
                    } catch (Exception e) {



                    }
                    Intent intent = new Intent(getApplicationContext(), AllocationGroupedByItemActivity.class);
                    intent.putExtra(Key.RETRIEVAL_ITEM_5_ITEMCODE, itemCode);
                    intent.putExtra(Key.RETRIEVAL_ITEM_1_DESCRIPTION, itemDescrp);
                    startActivity(intent);
                }
            }
        });


    }


}
