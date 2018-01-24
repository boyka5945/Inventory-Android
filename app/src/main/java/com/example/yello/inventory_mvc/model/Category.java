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

public class Category extends HashMap<String, String>
{
    public Category(String categoryID, String categoryName)
    {
        this.put(Key.CATEGORY_1_ID, categoryID);
        this.put(Key.CATEGORY_2_NAME, categoryName);
    }
    
    public static List<Category> ListCategories(String url) // based on url retrieve category list
    {
        ArrayList<Category> categories = new ArrayList<>();
    
        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);
    
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);
    
                categories.add(new Category(obj.getString(Key.CATEGORY_1_ID),
                                            obj.getString(Key.CATEGORY_2_NAME)
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
        return categories;
    }
}
