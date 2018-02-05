package com.example.yello.inventory_mvc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Disbursement;


import java.util.List;

/**
 * Created by weihan on 27/1/2018.
 */

public class CollectItemAdapter extends ArrayAdapter<Disbursement>{

    private final Context context;

    private List<Disbursement> items;

    int resource;


    public CollectItemAdapter(Context context, int resource, List<Disbursement> items) {
        super(context, resource, items);
        this.resource = resource;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


     View rowView = inflater.inflate(resource,null);
        final Disbursement disburse = items.get(position);


        if (disburse != null) {
            TextView text1 = (TextView) rowView.findViewById(R.id.textView5);
            text1.setText(disburse.get("StationeryDescription"));
            TextView text2 = (TextView) rowView.findViewById(R.id.textView6);
            text2.setText(disburse.get("ActualQty"));
            TextView text3 = (TextView) rowView.findViewById(R.id.textView7);
            text3.setText(disburse.get("NeedQty"));
        }
        return rowView;
    }


}
