package com.example.yello.inventory_mvc.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.yello.inventory_mvc.R;


import com.example.yello.inventory_mvc.utility.Key;

public class RetrievalDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval_details);

        String name = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_1_DESCRIPTION);
        String qty = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_2_QTY);
        String location = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_3_LOCATION);

        TextView itemName = (TextView) findViewById(R.id.textview_descrp);
        TextView loc = (TextView) findViewById(R.id.textview_location);
        TextView itemCount = (TextView) findViewById(R.id.textview_qty);

        itemName.setText("Item Name: " + name);
        loc.setText("Location: " + location);
        itemCount.setText("Qty: " + qty);




    }
}
