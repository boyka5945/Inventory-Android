package com.example.yello.inventory_mvc.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.activity.BrowseCatalogueActivity;
import com.example.yello.inventory_mvc.activity.NewRequisitionActivity;
import com.example.yello.inventory_mvc.activity.NewRequisitionFormActivity;
import com.example.yello.inventory_mvc.activity.WelcomeActivity;
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
    private Requisition_Detail newItem = null;
    private EditText editTextQty = null;
    private TextView textViewItemCode = null;
    private String showBackToCatalogeBtn;
    
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
        textViewItemCode = (TextView) view.findViewById(R.id.textView_description_value);
        
        Bundle bundle = this.getArguments(); // get bundle
        if (bundle != null)
        {
            HashMap<String, String> stationery = (HashMap<String, String>) bundle.getSerializable(Key.BUNDLE_STATIONERY);
            showBackToCatalogeBtn = bundle.getString(Key.BUNDLE_SHOW_BUTTON);
            if(showBackToCatalogeBtn == null)
            {
                showBackToCatalogeBtn = "";
            }
            
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
        
        
        
        Button backToCatalogue = (Button) view.findViewById(R.id.button_back_to_catalogue);
        if(showBackToCatalogeBtn.equals("hide")) // hide the button when in landscape mode
        {
            backToCatalogue.setVisibility(View.GONE);
        }
        
        backToCatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getActivity(), BrowseCatalogueActivity.class));
            }
        });
        
        Button goToForm = (Button) view.findViewById(R.id.button_go_to_form);
        goToForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getActivity(), NewRequisitionFormActivity.class));
            }
        });
        
        
        return view;
    }
    
    @Override
    public void onClick(View v)
    {
        int quantity = TryParse(editTextQty.getText().toString());
        Resources res = getResources();
        
        
        if(editTextQty.getText().equals("") || editTextQty.getText().equals(null))
        {
            Toast.makeText(getActivity(), R.string.error_invalid_request_quantity, Toast.LENGTH_LONG).show();
        }
        else if(quantity <= 0)
        {
            Toast.makeText(getActivity(), R.string.must_be_greater_than_1, Toast.LENGTH_LONG).show();
        }
        else
        {
            newItem.put(Key.REQUISITION_DETAIL_6_REQUEST_QTY, String.valueOf(quantity));
            try
            {
                RequisitionForm.addRequestItem(newItem);
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(), R.string.error_adding_request_item, Toast.LENGTH_LONG).show();
            }
    
            String text = res.getString(R.string.success_add_item_message, quantity, textViewItemCode.getText());
    
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            
            if(!showBackToCatalogeBtn.equals("hide"))
            {
                this.getActivity().finish();
            }
            
        }
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
