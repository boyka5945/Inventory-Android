package com.example.yello.inventory_mvc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yello.inventory_mvc.R;

/**
 * Created by HP on 1/25/2018.
 */

public class DetailRequisitionFragment extends Fragment
{


    public DetailRequisitionFragment()
    {
        // Required empty public constructor
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_requisition, container, false);

    }

}
