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
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;


import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;

public class RetrievalDetailsActivity extends AppCompatActivity {

    private  int Qty = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        setContentView(R.layout.activity_retrieval_details);

        final int retrievedQty = Integer.parseInt( getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED));
        final String name = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_1_DESCRIPTION);
        final String qty = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_2_QTY);
        final String location = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_3_LOCATION);

        Qty = retrievedQty;

        TextView itemName = (TextView) findViewById(R.id.tvDescrp);
         TextView loc = (TextView) findViewById(R.id.tvLoc);
         TextView itemCount = (TextView) findViewById(R.id.tvQty);
         Button plus = (Button) findViewById(R.id.button5);
         Button minus = (Button) findViewById(R.id.button6) ;


        itemName.setText( name);
        loc.setText(location);
        itemCount.setText( qty);

        final EditText qtyRetrieved = (EditText) findViewById(R.id.editText3);
        qtyRetrieved.setText(Integer.toString(retrievedQty));



        Button confirm = (Button) findViewById(R.id.button3);
        Button cancel = (Button) findViewById(R.id.button2);

        plus.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {


                        qtyRetrieved.setText(Integer.toString(Qty++));

                    }
                }
        );

        minus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Integer.parseInt(qtyRetrieved.getText().toString()) - 1 >= 0) {

                            qtyRetrieved.setText(Integer.toString(Qty--));
                        }
                    }
                }
        );

        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), RetrievalListActivity.class);
                        startActivity(intent);

                    }}

        );





        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String qtyR = qtyRetrieved.getText().toString();

                Retrieval_Item ri = new Retrieval_Item();
                ri.put(Key.RETRIEVAL_ITEM_1_DESCRIPTION, name);
                ri.put(Key.RETRIEVAL_ITEM_2_QTY, qty);
                ri.put(Key.RETRIEVAL_ITEM_3_LOCATION, location);
                ri.put(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED, qtyR);



                    new AsyncTask<Retrieval_Item, Void, String>() {


                        @Override
                        protected String doInBackground(Retrieval_Item... params) {
                            Retrieval_Item.UpdateRetrieval(params[0]);

                            return null;
                        }

                        @Override
                        protected void onPostExecute(String result) {

                            if(result.toLowerCase() == "true"){
                                StringBuilder sb = new StringBuilder();
                                sb.append("Retrieved" +" " + qtyR +" " + name );
                                Toast t = Toast.makeText(RetrievalDetailsActivity.this, sb.toString(), Toast.LENGTH_SHORT);
                                t.show();
                            }

                            else{

                                StringBuilder sb = new StringBuilder();
                                sb.append("An Error occured: " + result);
                                Toast t = Toast.makeText(RetrievalDetailsActivity.this , sb.toString() , Toast.LENGTH_SHORT);
                                t.show();

                            }
                            finish();
                        }
                    }.execute(ri);






               Intent intent = new Intent(getApplicationContext(), RetrievalListActivity.class);
               startActivity(intent);







            }
        });

    }


}
