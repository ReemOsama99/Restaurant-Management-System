package customerPackage;

import customerPackage.BillForm;
import customerPackage.Customer;
import customerPackage.DeliveryBoy;
import customerPackage.Order;
import customerPackage.Voucher;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javafx.util.Pair;
import java.util.Iterator;
import javafx.scene.control.Spinner;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner.DefaultEditor;
import managerPackage.Announcement;
import menuItems.Inventory;
import menuItems.MainMenu;
import menuItems.Menu;
import starting.HomepageForm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dell
 */
public class OrderForm extends javax.swing.JFrame
{

    BillForm bill;
    static Order currentOrder;
    static int quantity;
    String category;
    static HashMap<String, Integer> orderPrice;
    MainMenu mainMenu = new MainMenu();
    boolean isCategory;
    Inventory inventory = new Inventory();
    static Customer customer = new Customer();
    static DeliveryBoy deliveryBoy;
    static Voucher voucher = new Voucher();
    long ONE_MINUTE_IN_MILLIS = 60000;
    long orderStartMillis;
    Date afterAddingTenMins;
    boolean notFirstVisit = false;
    String menuIds;
    boolean pressedOffers = false;

    /**
     * Creates new form Order
     */
    public OrderForm()
    {
        currentOrder = new Order();
        initComponents();
        mainMenu.setCategoryItem();
        inventory.setInventoryItems();
        initializeComp();
    }

    public OrderForm(Customer customer, String Ids)
    {
        initComponents();
        this.customer = customer;
        menuIds = Ids;
        String iiid = String.valueOf(customer.getId());
        boolean isLastDelivered=checkLastOrders(customer);
        if(isLastDelivered)
        {
           currentOrder = new Order(String.valueOf(Order.getNoOrders() + 1), String.valueOf(customer.getId()), customer.getName(), customer.getAddress(), customer.getMobilePhone());
        }
        else
        {
            currentOrder=customer.getOrders().get(customer.getOrders().size()-1);
            notFirstVisit=true;
        }
        if(customer.getOrders().size()>0)
        {
           customer.setTempOrder(customer.getOrders().get(customer.getOrders().size()-1));
           //System.out.println(customer.getTempOrder().getId()+" "+customer.getTempOrder().getStatus()+" in comstructor order Form");
        }
        //System.out.println(String.valueOf(customer.getId()));
        
        initializeComp();
    }
    private boolean checkLastOrders(Customer c)
    {
        Order tempOrder=new Order();
        if(c.getOrders().size()>0)
        {
            tempOrder=c.getOrders().get(c.getOrders().size()-1);
//            if(tempOrder.checkStatus(tempOrder))
//            {
//                return true;
//            }
            if(tempOrder.getStatus())
            {
                return true;
            }
            else
            {
               return false;
            }
            
        }
        else
        {
            return true;
        }
        
    }
    private void initializeComp()
    {
        this.setLocationRelativeTo(null); //center form in screen
        this.orderPrice = new HashMap<>();
        deliveryBoy = new DeliveryBoy();
        deliveryBoy.setDeliveryBoys();
        voucher.setVouchers();
        ((DefaultEditor) spin_quantity.getEditor()).getTextField().setEditable(false); //to make spinner non editable
        cbx_choose_order.setModel(new DefaultComboBoxModel<>(new String[]
        {
            ""
        })); //clear combo box in the first open
        cbx_choose_order.setSelectedIndex(-1);
        cbx_promoCode.setSelectedIndex(-1);
        fillVoucher();
        cbx_promoCode.setSelectedIndex(-1);
    }

    public void offer(String voucherId)
    {
        int precentage;
        precentage = voucher.getPercentage(voucherId);

        //if the voucher is valid set the voucher obj info
        if (precentage != 0)
        {
            voucher.setId(voucherId);
            voucher.setPercentage(precentage);
            voucher.setReleaseDate(Voucher.voucherList.get(voucherId).getReleaseDate());
            voucher.setExpirationDate(Voucher.voucherList.get(voucherId).getExpirationDate());

        }
    }

