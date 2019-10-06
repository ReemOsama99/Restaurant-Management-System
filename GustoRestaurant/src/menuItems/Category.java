/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuItems;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Category
{
   private String category_name;
    
    //List of menu items for every category
    protected ArrayList<MenuItem>menuitemlist;

    //Getters and Setters for attributes
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public void setMenuItemList(MenuItem menuItem) {
       menuitemlist.add(menuItem);
    }

    public ArrayList<MenuItem> getMenuitemlist() {
        return menuitemlist;
    }

    public String getCategory_name() {
        return category_name;
    }
}
