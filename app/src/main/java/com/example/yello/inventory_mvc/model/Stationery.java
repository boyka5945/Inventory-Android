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
 * Created by CK Tan on 1/23/2018.
 */

public class Stationery extends HashMap<String, String>
{
    public Stationery(String itemCode, String description, String UOM, String category, String location)
    {
        this.put(Key.STATIONERY_1_ITEM_CODE, itemCode);
        this.put(Key.STATIONERY_2_DESCRIPTION, description);
        this.put(Key.STATIONERY_3_UOM, UOM);
        this.put(Key.STATIONERY_4_CATEGORY, category);
        this.put(Key.STATIONERY_5_LOCATION, location);
    }
    
    public static List<Stationery> ListStationery(String url) // based on url retrieve stationery list (can be search query)
    {
        ArrayList<Stationery> stationeries = new ArrayList<>();
        
        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);
            
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);
                
                stationeries.add(new Stationery(obj.getString(Key.STATIONERY_1_ITEM_CODE),
                                                obj.getString(Key.STATIONERY_2_DESCRIPTION),
                                                obj.getString(Key.STATIONERY_3_UOM),
                                                obj.getString(Key.STATIONERY_4_CATEGORY),
                                                obj.getString(Key.STATIONERY_5_LOCATION)
                                                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return stationeries;
    }
    
    public static Stationery getStationery(String itemCode)
    {
        String url = UrlString.getStationery + itemCode;
        Stationery stationery = null;
        
        try
        {
            JSONObject obj = JSONParser.getJSONFromUrl(url);
            
            stationery = new Stationery(obj.getString(Key.STATIONERY_1_ITEM_CODE),
                                        obj.getString(Key.STATIONERY_2_DESCRIPTION),
                                        obj.getString(Key.STATIONERY_3_UOM),
                                        obj.getString(Key.STATIONERY_4_CATEGORY),
                                        obj.getString(Key.STATIONERY_5_LOCATION));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return stationery;
    }
}
