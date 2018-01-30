package com.example.yello.inventory_mvc.activity;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Disbursement;

import java.util.List;


import static com.example.yello.inventory_mvc.utility.UrlString.GetDepartment;
import static com.example.yello.inventory_mvc.utility.UrlString.GetPendingItemsByItem;

public class ViewPendingItemActivity extends AppCompatActivity {
    private String url2 = GetPendingItemsByItem+"ZOOL";
    private String url1 = GetDepartment+"ZOOL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending_item);
        final ListView lv = findViewById(R.id.pending_item_list);

        new AsyncTask<String, Void, List<Disbursement>>() {
            @Override
            protected List<Disbursement> doInBackground(String... params) {
                return Disbursement.GetDisbursementList(params[0]);
            }
            @Override
            protected void onPostExecute(List<Disbursement> result) {
//
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), result,
                        R.layout.collect_item_row,
                        new String[]{"StationeryDescription", "ItemCode", "NeedQty"},
                        new int[]{ R.id.textView_wh1,R.id.textView_wh2,R.id.textView_wh3});
                lv.setAdapter(adapter);
            }

        }.execute(url1);
    }
}
