package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by CK Tan on 1/23/2018.
 */

public class Requisition_Detail extends HashMap<String, String>
{
    public Requisition_Detail() {} // default constructor
    
    public Requisition_Detail(String requisitionNo, String itemCode, String description,
                              String uom, String remarks, String requestQty,
                              String fulfilledQty, String clerkID, String retrievedDate,
                              String allocateQty, String nextCollectionDate)
    {
        this.put(Key.REQUISITION_DETAIL_1_REQUISITION_NO, requisitionNo);
        this.put(Key.REQUISITION_DETAIL_2_ITEM_CODE, itemCode);
        this.put(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION, description);
        this.put(Key.REQUISITION_DETAIL_4_ITEM_UOM, uom);
        this.put(Key.REQUISITION_DETAIL_5_REMARKS, remarks);
        this.put(Key.REQUISITION_DETAIL_6_REQUEST_QTY, requestQty);
        this.put(Key.REQUISITION_DETAIL_7_FULFILLED_QTY, fulfilledQty);
        this.put(Key.REQUISITION_DETAIL_8_CLERK_ID, clerkID);
        this.put(Key.REQUISITION_DETAIL_9_RETRIEVED_DATE, retrievedDate);
        this.put(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY, allocateQty);
        this.put(Key.REQUISITION_DETAIL_11_NEXT_COLLECTION_DATE, nextCollectionDate);
    }
    
    
    public Requisition_Detail(String itemCode, String description,
                              String uom, String requestQty)
    {
        this.put(Key.REQUISITION_DETAIL_2_ITEM_CODE, itemCode);
        this.put(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION, description);
        this.put(Key.REQUISITION_DETAIL_4_ITEM_UOM, uom);
        this.put(Key.REQUISITION_DETAIL_6_REQUEST_QTY, requestQty);
    }
    
    // TODO: remove hardcoded requesterID
    public static void addNewRequisition(List<Requisition_Detail> detail)
    {
        String requesterID = "S1014";
    
        JSONArray jarray = new JSONArray();
        for (Requisition_Detail d: detail)
        {
            JSONObject jdetail = new JSONObject();
            try
            {
                jdetail.put(Key.REQUISITION_DETAIL_2_ITEM_CODE, d.get(Key.REQUISITION_DETAIL_2_ITEM_CODE));
                jdetail.put(Key.REQUISITION_DETAIL_6_REQUEST_QTY, Integer.parseInt(d.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY)));
        
                jarray.put(jdetail);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        String result = JSONParser.postStream(UrlString.addNewRequest + requesterID, jarray.toString());
    }

}
