package com.example.yello.inventory_mvc.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.ArrayList;
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

                List<Retrieval_Item>removal = new ArrayList<>();
                for (Retrieval_Item r: result){
                    if(Integer.parseInt(r.get(Key.RETRIEVAL_ITEM_2_QTY).toString()) ==Integer.parseInt(r.get(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED).toString())){
                        removal.add(r);
                    }
                }

                result.removeAll(removal);

                SimpleAdapter adapter =
                        new SimpleAdapter(RetrievalListActivity.this, result,
                                R.layout.allocation_row,
                                new String[]{Key.RETRIEVAL_ITEM_1_DESCRIPTION, Key.RETRIEVAL_ITEM_2_QTY, Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED},
                                new int[]{R.id.itemNameCol, R.id.totalReqCol, R.id.totalRetCol});

                setListAdapter(adapter);
            }
        }.execute();


    }

    @Override
    protected void onListItemClick(ListView l, View v,
                                   int position, long id) {


        Retrieval_Item  ri= (Retrieval_Item) getListAdapter().getItem(position);
        Intent intent = new Intent(this, RetrievalDetailsActivity.class);
        intent.putExtra((Key.RETRIEVAL_ITEM_5_ITEMCODE), ri.get(Key.RETRIEVAL_ITEM_5_ITEMCODE));
        intent.putExtra(Key.RETRIEVAL_ITEM_1_DESCRIPTION, ri.get(Key.RETRIEVAL_ITEM_1_DESCRIPTION));
        intent.putExtra(Key.RETRIEVAL_ITEM_2_QTY, ri.get(Key.RETRIEVAL_ITEM_2_QTY));
        intent.putExtra(Key.RETRIEVAL_ITEM_3_LOCATION, ri.get(Key.RETRIEVAL_ITEM_3_LOCATION));
        intent.putExtra(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED, ri.get(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED));

        startActivity(intent);
    }


}
