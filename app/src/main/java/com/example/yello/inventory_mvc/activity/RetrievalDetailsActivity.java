package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.example.yello.inventory_mvc.R;


import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;

public class RetrievalDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        setContentView(R.layout.activity_retrieval_details);

        final String name = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_1_DESCRIPTION);
        final String qty = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_2_QTY);
        final String location = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_3_LOCATION);

         TextView itemName = (TextView) findViewById(R.id.tvDescrp);
         TextView loc = (TextView) findViewById(R.id.tvLoc);
         TextView itemCount = (TextView) findViewById(R.id.tvQty);


        itemName.setText( name);
        loc.setText(location);
        itemCount.setText( qty);

        final EditText qtyRetrieved = (EditText) findViewById(R.id.editText3);

        Button confirm = (Button) findViewById(R.id.button3);
/*        Button cancel = (Button) findViewById(R.id.button3);*/






        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String qtyR = qtyRetrieved.getText().toString();

                Retrieval_Item ri = new Retrieval_Item();
                ri.put(Key.RETRIEVAL_ITEM_1_DESCRIPTION, name);
                ri.put(Key.RETRIEVAL_ITEM_2_QTY, qty);
                ri.put(Key.RETRIEVAL_ITEM_3_LOCATION, location);
                ri.put(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED, "123456");

                new AsyncTask<Retrieval_Item, Void, Void>() {





                    @Override
                    protected Void doInBackground(Retrieval_Item... params) {
                        Retrieval_Item.UpdateRetrieval(params[0]);

                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result) {
                        finish();
                    }
                }.execute(ri);



               Intent intent = new Intent(getApplicationContext(), RetrievalListActivity.class);
               startActivity(intent);







            }
        });

    }


}
