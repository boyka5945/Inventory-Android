package com.example.yello.inventory_mvc.activity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Collection_Point;
import com.example.yello.inventory_mvc.model.Department;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.util.List;

public class DisbursementDeptActivity extends ListActivity {

    private String CollectionName;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_dept);

        new AsyncTask<Void, Void, List<Department>>() {

            @Override
            protected List<Department> doInBackground(Void... params) {
                return Department.ListDepartments(UrlString.GetAllDepartments);
            }

            @Override
            protected void onPostExecute(List<Department> result) {

                SimpleAdapter adapter =
                        new SimpleAdapter(getApplicationContext(), result,
                                android.R.layout.simple_list_item_2,
                                new String[]{Key.DEPARTMENT_2_NAME, Key.DEPARTMENT_1_CODE},
                                new int[]{android.R.id.text1, android.R.id.text2});

                setListAdapter(adapter);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onListItemClick(ListView l, View v,
                                   int position, long id) {
        final Department  ri= (Department) getListAdapter().getItem(position);

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... params) {
                List<Collection_Point> cl = Collection_Point.ListCollectionPoint(UrlString.GetAllCollectionPoints);
                for (int i = 0;i<cl.size();i++){
                    if (cl.get(i).get(Key.COLLECTION_POINT_ID) == ri.get(Key.DEPARTMENT_6_COLLECTION_POINT_ID)){
                        CollectionName = cl.get(i).get(Key.COLLECTION_POINT_NAME);
                        break;
                    }
                }
                return 0;
            }

            @Override
            protected void onPostExecute(Integer i) {

            }
        }.execute();

        Intent intent = new Intent(this, DisbursementDetails.class);
        intent.putExtra(Key.DEPARTMENT_1_CODE, ri.get(Key.DEPARTMENT_1_CODE));
        intent.putExtra(Key.DEPARTMENT_2_NAME, ri.get(Key.DEPARTMENT_2_NAME));
        intent.putExtra(Key.COLLECTION_POINT_NAME, CollectionName);
        startActivity(intent);
    }
}
