package com.example.yello.inventory_mvc.activity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.example.yello.inventory_mvc.utility.UrlString;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
    private Button btn2;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState){
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
        btn2 = (Button) findViewById(R.id.signature);
        //TextView itemCount = (TextView) findViewById(R.id.text);

        departmentName.setText(DepartmentName);
        cpoint.setText(CollectionPointID);
        //itemCount.setText("Qty: " + qty);



        lv = (ListView) findViewById(R.id.listv);

        url =  UrlString.GetDisbursementByDept + DepartmentCode;
        //List<Disbursement> list =
        //Disbursement.GetDisbursementList(url);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                Bitmap b = getScreenShot(rootView);
                if (null != b) {
                    ContentValues values = new ContentValues();
                    Uri imageFileUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);// 创建一个新的uri

                    try {
                        OutputStream imageFileOS = getContentResolver().openOutputStream(imageFileUri);// 输出流

                        b.compress(Bitmap.CompressFormat.PNG, 90, imageFileOS);// 生成图片

                        Toast.makeText(getApplicationContext(),
                                "Save successfully", Toast.LENGTH_LONG).show();

                        btn.setVisibility(View.VISIBLE);
                        btn2.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getApplicationContext(), Signature.class);
                        startActivity(intent);

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        });



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




    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static void store(Bitmap bm, String fileName){
        final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
        File dir = new File(dirPath);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void shareImage(File file){
//        Uri uri = Uri.fromFile(file);
//        Intent intent = new Intent(this, Signature.class);
//        intent.setAction(Intent.ACTION_SEND);
//        intent.setType("image/*");
//
//        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
//        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
//        intent.putExtra(Intent.EXTRA_STREAM, uri);
//        try {
//            startActivity(Intent.createChooser(intent, "Share Screenshot"));
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show();
//        }
//    }

}
