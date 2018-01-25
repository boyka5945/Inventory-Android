package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CK Tan on 1/24/2018.
 */

public class RequisitionForm
{
    private static List<Requisition_Detail> _requisitionForm = new ArrayList<>();
    
    public static List<Requisition_Detail> getInstance()
    {
        return _requisitionForm;
    }
    
    public static int getLength()
    {
        return _requisitionForm.size();
    }
    
    public static Boolean addRequestItem(Requisition_Detail newItem) throws Exception
    {
        Boolean contain = false;
        try
        {
            for (Requisition_Detail currentItemInForm : _requisitionForm)
            {
                if(currentItemInForm.get(Key.REQUISITION_DETAIL_2_ITEM_CODE).equals(newItem.get(Key.REQUISITION_DETAIL_2_ITEM_CODE)))
                {
                    int oldQty = Integer.parseInt(currentItemInForm.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY));
                    int newQty = Integer.parseInt(newItem.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY));
                    int totalQty = oldQty + newQty;
                    currentItemInForm.put(Key.REQUISITION_DETAIL_6_REQUEST_QTY, String.valueOf(totalQty));
                    contain = true;
                }
            }
    
            if(!contain)
            {
                _requisitionForm.add(newItem);
            }
    
            return true;
        }
        catch (Exception e)
        {
            throw new Exception("Error when adding new item into form");
        }
    }
    
    public static Requisition_Detail removeRequestItem(int position) throws Exception
    {
        try
        {
            return _requisitionForm.remove(position);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
    
    public static Boolean removeRequestItem(Requisition_Detail item) throws Exception
    {
        try
        {
            int pos = -1;
            
            for (int i = 0; i < _requisitionForm.size(); i++)
            {
                Requisition_Detail currentItemInForm = _requisitionForm.get(i);
                if(currentItemInForm.get(Key.REQUISITION_DETAIL_2_ITEM_CODE).equals(item.get(Key.REQUISITION_DETAIL_2_ITEM_CODE)))
                {
                    pos = i;
                }
            }
            
            if(pos == -1)
            {
                throw new Exception("Item no found");
            }
            
            _requisitionForm.remove(pos);
            
            return true;
        }
        catch (Exception e)
        {
            throw new Exception("Error when deleting item from form");
        }
    }
    
    public static Boolean clearAllRequestItems() throws Exception
    {
        try
        {
            _requisitionForm.clear();
            return true;
        }
        catch (Exception e)
        {
            throw new Exception("Error when trying to clear form");
        }
    }
    
    
    public static Boolean updateRequestQty(String itemCode, int quantity)
    {
        try
        {
            for (Requisition_Detail item : _requisitionForm)
            {
                if(item.get(Key.REQUISITION_DETAIL_2_ITEM_CODE).equals(itemCode))
                {
                    int currentQty = Integer.valueOf(item.get(Key.REQUISITION_DETAIL_6_REQUEST_QTY));
                    int newQty = currentQty + quantity;
                    if(newQty <= 0)
                    {
                        return false;
                    }
                    else
                    {
                        item.put(Key.REQUISITION_DETAIL_6_REQUEST_QTY, String.valueOf(newQty));
                        return true;
                    }
                }
            }
            
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    public static boolean changeRequestQty(String itemCode, int newQty)
    {
        try
        {
            for (Requisition_Detail item : _requisitionForm)
            {
                if(item.get(Key.REQUISITION_DETAIL_2_ITEM_CODE).equals(itemCode))
                {
                    if(newQty <= 0)
                    {
                        return false;
                    }
                    else
                    {
                        item.put(Key.REQUISITION_DETAIL_6_REQUEST_QTY, String.valueOf(newQty));
                        return true;
                    }
                }
            }
        
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
