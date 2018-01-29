package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.ArrayList;
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
                              String allocateQty, String nextCollectionDate,String status)
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
        this.put(Key.REQUISITION_DETAIL_12_STATUS,status);
    }

    public static List<Requisition_Detail> getDetailsByReqNo(String reqNo)
    {
        String url = UrlString.getDetailsByReqNo + reqNo;
        ArrayList<Requisition_Detail> reqDetails = new ArrayList<>();

        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                reqDetails.add(new Requisition_Detail(obj.getString(Key.REQUISITION_DETAIL_1_REQUISITION_NO),
                        obj.getString(Key.REQUISITION_DETAIL_2_ITEM_CODE),
                        obj.getString(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION),
                        obj.getString(Key.REQUISITION_DETAIL_4_ITEM_UOM),
                        obj.getString(Key.REQUISITION_DETAIL_5_REMARKS),
                        obj.getString(Key.REQUISITION_DETAIL_6_REQUEST_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_7_FULFILLED_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_8_CLERK_ID),
                        obj.getString(Key.REQUISITION_DETAIL_9_RETRIEVED_DATE),
                        obj.getString(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_11_NEXT_COLLECTION_DATE)
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return reqDetails;
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

        try
        {
            String result = JSONParser.postStream(UrlString.addNewRequest + requesterID, jarray.toString());
            RequisitionForm.clearAllRequestItems();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static List<Requisition_Detail> getDetailsByReqNo(String reqNo)
    {
        String url = UrlString.getRequisitionDetailByReqNo + reqNo;

        ArrayList<Requisition_Detail> requisition_details = new ArrayList<>();


        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                requisition_details.add(

                        new Requisition_Detail(
                                obj.getString(Key.REQUISITION_DETAIL_1_REQUISITION_NO),
                        obj.getString(Key.REQUISITION_DETAIL_2_ITEM_CODE),
                        obj.getString(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION),
                        obj.getString(Key.REQUISITION_DETAIL_4_ITEM_UOM),
                        obj.getString(Key.REQUISITION_DETAIL_5_REMARKS),
                        obj.getString(Key.REQUISITION_DETAIL_6_REQUEST_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_7_FULFILLED_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_8_CLERK_ID),
                        obj.getString(Key.REQUISITION_DETAIL_9_RETRIEVED_DATE),
                        obj.getString(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY),
                                obj.getString(Key.REQUISITION_DETAIL_11_NEXT_COLLECTION_DATE),
                                obj.getString(Key.REQUISITION_DETAIL_12_STATUS)
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return requisition_details;
    }

    public static void updateRequisitionDetail(String url){
        JSONArray array = JSONParser.getJSONArrayFromUrl(url);
    }

    public static  List<Requisition_Detail> ToAllocate(){
        String url = UrlString.GetAllRequisitionDetailsforAllocation;
        ArrayList<Requisition_Detail> rq = new ArrayList<>();

        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                rq.add(new Requisition_Detail(obj.getString(Key.REQUISITION_DETAIL_1_REQUISITION_NO),
                        obj.getString(Key.REQUISITION_DETAIL_2_ITEM_CODE),
                        obj.getString(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION),
                        obj.getString(Key.REQUISITION_DETAIL_4_ITEM_UOM),
                        obj.getString(Key.REQUISITION_DETAIL_5_REMARKS),
                        obj.getString(Key.REQUISITION_DETAIL_6_REQUEST_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_7_FULFILLED_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_8_CLERK_ID),
                        obj.getString(Key.REQUISITION_DETAIL_9_RETRIEVED_DATE),
                        obj.getString(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_11_NEXT_COLLECTION_DATE),
                        obj.getString(Key.REQUISITION_DETAIL_12_STATUS)


                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return rq;
    }


    public static void updateRequisitionDetails(String reqNo, String itemCode, String allocatedQty){


        JSONObject obj = new JSONObject();
        try {
            obj.put(Key.REQUISITION_RECORD_1_REQUISITION_NO,
                    Integer.parseInt(reqNo));
            obj.put(Key.REQUISITION_DETAIL_2_ITEM_CODE, itemCode);
            obj.put(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY,
                    Integer.parseInt(allocatedQty));


        } catch (Exception e) {
        }

        String result = JSONParser.postStream(UrlString.updateRequisitionDetails, obj.toString());


        //still need to minus away allocated qty from the retrieved?

    }



    public static Requisition_Detail GetRequisitionDetailsBy2Keys(String itemCode, String reqNo )
    {
        String url = UrlString.getRequisitionDetailsBy2Keys + itemCode +"/" + reqNo;

        Requisition_Detail requisition_details = null;

        try
        {

            JSONObject obj = JSONParser.getJSONFromUrl(url);


            requisition_details =  new Requisition_Detail(
                    obj.getString(Key.REQUISITION_DETAIL_1_REQUISITION_NO),
                    obj.getString(Key.REQUISITION_DETAIL_2_ITEM_CODE),
                    obj.getString(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION),
                    obj.getString(Key.REQUISITION_DETAIL_4_ITEM_UOM),
                    obj.getString(Key.REQUISITION_DETAIL_5_REMARKS),
                    obj.getString(Key.REQUISITION_DETAIL_6_REQUEST_QTY),
                    obj.getString(Key.REQUISITION_DETAIL_7_FULFILLED_QTY),
                    obj.getString(Key.REQUISITION_DETAIL_8_CLERK_ID),
                    obj.getString(Key.REQUISITION_DETAIL_9_RETRIEVED_DATE),
                    obj.getString(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY),
                    obj.getString(Key.REQUISITION_DETAIL_11_NEXT_COLLECTION_DATE),
                    obj.getString(Key.REQUISITION_DETAIL_11_NEXT_COLLECTION_DATE)

            );
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return requisition_details;
    }



}
