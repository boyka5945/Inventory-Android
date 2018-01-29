package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by czolb on 24/1/2018.
 */

public class Retrieval_Item extends HashMap<String,String> {

    public Retrieval_Item(String description, String qty, String location, String retrieved, String itemCode)
    {
        this.put(Key.RETRIEVAL_ITEM_1_DESCRIPTION, description);
        this.put(Key.RETRIEVAL_ITEM_2_QTY, qty);
        this.put(Key.RETRIEVAL_ITEM_3_LOCATION, location);
        this.put(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED, retrieved);
        this.put(Key.RETRIEVAL_ITEM_5_ITEMCODE, itemCode);

    }

    public Retrieval_Item(){}

    public static List<Retrieval_Item> ListRetrieval()
    {

        String url = UrlString.GetRetrievalList;
        ArrayList<Retrieval_Item> retrieval = new ArrayList<>();


        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                retrieval.add(new Retrieval_Item(obj.getString(Key.RETRIEVAL_ITEM_1_DESCRIPTION),
                        obj.getString(Key.RETRIEVAL_ITEM_2_QTY),
                        obj.getString(Key.RETRIEVAL_ITEM_3_LOCATION),
                        obj.getString(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED),
                        obj.getString(Key.RETRIEVAL_ITEM_5_ITEMCODE)

                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return retrieval;
    }

    public static void UpdateRetrieval(Retrieval_Item ri){

        JSONObject jretrieval = new JSONObject();
        try {
            jretrieval.put(Key.RETRIEVAL_ITEM_1_DESCRIPTION, ri.get(Key.RETRIEVAL_ITEM_1_DESCRIPTION));
            jretrieval.put(Key.RETRIEVAL_ITEM_2_QTY, Integer.parseInt(ri.get(Key.RETRIEVAL_ITEM_2_QTY)));
            jretrieval.put(Key.RETRIEVAL_ITEM_3_LOCATION, ri.get(Key.RETRIEVAL_ITEM_3_LOCATION));
            jretrieval.put(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED, Integer.parseInt(ri.get(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED)));

        } catch (Exception e) {
        }
        String result = JSONParser.postStream(UrlString.UpdateRetrieval, jretrieval.toString());

    }

    public static Retrieval_Item GetRetrievalForm(String itemCode )
    {
        String url = UrlString.GetRetrievalForm + itemCode;

        Retrieval_Item retrieval_Item = null;

        try
        {

            JSONObject ri = JSONParser.getJSONFromUrl(url);


            retrieval_Item =  new Retrieval_Item();

            retrieval_Item.put(Key.RETRIEVAL_ITEM_1_DESCRIPTION, ri.getString(Key.RETRIEVAL_ITEM_1_DESCRIPTION));
            retrieval_Item.put(Key.RETRIEVAL_ITEM_2_QTY, ri.getString(Key.RETRIEVAL_ITEM_2_QTY));
            retrieval_Item.put(Key.RETRIEVAL_ITEM_3_LOCATION, ri.getString(Key.RETRIEVAL_ITEM_3_LOCATION));
            retrieval_Item.put(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED, ri.getString(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED));


        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return retrieval_Item;
    }




}
