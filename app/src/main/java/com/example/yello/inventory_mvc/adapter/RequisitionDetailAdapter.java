package com.example.yello.inventory_mvc.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.util.List;

/**
 * Created by HP on 1/26/2018.
 */

public class RequisitionDetailAdapter extends ArrayAdapter<Requisition_Detail> {
    private List<Requisition_Detail> items;

    int resource;
    private ListView listView;
    String reqNo;
    String itemCode;
    String qty;

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
        //View view = inflater.inflate(R.layout.fragment_detail_requisition, parent, false);
        final Requisition_Detail detail = items.get(position);
        final Button edit = (Button) v.findViewById(R.id.confirm_button);


        EditText edit_number = v.findViewById(R.id.editText_requested_qty);
        TextView show_number = v.findViewById(R.id.textView_requested_qty_value);



        if (detail != null) {
            ((TextView) v.findViewById(R.id.textView_item_description_value)).setText(
                    detail.get(
                            Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION));
            ((TextView) v.findViewById(R.id.textView_uom_value)).setText(detail.get(
                    Key.REQUISITION_DETAIL_4_ITEM_UOM));
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
            edit.setVisibility(View.GONE);
        }


        edit.setTag(position);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();

                final EditText currentEditText = (EditText) ((View) v.getParent()).findViewById(R.id.editText_requested_qty);
                new AsyncTask<Void, Void, String>()
                {
                    @Override
                    protected  String doInBackground(Void... params)
                    {
                        reqNo = detail.get(Key.REQUISITION_DETAIL_1_REQUISITION_NO);
                        itemCode = detail.get(Key.REQUISITION_DETAIL_2_ITEM_CODE);
                        qty = currentEditText.getText().toString();
                        if (!qty.equals("") && !qty.equals(null)&& !qty.equals("0")){
                            String urls = UrlString.updateReqDetail + "/" + reqNo + "/" + itemCode + "/" + qty;
                            Requisition_Detail.updateRequisitionDetail(urls);
                        }
                        return "0";
                    }

                    @Override
                    protected void onPostExecute(String result)

                    {
                        if(qty.equals("") || qty.equals(null))
                        {
                            Toast.makeText(getContext(), R.string.error_invalid_request_quantity, Toast.LENGTH_LONG).show();
                        }
                        else if(qty.equals("0"))
                        {
                            Toast.makeText(getContext(), R.string.must_be_greater_than_1, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getContext(), R.string.pending_requestedQty_change, Toast.LENGTH_LONG).show();
                        }
                    }
                }.execute();
            }
        });

        return v;

    }


}
