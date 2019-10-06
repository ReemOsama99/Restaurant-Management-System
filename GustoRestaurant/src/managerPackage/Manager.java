/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerPackage;

import customerPackage.DeliveryBoy;
import gustorestaurant.MyConnection;
import gustorestaurant.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import menuItems.Inventory;
import menuItems.InventoryForm;
import menuItems.InventoryItem;
import menuItems.MainMenu;
import menuItems.MenuItem;
import starting.HomepageForm;
import starting.LoginForm;
import starting.SignUpForm;

/**
 *
 * @author PC
 */
public class Manager extends User
{

    public Manager(String name, String username, String mobilePhone, String password)
    {
        super(name, username, mobilePhone, password);
    }

    public Manager()
    {

    }

    public String getName()
    {
        return name;
    }

    void addDeliveryBoy(DeliveryBoy deliveryBoy)
    {
        try
        {

            String Query = "INSERT INTO menu_item.delivery_boys (id_delivery_boys ,name_delivery_boys ,start_time_delivery_boys,finish_time_delivery_boys ,status_delivery_boys ,orderList_delivery_boys)"
                    + " VALUES (?,?,?,?,?,?,?)";

            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(Query);

            preparedStmt.setString(1, deliveryBoy.getId());
            preparedStmt.setString(2, deliveryBoy.getName());
            preparedStmt.setString(3, String.valueOf(deliveryBoy.getStart()));
            preparedStmt.setString(4, String.valueOf(deliveryBoy.getEnd()));
            preparedStmt.setInt(5, 1);
            preparedStmt.setString(6, null);
            preparedStmt.setString(7, null);
            preparedStmt.execute();

        } catch (SQLException ex)
        {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addInventoryItem(String itemName, int itemPrice, String CategoryName, int avaliable_quantity)
    {
        Boolean itemFound = false;
        String query; // Database Query    
        int itemID = 0; //variable to set id for item
        MenuItem menuitem = new MenuItem();
        InventoryItem inventory = new InventoryItem();

        //To Check if menu item already exists
        
        for (int i = 0; i < MainMenu.categoryList.get(CategoryName).size(); ++i)
        {
            
            if (MainMenu.categoryList.get(CategoryName).get(i).getItemName().equals(itemName))
            {
                JOptionPane.showMessageDialog(null, "This Item already exists");
                itemFound = true;
                break;
            }
        }
        if (!itemFound)
        {
            try
            {
                //Add new added item into database in menu_item table
                query = "INSERT INTO menu_item ( idmenu_item, namemenu_item,rankmenu_item , pricemenu_item , category )" + " VALUES(?,?,?,?,?)";
                PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query);
                //Set random itemID
                itemID = Inventory.inventoryList.size() + 1;
                preparedStmt.setInt(1, itemID);
                preparedStmt.setString(2, itemName);
                preparedStmt.setInt(3, 0);
                preparedStmt.setInt(4, itemPrice);
                preparedStmt.setString(5, CategoryName);
                if (preparedStmt.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "New item is added ");
                    //To add new addded item to my main menu map
                    menuitem.setItemId(itemID);
                    menuitem.setItemName(itemName);
                    menuitem.setItemPrice(itemPrice);
                    menuitem.setItemRate(0);
                    MainMenu.categoryList.get(CategoryName).add(menuitem);
                    //To display new added item info on table
                    InventoryForm.model.addRow(new Object[]
                    {
                        itemID, itemName, 0, 0, avaliable_quantity
                    });
                }

            } catch (SQLException ex)
            {

                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
            try
            {
                //Add new inventory info in database in inventory table
                query = "INSERT INTO inventory ( item_id , units_sold , avaliable_quantity , number_rate)" + " VALUES(?,?,?,?)";
                PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query);
                preparedStmt.setInt(1, itemID);
                preparedStmt.setInt(2, 0);
                preparedStmt.setInt(3, avaliable_quantity);
                preparedStmt.setInt(4, 0);
                preparedStmt.execute();
                //To add new added item in inventory List
                inventory.setMenuItem(menuitem);
                inventory.setAvaliableMenuItem(avaliable_quantity);
                inventory.setSoldMenuItem(0);
                Inventory.inventoryList.put(menuitem.getItemName(), inventory);

            } catch (SQLException ex)
            {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void viewInventoryItem(String categoryName)
    {
        String itemName;
        try
        {

            //Looping over categoryList(main menu map) to display List of menuItem and inventoryItem of choosen category on table
            for (int i = 0; i < MainMenu.categoryList.get(categoryName).size(); ++i)
            {
                //getting item name to get inventory info from inventoryList map with itemNam(key of map)
                itemName = MainMenu.categoryList.get(categoryName).get(i).getItemName();

                //Getting Inventory Info from Inventory List And Display it on table
                //Getting from categoryList(main menu map) menuItemID,menuItemName,menuItemRate
                //Getting from inventoryList(inventory map) inventoryItemUnits_sold, inventoryItemAvaliable_Quantity
                //Displaying info on table
                InventoryForm.model.addRow(new Object[]
                {
                    MainMenu.categoryList.get(categoryName).get(i).getItemId(), MainMenu.categoryList.get(categoryName).get(i).getItemName(),
                    MainMenu.categoryList.get(categoryName).get(i).getItemRate(), Inventory.inventoryList.get(itemName).getSoldMenuItem(), Inventory.inventoryList.get(MainMenu.categoryList.get(categoryName).get(i).getItemName()).getAvaliableMenuItem()
                });

            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please Choose one Category");
        }
    }

    public void deleteInventoryItem(int deleteId, String categoryName)
    {
        try
        {
            //Delete Menu Item from menu item table in database
            String query = "delete from menu_item where idmenu_item = ? ";
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, deleteId);

            if (preparedStmt.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "Item Deleted");

                //Search in categoryList(main menu map) for deleteItemID to delete it from map 
                //After getting name of deleteItem from categoryList delete it from inventoryList(inventory map)
                for (int i = 0; i < MainMenu.categoryList.get(categoryName).size(); ++i)
                {
                    if (MainMenu.categoryList.get(categoryName).get(i).getItemId() == deleteId)
                    {
                        //getting name of item from categoryList
                        String iname = MainMenu.categoryList.get(categoryName).get(i).getItemName();

                        //Remove menu item from main menu map (main menu map)
                        MenuItem removedMenuItem = new MenuItem();
                        removedMenuItem = MainMenu.categoryList.get(categoryName).remove(i);

                        //Remove Inventory item from inventory list map
                        InventoryItem removedInventoryItem = new InventoryItem();
                        if (Inventory.inventoryList.containsKey(iname))
                        {
                            removedInventoryItem = Inventory.inventoryList.remove(iname);
                        }

                        //Delete item row from table
                        InventoryForm.model.removeRow(i);
                        break;

                    }

                }
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            //Delete Inventory Item from inventory table in database
            String query = "delete from inventory where item_id= ? ";
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, deleteId);
            preparedStmt.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addAnnouncement(Announcement a)
    {
        HomepageForm.offers.add(a);
        String items = convertItemstoString(a.menuItems);
        PreparedStatement ps;
        String query = "INSERT INTO `announcements`(`managerName`, `message`, `menuItems`, `price`) VALUES (?,?,?,?)";
        try
        {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, this.name);
            ps.setString(2, a.message);
            ps.setString(3, items);
            ps.setInt(4, a.price);
            boolean execute = ps.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //manage delievery boys
    @Override
    public boolean register()
    {
        boolean registered = false;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM manager WHERE manager_username =?";
        try
        {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, this.userName);
            rs = ps.executeQuery();
            if (rs.next())
            {
                JOptionPane.showMessageDialog(null, "This Username Already Exists");
                SignUpForm.setTextField();
            }
            else
            {
                query = "INSERT INTO `manager`(`manager_username`, `manager_mobile`, `manager_password`, `manager_name`) VALUES (?,?,?,?)";
                ps = MyConnection.getConnection().prepareStatement(query);

                String encrypted = User.encryptThisString(this.password);
                this.password = encrypted;
                ps.setString(1, this.userName);
                ps.setString(2, this.mobilePhone);
                ps.setString(3, this.password);
                ps.setString(4, this.name);

                if (ps.executeUpdate() > 0)
                {
                    //JOptionPane.showMessageDialog(null, "New User Add");
                    Manager m = new Manager(this.name, this.userName, this.mobilePhone, this.password);
                    ManagerWelcomeForm mf = new ManagerWelcomeForm(m);
                    mf.setVisible(true);
                }
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registered;
    }

    /**
     *
     * @param muserName
     * @param mPassword
     * @return
     */
    @Override
    public boolean login(String muserName, String mPassword)
    {
        boolean loggedIn = false;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM manager WHERE manager_username=? AND  manager_password=?";
        try
        {
            String encrypted = User.encryptThisString(mPassword);
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, muserName);
            ps.setString(2, encrypted);
            rs = ps.executeQuery();
            if (rs.next())
            {
                String mphone = rs.getString("manager_mobile");
                String mname = rs.getString("manager_name");
                Manager m = new Manager(mname, muserName, mphone, mPassword);
                ManagerWelcomeForm mf = new ManagerWelcomeForm(m);
                mf.setVisible(true);
                loggedIn = true;
            }
            else
            {
                LoginForm.setTextFields();
                JOptionPane.showMessageDialog(null, "Username or password incorrect.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex)
        {
            System.out.println("fail");
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loggedIn;
    }
}
