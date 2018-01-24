package com.example.yello.inventory_mvc.activity;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Retrieval_Item;

import java.util.List;

public class RetrievalListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval_list);

        new AsyncTask<Void, Void, List<Retrieval_Item>>() {

            @Override
            protected List<Retrieval_Item> doInBackground(Void... params) {
                return Retrieval_Item.ListRetrieval();
            }

            @Override
            protected void onPostExecute(List<Retrieval_Item> result) {

                SimpleAdapter adapter =
                        new SimpleAdapter(getApplicationContext(), result,
                                android.R.layout.simple_list_item_2,
                                new String[]{"Item Name", "Qty"},
                                new int[]{android.R.id.text1, android.R.id.text2});
                setListAdapter(adapter);
            }
        }.execute();


    }
}
