package com.example.yello.inventory_mvc.activity;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.SimpleAdapter;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.yello.inventory_mvc.R;

        import com.example.yello.inventory_mvc.model.Collection_Point;
        import com.example.yello.inventory_mvc.model.Department;
        import com.example.yello.inventory_mvc.model.LoginUser;
        import com.example.yello.inventory_mvc.model.RequisitionForm;
        import com.example.yello.inventory_mvc.model.Requisition_Detail;
        import com.example.yello.inventory_mvc.utility.Key;
        import com.example.yello.inventory_mvc.utility.UrlString;

        import java.util.List;

        import static com.example.yello.inventory_mvc.utility.UrlString.GetDepartment;

public class ChangeCollectionPointActivity extends Activity {
    private String url2 = GetDepartment + "ZOOL";
    /*private String url2 = GetDepartment + LoginUser.deptCode;*/
    private Spinner collectionPointSpinner;
    private String deptCode;
    private TextView cpoint;
    private TextView departmentName;
    private Button submitbtn;
    protected Department dept;
    protected String selectedCollectionPt;
    private int collectionPointNameSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_collection_point);

        departmentName = (TextView) findViewById(R.id.Collection_point_department);
        cpoint = (TextView) findViewById(R.id.Collection_point_current);

        new AsyncTask<String, Void, Department>() {
            @Override
            protected Department doInBackground(String... params) {
                Department d = Department.getDepartment(params[0]);
                deptCode = d.get(Key.DEPARTMENT_1_CODE);
                return d;
            }

            @Override
            protected void onPostExecute(Department result) {
                departmentName.setText("Department: " + result.get(Key.DEPARTMENT_2_NAME));
                cpoint.setText("Collection Point: " + result.get(Key.DEPARTMENT_7_COLLECTION_NAME));
                dept=result;
            }

        }.execute(url2);

        collectionPointSpinner = (Spinner) findViewById(R.id.Collection_point_spinner);
        new AsyncTask<String, Void, List<Collection_Point>>() {
            @Override
            protected List<Collection_Point> doInBackground(String... strings) {
                return Collection_Point.ListCollectionPoint(strings[0]);
            }

            @Override
            protected void onPostExecute(List<Collection_Point> result) {
                SimpleAdapter adapter = new SimpleAdapter(ChangeCollectionPointActivity.this,
                        result,
                        android.R.layout.simple_list_item_1,
                        new String[]{Key.COLLECTION_POINT_NAME},
                        new int[]{android.R.id.text1});
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                collectionPointSpinner.setAdapter(adapter);
            }

        }.execute(UrlString.GetAllCollectionPoints);


        //cpoint.setText(collectionPointNameSelected);



        submitbtn = (Button) this.findViewById(R.id.Collection_point_button_update);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ChangeCollectionPointActivity.this)
                        .setTitle("Change Collection Point")
                        .setMessage("Are you sure you want to change collection point?")
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog, int which)
                                    {

                                        new AsyncTask<String, Void, Boolean>()
                                        {
                                            @Override
                                            protected Boolean doInBackground(String... params)
                                            {

                                                try
                                                {
                                                    Collection_Point p = (Collection_Point) collectionPointSpinner.getSelectedItem();
                                                    Department.UpdateAllocationPoint(deptCode, p.get(Key.COLLECTION_POINT_ID));
                                                    return true;
                                                }
                                                catch (Exception e)
                                                {
                                                    Toast.makeText(
                                                            ChangeCollectionPointActivity.this,
                                                            R.string.error_update_new_cp,
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
                                                            ChangeCollectionPointActivity.this,
                                                            R.string.success_change_cp,
                                                            Toast.LENGTH_LONG).show();
                                                    // TODO: Jump to requisitio list
                                                    recreate();
                                                }
                                            }
                                        }.execute();

                                    }




                                }
                                ) .setNegativeButton(android.R.string.no,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        }).show();

            }

        });
    }
}