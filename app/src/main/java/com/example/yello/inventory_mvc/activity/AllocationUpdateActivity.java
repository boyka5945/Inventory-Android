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


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Requisition_Detail.updateRequisitionDetails(orderNum, itemCode, tvAllocatedQty.getText().toString());
                Intent intent = new Intent(getApplicationContext(), AllocationGroupedByItemActivity.class);
                startActivity(intent);
            }
        });

        tvOrderNum.setText(orderNum);
        tvItemCode.setText(itemCode);
        tvDepartment.setText(getIntent().getExtras().getString("departmentCode"));
        tvUnfulfilled.setText(getIntent().getExtras().getString("qtyUnfulfilled"));


/*        new AsyncTask<Void, Void, Requisition_Detail>() {

            @Override
            protected Requisition_Detail doInBackground(Void... params) {
                return Requisition_Detail.GetRequisitionDetailsBy2Keys(itemCode, orderNum);
            }

            @Override
            protected void onPostExecute(Requisition_Detail result) {

                tvDepartment.setText(result.get(Key.REQUISITION_RECORD_2_DEPT_CODE));
                //tvEmployee.setText(result.get(Key.Requisi)); //need to get from req details model/delete


            }
        }.execute();*/

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


    }


}
