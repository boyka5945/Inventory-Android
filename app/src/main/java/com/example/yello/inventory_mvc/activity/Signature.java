package com.example.yello.inventory_mvc.activity;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;

public class Signature extends Activity implements OnTouchListener,
        OnClickListener {
    private float downX;
    private float downY;
    private float upX;
    private float upY;
    Matrix matrix;
    private Canvas canvas;
    private Paint paint;
    private ImageView mImageView;
    private Bitmap alteredBitmap;
    Bitmap chooseBitmap;
    // Point
    private Point point = null;

    List<Point> list = null;

    private List<List<Point>> linesList = null;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        Button pickImageBtn = (Button) findViewById(R.id.pickImageBtn);
        Button saveBtn = (Button) findViewById(R.id.saveBtn);

        mImageView = (ImageView) findViewById(R.id.pickedImage);

        pickImageBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);


        list = new ArrayList<Point>();
        linesList = new ArrayList<List<Point>>();


    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            }
        } else {
            //Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        Log.d("touch_draw", "ontouch()");

        point = new Point((int) event.getX(), (int) event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                list.add(point);
                break;
            case MotionEvent.ACTION_MOVE:
                upX = event.getX();
                upY = event.getY();
                canvas.drawLine(downX, downY, upX, upY, paint);
                mImageView.invalidate();
                downX = upX;
                downY = upY;
                list.add(point);
                break;
            case MotionEvent.ACTION_UP:
                list.add(point);

                List<Point> list_temp = new ArrayList<Point>(list);
                linesList.add(list_temp);

                list.clear();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;

        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            switch (requestCode) {
                //Location
                case 3:
                    break;
                //Read External Storage
                case 4:
                    break;
            }
            //Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        Log.d("bitmap", "has onClick");
        switch (v.getId()) {

            case R.id.pickImageBtn:

                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,4);
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 11);
                break;

            case R.id.saveBtn:


                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,3);
                if (null != alteredBitmap) {
                    Uri imageFileUri = getContentResolver().insert(
                            Media.EXTERNAL_CONTENT_URI, new ContentValues());

                    try {
                        OutputStream imageFileOS = getContentResolver()
                                .openOutputStream(imageFileUri);

                        alteredBitmap
                                .compress(CompressFormat.JPEG, 90, imageFileOS);

                        Toast.makeText(this, "has saved", Toast.LENGTH_SHORT)
                                .show();

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(this, "请选择图片！", Toast.LENGTH_SHORT)
                            .show();
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d("bitmap", "requestCode is :" + requestCode);
        if (resultCode == RESULT_OK) {
            Log.d("bitmap", "has result ok");
            Uri uri = intent.getData();

            try {
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;
                chooseBitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(uri), null, opts);
                opts.inJustDecodeBounds = false;
                chooseBitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(uri), null, opts);
                Log.d("bitmap", "chooseBitmap is :" + chooseBitmap);

                alteredBitmap = Bitmap.createBitmap(chooseBitmap.getWidth(),
                        chooseBitmap.getHeight(), chooseBitmap.getConfig());
                canvas = new Canvas(alteredBitmap);
                paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStyle(Style.FILL_AND_STROKE);
                matrix = new Matrix();
                canvas.drawBitmap(chooseBitmap, matrix, paint);

                mImageView.setImageBitmap(alteredBitmap);
                mImageView.setOnTouchListener(this);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}