package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by weihan on 29/1/2018.
 */

public class Collection_Point extends HashMap<String, String> {
    public Collection_Point(String collection_point, String collectionPointID) {

        this.put(Key.COLLECTION_POINT_NAME,collection_point);
        this.put(Key.COLLECTION_POINT_ID,collectionPointID);
    }

    public static List<Collection_Point> ListCollectionPoint(String url)
    {
        ArrayList<Collection_Point> cp = new ArrayList<>();

        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                cp.add(new Collection_Point(obj.getString(Key.COLLECTION_POINT_NAME),
                        obj.getString(Key.COLLECTION_POINT_ID)
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return cp;
    }
}

