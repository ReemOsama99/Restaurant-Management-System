/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerPackage;

import java.util.ArrayList;
import menuItems.MenuItem;

/**
 *
 * @author PC
 */
public class Announcement
{
    String managerName;
    String message;
    int price;
    ArrayList<MenuItem> menuItems;

    public String getManagerName()
    {
        return managerName;
    }

    public String getMessage()
    {
        return message;
    }

    public int getPrice()
    {
        return price;
    }

    public ArrayList<MenuItem> getMenuItems()
    {
        return menuItems;
    }

    public Announcement(String managerName, String message, int price, ArrayList<MenuItem> menuItems)
    {
        this.managerName = managerName;
        this.message = message;
        this.menuItems = menuItems;
        this.price = price;
    }
}
