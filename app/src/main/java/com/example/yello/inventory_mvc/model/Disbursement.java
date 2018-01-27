package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by E0224950 on 26/1/2018.
 */

public class Disbursement extends HashMap<String, String> {

    public Disbursement(){}

    public Disbursement(String itemCode, String description, String need, String actualQty){
        this.put(Key.STATIONERY_1_ITEM_CODE, itemCode);
        this.put("StationeryDescription", description);
        this.put("NeedQty", need);
        this.put("ActualQty", actualQty);
    }
    public static List<Disbursement> GetDisbursementList(String url) // based on url retrieve stationery list (can be search query)
    {
        ArrayList<Disbursement> disbursements = new ArrayList<>();

        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                disbursements.add(new Disbursement(obj.getString(Key.STATIONERY_1_ITEM_CODE),
                        obj.getString("StationeryDescription"),
                        obj.getString("NeedQty"),
                        obj.getString("ActualQty")
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return disbursements;
    }

    public static void updateActualQty(String url) // based on url retrieve stationery list (can be search query)
    {
        JSONArray array = JSONParser.getJSONArrayFromUrl(url);
//        )
//        ArrayList<Disbursement> disbursements = new ArrayList<>();
//
//        try
//        {
//            JSONArray array = JSONParser.getJSONArrayFromUrl(url);
//
//            for (int i = 0; i < array.length(); i++)
//            {
//                JSONObject obj = array.getJSONObject(i);
//
//                disbursements.add(new Disbursement(obj.getString(Key.STATIONERY_1_ITEM_CODE),
//                        obj.getString("StationeryDescription"),
//                        obj.getString("NeedQty")
//                ));
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }

    }
}
