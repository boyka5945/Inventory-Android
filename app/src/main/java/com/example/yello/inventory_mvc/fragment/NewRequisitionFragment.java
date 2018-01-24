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
import com.example.yello.inventory_mvc.utility.Key;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewRequisitionFragment extends Fragment
{
    public NewRequisitionFragment()
    {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_requisition, container, false);
        
        final EditText editTextQty = (EditText) view.findViewById(R.id.editText_qty_value);
        
        Bundle bundle = this.getArguments(); // get bundle
    
        if (bundle != null)
        {
            HashMap<String, String> stationery = (HashMap<String, String>) bundle.getSerializable(Key.BUNDLE_STATIONERY);
            
            ((TextView) view.findViewById(R.id.textView_category_value)).setText(stationery.get(Key.STATIONERY_4_CATEGORY));
            ((TextView) view.findViewById(R.id.textView_description_value)).setText(stationery.get(Key.STATIONERY_2_DESCRIPTION));
            ((TextView) view.findViewById(R.id.textView_UOM_value)).setText(stationery.get(Key.STATIONERY_3_UOM));
        }
        
        Button addToFormBtn = (Button) view.findViewById(R.id.button);
        addToFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
    
                Toast.makeText(getActivity(), editTextQty.getText(), Toast.LENGTH_LONG).show();
            }
        });
    
        return view;
    }
    
    
}
