package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.yello.inventory_mvc.R;

import com.example.yello.inventory_mvc.model.Collection_Point;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.util.List;

public class ChangeCollectionPointActivity extends AppCompatActivity
{

    private Spinner collectionPointSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_collection_point);

        collectionPointSpinner = (Spinner) findViewById(R.id.Collection_point_spinner);
        new AsyncTask<String,Void,List<Collection_Point>>(){
            @Override
            protected List<Collection_Point> doInBackground(String... strings)
            {
                return Collection_Point.ListCollectionPoint(strings[0]);
            }

            @Override
            protected void onPostExecute(List<Collection_Point> result)
            {
                SimpleAdapter adapter = new SimpleAdapter(ChangeCollectionPointActivity.this,
                        result,
                        android.R.layout.simple_list_item_1,
                        new String[]{Key.COLLECTION_POINT_NAME},
                        new int[]{android.R.id.text1});
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                collectionPointSpinner.setAdapter(adapter);
            }

        }.execute(UrlString.GetAllCollectionPoints);
       // collectionPointSpinner.setOnItemSelectedListener(this);

        // Handle search query
      //  Intent intent = this.getIntent();
       // handleIntent(intent);
    }
}
