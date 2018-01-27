package com.example.yello.inventory_mvc.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
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
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition_form);
        
        listView = (ListView) this.findViewById(R.id.listView);
        adapter = new NewRequisitionFormAdapter(this, R.layout.new_requistion_row,
                                                RequisitionForm.getInstance());
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(android.R.id.empty));
        registerForContextMenu(listView);
        
        submitButton = (Button) this.findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                
                
                new AlertDialog.Builder(NewRequisitionFormActivity.this)
                        .setTitle("Submit Requisition")
                        .setMessage("Confirm submission?")
                        .setPositiveButton(android.R.string.yes,
                                           new DialogInterface.OnClickListener()
                                           {
                                               public void onClick(final DialogInterface dialog, int which)
                                               {
                                
                                                   new AsyncTask<String, Void, Boolean>()
                                                   {
                                                       @Override
                                                       protected Boolean doInBackground(String... params)
                                                       {
                                                           if (RequisitionForm.getLength() == 0)
                                                           {
                                                               Toast.makeText(
                                                                       NewRequisitionFormActivity.this,
                                                                       R.string.no_request_item,
                                                                       Toast.LENGTH_LONG).show();
                                                               return false;
                                                           }
                                        
                                        
                                                           try
                                                           {
                                                               Requisition_Detail.addNewRequisition(
                                                                       RequisitionForm.getInstance());
                                                               return true;
                                                           }
                                                           catch (Exception e)
                                                           {
                                                               Toast.makeText(
                                                                       NewRequisitionFormActivity.this,
                                                                       R.string.error_subtmit_new_requisition,
                                                                       Toast.LENGTH_LONG).show();
                                                               return false;
                                                           }
                                                       }
                                    
                                                       @Override
                                                       protected void onPostExecute(Boolean result)
                                                       {
                                                           if (result)
                                                           {
                                                               Toast.makeText(
                                                                       NewRequisitionFormActivity.this,
                                                                       R.string.success_submit_requisition,
                                                                       Toast.LENGTH_LONG).show();
                                                               // TODO: Jump to requisitio list
                                                               finish();
                                                           }
                                                       }
                                                   }.execute();
                                               }
                                           })
                        .setNegativeButton(android.R.string.no,
                                           new DialogInterface.OnClickListener()
                                           {
                                               public void onClick(DialogInterface dialog, int which)
                                               {
                                                   dialog.dismiss();
                                               }
                                           })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                
            }
        });
        
        clearButton = (Button) this.findViewById(R.id.clear_btn);
        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                
                
                new AlertDialog.Builder(NewRequisitionFormActivity.this)
                        .setTitle("Clear form")
                        .setMessage("Are you sure you want to clear all items?")
                        .setPositiveButton(android.R.string.yes,
                                           new DialogInterface.OnClickListener()
                                           {
                                               public void onClick(final DialogInterface dialog, int which)
                                               {
                                
                                                   new AsyncTask<String, Void, Boolean>()
                                                   {
                                                       @Override
                                                       protected Boolean doInBackground(String... params)
                                                       {
                                                           if (RequisitionForm.getLength() == 0)
                                                           {
                                                               Toast.makeText(
                                                                       NewRequisitionFormActivity.this,
                                                                       R.string.no_request_item,
                                                                       Toast.LENGTH_LONG).show();
                                                               return false;
                                                           }
                                        
                                                           try
                                                           {
                                                               RequisitionForm.clearAllRequestItems();
                                                               return true;
                                                           }
                                                           catch (Exception e)
                                                           {
                                                               Toast.makeText(
                                                                       NewRequisitionFormActivity.this,
                                                                       R.string.error_clear_requisition,
                                                                       Toast.LENGTH_LONG).show();
                                            
                                                               return false;
                                                           }
                                                       }
                                    
                                                       @Override
                                                       protected void onPostExecute(Boolean result)
                                                       {
                                                           if (result)
                                                           {
                                                               Toast.makeText(
                                                                       NewRequisitionFormActivity.this,
                                                                       R.string.success_clear_requisition,
                                                                       Toast.LENGTH_LONG).show();
                                            
                                                               finish();
                                                           }
                                        
                                        
                                                       }
                                                   }.execute();
                                               }
                                           })
                        .setNegativeButton(android.R.string.no,
                                           new DialogInterface.OnClickListener()
                                           {
                                               public void onClick(DialogInterface dialog, int which)
                                               {
                                                   dialog.dismiss();
                                               }
                                           })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                
                
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
                    Toast.makeText(this, R.string.error_pls_try_again, Toast.LENGTH_LONG);
                }
                
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    
    
}