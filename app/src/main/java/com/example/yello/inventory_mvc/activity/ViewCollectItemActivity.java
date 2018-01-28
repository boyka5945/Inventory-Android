package com.example.yello.inventory_mvc.activity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.adapter.CollectItemAdapter;
import com.example.yello.inventory_mvc.adapter.NewRequisitionFormAdapter;
import com.example.yello.inventory_mvc.model.Disbursement;
import com.example.yello.inventory_mvc.model.RequisitionForm;

import java.util.List;

import static com.example.yello.inventory_mvc.utility.UrlString.GetDisbursementByDept;


public class ViewCollectItemActivity extends ListActivity {




    private String url = GetDisbursementByDept+"ZOOL";
    @SuppressLint({"StaticFieldLeak", "SetTextI18n"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_collect_item);

      //TextView t1 = findViewById(R.id.collect_item_title);
     // t1.setText("Items Ready to be Collected");
//        t1.setText("Items Ready to be Collected");
       //TextView t2 = findViewById(R.id.collect_item_department);
//        t1.setText("Department Name:");
       //TextView t3 = findViewById(R.id.collect_item_collectpoint);
//        t1.setText("shit");
       //TextView t4 = findViewById(R.id.collect_item_representative);
//        t1.setText("Collection Point:");
//        t5 = findViewById(R.id.collect_item_cpshow);
//        t1.setText("shit");
//        t6 = findViewById(R.id.collect_item_rep);
//        t1.setText("Representative:");
//        t7 = findViewById(R.id.collect_item_repshow);
//        t1.setText("shit");

//        setContentView(R.layout.activity_view_collect_item);

      //  listView listView = (ListView) this.findViewById(R.id.listView);
        //adapter = new CollectItemAdapter(this, R.layout.collect_item_row,
         //       Disbursement.GetDisbursementList(url));


        //registerForContextMenu(listView);

        new AsyncTask<String, Void, List<Disbursement>>() {
            @Override
            protected List<Disbursement> doInBackground(String... params) {
                return Disbursement.GetDisbursementList(params[0]);
            }
            @Override
            protected void onPostExecute(List<Disbursement> result) {
//               CollectItemAdapter adapter = new CollectItemAdapter(get),
//                        result, R.layout.collect_item_row,
//                        new String[]{"StationeryDescription", "NeedQty"},
//                        new int[]{ R.id.text, R.id.text2});
//                setListAdapter(adapter);
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), result,
                        R.layout.collect_item_row,
                        new String[]{"StationeryDescription", "ItemCode", "NeedQty"},
                        new int[]{ R.id.textView_wh1,R.id.textView_wh2,R.id.textView_wh3});
                setListAdapter(adapter);
                }

        }.execute(url);
    }

}