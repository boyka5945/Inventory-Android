//package com.example.yello.inventory_mvc.fragment;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.app.ListFragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//
//import com.example.yello.inventory_mvc.R;
//import com.example.yello.inventory_mvc.activity.DetailRequisitionActivity;
//import com.example.yello.inventory_mvc.model.Requisition_Detail;
//import com.example.yello.inventory_mvc.model.Requisition_Record;
//import com.example.yello.inventory_mvc.utility.Key;
//import com.example.yello.inventory_mvc.utility.UrlString;
//
//import java.util.List;
//
///**
// * Created by HP on 1/24/2018.
// */
//
//public class ViewRequisitionRecordsFragment extends ListFragment
//{
//    private Button edit;
//    private Button remove;
//
//    public ViewRequisitionRecordsFragment()
//    {
//        // Required empty public constructor
//        super();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState)
//    {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_requisition_records_view, container, false);
//
//        String url = null;
//
//        Bundle bundle = this.getArguments(); // Get bundle from starting activity
//        if(bundle != null)
//        {
//            url = bundle.getString(Key.BUNDLE_URL);
//        }
//
//        if(url == null) // If no search query from activity, get all stationery
//        {
//            url = UrlString.getAllRequisitionRecords;
//        }
//
//        final Activity containerActivity = this.getActivity(); // get starting activity
//
//        new AsyncTask<String, Void, List<Requisition_Record>>()
//        {
//
//            @Override
//            protected List<Requisition_Record> doInBackground(String... strings)
//            {
//                return Requisition_Record.ListRequisitionRecord(strings[0]);
//            }
//
//            @Override
//            protected void onPostExecute(List<Requisition_Record> result)
//            {
//                SimpleAdapter adapter = new SimpleAdapter(containerActivity.getApplicationContext(),
//                        result,
//                        android.R.layout.simple_list_item_2,
//                        new String[] {Key.REQUISITION_RECORD_1_REQUISITION_NO, Key.REQUISITION_RECORD_9_STATUS},
//                        new int[] {android.R.id.text1, android.R.id.text2});
//
//                ViewRequisitionRecordsFragment.this.setListAdapter(adapter);
//            }
//        }.execute(url);
//
//        return view;
//    }
//
//
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Requisition_Record record = (Requisition_Record) l.getAdapter().getItem(position); // get selected requisition
//        /*if (record.get(Key.REQUISITION_RECORD_9_STATUS).equals("1"))
//        {
//            edit = (Button) getActivity().findViewById(R.id.edit_button);
//            edit.setVisibility(View.VISIBLE);
//            remove = (Button) getActivity().findViewById(R.id.remove_button);
//            remove.setVisibility(View.VISIBLE);
//        }*/
//        if(getActivity().findViewById(R.id.frameLayoutRequisitionRecordInfo) == null)
//        {
//            //single-pane
//            Intent intent = new Intent (getActivity(),DetailRequisitionActivity.class);
//            intent.putExtra(Key.BUNDLE_REQUISITION, record.get(Key.REQUISITION_RECORD_1_REQUISITION_NO));
//            startActivity(intent);
//        }
//        else
//        {
//            //multi-pane
//            displayDetails(record.get(Key.REQUISITION_RECORD_1_REQUISITION_NO));
//        }
//    }
//
//
//
//    protected void displayDetails(String reqNo)
//    {
//        final String TAG = "DETAIL_REQUISITION_FRAGMENT";
//        FragmentManager manager = getFragmentManager(); // get fragment manager
//        FragmentTransaction transaction = manager.beginTransaction(); // get transaction
//
//        Fragment fragment = new DetailRequisitionFragment(); // initialize fragment
//        Bundle bundle = new Bundle();
//        bundle.putString(Key.BUNDLE_REQUISITION, reqNo); // put selected requisition into bundle
//        fragment.setArguments(bundle); // put bundle inside fragment
//
//        if(manager.findFragmentByTag(TAG) == null) // first time
//        {
//            transaction.add(R.id.frameLayoutRequisitionRecordInfo, fragment, TAG); // new
//        }
//        else // not first time
//        {
//            transaction.replace(R.id.frameLayoutRequisitionRecordInfo, fragment, TAG); // replace old fragmet
//        }
//
//        transaction.commit(); // commit transaction
//
//
//
//    }
//}

