package com.example.yello.inventory_mvc.utility;

/**
 * Created by CK Tan on 1/23/2018.
 */

public final class UrlString
{
    public static final String ip = "192.168.1.3S100";
    public static final String server = "/AD_Inventory_WCF";
    public static final String host = "http://" + ip + server + "/Service.svc";
    
    
    // UriTemplate = "/GetRequisitionByItemCode/{itemCode}"
    public static final String getRequisitionByItemCode = host + "/GetRequisitionByItemCode/";
    
    // UriTemplate = "/GetRequisitionDetailsBy2Keys/{itemCode}/{requisitionNo}"
    public static final String getRequisitionDetailsBy2Keys = host + "/GetRequisitionDetailsBy2Keys/";
    
    // UriTemplate = "/GetAllStationeries"
    public static final String getAllStationeries = host + "/GetAllStationeries";
    
    // UriTemplate = "/GetStationery/{itemCode}"
    public static final String getStationery = host + "/GetStationery/";
    
    // UriTemplate = "/GetStationeries/{categoryName}/{searchString}"
    public static final String getStationeryByCriteria = host + "/GetStationeries/";
    
    // UriTemplate = "/GetStationeries/{categoryName}"
    public static final String getStationeryByCategory = host + "/GetStationeries/";
    
    // UriTemplate = "/GetAllCategories"
    public static final String getAllCategories = host + "/GetAllCategories";

    // UriTemplate = "/GetAllCollectionPoints"
    public static final String GetAllCollectionPoints = host + "/GetAllCollectionPoints";

    // UriTemplate = "/GetDepartment/{deptCode}"
    public static final String GetDepartment = host + "/GetDepartment/";

    //UriTemplate = "GetDetailByReqNo/{reqNo}"
    public static final String getDetailsByReqNo=host+"/GetDetailByReqNo/";

    //UriTemplate = "GetRequisitionByDept/{deptCode}"
    public static final String getRequistionRecordByDept=host+"/GetRequisitionRecordByDept/";

     //UriTemplate = "/GetRequisitionRecordByRequesterID/{requesterID}"
    public static final String getRequisitionRecordsByRequesterID=host+"/GetRequisitionRecordByRequesterID/";

    // UriTemplate = "/ValidateUser/{userid}/{password}"
    //public static final String validateUser = host + "/ValidateUser/";

    // UriTemplate = "/GetUser/{userid}/{password}"
    public static final String validateUser = host + "/ValidateUser";

    // UriTemplate = "/AddNewRequest/{requesterID}
    public static final String addNewRequest = host + "/AddNewRequest/";


    // UriTemplate = "/GetAllRequisitionRecords"
    public static final String getAllRequisitionRecords = host + "/GetAllRequisitionRecords/";

    // UriTemplate = "/GetDetailByReqNo/{reqNo}"
    public static final String getRequisitionDetailByReqNo = host + "/GetDetailByReqNo/";

    // UriTemplate = "/GetRequisitionDetailsBy2Keys/{itemCode}/{requisitionNo}"
    public static final String GetRequisitionDetailsBy2Keys = host + "/GetRequisitionDetailsBy2Keys/";

    // UriTemplate = "/GetRetrievalList"
    public static final String GetRetrievalList = host + "/GetRetrievalList";

    // UriTemplate = "/UpdateRetrieval"
    public static final String UpdateRetrieval = host + "/UpdateRetrieval";

    // UriTemplate = "/UpdateRequistion/{requisitionNo}/{status}/{approvestaff_id}"
    public static final String UpdateRequisition=host+"/UpdateRequisition/";


    //UriTemplate = "/UpdateRequisitionDetail"
    public  static final String updateReqDetail = host + "/UpdateRequisitionDetail";

    // UriTemplate = "/GetRetrievalForm/{itemCode}"
    public static final String GetRetrievalForm = host + "/GetRetrievalForm/";

    // UriTemplate = "/GetAllRequisitionDetailsforAllocation"
    public static final String GetAllRequisitionDetailsforAllocation = host + "/GetAllRequisitionDetailsforAllocation";
    // UriTemplate = "/GetDisbursementByDept/{deptCode}""
    public static final String GetDisbursementByDept = host + "/GetDisbursementByDept/";
    // UriTemplate = "/GetPendingItemsByItem/{deptCode}"
    public static final String GetPendingItemsByItem = host + "/GetPendingItemsByItem/";

    // UriTemplate = "/GetAllRequestRecordForItemAllocation/{itemCode}"
   public static final String GetAllRequestRecordForItemAllocation = host + "/GetAllRequestRecordForItemAllocation/";

    //UriTemplate = "/UpdateCollectionPoint/deptCode/collectionPtID"
   public static final String UpdateCollectionPoint = host +"/UpdateCollectionPoint/";
//ps
   // public static final String userChangePassword = host + "/ChangePassword/";
   // UriTemplate = "/updateRequisitionDetails"
    public static final String updateRequisitionDetails = host + "/updateRequisitionDetails";
}
