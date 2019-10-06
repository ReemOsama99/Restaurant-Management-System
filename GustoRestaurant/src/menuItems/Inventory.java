/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuItems;

import gustorestaurant.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Inventory
{

    private double newRate, rate;
    private int numberOfRates;
    /**
     *
     */
    //Map that contains Inventory List with menu item name as a key and value inventory item 
    public static HashMap<String, InventoryItem> inventoryList = new HashMap<String, InventoryItem>();

    //variables for calculating new rate of menu item
    public void setInventoryItems()
    {
        try
        {
            //Reading Inventory info from inventory table in database
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement("select * from inventory");
            ResultSet rs = preparedStmt.executeQuery();
            try
            {
                //Getting All Rows in Database from inventory table
                while (rs.next())
                {
                    //looping over inventoryList map to add inventory info with id read from database
                    inventoryList.keySet().forEach((itemName) ->
                    {
                        try
                        {
                            if (inventoryList.get(itemName).getMenuItem().getItemId() == rs.getInt("item_id"))
                            {
                                inventoryList.get(itemName).setAvaliableMenuItem(rs.getInt("avaliable_quantity"));
                                inventoryList.get(itemName).setSoldMenuItem(rs.getInt("units_sold"));
                                inventoryList.get(itemName).setNumberOfRates(rs.getInt("number_rate"));
                            }
                        } catch (SQLException ex)
                        {
                            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    });
                }

            } catch (SQLException ex)
            {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rateItem(Map<String, Double> rateMap)
    {

        //looping over rateMap and get rate of item and updating rate
        rateMap.keySet().forEach((itemName) ->
        {
            numberOfRates = inventoryList.get(itemName).getNumberOfRates();
            rate = inventoryList.get(itemName).getMenuItem().getItemRate();

            //Setting new rate
            if (numberOfRates == 0)
            {
                newRate = rateMap.get(itemName);
            }
            else
            {
                newRate = (rate + rateMap.get(itemName)) / numberOfRates;
            }

            //Rounding rate to two decimal places
            newRate = Math.round(newRate * 10.0) / 10.0;

            //Update new rate in main menu map    
            inventoryList.get(itemName).getMenuItem().setItemRate(newRate);

            //Update new rate in menu item table in database
            try
            {
                String query = "UPDATE menu_item SET rankmenu_item = " + newRate + " WHERE idmenu_item = " + inventoryList.get(itemName).getMenuItem().getItemId();
                PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query);
                preparedStmt.execute();
            } catch (SQLException ex)
            {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
