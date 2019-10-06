/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerPackage;

/**
 *
 * @author PC
 */
import gustorestaurant.MyConnection;
import java.awt.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryBoy
{
    private String id;
    private String name;
    static final int maxOrders = 2;
    private boolean available;
    private Date end;
    private Date start;
    private ArrayList<Order> ordersList;
    private Order order = new Order();
    DateFormat dateFormat = new SimpleDateFormat("h:mm aa");
    public static HashMap<String, DeliveryBoy> deliveryBoyList = new HashMap<>();
    //System.out.println(dateFormat.format (date));
    
    public DeliveryBoy()
    {
        this.ordersList = new ArrayList<>();
    }

    public DeliveryBoy(String id, String name, boolean available, ArrayList<Order> ordersList)
    {
        this.id = id;
        this.name = name;
        this.available = available;
        this.ordersList = ordersList;
    }
    // to fill delivery Boys List from database 

    void addOrder(String orderId)
    {
        //to read null Strings from data base
        if (orderId == null)
        {
            return;
        }

        this.ordersList.add(order);
    }

    public Date getEnd()
    {
        return end;
    }

    public Date getStart()
    {
        return start;
    }

    public String getId()
    {
        return id;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public void setAvailable(boolean available)
    {
        this.available = available;
    }

    public ArrayList<Order> getOrdersList()
    {
        return ordersList;
    }

    public void setOrdersList(ArrayList<Order> ordersList)
    {
        this.ordersList = ordersList;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }

    public void setStart(Date start)
    {

        this.start = start;
    }

    public void setDeliveryBoys()
    {
        DeliveryBoy deliveryBoy;
        try
        {

            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement("select * from menu_item.delivery_boys");
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next())
            {

                deliveryBoy = new DeliveryBoy();
                deliveryBoy.setId(rs.getString("id_delivery_boys"));
                deliveryBoy.setName(rs.getString("name_delivery_boys"));

                deliveryBoy.setStart(dateFormat.parse(rs.getString("start_time_delivery_boys")));
                

                deliveryBoy.setEnd(dateFormat.parse(rs.getString("finish_time_delivery_boys")));

                deliveryBoy.setAvailable(rs.getBoolean("status_delivery_boys"));
                
                if (rs.getString("order1_delivery_boys") != null)
                {
                    deliveryBoy.addOrder(rs.getString("order1_delivery_boys"));
                }
                if (rs.getString("order2_delivery_boys") != null)
                {
                    deliveryBoy.addOrder(rs.getString("order2_delivery_boys"));
                }
                System.out.println(deliveryBoy.getName()+" "+deliveryBoy.isAvailable()+" "
                        +dateFormat.format(deliveryBoy.getStart())+" "+dateFormat.format(deliveryBoy.getEnd())+" "+deliveryBoy.getOrdersList().size()+" in reading");
                deliveryBoyList.put(deliveryBoy.getId(), deliveryBoy);

            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex)
        {
            Logger.getLogger(DeliveryBoy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}