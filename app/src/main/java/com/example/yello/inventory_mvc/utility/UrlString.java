package com.example.yello.inventory_mvc.utility;

/**
 * Created by CK Tan on 1/23/2018.
 */

public final class UrlString
{
    public static final String ip = "172.17.255.3";
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
    public static final String getStationeryByCategory = host + "/GetStationeryByCategory/";
    
    // UriTemplate = "/GetAllCategories"
    public static final String getAllCategories = host + "/GetAllCategories";

    // UriTemplate = "/ValidateUser/{userid}/{password}"
    //public static final String validateUser = host + "/ValidateUser/";

    // UriTemplate = "/GetUser/{userid}/{password}"
    public static final String validateUser = host + "/GetUser/";

    // UriTemplate = "/GetAllRequisitionRecords"
    public static final String getAllRequisitionRecords = host + "/GetAllRequisitionRecords";


}
