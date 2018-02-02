package com.example.yello.inventory_mvc.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Disbursement;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

public class InputQuantityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_quantity);

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);

        final Button button = (Button)findViewById(R.id.confirm);
        final EditText et = (EditText)findViewById((R.id.inputQty));

        final int needqty = Integer.parseInt(getIntent().getExtras().getString("NeedQty"));

        final String stationeryDescription = getIntent().getExtras().getString("StationeryDescription");
        final String itemCode = getIntent().getExtras().getString(Key.STATIONERY_1_ITEM_CODE);
        final String deptCode = getIntent().getExtras().getString(Key.DEPARTMENT_1_CODE);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please input a number.", Toast.LENGTH_LONG).show();
                } else if (needqty < Integer.parseInt(et.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Actual quantity can not be more than need.", Toast.LENGTH_LONG).show();

                } else {
                    String url = UrlString.SaveTmpDisbursement + itemCode +"/" + Integer.toString(needqty) + "/" + et.getText().toString() + "/" + deptCode;
                    Disbursement.updateActualQty(url);

                    Intent intent = new Intent();
                    intent.putExtra("ActualQty", 0);
                    setResult(2, intent);
                    finish();
               }
            }
        });
    }
}
