package com.example.yello.inventory_mvc.activity;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.RequisitionForm;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.List;

public class NewRequisitionFormActivity extends ListActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition_form);
        this.setListAdapter(new SimpleAdapter(
                this,
                RequisitionForm.getInstance(),
                android.R.layout.simple_list_item_2,
                new String[]{Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION, Key.REQUISITION_DETAIL_6_REQUEST_QTY},
                new int[]{android.R.id.text1, android.R.id.text2}));
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Requisition_Detail requestItem = (Requisition_Detail) getListAdapter().getItem(position);
        Toast.makeText(getApplicationContext(), requestItem.get(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION) + " selected", Toast.LENGTH_LONG).show();
    }
    
}