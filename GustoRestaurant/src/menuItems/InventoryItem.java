/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuItems;

/**
 *
 * @author PC
 */
public class InventoryItem
{

    private int soldMenuItem ;
    private int avaliableMenuItem;
    private int numberOfRates;
    private MenuItem menuItem;
    
    //Getters And Setters for attributes of Inventory Item Class
    public int getNumberOfRates() {
        return numberOfRates;
    }

    public void setNumberOfRates(int numberOfRates) {
        this.numberOfRates = numberOfRates;
    }

    public int getSoldMenuItem() {
        return soldMenuItem;
    }
    
    public void setSoldMenuItem(int soldMenuItem) {
        this.soldMenuItem = soldMenuItem;
    }
    
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
    
    public MenuItem getMenuItem() {
        return menuItem;
    }
    
    public int getAvaliableMenuItem() {
        return avaliableMenuItem;
    }
    
    public void setAvaliableMenuItem(int avaliableMenuItem) {
        this.avaliableMenuItem = avaliableMenuItem;
    }
}