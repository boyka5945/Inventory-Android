package com.example.yello.inventory_mvc.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Disbursement;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
        CollectionPointID = getIntent().getExtras().getString(Key.COLLECTION_POINT_NAME);

        //store actual from sub activity
        //result = (TextView) findViewById(R.id.textView7);
        departmentName = (TextView) findViewById(R.id.textView_DepartmentName);
        cpoint = (TextView) findViewById(R.id.textView_collectionPoint);
        btn = (Button) findViewById(R.id.confirmQty);
        //TextView itemCount = (TextView) findViewById(R.id.text);

        departmentName.setText(DepartmentName);
        cpoint.setText(CollectionPointID);
        //itemCount.setText("Qty: " + qty);


        url = UrlString.GetDisbursementByDept + DepartmentCode;
        lv = (ListView) findViewById(R.id.listv);


        //List<Disbursement> list =
        //Disbursement.GetDisbursementList(url);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setVisibility(View.GONE);
                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                Bitmap b = getScreenShot(rootView);

                if (null != b) {
                        addJpgSignatureToGallery(b);
                        btn.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getApplicationContext(), Signature.class);
                        intent.putExtra(Key.DEPARTMENT_1_CODE, DepartmentCode);
                        startActivity(intent);
                }

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


    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", 1));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
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
