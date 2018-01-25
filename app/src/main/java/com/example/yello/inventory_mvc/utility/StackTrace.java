package com.example.yello.inventory_mvc.utility;

/**
 * Created by CK Tan on 1/24/2018.
 */

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTrace
{
    public static String trace(Exception ex)
    {
        StringWriter outStream = new StringWriter();
        ex.printStackTrace(new PrintWriter(outStream));
        
        return outStream.toString();
    }
}
