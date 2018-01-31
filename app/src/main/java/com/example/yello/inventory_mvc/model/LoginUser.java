package com.example.yello.inventory_mvc.model;

/**
 * Created by PaingSu on 1/26/2018.
 */

public class LoginUser
{
    public static String userID;
    public static Integer roleID;
    public static String deptCode;
    public static String name;
    private static LoginUser _user = new LoginUser();


    public static LoginUser getLoginUser()
    {
       return _user;
    }


}
