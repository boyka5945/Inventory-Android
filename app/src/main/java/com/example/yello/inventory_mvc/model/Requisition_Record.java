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

public class Requisition_Record extends HashMap<String, String>
{
    public Requisition_Record(String requisitionNo, String deptCode, String deptName,
                              String requesterID, String requesterName, String approverID,
                              String approverName, String approvedDate, String status, String requestDate)
    {
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
    
}
