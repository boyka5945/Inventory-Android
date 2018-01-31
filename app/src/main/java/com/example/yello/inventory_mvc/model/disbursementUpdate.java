
package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by E0224950 on 26/1/2018.
 */

public class disbursementUpdate extends HashMap<String, String> {

    public disbursementUpdate() {
    }

    public disbursementUpdate(String itemCode, String need, String actualQty, String deptCode, String count, String staffID) {
        this.put(Key.STATIONERY_1_ITEM_CODE, itemCode);
        this.put("NeedQty", need);
        this.put("ActualQty", actualQty);
        this.put(Key.DEPARTMENT_1_CODE, deptCode);
        this.put("Count",count);
        this.put("StaffID", staffID);
    }

    public static void updateDisbursement(disbursementUpdate d) {
        String url = UrlString.UpdateDisbursement +
                d.get(Key.STATIONERY_1_ITEM_CODE) + "/" +
                d.get("NeedQty") + "/" +
                d.get("ActualQty") + "/" +
                d.get(Key.DEPARTMENT_1_CODE) + "/" +
                d.get("Count") + "/" +
                d.get("StaffID");
        JSONArray array = JSONParser.getJSONArrayFromUrl(url);
    }
}
