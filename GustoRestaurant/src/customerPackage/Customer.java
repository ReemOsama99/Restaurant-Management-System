/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerPackage;

import gustorestaurant.MyConnection;
import gustorestaurant.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import menuItems.Inventory;
import menuItems.MainMenu;
import menuItems.MenuItem;
import starting.LoginForm;
import starting.SignUpForm;

/**
 *
 * @author PC
 */
public class Customer extends User
{

    private String address;
    private int id;
    private ArrayList<Order> orders = new ArrayList<>(); //list of all customer's orders
    private Order tempOrder = new Order(); //to be able to access class order, current order

    public int getId()
    {
        return id;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public Customer(int id, String name, String username, String mobilePhone, String password, String address, ArrayList<Order> o)
    {
        super(name, username, mobilePhone, password);
        this.id = id;
        this.address = address;
        this.orders = o;
    }

    public Customer(int id, String name, String username, String mobilePhone, String password, String address)
    {
        super(name, username, mobilePhone, password);
        this.id = id;
        this.address = address;
    }

    public void placeOrders(Order order)
    {
        tempOrder = order; //it contains the current order of the customer(the order being processed)
        orders.add(order);
    }

    public void complain(String message)
    {
        tempOrder.setComplainMessage(message);
    }

    public void rating(String foodItem, double rate)
    {
        tempOrder.ratings.put(foodItem, rate);
    }

    public void returnOrder(Order order) //a search in jframe with order id then send the order itself to the function
    {
        orders.remove(order);
    }

    public void cancelOrder()
    {
        //Date currentTime = new Date();
        long currentTime = System.currentTimeMillis();
        //check if 5 minutes or less has passed
        Date cancellationTime = new Date(tempOrder.getCanelTimeInMillis());
        System.out.println(cancellationTime);
        /*currentTime.equals(cancellationTime) || currentTime.before(cancellationTime)*/
        if (tempOrder.getCanelTimeInMillis()>=currentTime && !BillForm.delivered)
        {
            returnOrder(tempOrder);
            BillForm.returned=true;
            JOptionPane.showMessageDialog(null, "The order has been cancelled\nThank you for your visit", "Cancel", JOptionPane.INFORMATION_MESSAGE);
        }
        //more than 5 minutes has passed
        else
        {
            JOptionPane.showMessageDialog(null, "Unfortunately you can't cancel the order now", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getAddress()
    {
        return address;
    }

    public Order getTempOrder()
    {
        return tempOrder;
    }

    public ArrayList<Order> getOrders()
    {
        return orders;
    }

    public String getName()
    {
        return name;
    }

    public void setTempOrder(Order tempOrder) {
        this.tempOrder = tempOrder;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public static int getNoCustomers() //for genearting id numbers of new customers
    {
        int count = 0;
        try
        {
            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement("SELECT count(*) FROM menu_item.customer");
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next())
            {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
        
    }

    public Customer()
    {
    }

    @Override
    public boolean register()
    {
        boolean registered = false; //to hide signup form if successfully signed in
        PreparedStatement ps = null;
        ResultSet rs;
        String query = "SELECT * FROM customer WHERE customer_username = ?";
        try
        {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, this.userName);
            rs = ps.executeQuery();
            if (rs.next()) //check if username already exists to prevent duplicate entries
            {
                JOptionPane.showMessageDialog(null, "This Username Already Exists");
                SignUpForm.setTextField();
                return registered;
            }
            else
            {
                query = "INSERT INTO `customer`(`customer_username`, `customer_mobile`, `customer_password`, `customer_name`, `customer_address`, `customer_id`, `customer_listOfOrders`) VALUES (?,?,?,?,?,?,?)";
                ps = MyConnection.getConnection().prepareStatement(query);

                String encrypted = User.encryptThisString(this.password);
                ps.setString(1, this.userName);
                ps.setString(2, this.mobilePhone);
                ps.setString(3, encrypted);
                ps.setString(4, this.name);
                ps.setString(5, this.address);
                ps.setInt(6, this.id);
                ps.setString(7, null);
                this.password = encrypted;
                if (ps.executeUpdate() > 0)
                {
                    CustomerWelcomeForm cf = new CustomerWelcomeForm(this, null);
                    cf.setVisible(true);
                    registered = true;
                }
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registered;
    }

    @Override
    public boolean login(String cuserName, String cPassword)
    {
        boolean loggedIn = false;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM customer WHERE customer_username=? AND  customer_password=?";
        try
        {
            ps = MyConnection.getConnection().prepareStatement(query);
            String encrypted = User.encryptThisString(cPassword);
            ps.setString(1, cuserName);
            ps.setString(2, encrypted);
            rs = ps.executeQuery();
            if (rs.next())
            {
                String cphone = rs.getString("customer_mobile");
                String cname = rs.getString("customer_name");
                String caddress = rs.getString("customer_address");
                int cid = rs.getInt("customer_id");
                String ordersString = rs.getString("customer_listOfOrders");
                if (ordersString != null)
                {
                    this.orders = convertStringtoOrders(ordersString);
                }
                else
                {
                    ordersString = null;
                }
                Customer c = new Customer(cid, cname, cuserName, cphone, cPassword, caddress, this.orders);
                System.out.println(c.getId());
                CustomerWelcomeForm cf = new CustomerWelcomeForm(c, ordersString);
                cf.setVisible(true);
                loggedIn = true;
            }
            else
            {
                LoginForm.setTextFields();
                JOptionPane.showMessageDialog(null, "Username or password incorrect.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loggedIn;
    }

    private ArrayList<Order> convertStringtoOrders(String s) //converts order ids read from db to objects of order
    {
        ArrayList<Order> OrderItems = new ArrayList<>();
        Order o;
        String[] order = s.split("/");
        for (String a : order)
        {
            int x = Integer.valueOf(a);
            PreparedStatement ps = null;
            ResultSet rs;
            String query = "SELECT * FROM order_info WHERE order_id = ?";
            try
            {
                ps = MyConnection.getConnection().prepareStatement(query);
                ps.setInt(1, x); //order id
                rs = ps.executeQuery();
                if (rs.next())
                {
                    String cid = String.valueOf(this.id);
                    String cname = this.name;
                    String oid = String.valueOf(x);
                    String caddress = this.address;
                    String cmobile = this.mobilePhone;
                    String oname = rs.getString("order_cutomerName");
                    String oboy = rs.getString("order_deliveryBoy");
                    String ocomplain = rs.getString("order_complainMessage");

                    //String ostatus = rs.getString("order_receivedStatus");
                    boolean status = rs.getBoolean("order_receivedStatus");
                    //boolean status = /*Boolean.valueOf(*/ostatus/*)*/;
                    
                    DateFormat dateFormat = new SimpleDateFormat("h:mm aa");
                    //Date orderStartTime = dateFormat.parse(rs.getString("order_StartTime"));
                    //Date deliveryTime = dateFormat.parse(rs.getString("order_deliveryTime"));
                    long startinMillis = rs.getLong("order_StartMillis");
                    long deliveryinMillis = rs.getLong("order_DeliveryMillis");
                    
                    HashMap<String, Integer> orderList = new HashMap<>();

                    String menuString = rs.getString("order_menuItemList");
                    String[] allOrders = menuString.split("&");
                    for (String z : allOrders)
                    {
                        String[] specific = z.split("#");
                        for (String aa : specific)
                        {
                            int q = Integer.valueOf(specific[1]);
                            orderList.put(specific[0], q);
                        }
                    }
                    o = new Order(oid, cid, cname, caddress, cmobile);
                    //o.setOrderStartTime(orderStartTime);
                    o.setComplainMessage(ocomplain);
                    //o.setDeliveryTime(deliveryTime);
                    o.setStartTimeInMillis(startinMillis);
                    o.setDeliveryTimeInMillis(deliveryinMillis);
                    o.setStatus(status);
                    System.out.println(o.getId()+" "+status+" "+o.getStatus()+" in reading orders_info in customer class");
                    o.setAssociatedDeliveryBoyId(oboy);
                    o.setOrderList(orderList);
                    OrderItems.add(o);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Order not found");
                }

            } catch (SQLException ex)
            {
                Logger.getLogger(Customer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return OrderItems;
    }
    public static void viewBestSellers()
    {
        ArrayList<MenuItem> orderedList;
        for (String category : MainMenu.categoryList.keySet())
        {
            
            orderedList = MainMenu.categoryList.get(category);
            Collections.sort(orderedList, Collections.reverseOrder());
            
            String bestItem =orderedList.get(0).getItemName();
            String rate = String.valueOf(orderedList.get(0).getItemRate());
            String numberOfRates = String.valueOf(Inventory.inventoryList.get(orderedList.get(0).getItemName()).getNumberOfRates());
            
            BestSellerForm.jTextArea1.append("From " + category + ": \n");
            BestSellerForm.jTextArea1.append(bestItem + ",  ");
            BestSellerForm.jTextArea1.append("rated " + rate + "/5  by " + numberOfRates + " users.");
            BestSellerForm.jTextArea1.append("\n\n");
        }
    }

}
