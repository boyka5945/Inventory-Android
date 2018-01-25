package com.example.yello.inventory_mvc.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.RequisitionForm;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.List;

/**
 * Created by CK Tan on 1/25/2018.
 */

public class NewRequisitionFormAdapter extends ArrayAdapter<Requisition_Detail>
{
    private List<Requisition_Detail> items;
    int resource;
    Button plusButton;
    Button minusButton;
    EditText editTextQty;
    
    public NewRequisitionFormAdapter(Context context, int resource, List<Requisition_Detail> items)
    {
        super(context, resource, items);
        this.resource = resource;
        this.items = items;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(resource, null);
        
        editTextQty = ((EditText) view.findViewById(R.id.editText_quantity));
        editTextQty.setTag(position);
        
        final Requisition_Detail detail = items.get(position);
        if (detail != null)
        {
            ((TextView) view.findViewById(R.id.textView_description_value)).setText(detail.get(
                    Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION));
            ((TextView) view.findViewById(R.id.textView_UOM_value)).setText(detail.get(
                    Key.REQUISITION_DETAIL_4_ITEM_UOM));
            editTextQty.setText(detail.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY));
        }
        
        editTextQty.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    int position = (Integer) v.getTag();
                    Requisition_Detail requestItem = items.get(position);
    
                    int newQty = TryParse(editTextQty.getText().toString());
                    
                    if(newQty == 0)
                    {
                        editTextQty.setText(requestItem.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY));
                        Toast.makeText(getContext(), "Quantity must be greater than or equal to 1.",
                                       Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    else if (RequisitionForm.changeRequestQty(requestItem.get(Key.REQUISITION_DETAIL_2_ITEM_CODE), newQty))
                    {
                        editTextQty.setText(String.valueOf(newQty));
                        Toast.makeText(getContext(), "Quantity has been updated.", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    else
                    {
                        editTextQty.setText(requestItem.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY));
                        Toast.makeText(getContext(), "Opps...Some error occur. Pleas try again.",
                                       Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                return false;
            }
        });
        
        // TODO : Use string resource
        plusButton = (Button) view.findViewById(R.id.plus_button);
        plusButton.setTag(position);
        plusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = (Integer) v.getTag();
                Requisition_Detail requestItem = items.get(position);
                
                if (RequisitionForm.updateRequestQty(
                        requestItem.get(Key.REQUISITION_DETAIL_2_ITEM_CODE), 1))
                {
                    int newQty = Integer.valueOf(editTextQty.getText().toString()) + 1;
                    editTextQty.setText(String.valueOf(newQty));
                    
                    Toast.makeText(getContext(), "Quantity +1", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Opps...Some error occur. Pleas try again.",
                                   Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        // TODO : Use string resource
        minusButton = (Button) view.findViewById(R.id.minus_button);
        minusButton.setTag(position);
        minusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = (Integer) v.getTag();
                Requisition_Detail requestItem = items.get(position);
                
                if (RequisitionForm.updateRequestQty(
                        requestItem.get(Key.REQUISITION_DETAIL_2_ITEM_CODE), -1))
                {
                    int newQty = Integer.valueOf(editTextQty.getText().toString()) - 1;
                    editTextQty.setText(String.valueOf(newQty));
                    
                    Toast.makeText(getContext(), "Quantity -1", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),
                                   "Request quantity must be greater than or equal to 1",
                                   Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        
        return view;
    }
    
    private Integer TryParse(String s)
    {
        
        try
        {
            return Integer.valueOf(s);
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    
}
