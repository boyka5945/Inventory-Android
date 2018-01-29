package com.example.yello.inventory_mvc.utility;

/**
 * Created by CK Tan on 1/23/2018.
 */

public final class Key
{
    // <--- FOR PASSING VALUE BETWEEN BUNDLE --->
    public  static  final String BUNDLE_URL = "url";
    public  static  final String BUNDLE_STATIONERY = "stationery";
    public  static  final String BUNDLE_REQUISITION = "requisitionRecord";
    public  static  final String BUNDLE_CATEGORY = "category";

    public  static  final String BUNDLE_SHOW_BUTTON = "button";

    public  static  final String BUNDLE_REQUESITION_RECORD="requisition_record";
    public  static  final String BUNDLE_REQUISITION_DETAIL="requisition_detail";


    // <--- FOR MODEL : TO BE THE SAME AS THE PROPERTIES NAME IN WCF (JSON) --->
    
    // FOR STATIONERY
    public static final String STATIONERY_1_ITEM_CODE = "ItemCode";
    public static final String STATIONERY_2_DESCRIPTION = "Description";
    public static final String STATIONERY_3_UOM = "UOM";
    public static final String STATIONERY_4_CATEGORY = "Category";
    public static final String STATIONERY_5_LOCATION = "Location";
    
    
    // FOR REQUISITION RECORD
    public static final String REQUISITION_RECORD_1_REQUISITION_NO = "RequisitionNo";
    public static final String REQUISITION_RECORD_2_DEPT_CODE = "DeptCode";
    public static final String REQUISITION_RECORD_3_DEPT_NAME = "DeptName";
    public static final String REQUISITION_RECORD_4_REQUESTER_ID = "RequesterID";
    public static final String REQUISITION_RECORD_5_REQUESTER_NAME = "RequesterName";
    public static final String REQUISITION_RECORD_6_APPROVER_ID = "ApprovingStaffID";
    public static final String REQUISITION_RECORD_7_APPROVER_NAME = "ApprovingStaffName";
    public static final String REQUISITION_RECORD_8_APPROVED_DATE = "ApproveDate";
    public static final String REQUISITION_RECORD_9_STATUS = "Status";
    public static final String REQUISITION_RECORD_10_REQUEST_DATE = "RequestDate";
    
    
    // FOR REQUISITION DETAIL
    public static final String REQUISITION_DETAIL_1_REQUISITION_NO = "RequisitionNo";
    public static final String REQUISITION_DETAIL_2_ITEM_CODE = "ItemCode";
    public static final String REQUISITION_DETAIL_3_ITEM_DESCRIPTION = "StationeryDescription";
    public static final String REQUISITION_DETAIL_4_ITEM_UOM = "UOM";
    public static final String REQUISITION_DETAIL_5_REMARKS = "Remarks";
    public static final String REQUISITION_DETAIL_6_REQUEST_QTY = "RequestQty";
    public static final String REQUISITION_DETAIL_7_FULFILLED_QTY = "FulfilledQty";
    public static final String REQUISITION_DETAIL_8_CLERK_ID = "ClerkID";
    public static final String REQUISITION_DETAIL_9_RETRIEVED_DATE = "RetrievedDate";
    public static final String REQUISITION_DETAIL_10_ALLOCATE_QTY = "AllocateQty";
    public static final String REQUISITION_DETAIL_11_NEXT_COLLECTION_DATE = "NextCollectionDate";
    
    
    // FOR CATEGORY
    public static final String CATEGORY_1_ID = "CategoryID";
    public static final String CATEGORY_2_NAME = "CategoryName";

    //FOR USER
    public static final String USER_1_USERID = "UserID";
    public static final String USER_2_PASSWORD = "Password";
    /*
    public static final String USER_3_NAME = "Name";
    public static final String USER_4_CONTACT_NUMBER = "ContactNo";
    public static final String USER_5_ADDRESS = "Address";
    public static final String USER_8_USER_EMAIL = "User_Email";
    */
    public static final String USER_6_ROLE = "Role";
    public static final String USER_7_DEPARTMENT_CODE = "DepartmentCode";

    //FOR DEPARTMENT

    public static final String DEPARTMENT_1_CODE = "DepartmentCode";
    public static final String DEPARTMENT_2_NAME = "DepartmentName";
    public static final String DEPARTMENT_3_CONTACT_NAME = "ContactName";
/*    public static final String DEPARTMENT_4_PHONE_NO = "PhoneNo";
    public static final String DEPARTMENT_5_FAX_NO = "FaxNo";*/
    public static final String DEPARTMENT_6_COLLECTION_POINT_ID = "CollectionPointID";

    //FOR RETRIEVAL_ITEM

    public static final String RETRIEVAL_ITEM_1_DESCRIPTION ="Description";
    public static final String RETRIEVAL_ITEM_2_QTY ="Qty";
    public static final String RETRIEVAL_ITEM_3_LOCATION ="Location";
    public static final String RETRIEVAL_ITEM_4_QTY_RETRIEVED = "QtyRetrieved";


}