    public void fillVoucher()
    {
        Voucher.voucherList.keySet().forEach((voucherId) ->
        {
            cbx_promoCode.addItem(voucherId);
        });

    }

    public void fillComboBox(String categoryName)
    {
        cbx_choose_order.setModel(new DefaultComboBoxModel<>(new String[]
        {
            ""
        }));
        isCategory = false;
        MainMenu.categoryList.keySet().forEach((category) ->
        {
            if (category.equals(categoryName))
            {
                isCategory = true;
            }
            for (int i = 0; i < MainMenu.categoryList.get(category).size(); ++i)
            {
                String itemName = MainMenu.categoryList.get(category).get(i).getItemName();
                int price = MainMenu.categoryList.get(category).get(i).getItemPrice();
                if (isCategory)
                {
                    cbx_choose_order.addItem(itemName);
                }
                orderPrice.put(itemName, price);
            }
            isCategory = false;
        });
    }

    public final void setInitialValues() //clear combo box and spinner after each entery
    {
        //cbx_choose_order.setModel(new DefaultComboBoxModel<>(new String[]{""}));
        cbx_choose_order.setSelectedIndex(-1);
        spin_quantity.setValue(1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_background = new javax.swing.JPanel();
        btn_orderStatus = new javax.swing.JButton();
        btn_pizza = new javax.swing.JButton();
        btn_pasta = new javax.swing.JButton();
        btn_showBill = new javax.swing.JButton();
        btn_burgers = new javax.swing.JButton();
        btn_drinks = new javax.swing.JButton();
        btn_stillOrdering = new javax.swing.JButton();
        cbx_promoCode = new javax.swing.JComboBox<>();
        cbx_choose_order = new javax.swing.JComboBox<>();
        spin_quantity = new javax.swing.JSpinner();
        btn_appetizers1 = new javax.swing.JButton();
        btn_desserts1 = new javax.swing.JButton();
        btn_offers = new javax.swing.JButton();
        lbl_promoCode = new javax.swing.JLabel();
        lbl_OrderType1 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_order_background = new javax.swing.JLabel();
        pHeader = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        lbLogo = new javax.swing.JLabel();
        btnOrdersCustomer = new javax.swing.JButton();
        btnMakeOrderCustomer = new javax.swing.JButton();
        btnMenuCustomer = new javax.swing.JButton();
        btnBestSellersCustomer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Order");
        setMinimumSize(new java.awt.Dimension(1217, 788));
        setUndecorated(true);

        pnl_background.setPreferredSize(new java.awt.Dimension(1216, 711));
        pnl_background.setLayout(null);

        btn_orderStatus.setBackground(new java.awt.Color(255, 213, 0));
        btn_orderStatus.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_orderStatus.setForeground(new java.awt.Color(102, 0, 102));
        btn_orderStatus.setText("Order Status");
        btn_orderStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_orderStatus.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_orderStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_orderStatusActionPerformed(evt);
            }
        });
        pnl_background.add(btn_orderStatus);
        btn_orderStatus.setBounds(880, 110, 190, 50);

        btn_pizza.setBackground(new java.awt.Color(255, 255, 255));
        btn_pizza.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_pizza.setForeground(new java.awt.Color(102, 0, 102));
        btn_pizza.setText("Pizza");
        btn_pizza.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pizza.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_pizza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pizzaActionPerformed(evt);
            }
        });
        pnl_background.add(btn_pizza);
        btn_pizza.setBounds(30, 400, 180, 50);

        btn_pasta.setBackground(new java.awt.Color(255, 255, 255));
        btn_pasta.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_pasta.setForeground(new java.awt.Color(102, 0, 102));
        btn_pasta.setText("Pasta");
        btn_pasta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pasta.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_pasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pastaActionPerformed(evt);
            }
        });
        pnl_background.add(btn_pasta);
        btn_pasta.setBounds(30, 220, 180, 50);

        btn_showBill.setBackground(new java.awt.Color(255, 255, 255));
        btn_showBill.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_showBill.setForeground(new java.awt.Color(102, 0, 102));
        btn_showBill.setText("Finish and show bill");
        btn_showBill.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_showBill.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_showBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_showBillActionPerformed(evt);
            }
        });
        pnl_background.add(btn_showBill);
        btn_showBill.setBounds(810, 680, 310, 50);

        btn_burgers.setBackground(new java.awt.Color(255, 255, 255));
        btn_burgers.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_burgers.setForeground(new java.awt.Color(102, 0, 102));
        btn_burgers.setText("Burgers");
        btn_burgers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_burgers.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_burgers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_burgersActionPerformed(evt);
            }
        });
        pnl_background.add(btn_burgers);
        btn_burgers.setBounds(30, 310, 180, 50);

        btn_drinks.setBackground(new java.awt.Color(255, 255, 255));
        btn_drinks.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_drinks.setForeground(new java.awt.Color(102, 0, 102));
        btn_drinks.setText("Drinks");
        btn_drinks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_drinks.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_drinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_drinksActionPerformed(evt);
            }
        });
        pnl_background.add(btn_drinks);
        btn_drinks.setBounds(30, 580, 180, 50);

        btn_stillOrdering.setBackground(new java.awt.Color(255, 255, 255));
        btn_stillOrdering.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_stillOrdering.setForeground(new java.awt.Color(102, 0, 102));
        btn_stillOrdering.setText("Still hungry?");
        btn_stillOrdering.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_stillOrdering.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_stillOrdering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stillOrderingActionPerformed(evt);
            }
        });
        pnl_background.add(btn_stillOrdering);
        btn_stillOrdering.setBounds(450, 680, 230, 50);

        cbx_promoCode.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        pnl_background.add(cbx_promoCode);
        cbx_promoCode.setBounds(630, 500, 430, 60);

        cbx_choose_order.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        cbx_choose_order.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnl_background.add(cbx_choose_order);
        cbx_choose_order.setBounds(620, 230, 430, 60);

        spin_quantity.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        spin_quantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        spin_quantity.setAutoscrolls(true);
        spin_quantity.setEditor(new javax.swing.JSpinner.NumberEditor(spin_quantity, ""));
        spin_quantity.setOpaque(false);
        pnl_background.add(spin_quantity);
        spin_quantity.setBounds(700, 380, 130, 50);

        btn_appetizers1.setBackground(new java.awt.Color(255, 255, 255));
        btn_appetizers1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_appetizers1.setForeground(new java.awt.Color(102, 0, 102));
        btn_appetizers1.setText("Appetizers");
        btn_appetizers1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_appetizers1.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_appetizers1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_appetizers1ActionPerformed(evt);
            }
        });
        pnl_background.add(btn_appetizers1);
        btn_appetizers1.setBounds(30, 130, 180, 50);

        btn_desserts1.setBackground(new java.awt.Color(255, 255, 255));
        btn_desserts1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_desserts1.setForeground(new java.awt.Color(102, 0, 102));
        btn_desserts1.setText("Desserts");
        btn_desserts1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_desserts1.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_desserts1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_desserts1ActionPerformed(evt);
            }
        });
        pnl_background.add(btn_desserts1);
        btn_desserts1.setBounds(30, 490, 180, 50);

        btn_offers.setBackground(new java.awt.Color(255, 255, 255));
        btn_offers.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btn_offers.setForeground(new java.awt.Color(102, 0, 102));
        btn_offers.setText("Hot Offers");
        btn_offers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_offers.setPreferredSize(new java.awt.Dimension(180, 50));
        btn_offers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_offersActionPerformed(evt);
            }
        });
        pnl_background.add(btn_offers);
        btn_offers.setBounds(30, 670, 180, 50);

        lbl_promoCode.setBackground(new java.awt.Color(255, 213, 0));
        lbl_promoCode.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        lbl_promoCode.setForeground(new java.awt.Color(255, 213, 0));
        lbl_promoCode.setText("Promo Code:");
        pnl_background.add(lbl_promoCode);
        lbl_promoCode.setBounds(330, 500, 230, 50);

        lbl_OrderType1.setBackground(new java.awt.Color(255, 213, 0));
        lbl_OrderType1.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        lbl_OrderType1.setForeground(new java.awt.Color(255, 213, 0));
        lbl_OrderType1.setText("Choose Item:");
        pnl_background.add(lbl_OrderType1);
        lbl_OrderType1.setBounds(330, 240, 230, 50);

        lbl_quantity.setBackground(new java.awt.Color(255, 213, 0));
        lbl_quantity.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 213, 0));
        lbl_quantity.setText("Quantity:");
        pnl_background.add(lbl_quantity);
        lbl_quantity.setBounds(330, 370, 230, 50);

        lbl_order_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customerPackage/OrderForm bg.jpg"))); // NOI18N
        pnl_background.add(lbl_order_background);
        lbl_order_background.setBounds(0, 70, 1217, 720);

        pHeader.setBackground(new java.awt.Color(255, 213, 0));
        pHeader.setName("pHeader"); // NOI18N
        pHeader.setPreferredSize(new java.awt.Dimension(1062, 70));
        pHeader.setLayout(null);

        btnLogout.setBackground(new java.awt.Color(255, 213, 0));
        btnLogout.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(127, 72, 101));
        btnLogout.setText("Log out");
        btnLogout.setToolTipText("");
        btnLogout.setBorder(null);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setName(""); // NOI18N
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        pHeader.add(btnLogout);
        btnLogout.setBounds(1080, 19, 101, 40);

        lbLogo.setBackground(new java.awt.Color(255, 255, 255));
        lbLogo.setFont(new java.awt.Font("Rockwell Condensed", 0, 52)); // NOI18N
        lbLogo.setForeground(new java.awt.Color(127, 72, 101));
        lbLogo.setText("GUSTO");
        lbLogo.setToolTipText("");
        pHeader.add(lbLogo);
        lbLogo.setBounds(30, 10, 120, 62);

        btnOrdersCustomer.setBackground(new java.awt.Color(255, 213, 0));
        btnOrdersCustomer.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btnOrdersCustomer.setForeground(new java.awt.Color(127, 72, 101));
        btnOrdersCustomer.setText("My Orders");
        btnOrdersCustomer.setBorder(null);
        btnOrdersCustomer.setBorderPainted(false);
        btnOrdersCustomer.setContentAreaFilled(false);
        btnOrdersCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOrdersCustomer.setPreferredSize(new java.awt.Dimension(87, 29));
        btnOrdersCustomer.setRequestFocusEnabled(false);
        btnOrdersCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdersCustomerActionPerformed(evt);
            }
        });
        pHeader.add(btnOrdersCustomer);
        btnOrdersCustomer.setBounds(901, 26, 184, 29);

        btnMakeOrderCustomer.setBackground(new java.awt.Color(255, 213, 0));
        btnMakeOrderCustomer.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btnMakeOrderCustomer.setForeground(new java.awt.Color(127, 72, 101));
        btnMakeOrderCustomer.setText("Make Order");
        btnMakeOrderCustomer.setBorder(null);
        btnMakeOrderCustomer.setBorderPainted(false);
        btnMakeOrderCustomer.setContentAreaFilled(false);
        btnMakeOrderCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMakeOrderCustomer.setPreferredSize(new java.awt.Dimension(87, 29));
        btnMakeOrderCustomer.setRequestFocusEnabled(false);
        btnMakeOrderCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakeOrderCustomerActionPerformed(evt);
            }
        });
        pHeader.add(btnMakeOrderCustomer);
        btnMakeOrderCustomer.setBounds(750, 20, 170, 40);

        btnMenuCustomer.setBackground(new java.awt.Color(255, 213, 0));
        btnMenuCustomer.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btnMenuCustomer.setForeground(new java.awt.Color(127, 72, 101));
        btnMenuCustomer.setText("Main Menu");
        btnMenuCustomer.setBorder(null);
        btnMenuCustomer.setBorderPainted(false);
        btnMenuCustomer.setContentAreaFilled(false);
        btnMenuCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuCustomer.setPreferredSize(new java.awt.Dimension(87, 29));
        btnMenuCustomer.setRequestFocusEnabled(false);
        btnMenuCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuCustomerActionPerformed(evt);
            }
        });
        pHeader.add(btnMenuCustomer);
        btnMenuCustomer.setBounds(430, 20, 153, 40);

        btnBestSellersCustomer.setBackground(new java.awt.Color(255, 213, 0));
        btnBestSellersCustomer.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btnBestSellersCustomer.setForeground(new java.awt.Color(127, 72, 101));
        btnBestSellersCustomer.setText("Best Selling");
        btnBestSellersCustomer.setBorder(null);
        btnBestSellersCustomer.setBorderPainted(false);
        btnBestSellersCustomer.setContentAreaFilled(false);
        btnBestSellersCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBestSellersCustomer.setPreferredSize(new java.awt.Dimension(87, 29));
        btnBestSellersCustomer.setRequestFocusEnabled(false);
        btnBestSellersCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBestSellersCustomerActionPerformed(evt);
            }
        });
        pHeader.add(btnBestSellersCustomer);
        btnBestSellersCustomer.setBounds(590, 20, 153, 40);

        pnl_background.add(pHeader);
        pHeader.setBounds(0, 0, 1220, 80);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_background, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pizzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pizzaActionPerformed
        // TODO add your handling code here:
        category = btn_pizza.getText();
        fillComboBox("Pizza");
    }//GEN-LAST:event_btn_pizzaActionPerformed

    private void btn_pastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pastaActionPerformed
        // TODO add your handling code here:
        category = btn_pasta.getText();
        fillComboBox("Pasta");
    }//GEN-LAST:event_btn_pastaActionPerformed

    private void btn_showBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_showBillActionPerformed
        // TODO add your handling code here:
        if (!notFirstVisit)
        {
            notFirstVisit=true;
            try
            {
                //notFirstVisit = true;
                if (cbx_choose_order.getSelectedItem() == null || cbx_choose_order.getSelectedIndex() == -1)
                {
                    //notFirstVisit = false;
                    throw new Exception("Please select the desired Food item");
                }
                //if the customer entered a promo code
                if (cbx_promoCode.getSelectedIndex() != -1)
                {
                    String voucherId = (String) cbx_promoCode.getSelectedItem();
                    //if he chose the welcome promo code check if it is really his first visit
                    if (voucherId.equals("WELCOME") && !customer.getOrders().isEmpty())
                    {
                        //it is not his first visit
                        JOptionPane.showMessageDialog(this, "This promo code is not valid", "Unvalid voucher", JOptionPane.WARNING_MESSAGE);

                    }
                    else if (!voucherId.equals("WELCOME") && customer.getOrders().isEmpty())
                    {
                        //if it is his first visit allow him to choose only one voucher
                        int yesNoOption = JOptionPane.showConfirmDialog(this, "Welcome, it is your first visit here. Would you like to take our 20% discount offer for welcoming you?");
                        //take welcome promo code
                        if (yesNoOption == 0)
                        {
                            voucherId = "WELCOME";
                            int precentage = voucher.getPercentage(voucherId);
                            voucher.setId(voucherId);
                            voucher.setPercentage(precentage);
                            voucher.setReleaseDate(Voucher.voucherList.get(voucherId).getReleaseDate());
                            voucher.setExpirationDate(Voucher.voucherList.get(voucherId).getExpirationDate());
                            //System.out.println(precentage);

                        }
                        //proceed with the chosen promo code
                        else
                        {
                            offer(voucherId);
                        }
                        currentOrder.setVoucherFound(true);
                    }
                    else
                    {
                        offer(voucherId);
                        currentOrder.setVoucherFound(true);
                    }
                }
                quantity = (Integer) spin_quantity.getValue();

                currentOrder.orderList.put((String) cbx_choose_order.getSelectedItem(), quantity);

                orderStartMillis = System.currentTimeMillis();
                currentOrder.setStartTimeInMillis(orderStartMillis);
                //Start time in milli seconds converted to date Format
                Date orderStart = new Date(orderStartMillis);

                //currentOrder.setOrderStartTime(orderStart);
                currentOrder.setCanelTimeInMillis(orderStartMillis + (0 * ONE_MINUTE_IN_MILLIS));
                long dTimeMillis=orderStartMillis + (ONE_MINUTE_IN_MILLIS * 1);
                Date estimatedDeliveryTime = new Date(dTimeMillis);

                currentOrder.setDeliveryTimeInMillis(dTimeMillis);
                //currentOrder.setDeliveryTime(estimatedDeliveryTime);

                customer.placeOrders(currentOrder);
                deliveryBoy.setOrder(currentOrder);
                boolean flag = currentOrder.assignOrder();
                if (flag)
                {
                    JOptionPane.showMessageDialog(this, "Your 20 minutes preparing started to count", "Ticking..", JOptionPane.INFORMATION_MESSAGE);

                    setInitialValues();
                    bill = new BillForm(currentOrder.orderList, voucher, menuIds);
                    bill.setVisible(true);
                }

            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "You didn't recieve your last order yet.\nPlease check the status");
        }
    }//GEN-LAST:event_btn_showBillActionPerformed

    private void btn_burgersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_burgersActionPerformed
        // TODO add your handling code here:

        category = btn_burgers.getText();
        fillComboBox("Burger");

    }//GEN-LAST:event_btn_burgersActionPerformed

    private void btn_drinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_drinksActionPerformed
        // TODO add your handling code here:
        category = btn_drinks.getText();
        fillComboBox("Drinks");

    }//GEN-LAST:event_btn_drinksActionPerformed

    private void btn_stillOrderingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stillOrderingActionPerformed
        // TODO add your handling code here:
        if (!notFirstVisit)
        {
            try
            {
                if (cbx_choose_order.getSelectedItem() == null || cbx_choose_order.getSelectedIndex() == -1)
                {
                    throw new Exception("Please select the desired Food item");
                }
                else if (cbx_choose_order.getSelectedItem() != null || cbx_choose_order.getSelectedIndex() != -1)
                {
                    quantity = (Integer) spin_quantity.getValue();
                    currentOrder.orderList.put((String) cbx_choose_order.getSelectedItem(), quantity);

                    setInitialValues();
                }
            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "You have already ordered");
        }
    }//GEN-LAST:event_btn_stillOrderingActionPerformed

    private void btn_appetizers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_appetizers1ActionPerformed
        // TODO add your handling code here:
        category = btn_appetizers1.getText();
        fillComboBox("Appetizers");
    }//GEN-LAST:event_btn_appetizers1ActionPerformed

    private void btn_desserts1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_desserts1ActionPerformed
        // TODO add your handling code here:
        category = btn_desserts1.getText();
        fillComboBox("Desserts");
    }//GEN-LAST:event_btn_desserts1ActionPerformed

    private void btn_offersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_offersActionPerformed
        // TODO add your handling code here:
        cbx_choose_order.setModel(new DefaultComboBoxModel<>(new String[]
        {
            ""
        }));
        for (int i = 0; i < HomepageForm.offers.size(); ++i)
        {
            cbx_choose_order.addItem(HomepageForm.offers.get(i).getMessage());
            orderPrice.put(HomepageForm.offers.get(i).getMessage(), HomepageForm.offers.get(i).getPrice());
        }

    }//GEN-LAST:event_btn_offersActionPerformed

    private void btn_orderStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_orderStatusActionPerformed

        
        System.out.println(currentOrder.getId()+" "+customer.getTempOrder().getId()+" "+customer.getTempOrder().getStatus()+" in checkStatus btn order Form");
        if(customer.getOrders().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Not vaid yet it is still your first order", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
        if(/*currentOrder.getStatus()*/customer.getTempOrder().getStatus()) //or currentOrder.getStatus()
        {
            JOptionPane.showMessageDialog(this, "All Your orders have been delivered");
            notFirstVisit=false;
        }
        else
        {
            if(customer.getTempOrder().checkStatus(customer.getTempOrder())/*customer.getTempOrder().getStartTimeInMillis()+(ONE_MINUTE_IN_MILLIS*1)<=now*/)
            {
                JOptionPane.showMessageDialog(this, "Your order has been delivered");
                notFirstVisit=false;
                //customer.getTempOrder().setStatus(true);
                customer.getTempOrder().updateOrder(customer.getTempOrder());
                currentOrder = new Order(String.valueOf(Order.getNoOrders() + 1), String.valueOf(customer.getId()), customer.getName(), customer.getAddress(), customer.getMobilePhone());
                deliveryBoy.getOrdersList().remove(OrderForm.currentOrder);
                deliveryBoy.setAvailable(true);
                currentOrder.updateDeliveryBoys(deliveryBoy);
            }
            else
            {
               JOptionPane.showMessageDialog(this, "Still being cooked");
               notFirstVisit=true;
            }
        }
        }
        
    }//GEN-LAST:event_btn_orderStatusActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        HomepageForm homepage = new HomepageForm(true);
        homepage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnOrdersCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdersCustomerActionPerformed
        ShowOrders s = new ShowOrders(customer, menuIds);
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnOrdersCustomerActionPerformed

    private void btnMakeOrderCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakeOrderCustomerActionPerformed
        // TODO add your handling code here:
        OrderForm o = new OrderForm(customer, menuIds);
        o.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMakeOrderCustomerActionPerformed

    private void btnMenuCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuCustomerActionPerformed
        Menu m = new Menu();
        m.setVisible(true);
    }//GEN-LAST:event_btnMenuCustomerActionPerformed

    private void btnBestSellersCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBestSellersCustomerActionPerformed
        BestSellerForm b= new BestSellerForm(customer, menuIds);
        b.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBestSellersCustomerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new OrderForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBestSellersCustomer;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMakeOrderCustomer;
    private javax.swing.JButton btnMenuCustomer;
    private javax.swing.JButton btnOrdersCustomer;
    private javax.swing.JButton btn_appetizers1;
    private javax.swing.JButton btn_burgers;
    private javax.swing.JButton btn_desserts1;
    private javax.swing.JButton btn_drinks;
    private javax.swing.JButton btn_offers;
    private javax.swing.JButton btn_orderStatus;
    private javax.swing.JButton btn_pasta;
    private javax.swing.JButton btn_pizza;
    private javax.swing.JButton btn_showBill;
    private javax.swing.JButton btn_stillOrdering;
    private javax.swing.JComboBox<String> cbx_choose_order;
    private javax.swing.JComboBox<String> cbx_promoCode;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbl_OrderType1;
    private javax.swing.JLabel lbl_order_background;
    private javax.swing.JLabel lbl_promoCode;
    private javax.swing.JLabel lbl_quantity;
    public static javax.swing.JPanel pHeader;
    private javax.swing.JPanel pnl_background;
    private javax.swing.JSpinner spin_quantity;
    // End of variables declaration//GEN-END:variables
}
