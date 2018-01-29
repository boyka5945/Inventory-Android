package com.example.yello.inventory_mvc.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.List;

/**
 * Created by HP on 1/26/2018.
 */

public class RequisitionDetailAdapter extends ArrayAdapter<Requisition_Detail> {
    private List<Requisition_Detail> items;

    int resource;


    public RequisitionDetailAdapter(Context context, int resource, List<Requisition_Detail> items) {
        super(context, resource, items);
        this.resource = resource;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, null);
        final Requisition_Detail detail = items.get(position);

        EditText edit_number = v.findViewById(R.id.editText_requested_qty);
        TextView show_number = v.findViewById(R.id.textView_requested_qty_value);



        if (detail != null) {
            ((TextView) v.findViewById(R.id.textView_item_description_value)).setText(
                    detail.get(
                            Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION));
            ((TextView) v.findViewById(R.id.textView_requested_qty_value)).setText(detail.get(
                    Key.REQUISITION_DETAIL_6_REQUEST_QTY));
            ((TextView) v.findViewById(R.id.textView_received_qty_value)).setText(detail.get(
                    Key.REQUISITION_DETAIL_7_FULFILLED_QTY));
            edit_number.setText(detail.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY));

        }

        if(detail.get(Key.REQUISITION_DETAIL_12_STATUS).equals("Pending Approval"))
        {
            show_number.setVisibility(View.GONE);
            v.findViewById(R.id.textView_received_qty_label).setVisibility(View.GONE);
            v.findViewById(R.id.textView_received_qty_value).setVisibility(View.GONE);
        }
        else
        {
            edit_number.setVisibility(View.GONE);
        }



        return v;
    }
}