package com.example.yello.inventory_mvc.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.activity.DetailRequisitionActivity;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.model.Requisition_Record;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import java.util.List;

/**
 * Created by HP on 1/24/2018.
 */

public class ViewRequisitionRecordsFragment extends ListFragment
{
    private Button edit;
    private Button remove;

    public ViewRequisitionRecordsFragment()
    {
        // Required empty public constructor
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requisition_records_view, container, false);

        String url = null;

        Bundle bundle = this.getArguments(); // Get bundle from starting activity
        if(bundle != null)
        {
            url = bundle.getString(Key.BUNDLE_URL);
        }

        if(url == null) // If no search query from activity, get all stationery
        {
            url = UrlString.getAllRequisitionRecords;
        }

        final Activity containerActivity = this.getActivity(); // get starting activity

        new AsyncTask<String, Void, List<Requisition_Record>>()
        {

            @Override
            protected List<Requisition_Record> doInBackground(String... strings)
            {
                return Requisition_Record.ListRequisitionRecord(strings[0]);
            }

            @Override
            protected void onPostExecute(List<Requisition_Record> result)
            {
                SimpleAdapter adapter = new SimpleAdapter(containerActivity.getApplicationContext(),
                        result,
                        android.R.layout.simple_list_item_2,
                        new String[] {Key.REQUISITION_RECORD_1_REQUISITION_NO, Key.REQUISITION_RECORD_9_STATUS},
                        new int[] {android.R.id.text1, android.R.id.text2});

                ViewRequisitionRecordsFragment.this.setListAdapter(adapter);
            }
        }.execute(url);

        return view;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Requisition_Record record = (Requisition_Record) l.getAdapter().getItem(position); // get selected requisition
        /*if (record.get(Key.REQUISITION_RECORD_9_STATUS).equals("1"))
        {
            edit = (Button) getActivity().findViewById(R.id.edit_button);
            edit.setVisibility(View.VISIBLE);
            remove = (Button) getActivity().findViewById(R.id.remove_button);
            remove.setVisibility(View.VISIBLE);
        }*/
        if(getActivity().findViewById(R.id.frameLayoutRequisitionRecordInfo) == null)
        {
            //single-pane
            Intent intent = new Intent (getActivity(),DetailRequisitionActivity.class);
            String s = record.get(Key.REQUISITION_RECORD_1_REQUISITION_NO);
            intent.putExtra(Key.BUNDLE_REQUISITION, record.get(Key.REQUISITION_RECORD_1_REQUISITION_NO));
            startActivity(intent);
        }
        else
        {
            //multi-pane
            displayDetails(record.get(Key.REQUISITION_RECORD_1_REQUISITION_NO));
        }
    }



    protected void displayDetails(String reqNo)
    {
        final String TAG = "DETAIL_REQUISITION_FRAGMENT";
        FragmentManager manager = getFragmentManager(); // get fragment manager
        FragmentTransaction transaction = manager.beginTransaction(); // get transaction

        Fragment fragment = new DetailRequisitionFragment(); // initialize fragment
        Bundle bundle = new Bundle();
        bundle.putString(Key.BUNDLE_REQUISITION, reqNo); // put selected requisition into bundle
        fragment.setArguments(bundle); // put bundle inside fragment

        if(manager.findFragmentByTag(TAG) == null) // first time
        {
            transaction.add(R.id.frameLayoutRequisitionRecordInfo, fragment, TAG); // new
        }
        else // not first time
        {
            transaction.replace(R.id.frameLayoutRequisitionRecordInfo, fragment, TAG); // replace old fragmet
        }

        transaction.commit(); // commit transaction



    }
}
