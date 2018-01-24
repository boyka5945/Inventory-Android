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

public class Retrieval_Item extends HashMap<String,String> {

    public Retrieval_Item(String description, String qty)
    {
        this.put(Key.RETRIEVAL_ITEM_1_DESCRIPTION, description);
        this.put(Key.RETRIEVAL_ITEM_2_QTY, qty);

    }

    public static List<Retrieval_Item> ListRetrieval()
    {
        String url = UrlString.getRetrievalItems;
        ArrayList<Retrieval_Item> retrieval = new ArrayList<>();

        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                retrieval.add(new Retrieval_Item(obj.getString(Key.RETRIEVAL_ITEM_1_DESCRIPTION),
                        obj.getString(Key.RETRIEVAL_ITEM_2_QTY)

                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return retrieval;
    }
}
