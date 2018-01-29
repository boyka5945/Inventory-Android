package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.yello.inventory_mvc.utility.UrlString.GetDepartment;
import static com.example.yello.inventory_mvc.utility.UrlString.host;

/**
 * Created by czolb on 24/1/2018.
 */

public class Department extends HashMap<String, String> {

    public Department(String departmentCode, String departmentName, String contactName, String collectionPointID, String collectionPointName){

        this.put(Key.DEPARTMENT_1_CODE, departmentCode);
        this.put(Key.DEPARTMENT_2_NAME, departmentName);
        this.put(Key.DEPARTMENT_3_CONTACT_NAME, contactName);
        this.put(Key.DEPARTMENT_6_COLLECTION_POINT_ID, collectionPointID);
        this.put(Key.DEPARTMENT_7_COLLECTION_NAME,collectionPointName);
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
                        obj.getString(Key.DEPARTMENT_6_COLLECTION_POINT_ID),
                        obj.getString(Key.DEPARTMENT_7_COLLECTION_NAME)
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return departments;
    }

    public static Department getDepartment(String deptCode) {
        String url = UrlString.GetDepartment+deptCode;
        Department d = null;
        try {
            JSONObject obj = JSONParser.getJSONFromUrl(url);


            d = new Department(obj.getString(Key.DEPARTMENT_2_NAME),
                    obj.getString(Key.DEPARTMENT_7_COLLECTION_NAME),
                    obj.getString(Key.DEPARTMENT_3_CONTACT_NAME),
                    obj.getString(Key.DEPARTMENT_1_CODE),
                    obj.getString(Key.DEPARTMENT_6_COLLECTION_POINT_ID));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }
    }


