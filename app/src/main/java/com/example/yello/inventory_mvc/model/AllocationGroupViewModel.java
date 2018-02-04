package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by czolb on 27/1/2018.
 */

public class AllocationGroupViewModel extends HashMap<String,String> {

//COLUMNS:
    //order number
    //department code
    //qty requested



    public AllocationGroupViewModel(String orderNum, String departmentCode, String qtyUnfulfilled){

        this.put("orderNum", orderNum);
        this.put("departmentCode", departmentCode);
        this.put("qtyUnfulfilled", qtyUnfulfilled);


    }

    public static List<AllocationGroupViewModel> getAllocationListGroupedByItem(String itemCode){

        List<AllocationGroupViewModel> list = new ArrayList<>();
        List<Requisition_Record> reqRec = Requisition_Record.GetAllRequestRecordForItemAllocation(itemCode);

        for (Requisition_Record rr : reqRec){
            int qtyUnfulfilled ;
            String unFulfilled = null;

            String reqNum = rr.get(Key.REQUISITION_RECORD_1_REQUISITION_NO);
            List<Requisition_Detail> rdList = Requisition_Detail.getDetailsByReqNo(reqNum);
            for(Requisition_Detail rd : rdList){
                if(rd.containsValue(itemCode)){

                    qtyUnfulfilled = Integer.parseInt(rd.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY)) -
                            Integer.parseInt(rd.get(Key.REQUISITION_DETAIL_7_FULFILLED_QTY)) -  Integer.parseInt(rd.get(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY)) ;
                    unFulfilled = Integer.toString(qtyUnfulfilled);
                }
            }
            list.add( new AllocationGroupViewModel(reqNum, rr.get(Key.REQUISITION_RECORD_2_DEPT_CODE), unFulfilled ));
        }

        return list;






    }




}