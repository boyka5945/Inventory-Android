package com.example.yello.inventory_mvc.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.adapter.NewRequisitionFormAdapter;
import com.example.yello.inventory_mvc.model.RequisitionForm;
import com.example.yello.inventory_mvc.model.Requisition_Detail;
import com.example.yello.inventory_mvc.model.Stationery;
import com.example.yello.inventory_mvc.utility.Key;

import java.util.List;

public class NewRequisitionFormActivity extends Activity
{
    private Button submitButton;
    private Button clearButton;
    private ListView listView;
    private NewRequisitionFormAdapter adapter;
    
    // TODO: Create land layout
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition_form);
        
        listView = (ListView) this.findViewById(R.id.listView);
        adapter = new NewRequisitionFormAdapter(this, R.layout.new_requistion_row,
                                                RequisitionForm.getInstance());
        listView.setAdapter(adapter);
//        listView.setLongClickable(true);
//        listView.setOnItemLongClickListener(this);
        registerForContextMenu(listView);
        
        submitButton = (Button) this.findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AsyncTask<String, Void, Boolean>()
                {
                    @Override
                    protected Boolean doInBackground(String... params)
                    {
                        if (RequisitionForm.getLength() == 0)
                        {
                            return false;
                        }
                        
                        try
                        {
                            Requisition_Detail.addNewRequisition(RequisitionForm.getInstance());
                            return true;
                        }
                        catch (Exception e)
                        {
                            return false;
                        }
                        
                    }
                    
                    @Override
                    protected void onPostExecute(Boolean result)
                    {
                        if (result)
                        {
                            Toast.makeText(NewRequisitionFormActivity.this,
                                           "Requsition form was submitted",
                                           Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(NewRequisitionFormActivity.this,
                                           "Your requistion form is empty.",
                                           Toast.LENGTH_LONG).show();
                        }
                        
                    }
                }.execute();
            }
        });
        
        clearButton = (Button) this.findViewById(R.id.clear_btn);
        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AsyncTask<String, Void, Boolean>()
                {
                    @Override
                    protected Boolean doInBackground(String... params)
                    {
                        try
                        {
                            RequisitionForm.clearAllRequestItems();
                            return true;
                        }
                        catch (Exception e)
                        {
                            return false;
                        }
                    }
                    
                    @Override
                    protected void onPostExecute(Boolean result)
                    {
                        if (result)
                        {
                            Toast.makeText(NewRequisitionFormActivity.this,
                                           "Requsition form was cleared", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(NewRequisitionFormActivity.this,
                                           "Error occured when clearing the form",
                                           Toast.LENGTH_LONG).show();
                        }
                    }
                }.execute();
            }
        });
        
        Button backToCatalogue = (Button) this.findViewById(R.id.button_back_to_catalogue);
        backToCatalogue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(
                        new Intent(NewRequisitionFormActivity.this, BrowseCatalogueActivity.class));
            }
        });
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.requisition_form_context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.remove_item:
                // for ListView, can do following for index to data
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int index = (int) info.id;
                try
                {
                    adapter.remove(RequisitionForm.removeRequestItem(index));
                    //adapter.notifyDataSetChanged();
                    return true;
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Opps..some error occurs...", Toast.LENGTH_LONG);
                }
                
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

//    @Override
//    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
//    {
//        Toast.makeText(this.getApplicationContext(),position,Toast.LENGTH_LONG).show();
//        return true;
//    }


//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id)
//    {
//        final Requisition_Detail requestItem = (Requisition_Detail) getListAdapter().getItem(
//                position);
//
//        new AsyncTask<String, Void, Void>()
//        {
//            @Override
//            protected Void doInBackground(String... params)
//            {
//                Requisition_Detail.addNewRequisition(RequisitionForm.getInstance());
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void result)
//            {
//                finish();
//            }
//        }.execute();
//
//    }
    
}