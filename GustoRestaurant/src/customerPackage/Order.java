/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerPackage;

import gustorestaurant.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Order
{

    private String Id;
    private String complainMessage;
    private String customerId;
    private String customerName;
    private String customerMobile;
    private String customerAddress;
    //private Date deliveryTime;
    private String associatedDeliveryBoyId;
    protected HashMap<String, Integer> orderList = new HashMap<>();
    protected HashMap<String, Double> ratings = new HashMap<>();
    DateFormat dateFormat = new SimpleDateFormat("h:mm aa");
    private boolean voucherFound = false;
    boolean assigned = false;
    //private Date orderStartTime;
    private long CanelTimeInMillis = (60000 * 0);
    private long startTimeInMillis = (60000 * 0);
    private long deliveryTimeInMillis = (60000 * 0);

    private boolean status = false;

    public Order(String Id, String customerId, String customerName, String customerAddress, String customerMobile)
    {
        this.Id = Id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerMobile = customerMobile;
    }

    public static int getNoOrders() //for genearting id numbers of new orders
    {
        int count = 0;
        try
        {
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement("SELECT count(*) FROM menu_item.order_info");
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next())
            {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public Order()
    {
    }

    public Order(String Id)
    {
        this.Id = Id;
    }

    public void setAssociatedDeliveryBoyId(String associatedDeliveryBoyId)
    {
        this.associatedDeliveryBoyId = associatedDeliveryBoyId;
    }

    public void setOrderList(HashMap<String, Integer> orderList)
    {
        this.orderList = orderList;
    }

    public void setCanelTimeInMillis(long CanelTimeInMillis)
    {
        this.CanelTimeInMillis = CanelTimeInMillis;
    }

//    public void setDeliveryTime(Date deliveryTime)
//    {
//        this.deliveryTime = deliveryTime;
//    }
//
//    public void setOrderStartTime(Date orderStartTime)
//    {
//        this.orderStartTime = orderStartTime;
//    }

    public void setId(String Id)
    {
        this.Id = Id;
    }

    public void setDeliveryTimeInMillis(long deliveryTimeInMillis) {
        this.deliveryTimeInMillis = deliveryTimeInMillis;
    }

    public long getDeliveryTimeInMillis() {
        return deliveryTimeInMillis;
    }

    public void setStartTimeInMillis(long startTimeInMillis) {
        this.startTimeInMillis = startTimeInMillis;
    }

    public long getStartTimeInMillis() {
        return startTimeInMillis;
    }

    public boolean isVoucherFound()
    {
        return voucherFound;
    }

    public void setVoucherFound(boolean voucherFound)
    {
        this.voucherFound = voucherFound;
    }

    public void setComplainMessage(String complainMessage)
    {
        this.complainMessage = complainMessage;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getAssociatedDeliveryBoyId()
    {
        return associatedDeliveryBoyId;
    }

//    public Date getOrderStartTime()
//    {
//        return orderStartTime;
//    }

    public String getId()
    {
        return Id;
    }

//    public Date getDeliveryTime()
//    {
//        return deliveryTime;
//    }

    public long getCanelTimeInMillis()
    {
        return CanelTimeInMillis;
    }

    public String getComplainMessage()
    {
        return complainMessage;
    }

    static public boolean checkStatus(Order order)
    {
        //Date currentTime = new Date();
        long currentTime = System.currentTimeMillis();
        //long ONE_MINUTE_IN_MILLIS = 60000;
        if (order.getDeliveryTimeInMillis()<=currentTime/*currentTime.after(order.getDeliveryTime()) || currentTime.equals(order.getDeliveryTime())*/)
        {
            order.setStatus(true);
        }
        
        return order.getStatus();
    }

    public boolean getStatus()
    {
        return status;
    }

    public void writeNewOrder(String orderIds)
    {
        //boolean isEmptyList = false;
//        String start = (String) dateFormat.format(orderStartTime);
//        String duration = dateFormat.format(deliveryTime);
        String menuItemsIds = "";
        //Add the new order to order_info table in data base
        try
        {
            //Add new added item into database in menu_item table
            String query = "INSERT INTO order_info ( order_id, order_cutomerName,order_StartMillis ,order_deliveryBoy, order_receivedStatus , order_complainMessage , order_menuItemList , order_DeliveryMillis )" + " VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query);

            preparedStmt.setInt(1, Integer.valueOf(Id));
            preparedStmt.setString(2, customerName);
            preparedStmt.setLong(3, startTimeInMillis);
            preparedStmt.setString(4, associatedDeliveryBoyId);
            preparedStmt.setBoolean(5, status);
            //preparedStmt.setString(5, String.valueOf(status));
            preparedStmt.setString(6, complainMessage);
            //take the ordered food items ids from the inventoryList map in a string
            for (String foodItem : orderList.keySet())
            {
                menuItemsIds += String.valueOf(foodItem);
                menuItemsIds += "#";
                menuItemsIds += String.valueOf(orderList.get(foodItem));
                menuItemsIds += "&";
            }
            menuItemsIds = menuItemsIds.substring(0, menuItemsIds.length() - 1);
            preparedStmt.setString(7, menuItemsIds);
            preparedStmt.setLong(8,  deliveryTimeInMillis);
            //preparedStmt.setLong(9, startTimeInMillis);
            //preparedStmt.setLong(10, deliveryTimeInMillis);
            preparedStmt.execute();

        } catch (SQLException ex)
        {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Add the new order id to the list of order ids
        try
        {
            //Add new added item into database in menu_item table
            if (orderIds == null)
            {
                //write the order id as string in the data base
                orderIds = Id;
            }
            else
            {
                orderIds += "/" + Id;
            }
            /*String query = "UPDATE customer SET customer_listOfOrders='" + orderIds + "'"
             + " WHERE customer_name =" + customerName + "";
             PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query);
             preparedStmt.execute();*/
             System.out.println(customerId+" "+Integer.valueOf(customerId));
            String query = "UPDATE customer SET customer_listOfOrders = '" + orderIds
                    + "' WHERE customer_id = " + Integer.valueOf(customerId);
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query);
            preparedStmt.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateOrder(Order order)
    {
        try
        {
             System.out.println(order.getId()+" "+Integer.valueOf(Id)+" in updateorders fun");
            String query = "UPDATE order_info SET order_receivedStatus = " + order.getStatus()
                    + " WHERE order_id = " + Integer.valueOf(order.getId());
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(query);
            preparedStmt.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void updateDeliveryBoys(DeliveryBoy deliveryBoy)
    {
        int status;

        try
        {
            System.out.println(deliveryBoy.getOrdersList().size()+" "+deliveryBoy.isAvailable()+" we are in update function");
            if (deliveryBoy.isAvailable())
            {
                status = 1;
            }
            else
            {
                status = 0;
            }
             System.out.println(status+" we are in update function");
            if (deliveryBoy.getOrdersList().isEmpty())
            {

                String Query = " UPDATE menu_item.delivery_boys SET status_delivery_boys=" + deliveryBoy.isAvailable()
                        + ",order1_delivery_boys = " + null
                        + ",order2_delivery_boys =" + null
                        + " WHERE id_delivery_boys =" + deliveryBoy.getId() + "";
                PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(Query);
                preparedStmt.execute();
            }
            else if (deliveryBoy.getOrdersList().size() == 1)
            {
                String Query = " UPDATE menu_item.delivery_boys SET status_delivery_boys=" + deliveryBoy.isAvailable()
                        + ",order1_delivery_boys = " + deliveryBoy.getOrdersList().get(0).Id
                        + ",order2_delivery_boys =" + null
                        + " WHERE id_delivery_boys =" + deliveryBoy.getId() + "";
                PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(Query);
                preparedStmt.execute();
            }

            else if (deliveryBoy.getOrdersList().size() == 2)
            {
                String Query = " UPDATE menu_item.delivery_boys SET status_delivery_boys=" + deliveryBoy.isAvailable()
                        + ",order1_delivery_boys = " + deliveryBoy.getOrdersList().get(0).Id
                        + ",order2_delivery_boys =" + deliveryBoy.getOrdersList().get(1).Id
                        + " WHERE id_delivery_boys =" + deliveryBoy.getId() + "";
                PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement(Query);
                preparedStmt.execute();

            }

        } catch (SQLException ex)
        {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    boolean assignOrder()
    {
        try
        {
            Date currentTime = new Date();
            String Now = dateFormat.format(currentTime/*Date.from(Instant.now())*/);
            Date now = dateFormat.parse(Now);
            
            
            for (String deliveryBoyId : DeliveryBoy.deliveryBoyList.keySet())
            {
                System.out.println(assigned+" "+DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getOrdersList().size()+" "+DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getName()+" "+dateFormat.format(DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getStart())+" "+now+" "+dateFormat.format(DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getEnd())+" in assign function");
                if ((!assigned) && (DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getStart().before(now) || DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getStart().equals(now)) && DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getEnd().after(now)) // we can add
                {

                    if (DeliveryBoy.deliveryBoyList.get(deliveryBoyId).isAvailable())
                    {
                        //System.out.println(OrderForm.deliveryBoy.isAvailable());
                        assigned = true;
                        DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getOrdersList().add(this);
                        associatedDeliveryBoyId = deliveryBoyId;

                        if (DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getOrdersList().size() == DeliveryBoy.maxOrders)
                        {
                            DeliveryBoy.deliveryBoyList.get(deliveryBoyId).setAvailable(false);
                        }
                        if (assigned)
                        {
                            //updateDeliveryBoys(DeliveryBoy.deliveryBoyList.get(deliveryBoyId));
                            System.out.println(assigned+" "+DeliveryBoy.deliveryBoyList.get(deliveryBoyId).getOrdersList().size()+" "+DeliveryBoy.deliveryBoyList.get(deliveryBoyId).isAvailable()+" in assign function");
                            break;
                        }

                    }
                }
                

            }
            
            if (!assigned)
            {
                JOptionPane.showMessageDialog(null, "We are so sorry but there is no available delivery for now.\nTry to order after a while.");
            }
        } catch (ParseException ex)
        {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assigned;

    }
}
