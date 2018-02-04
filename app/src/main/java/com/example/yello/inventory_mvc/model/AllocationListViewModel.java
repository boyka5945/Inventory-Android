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
 * Created by czolb on 3/2/2018.
 */

public class AllocationListViewModel extends HashMap<String, String> {


    public AllocationListViewModel(String itemName, String itemCode, String qtyPendingAllocation, String qtyRetrieved){

        this.put("StationeryDescription", itemName);
        this.put("ItemCode", itemCode);
        this.put("AllocateQty", qtyPendingAllocation);
        this.put("qtyRetrieved", qtyRetrieved);

    }





    public static List<AllocationListViewModel> getAllocationList(){

        List<AllocationListViewModel> model = new ArrayList<>();

        List<Retrieval_Item> allPendingRequestsOnThisDay = Retrieval_Item.ListRetrieval();

        List<Requisition_Detail>ListOfAllItemsToAllocate = Requisition_Detail.GetConsolidatedAllocationList();



        for (Requisition_Detail rd : ListOfAllItemsToAllocate) {

            String retrieved = null;
            String itemCode =rd.get(Key.REQUISITION_DETAIL_2_ITEM_CODE);
            String itemName = rd.get(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION);


            int qtyPendingAllocation =  Integer.parseInt(rd.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY)) - Integer.parseInt(rd.get(Key.REQUISITION_DETAIL_7_FULFILLED_QTY))
                    - Integer.parseInt(rd.get(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY));

            for (Retrieval_Item r : allPendingRequestsOnThisDay){
                if(r.get(Key.RETRIEVAL_ITEM_5_ITEMCODE).equals(rd.get(Key.REQUISITION_DETAIL_2_ITEM_CODE)))

                retrieved = r.get(Key.RETRIEVAL_ITEM_4_QTY_RETRIEVED);
            }

            AllocationListViewModel row = new AllocationListViewModel(itemName, itemCode, Integer.toString(qtyPendingAllocation), retrieved);
            model.add(row);








        }





        return model;





    }
}
