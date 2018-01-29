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

public class Requisition_Record extends HashMap<String, String> {
    public Requisition_Record(String requisitionNo, String deptCode, String deptName,
                              String requesterID, String requesterName, String approverID,
                              String approverName, String approvedDate, String status, String requestDate) {
        this.put(Key.REQUISITION_RECORD_1_REQUISITION_NO, requisitionNo);
        this.put(Key.REQUISITION_RECORD_2_DEPT_CODE, deptCode);
        this.put(Key.REQUISITION_RECORD_3_DEPT_NAME, deptName);
        this.put(Key.REQUISITION_RECORD_4_REQUESTER_ID, requesterID);
        this.put(Key.REQUISITION_RECORD_5_REQUESTER_NAME, requesterName);
        this.put(Key.REQUISITION_RECORD_6_APPROVER_ID, approverID);
        this.put(Key.REQUISITION_RECORD_7_APPROVER_NAME, approverName);
        this.put(Key.REQUISITION_RECORD_8_APPROVED_DATE, approvedDate);
        this.put(Key.REQUISITION_RECORD_9_STATUS, status);
        this.put(Key.REQUISITION_RECORD_10_REQUEST_DATE, requestDate);
    }


    public Requisition_Record() {

    }

    public static List<Requisition_Record> getRequistionRecordByDept(String deptCode)
    {
        String url = UrlString.getRequistionRecordByDept + deptCode;
        ArrayList<Requisition_Record> reqRecord = new ArrayList<>();

        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                reqRecord.add(new Requisition_Record(obj.getString(Key.REQUISITION_RECORD_1_REQUISITION_NO),
                        obj.getString(Key.REQUISITION_RECORD_2_DEPT_CODE),
                        obj.getString(Key.REQUISITION_RECORD_3_DEPT_NAME),
                        obj.getString(Key.REQUISITION_RECORD_4_REQUESTER_ID),
                        obj.getString(Key.REQUISITION_RECORD_5_REQUESTER_NAME),
                        obj.getString(Key.REQUISITION_RECORD_6_APPROVER_ID),
                        obj.getString(Key.REQUISITION_RECORD_7_APPROVER_NAME),
                        obj.getString(Key.REQUISITION_RECORD_8_APPROVED_DATE),
                        obj.getString(Key.REQUISITION_RECORD_9_STATUS),
                        obj.getString(Key.REQUISITION_RECORD_10_REQUEST_DATE)
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return reqRecord;
    }

    public static List<Requisition_Record> ListRequisitionRecord(String url)
    {
        ArrayList<Requisition_Record> requisition_records = new ArrayList<>();

        try {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                requisition_records.add(new Requisition_Record(obj.getString(Key.REQUISITION_RECORD_1_REQUISITION_NO),
                        obj.getString(Key.REQUISITION_RECORD_2_DEPT_CODE),
                        obj.getString(Key.REQUISITION_RECORD_3_DEPT_NAME),
                        obj.getString(Key.REQUISITION_RECORD_4_REQUESTER_ID),
                        obj.getString(Key.REQUISITION_RECORD_5_REQUESTER_NAME),
                        obj.getString(Key.REQUISITION_RECORD_6_APPROVER_ID),
                        obj.getString(Key.REQUISITION_RECORD_7_APPROVER_NAME),
                        obj.getString(Key.REQUISITION_RECORD_8_APPROVED_DATE),
                        obj.getString(Key.REQUISITION_RECORD_9_STATUS),
                        obj.getString(Key.REQUISITION_RECORD_10_REQUEST_DATE)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requisition_records;
    }

    public static Requisition_Record getRequisitionRecord(String requisitionNo) {
        String url = UrlString.getAllRequisitionRecords + requisitionNo;
        Requisition_Record requisitionRecord = null;

        try {
            JSONObject obj = JSONParser.getJSONFromUrl(url);

            requisitionRecord = new Requisition_Record(obj.getString(Key.REQUISITION_RECORD_1_REQUISITION_NO),
                    obj.getString(Key.REQUISITION_RECORD_2_DEPT_CODE),
                    obj.getString(Key.REQUISITION_RECORD_3_DEPT_NAME),
                    obj.getString(Key.REQUISITION_RECORD_4_REQUESTER_ID),
                    obj.getString(Key.REQUISITION_RECORD_5_REQUESTER_NAME),
                    obj.getString(Key.REQUISITION_RECORD_6_APPROVER_ID),
                    obj.getString(Key.REQUISITION_RECORD_7_APPROVER_NAME),
                    obj.getString(Key.REQUISITION_RECORD_8_APPROVED_DATE),
                    obj.getString(Key.REQUISITION_RECORD_9_STATUS),
                    obj.getString(Key.REQUISITION_RECORD_10_REQUEST_DATE));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requisitionRecord;
    }



    public static List<Requisition_Record> GetAllRequestRecordForItemAllocation(String itemCode) {
        String url = UrlString.GetAllRequestRecordForItemAllocation + itemCode;
        List<Requisition_Record> requisitionRecord = new ArrayList<>();

        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(url);
            for (int i=0; i<a.length(); i++) {
                JSONObject obj = a.getJSONObject(i);
                Requisition_Record c = new Requisition_Record (obj.getString(Key.REQUISITION_RECORD_1_REQUISITION_NO),
                        obj.getString(Key.REQUISITION_RECORD_2_DEPT_CODE),
                        obj.getString(Key.REQUISITION_RECORD_3_DEPT_NAME),
                        obj.getString(Key.REQUISITION_RECORD_4_REQUESTER_ID),
                        obj.getString(Key.REQUISITION_RECORD_5_REQUESTER_NAME),
                        obj.getString(Key.REQUISITION_RECORD_6_APPROVER_ID),
                        obj.getString(Key.REQUISITION_RECORD_7_APPROVER_NAME),
                        obj.getString(Key.REQUISITION_RECORD_8_APPROVED_DATE),
                        obj.getString(Key.REQUISITION_RECORD_9_STATUS),
                        obj.getString(Key.REQUISITION_RECORD_10_REQUEST_DATE));
                requisitionRecord.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return requisitionRecord;

    }


    public static List<Requisition_Record> GetRecordListByItemCode(String ItemCode, String Dept)
    {
        ArrayList<Requisition_Record> requisition_records = new ArrayList<>();

        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl("http://172.17.255.3/AD_Inventory_WCF/Service.svc/" + ItemCode);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                requisition_records.add(new Requisition_Record(obj.getString(Key.REQUISITION_RECORD_1_REQUISITION_NO),
                        obj.getString(Key.REQUISITION_RECORD_2_DEPT_CODE),
                        obj.getString(Key.REQUISITION_RECORD_3_DEPT_NAME),
                        obj.getString(Key.REQUISITION_RECORD_4_REQUESTER_ID),
                        obj.getString(Key.REQUISITION_RECORD_5_REQUESTER_NAME),
                        obj.getString(Key.REQUISITION_RECORD_6_APPROVER_ID),
                        obj.getString(Key.REQUISITION_RECORD_7_APPROVER_NAME),
                        obj.getString(Key.REQUISITION_RECORD_8_APPROVED_DATE),
                        obj.getString(Key.REQUISITION_RECORD_9_STATUS),
                        obj.getString(Key.REQUISITION_RECORD_10_REQUEST_DATE)
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return requisition_records;
    }



    public static void UpdateRequisition(String reqrecord, String status, String approvestaff_id)
    {

        String url = UrlString.UpdateRequisition + reqrecord + "/" + status + "/" + approvestaff_id;

       // JSONObject jretrieval = new JSONObject();
       // try {
       //     jretrieval.put(Key.REQUISITION_RECORD_1_REQUISITION_NO, reqrecord.get(Key.REQUISITION_RECORD_1_REQUISITION_NO));
       //     jretrieval.put(Key.REQUISITION_RECORD_9_STATUS, Integer.parseInt(reqrecord.get(Key.REQUISITION_RECORD_9_STATUS)));
       //     jretrieval.put(Key.REQUISITION_RECORD_6_APPROVER_ID, reqrecord.get(Key.REQUISITION_RECORD_6_APPROVER_ID));

       // } catch (Exception e) {
       // }
       // String result = JSONParser.postStream(UrlString.UpdateRequisition, jretrieval.toString());

        String result = JSONParser.getStream(url);


    }

}
