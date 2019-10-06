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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class MainMenu
{
//Setting Restaurant Name

    private final String restaurantName = "GUSTO";
    private Category category;
    

    //Map that contains key String(Category name) and value List of menu item 
    public static HashMap<String, ArrayList<MenuItem>> categoryList = new HashMap<>();

    public String getrestaurantName()
    {
        return restaurantName;
    }

    //Read from database and fill main menu Map
    public void setCategoryItem()
    {
         InventoryItem inventory ;
          MenuItem menuitem;
        try
        {

            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement("select * from menu_item");
            ResultSet rs = preparedStmt.executeQuery();

            // Looping over database rows and get info 
            while (rs.next())
            {

                /**
                 * Filling object of MenuItem to add it to List of menu items of
                 * Map
                 */
                menuitem = new MenuItem();
                menuitem.setItemId(rs.getInt("idmenu_item"));
                menuitem.setItemName(rs.getString("namemenu_item"));
                menuitem.setItemRate(rs.getDouble("rankmenu_item"));
                menuitem.setItemPrice(rs.getInt("pricemenu_item"));

                //Checking if Category exists in Map 
                if (!(categoryList.containsKey(rs.getString("category"))))
                {
                    category = new Category();
                    category.menuitemlist = new ArrayList<>();
                    category.setCategory_name(rs.getString("category"));
                    categoryList.put(rs.getString("category"), category.menuitemlist);
                }

                categoryList.get(rs.getString("category")).add(menuitem);

                //Filling object of inventory item to add it to inventoryList(inventory map) 
                inventory = new InventoryItem();
                inventory.setMenuItem(menuitem);
                Inventory.inventoryList.put(rs.getString("namemenu_item"), inventory);
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
