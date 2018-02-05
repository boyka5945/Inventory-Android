package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by czolb on 24/1/2018.
 */

public class User extends HashMap<String, String> {

    public User(String userID, String password, String role, String departmentCode )
    {
        this.put(Key.USER_1_USERID, userID);
        this.put(Key.USER_2_PASSWORD, password);
        this.put(Key.USER_6_ROLE, role);
        this.put(Key.USER_7_DEPARTMENT_CODE, departmentCode);

    }


    public static String validateUser(String userID, String password){
        JSONObject userinfo = new JSONObject();
        try {
            userinfo.put(Key.USER_1_USERID, userID);
            userinfo.put(Key.USER_2_PASSWORD, password);
            userinfo.put(Key.USER_6_ROLE, 5);
            userinfo.put(Key.USER_7_DEPARTMENT_CODE, "xx");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        String result = JSONParser.postStream(UrlString.validateUser, userinfo.toString());
        return result;
    }

}
