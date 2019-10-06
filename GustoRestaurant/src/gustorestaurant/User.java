/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gustorestaurant;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import managerPackage.Announcement;
import menuItems.Inventory;
import static menuItems.Inventory.inventoryList;
import menuItems.MainMenu;
import menuItems.MenuItem;
import starting.HomepageForm;

/**
 *
 * @author PC
 */
abstract public class User
{

    protected final String name;
    protected final String userName;
    protected String mobilePhone;
    protected String password;

    public User()
    {
        name = "";
        userName = "";
    }

    public User(String name, String userName, String mobilePhone, String password)
    {
        this.mobilePhone = mobilePhone;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     */
    public abstract boolean register();

    /**
     *
     * @param userName
     * @param password
     */
    public abstract boolean login(String userName, String password);

    public static String encryptThisString(String input)
    {
        try
        {
            // getInstance() method is called with algorithm SHA-1 
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }

            // return the HashText 
            return hashtext;
        } 
        catch (NoSuchAlgorithmException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return null;
    }

    //reading announcements from DB
    public static void viewAnnouncements()
    {
        try
        {
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement("select * from announcements");
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next())
            {
                String name = rs.getString("managerName");
                String message = rs.getString("message");
                int price = rs.getInt("price");
                String items = rs.getString("menuItems");
                ArrayList<MenuItem> getItem = convertStringtoItems(items);
                Announcement a = new Announcement(name, message, price, getItem);
                HomepageForm.offers.add(a);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //helper function, converts list of menu item names to string to write it in DB
    protected static String convertItemstoString(ArrayList<MenuItem> m)
    {
        String s = "";
        for (int i = 0; i < m.size(); ++i)
        {
            String itemName;
            itemName = m.get(i).getItemName();
            s += itemName + "+";
        }
        return s;
    }

    //helper function, converts string of menu item names in DB to objects of menu items
    protected static ArrayList<MenuItem> convertStringtoItems(String s)
    {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        String[] offerItems = s.split("\\+");
        for (String a : offerItems)
        {
            MenuItem m = inventoryList.get(a).getMenuItem();
            menuItems.add(m);
        }
        return menuItems;
    }
}
