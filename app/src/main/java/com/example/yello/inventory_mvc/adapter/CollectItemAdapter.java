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
//        View v = inflater.inflate(resource, null);
        final Disbursement disburse = items.get(position);
     View rowView = inflater.inflate(R.layout.collect_item_row, parent, false);
        TextView textViewItemName = (TextView) rowView.findViewById(R.id.textView5);
        TextView textViewItemCat = (TextView) rowView.findViewById(R.id.textView6);
        TextView textViewItemQty = (TextView) rowView.findViewById(R.id.textView7);

//        textViewItemName.setText(items[position]);
//
//        // Change icon based on name
//        String s = items[position];

        System.out.println(disburse);

        if (items != null) {
            ((TextView) rowView.findViewById(R.id.textView5)).setText(
                    disburse.get("StationeryDescription"));
            ((TextView) rowView.findViewById(R.id.textView6)).setText(disburse.get(
                    "ActualQty"));
            ((TextView) rowView.findViewById(R.id.textView7)).setText(disburse.get(
                    "NeedQty"));
        }
        return rowView;
    }


}
