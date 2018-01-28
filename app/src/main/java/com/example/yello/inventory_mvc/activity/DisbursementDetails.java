package com.example.yello.inventory_mvc.activity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Department;
import com.example.yello.inventory_mvc.model.Disbursement;
import com.example.yello.inventory_mvc.model.Requisition_Record;
import com.example.yello.inventory_mvc.model.Stationery;
import com.example.yello.inventory_mvc.model.disbursementUpdate;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.List;
import java.util.PriorityQueue;

import static com.example.yello.inventory_mvc.model.Disbursement.GetDisbursementList;

public class DisbursementDetails extends AppCompatActivity {

    //private TextView result;
    private TextView cpoint;
    private TextView departmentName;
    private ListView lv;
    private String url;
    private String DepartmentCode;
    private String DepartmentName;
    private String CollectionPointID;
    private Button btn;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_details);

        DepartmentCode = getIntent().getExtras().getString(Key.DEPARTMENT_1_CODE);
        DepartmentName = getIntent().getExtras().getString(Key.DEPARTMENT_2_NAME);
        CollectionPointID = getIntent().getExtras().getString(Key.DEPARTMENT_6_COLLECTION_POINT_ID);

        //store actual from sub activity
        //result = (TextView) findViewById(R.id.textView7);
        departmentName = (TextView) findViewById(R.id.textView_DepartmentName);
        cpoint = (TextView) findViewById(R.id.textView_collectionPoint);
        btn = (Button) findViewById(R.id.confirmQty);
        //TextView itemCount = (TextView) findViewById(R.id.text);

        departmentName.setText(DepartmentName);
        cpoint.setText(CollectionPointID);
        //itemCount.setText("Qty: " + qty);



        lv = (ListView) findViewById(R.id.listv);

        url = "http://172.17.255.3/AD_Inventory_WCF/Service.svc/GetDisbursementByDept/" + DepartmentCode;
        //List<Disbursement> list =
        //Disbursement.GetDisbursementList(url);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, Integer>() {

                    @Override
                    protected Integer doInBackground(String... params) {
                        //get disbursement
                        //pass argument
                        //1.itemCode/2.deptCode/3.actualQty/4.need
                        List<Disbursement> result = Disbursement.GetDisbursementList(url);
                        for(int i = 0;i< result.size();i++) {
                            //Stationery s = new Stationery(result.get(i).get(Key.STATIONERY_1_ITEM_CODE), null, null, null, null, result.get(i).get("ActualQty"));
                            //Stationery.updateStock(s);
                            disbursementUpdate d = new disbursementUpdate(result.get(i).get(Key.STATIONERY_1_ITEM_CODE), result.get(i).get("NeedQty") , result.get(i).get("ActualQty") , result.get(i).get(Key.DEPARTMENT_1_CODE), Integer.toString(i), "S1000");
                            disbursementUpdate.updateDisbursement(d);
                        }

                        //update details

                        return 0;
                    }

                    @Override
                    protected void onPostExecute(Integer result) {
                        Toast.makeText(getApplicationContext(),
                                "update successfully", Toast.LENGTH_LONG).show();

                    }
                }.execute(DepartmentCode);
            }
        });


        new AsyncTask<String, Void, List<Disbursement>>() {

            @Override
            protected List<Disbursement> doInBackground(String... params) {
                return GetDisbursementList(params[0]);
            }

            @Override
            protected void onPostExecute(List<Disbursement> result) {

                SimpleAdapter adapter =
                        new SimpleAdapter(getApplicationContext(), result,
                                R.layout.disbursement_list_layout,
                                new String[]{"StationeryDescription", "NeedQty", "ActualQty"},
                                new int[]{R.id.textView_stationery, R.id.textView_needQty, R.id.textView_actualQty});

                lv.setAdapter( adapter);
            }
        }.execute(url);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Disbursement  ri= (Disbursement) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getApplicationContext(), InputQuantityActivity.class);
                intent.putExtra(Key.STATIONERY_1_ITEM_CODE, ri.get(Key.STATIONERY_1_ITEM_CODE));
                intent.putExtra("StationeryDescription", ri.get("StationeryDescription"));
                intent.putExtra("NeedQty", ri.get("NeedQty"));
                intent.putExtra("ActualQty", ri.get("ActualQty"));
                intent.putExtra(Key.DEPARTMENT_1_CODE, ri.get(Key.DEPARTMENT_1_CODE));
                startActivityForResult(intent, 1);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == 1) {
                int actualQty = data.getIntExtra("ActualQty", 0);
                new AsyncTask<String, Void, List<Disbursement>>() {

                    @Override
                    protected List<Disbursement> doInBackground(String... params) {
                        return GetDisbursementList(params[0]);
                    }

                    @Override
                    protected void onPostExecute(List<Disbursement> result) {

                        SimpleAdapter adapter =
                                new SimpleAdapter(getApplicationContext(), result,
                                        R.layout.disbursement_list_layout,
                                        new String[]{"StationeryDescription", "NeedQty", "ActualQty"},
                                        new int[]{R.id.textView_stationery, R.id.textView_needQty, R.id.textView_actualQty});

                        lv.setAdapter( adapter);
                    }
                }.execute(url);
            }else{
                Toast.makeText(getApplicationContext(),
                        "not input anything.", Toast.LENGTH_LONG).show();
            }

        }
    }
}
