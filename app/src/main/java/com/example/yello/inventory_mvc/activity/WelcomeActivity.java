package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.LoginUser;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool);
        this.setSupportActionBar(myToolbar);

        Intent intent = this.getIntent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //switch (menu)
        switch (LoginUser.roleID)
        {
            case 2: getMenuInflater().inflate(R.menu.welcome_head_manager_acting, menu);
                break;
            case 3: getMenuInflater().inflate(R.menu.welcome_employee, menu);
                break;
            case 4: getMenuInflater().inflate(R.menu.welcome_user_representative,menu);
                break;
            case 5: getMenuInflater().inflate(R.menu.welcome_head_manager_acting,menu);
                break;
            case 6: getMenuInflater().inflate(R.menu.welcome_store_supervisor,menu);
                break;
            case 7: getMenuInflater().inflate(R.menu.welcome_store_clerk,menu);
                break;
            case 8: getMenuInflater().inflate(R.menu.welcome_head_manager_acting,menu);
                break;

            default:
                break;
        }
        //getMenuInflater().inflate(R.menu.welcome_dept_head,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Uri uri;
        Intent i;
        switch (item.getItemId()) {
           /* case R.id.viewRequisition:
                startActivity(new Intent(this, BrowseCatalogueActivity.class));
                return true;*/

            //Deptatment head,Manager,Acting Dept Head
            case R.id.manageRequest:
                startActivity(new Intent(this, ManageRequestActivity.class));
                return true;
            case R.id.changeCollectionPoint:
                startActivity(new Intent(this, ChangeCollectionPointActivity.class));
                return true;
            case R.id.viewCollectionPoint:
                startActivity(new Intent(this, ViewCollectItemActivity.class));
                return true;
            case R.id.viewPendingItem:
                startActivity(new Intent(this, ViewPendingItemActivity.class));
                return true;
            case R.id.log_out:
                Intent intent = new Intent(this,LoginActivity.class);
                /*startActivity(new Intent(this, LoginActivity.class));*/
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
                return true;

        //Store Clerk
            case R.id.viewRequistion:
                startActivity(new Intent(this, ViewRequisitionRecordsActivity.class));
                return true;
            case R.id.submit_new_requistion:
                startActivity(new Intent(this, NewRequisitionFormActivity.class));
                return true;
            case R.id.disbursementList:
                startActivity(new Intent(this, DisbursementDeptActivity.class));
                return true;
            case R.id.retrievalList:
                startActivity(new Intent(this, RetrievalListActivity.class));
                return true;
            case R.id.allocationList:
                startActivity(new Intent(this, AllocationListActivity.class));
                return true;
            case R.id.viewCollectionItem:
                startActivity(new Intent(this, ViewCollectItemActivity.class));
                return true;
            case R.id.viewPendingItemSC:
                startActivity(new Intent(this, ViewPendingItemActivity.class));
                return true;
            case R.id.log_outSC:
                Intent intentSC = new Intent(this,LoginActivity.class);
                /*startActivity(new Intent(this, LoginActivity.class));*/
                intentSC.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intentSC);
                return true;

        //Store Supervisor
            case R.id.viewRequisitionRecord:
                startActivity(new Intent(this, ViewRequisitionRecordsActivity.class));
                return true;
            case R.id.submitNewRequistion:
                startActivity(new Intent(this, NewRequisitionFormActivity.class));
                return true;
            case R.id.disbursementDept:
                startActivity(new Intent(this, DisbursementDeptActivity.class));
                return true;
            case R.id.log_outSS:
                Intent intentSS = new Intent(this,LoginActivity.class);
                /*startActivity(new Intent(this, LoginActivity.class));*/
                intentSS.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intentSS);
                return true;

        //user representative
            case R.id.viewRequisitionRecordUR:
                startActivity(new Intent(this, ViewRequisitionRecordsActivity.class));
                return true;
            case R.id.submitNewRequistionUR:
                startActivity(new Intent(this, NewRequisitionFormActivity.class));
                return true;
            case R.id.changeCollectionPointUR:
                startActivity(new Intent(this, ChangeCollectionPointActivity.class));
                return true;
            case R.id.viewCollectionItemUR:
                startActivity(new Intent(this, ViewCollectItemActivity.class));
                return true;
            case R.id.viewPendingItemUR:
                startActivity(new Intent(this, ViewPendingItemActivity.class));
                return true;
            case R.id.log_outUR:
                Intent intentUR = new Intent(this,LoginActivity.class);
                /*startActivity(new Intent(this, LoginActivity.class));*/
                intentUR.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intentUR);
                return true;

        //employee
            case R.id.viewRequisitionRecordE:
                startActivity(new Intent(this, ViewRequisitionRecordsActivity.class));
                return true;
            case R.id.submitNewRequistionE:
                startActivity(new Intent(this, NewRequisitionFormActivity.class));
                return true;
            case R.id.log_outE:
                Intent intentE = new Intent(this,LoginActivity.class);
                /*startActivity(new Intent(this, LoginActivity.class));*/
                intentE.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intentE);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
