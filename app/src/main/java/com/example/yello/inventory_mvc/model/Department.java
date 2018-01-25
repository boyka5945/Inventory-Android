package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by czolb on 24/1/2018.
 */

public class Department extends HashMap<String, String> {

    public Department(String departmentCode, String departmentName, String contactName, String collectionPointID){

        this.put(Key.DEPARTMENT_1_CODE, departmentCode);
        this.put(Key.DEPARTMENT_2_NAME, departmentName);
        this.put(Key.DEPARTMENT_3_CONTACT_NAME, contactName);
        this.put(Key.DEPARTMENT_6_COLLECTION_POINT_ID, collectionPointID);

    }



    public static List<Department> ListDepartments(String url) // based on url retrieve stationery list (can be search query)
    {
        ArrayList<Department> departments = new ArrayList<>();

        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                departments.add(new Department(obj.getString(Key.DEPARTMENT_1_CODE),
                        obj.getString(Key.DEPARTMENT_2_NAME),
                        obj.getString(Key.DEPARTMENT_3_CONTACT_NAME),
                        obj.getString(Key.DEPARTMENT_6_COLLECTION_POINT_ID)
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return departments;
    }
}
