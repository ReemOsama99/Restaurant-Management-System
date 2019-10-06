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
public class MenuItem implements Comparable<MenuItem>
{

    private int itemId;
    private String itemName;
    private double itemRate;
    private int itemPrice;

    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public int getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemName()
    {
        return itemName;
    }

    public double getItemRate()
    {
        return itemRate;
    }

    public void setItemRate(double itemRate)
    {
        this.itemRate = itemRate;
    }

    @Override
    public int compareTo(MenuItem o)
    {
        if (this.itemRate < o.itemRate)
        {
            return -1;
        }
        else if (this.itemRate > o.itemRate)
        {
            return 1;
        }
        else
        {
            return 0;
        }

    }
}
