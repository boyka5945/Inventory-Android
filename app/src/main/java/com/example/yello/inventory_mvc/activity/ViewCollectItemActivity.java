package com.example.yello.inventory_mvc.activity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.AsyncTask;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.example.yello.inventory_mvc.R;

import com.example.yello.inventory_mvc.model.Department;
import com.example.yello.inventory_mvc.model.Disbursement;
import com.example.yello.inventory_mvc.model.LoginUser;
import com.example.yello.inventory_mvc.utility.Key;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.yello.inventory_mvc.utility.UrlString.GetDepartment;
import static com.example.yello.inventory_mvc.utility.UrlString.GetDisbursementByDept;


public class ViewCollectItemActivity extends AppCompatActivity {

    private String url = GetDisbursementByDept+"ZOOL";
//    private String departmentCode = LoginUser.deptCode.toString();
    private TextView cpoint;
    private TextView departmentName;
private TextView rep;

    private String DepartmentCode;
    private String DepartmentName;
    private String CollectionPointID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collect_item);


//        departmentName = (TextView) findViewById(R.id.collect_item_department);
//        cpoint = (TextView) findViewById(R.id.collect_item_collectpoint);
//        rep = (TextView) findViewById(R.id.collect_item_representative);
////
////
//        departmentName.setText("Department: "+ Key.DEPARTMENT_2_NAME);
//        cpoint.setText("Collection Point: "+ Key.DEPARTMENT_7_COLLECTION_NAME);
//        rep.setText("Representative: "+ Key.USER_3_NAME);


    final ListView lv = findViewById(R.id.collect_item_list);


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

        }.execute(url);
    }

}