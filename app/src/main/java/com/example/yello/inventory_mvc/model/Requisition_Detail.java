package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.Key;

import java.util.HashMap;

/**
 * Created by CK Tan on 1/23/2018.
 */

public class Requisition_Detail extends HashMap<String, String>
{
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
    
}
