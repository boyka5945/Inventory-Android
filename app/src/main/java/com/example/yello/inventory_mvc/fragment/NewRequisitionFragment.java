package com.example.yello.inventory_mvc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.RequisitionForm;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewRequisitionFragment extends Fragment implements View.OnClickListener
{
    private List<Requisition_Detail> form = RequisitionForm.getInstance();
    private Requisition_Detail newItem = null;
    private EditText editTextQty = null;
    
    public NewRequisitionFragment()
    {
        // Required empty public constructor
        super();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_requisition, container, false);
        
        editTextQty = (EditText) view.findViewById(R.id.editText_qty_value);
        
        
        Bundle bundle = this.getArguments(); // get bundle
        if (bundle != null)
        {
            HashMap<String, String> stationery = (HashMap<String, String>) bundle.getSerializable(Key.BUNDLE_STATIONERY);
            
            ((TextView) view.findViewById(R.id.textView_category_value)).setText(stationery.get(Key.STATIONERY_4_CATEGORY));
            ((TextView) view.findViewById(R.id.textView_description_value)).setText(stationery.get(Key.STATIONERY_2_DESCRIPTION));
            ((TextView) view.findViewById(R.id.textView_UOM_value)).setText(stationery.get(Key.STATIONERY_3_UOM));
            
            String itemCode = stationery.get(Key.STATIONERY_1_ITEM_CODE);
            String description = stationery.get(Key.STATIONERY_2_DESCRIPTION);
            String uom = stationery.get(Key.STATIONERY_3_UOM);
            
            newItem = new Requisition_Detail(itemCode, description, uom, String.valueOf(0));
        }
        
        Button addToFormBtn = (Button) view.findViewById(R.id.button);
        
        addToFormBtn.setOnClickListener(this);
        return view;
    }
    
    
    @Override
    public void onClick(View v)
    {
        newItem.put(Key.REQUISITION_DETAIL_6_REQUEST_QTY, String.valueOf(editTextQty.getText()));
        form.add(newItem);
        Toast.makeText(getActivity(), editTextQty.getText(), Toast.LENGTH_LONG).show();
    }
}
