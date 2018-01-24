package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by czolb on 24/1/2018.
 */

public class User extends HashMap<String, String> {

    public User(String userID, String password, String name, String contactNo, String address, String role, String departmentCode, String userEmail )
    {
        this.put(Key.USER_1_USERID, userID);
        this.put(Key.USER_2_PASSWORD, password);
        this.put(Key.USER_3_NAME, name);
        this.put(Key.USER_4_CONTACT_NUMBER, contactNo);
        this.put(Key.USER_5_ADDRESS, address);
        this.put(Key.USER_6_ROLE, role);
        this.put(Key.USER_7_DEPARTMENT_CODE, departmentCode);
        this.put(Key.USER_8_USER_EMAIL, userEmail);
    }


}
