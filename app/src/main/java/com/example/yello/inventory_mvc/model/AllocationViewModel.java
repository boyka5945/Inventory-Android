package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by czolb on 27/1/2018.
 */

public class AllocationViewModel extends HashMap<String,String> {

//COLUMNS:
    //order number
    //department code
    //qty requested

    public AllocationViewModel(String orderNum, String departmentCode, String qtyReq){

        this.put("orderNum", orderNum);
        this.put("departmentCode", departmentCode);
        this.put("qtyReq", qtyReq);


    }

    public static List<AllocationViewModel> getAllocationListGroupedByItem(String itemCode){

        List<AllocationViewModel> list = new ArrayList<>();
        List<Requisition_Record> reqRec = Requisition_Record.GetAllRequestRecordForItemAllocation(itemCode);

        for (Requisition_Record rr : reqRec){
            String qty = null;
            String reqNum = rr.get(Key.REQUISITION_RECORD_1_REQUISITION_NO);
            List<Requisition_Detail> rdList = Requisition_Detail.getDetailsByReqNo(reqNum);
            for(Requisition_Detail rd : rdList){
                if(rd.containsValue(itemCode)){

                     qty = rd.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY);
                }
            }
            list.add( new AllocationViewModel(reqNum, rr.get(Key.REQUISITION_RECORD_2_DEPT_CODE), qty ));
        }

        return list;






    }




}
