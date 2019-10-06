package customerPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import customerPackage.OrderForm.*;
import customerPackage.OrderForm;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.util.Pair;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import menuItems.Inventory;

/**
 *
 * @author Dell
 */
public class BillForm extends javax.swing.JFrame
{
//Order order = new Order();

    Bill bill;
    String ordersS;
    int totalCash;
    int rate;
    static boolean returned = false;
    Voucher exceed1000 = new Voucher("Exceed1000", 20);
    boolean rated=false;
    boolean pressedStatusBtn=false;
    static boolean delivered=false;
    /**
     * Creates new form BillForm
     */
    public BillForm()
    {
        initComponents();
        pnl_rate.setVisible(false);
        pnl_bill.setVisible(true);
        lbl_complain.setVisible(false);
        txt_complainMessege.setVisible(false);
        btn_closeRating.setVisible(false);
    }

    public BillForm(HashMap<String, Integer> tempOrderList, Voucher voucher, String s)
    {
        initComponents();
        cbx_ChooseItemToRate.setModel(new DefaultComboBoxModel<>(new String[]
        {
            ""
        }));
        totalCash = 0;
        ordersS=s;
        System.out.println(ordersS);
        pnl_bill.setVisible(true);
        pnl_rate.setVisible(false);
        //Compalin part
        lbl_complain.setVisible(false);
        txt_complainMessege.setVisible(false);
        btn_closeRating.setVisible(false);

        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm aa");
        Date currentDate = new Date();

        txtArea_bill.setText("\n                  ******GUSTO******             \n\n");
        txtArea_bill.append("Date: " + dateFormat.format(currentDate) + "\n\n");

        txtArea_bill.append("PRICE     QTY        TOTAL\n");
        txtArea_bill.append("__________________________________________\n");
        tempOrderList.keySet().forEach((String foodItem) ->
        {
            cbx_ChooseItemToRate.addItem(foodItem);
            totalCash += OrderForm.orderPrice.get(foodItem) * tempOrderList.get(foodItem);
            txtArea_bill.append(foodItem + "\n");
            txtArea_bill.append("EGP" + OrderForm.orderPrice.get(foodItem) + "     " + tempOrderList.get(foodItem) + "           EGP" + OrderForm.orderPrice.get(foodItem) * tempOrderList.get(foodItem) + "\n\n");

        });

        txtArea_bill.append("Total Cash: " + totalCash + "\n");
        bill = new Bill(totalCash, dateFormat.format(currentDate), "5");
        txtArea_bill.append("Delivery Charges: " + bill.getDelieveryCharges() + "\n");
        txtArea_bill.append("Total with Delivery: " + (bill.getDelieveryCharges() + totalCash) + "\n");

        //If the customer didn't choose a promo code and his bill xceed 1000 -> he takes the offer
        if (totalCash > 1000 && !OrderForm.currentOrder.isVoucherFound())
        {
            totalCash = bill.getDiscount(exceed1000.getPercentage());
            displayVoucher(exceed1000, totalCash);
        }

        //If the customer choose a promo code
        if (OrderForm.currentOrder.isVoucherFound())
        {
            //if the voucher he chose was Exceed1000 and his bill actually exceeds 1000 -> he gets the voucher
            if (totalCash > 1000 && voucher.getId().equals("Exceed1000"))
            {
                totalCash = bill.getDiscount(voucher.getPercentage());
                displayVoucher(voucher, totalCash);
            }
            //The voucher he chose was Exceed1000 and his bill doesn't exceed 1000 -> he doesn't get the voucher
            else if (totalCash < 1000 && voucher.getId().equals("Exceed1000"))
            {
                JOptionPane.showMessageDialog(this, "You can't proceed with this voucher as your bill is below 1000", "Error", JOptionPane.ERROR_MESSAGE);
                txtArea_bill.append("Total Cash after discount: " + totalCash + "\n");
            }
            //His bill exceeds 1000 but he didn't choose the Exceed1000 voucher -> he chooses which voucher he wants to proceed with
            else if (totalCash > 1000 && !voucher.getId().equals("Exceed1000"))
            {
                int choice = JOptionPane.showConfirmDialog(this, "Your bill has exceeded 1000.Would you like to take the Exceed1000 voucher?");
                //Yes choice -> gets the Exceed1000 voucher
                if (choice == 0)
                {
                    totalCash = bill.getDiscount(exceed1000.getPercentage());
                    displayVoucher(voucher, totalCash);
                }
                //No -> gets he desired voucher
                else
                {
                    totalCash = bill.getDiscount(voucher.getPercentage());
                    displayVoucher(voucher, totalCash);
                }
            }
            //his bill is below 1000 and he didn't choose the Exceed1000 voucher -> proceeds with the chosen voucher 
            else
            {
                totalCash = bill.getDiscount(voucher.getPercentage());
                displayVoucher(voucher, totalCash);
            }
        }

        txtArea_bill.append("\n            Thank you for visiting Gusto\n");
        txtArea_bill.append("\n*******************************************\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_bill = new javax.swing.JPanel();
        pnl_rate = new javax.swing.JPanel();
        cbx_ChooseItemToRate = new javax.swing.JComboBox<>();
        lbl_OrderType = new javax.swing.JLabel();
        lbl_rate = new javax.swing.JLabel();
        btn_closeRating = new javax.swing.JButton();
        lbl_complain = new javax.swing.JLabel();
        txt_complainMessege = new javax.swing.JTextField();
        txt_getRate = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btn_finishRate = new javax.swing.JButton();
        btn_nextRate = new javax.swing.JButton();
        btn_orderStatus = new javax.swing.JButton();
        btn_backFromBill = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea_bill = new javax.swing.JTextArea();
        btn_rate = new javax.swing.JButton();
        btn_cancelOrder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(750, 100));
        setMinimumSize(new java.awt.Dimension(400, 725));
        setUndecorated(true);
        setSize(new java.awt.Dimension(400, 788));

        pnl_bill.setBackground(new java.awt.Color(255, 255, 255));
        pnl_bill.setLayout(null);

        pnl_rate.setBackground(new java.awt.Color(255, 255, 255));
        pnl_rate.setName(""); // NOI18N
        pnl_rate.setPreferredSize(new java.awt.Dimension(400, 788));

        cbx_ChooseItemToRate.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        cbx_ChooseItemToRate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_OrderType.setBackground(new java.awt.Color(255, 213, 0));
        lbl_OrderType.setFont(new java.awt.Font("Rockwell", 1, 28)); // NOI18N
        lbl_OrderType.setForeground(new java.awt.Color(102, 0, 102));
        lbl_OrderType.setText("Choose Item:");

        lbl_rate.setBackground(new java.awt.Color(255, 213, 0));
        lbl_rate.setFont(new java.awt.Font("Rockwell", 1, 28)); // NOI18N
        lbl_rate.setForeground(new java.awt.Color(102, 0, 102));
        lbl_rate.setText("Rating:");

        btn_closeRating.setBackground(new java.awt.Color(255, 213, 0));
        btn_closeRating.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btn_closeRating.setText("Done");
        btn_closeRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeRatingActionPerformed(evt);
            }
        });

        lbl_complain.setBackground(new java.awt.Color(255, 255, 255));
        lbl_complain.setFont(new java.awt.Font("Rockwell", 1, 28)); // NOI18N
        lbl_complain.setForeground(new java.awt.Color(102, 0, 102));
        lbl_complain.setText("Is there any complains?");
        lbl_complain.setPreferredSize(new java.awt.Dimension(93, 37));

        txt_complainMessege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_complainMessegeActionPerformed(evt);
            }
        });

        txt_getRate.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N

        btn_finishRate.setBackground(new java.awt.Color(255, 213, 0));
        btn_finishRate.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btn_finishRate.setText("Finish");
        btn_finishRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_finishRateActionPerformed(evt);
            }
        });

        btn_nextRate.setBackground(new java.awt.Color(255, 213, 0));
        btn_nextRate.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btn_nextRate.setText("Next");
        btn_nextRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextRateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_rateLayout = new javax.swing.GroupLayout(pnl_rate);
        pnl_rate.setLayout(pnl_rateLayout);
        pnl_rateLayout.setHorizontalGroup(
            pnl_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_rateLayout.createSequentialGroup()
                .addGroup(pnl_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_rateLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btn_nextRate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_finishRate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_rateLayout.createSequentialGroup()
                        .addGroup(pnl_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnl_rateLayout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(txt_complainMessege, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnl_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_getRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                                .addGroup(pnl_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbx_ChooseItemToRate, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_OrderType, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_rateLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(lbl_complain, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnl_rateLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btn_closeRating, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_rateLayout.setVerticalGroup(
            pnl_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_rateLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lbl_OrderType, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbx_ChooseItemToRate, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txt_getRate, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pnl_rateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_finishRate, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_nextRate, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(lbl_complain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(txt_complainMessege, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btn_closeRating, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        pnl_bill.add(pnl_rate);
        pnl_rate.setBounds(0, -10, 400, 820);

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
        pnl_bill.add(btn_orderStatus);
        btn_orderStatus.setBounds(100, 740, 190, 50);

        btn_backFromBill.setBackground(new java.awt.Color(255, 213, 0));
        btn_backFromBill.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btn_backFromBill.setText("Close");
        btn_backFromBill.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_backFromBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backFromBillActionPerformed(evt);
            }
        });
        pnl_bill.add(btn_backFromBill);
        btn_backFromBill.setBounds(120, 660, 150, 50);

        txtArea_bill.setEditable(false);
        txtArea_bill.setColumns(20);
        txtArea_bill.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        txtArea_bill.setRows(5);
        txtArea_bill.setBorder(null);
        jScrollPane1.setViewportView(txtArea_bill);

        pnl_bill.add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 400, 570);

        btn_rate.setBackground(new java.awt.Color(255, 213, 0));
        btn_rate.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btn_rate.setText("Rate");
        btn_rate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rateActionPerformed(evt);
            }
        });
        pnl_bill.add(btn_rate);
        btn_rate.setBounds(10, 580, 146, 50);

        btn_cancelOrder.setBackground(new java.awt.Color(255, 213, 0));
        btn_cancelOrder.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btn_cancelOrder.setText("Cancel Order");
        btn_cancelOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cancelOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelOrderActionPerformed(evt);
            }
        });
        pnl_bill.add(btn_cancelOrder);
        btn_cancelOrder.setBounds(210, 580, 185, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_bill, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_bill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void displayVoucher(Voucher voucher, int totalCash)
    {
        txtArea_bill.append("Total Cash after discount: " + totalCash + "\n\n");
        txtArea_bill.append("Voucher ID: " + voucher.getId() + "\n");
        txtArea_bill.append("Voucher discount percentage: " + voucher.getPercentage() + "\n");
    }
    private void btn_backFromBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backFromBillActionPerformed
        Inventory inventory = new Inventory();
        inventory.rateItem(OrderForm.currentOrder.ratings);
        if (!returned)
        {
            OrderForm.currentOrder.orderList.keySet().forEach((foodItem) ->
            {

                // Subtract the sold quantity in this order from avaliableItems field in inventory
                int availiable = Inventory.inventoryList.get(foodItem).getAvaliableMenuItem();

                availiable -= OrderForm.currentOrder.orderList.get(foodItem);
                Inventory.inventoryList.get(foodItem).setAvaliableMenuItem(availiable);

                //Add the sold quantity in this order to the soldQuantity field in inventory
                int sold = Inventory.inventoryList.get(foodItem).getSoldMenuItem();

                sold += OrderForm.currentOrder.orderList.get(foodItem);
                Inventory.inventoryList.get(foodItem).setSoldMenuItem(sold);

            });

        }
        // If the customer rated any fooditem
        if (OrderForm.currentOrder.ratings.size() > 0)
        {
            //looping over ratings map to get every rated item and add on its number of ratings
            OrderForm.currentOrder.ratings.keySet().forEach((foodItem) ->
            {
                // Get the current number of ratings of this food item and add 1    
                int ratings = Inventory.inventoryList.get(foodItem).getNumberOfRates();
                ratings += 1;
            });
        }
        if(!rated)
        {
            OrderForm.currentOrder.writeNewOrder(ordersS);
        }
        if(!pressedStatusBtn)
        {
            OrderForm.currentOrder.updateDeliveryBoys(OrderForm.deliveryBoy);
        }
        //OrderForm.currentOrder.updateDeliveryBoys(OrderForm.deliveryBoy);
        this.setVisible(false);

    }//GEN-LAST:event_btn_backFromBillActionPerformed

    private void btn_rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rateActionPerformed
        // open rate panel
        if (OrderForm.currentOrder.getStatus())
        {
            pnl_rate.setVisible(true);
            btn_rate.setVisible(false);
            btn_backFromBill.setVisible(false);
            btn_orderStatus.setVisible(false);
            btn_cancelOrder.setVisible(false);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Order is not delivered yet", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_rateActionPerformed

    private void btn_closeRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeRatingActionPerformed

        //if a complain message was written, suggest returning order
        if (!txt_complainMessege.getText().equals(""))
        {
            OrderForm.currentOrder.setComplainMessage(txt_complainMessege.getText());
            //if answer is yes -> remove order from the customers list of orders
            //if answer is no/complain message was written the order is added in data base then close the dialouge and the panel
            int yesNoOption = JOptionPane.showConfirmDialog(this, "Would you like to return the order?");
            if (yesNoOption == 0)
            {
                OrderForm.customer.returnOrder(OrderForm.currentOrder);
                JOptionPane.showMessageDialog(this, "Your order has been returned successfully\nThank you for your visit", "Return Order", JOptionPane.INFORMATION_MESSAGE);
                returned = true;
            }
            else
            {
                OrderForm.currentOrder.writeNewOrder(ordersS);
            }
        }
        else
        {
            OrderForm.currentOrder.writeNewOrder(ordersS);
        }
        rated=true;
        pnl_rate.setVisible(false);
        btn_rate.setVisible(true);
            btn_backFromBill.setVisible(true);
            btn_orderStatus.setVisible(true);
            btn_cancelOrder.setVisible(true);

    }//GEN-LAST:event_btn_closeRatingActionPerformed

    private void txt_complainMessegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_complainMessegeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_complainMessegeActionPerformed

    private void btn_finishRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_finishRateActionPerformed
        // TODO add your handling code here:
        if (!txt_getRate.getText().equals(""))
        {
            OrderForm.currentOrder.ratings.put(String.valueOf(cbx_ChooseItemToRate.getSelectedItem()), Double.valueOf(txt_getRate.getText()));
            lbl_complain.setVisible(true);
            txt_complainMessege.setVisible(true);
            btn_closeRating.setVisible(true);
        }
        else if ("".equals(txt_getRate.getText()))
        {
            if (OrderForm.currentOrder.ratings.size() > 1)
            {
                lbl_complain.setVisible(true);
                txt_complainMessege.setVisible(true);
                btn_closeRating.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please rate at least one element", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btn_finishRateActionPerformed

    private void btn_nextRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextRateActionPerformed
        // TODO add your handling code here:
        
        if ("".equals(txt_getRate.getText()))
        {
            if (OrderForm.currentOrder.ratings.size() > 1)
            {
                lbl_complain.setVisible(true);
                txt_complainMessege.setVisible(true);
                btn_closeRating.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please rate at least one element", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            OrderForm.currentOrder.ratings.put(String.valueOf(cbx_ChooseItemToRate.getSelectedItem()), Double.valueOf(txt_getRate.getText()));
            txt_getRate.setText("");
        }
    }//GEN-LAST:event_btn_nextRateActionPerformed

    private void btn_cancelOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelOrderActionPerformed
        // cancel order
        OrderForm.customer.cancelOrder();
    }//GEN-LAST:event_btn_cancelOrderActionPerformed

    private void btn_orderStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_orderStatusActionPerformed
        // TODO add your handling code here:
        if (OrderForm.currentOrder.checkStatus(OrderForm.currentOrder))
        {
            System.out.println(OrderForm.deliveryBoy.getOrdersList().size()+" we are in the orderStatusBtn");
            OrderForm.deliveryBoy.getOrdersList().remove(OrderForm.currentOrder);
            System.out.println(OrderForm.deliveryBoy.getOrdersList().size()+" we are in the orderStatusBtn");
            
            OrderForm.deliveryBoy.setAvailable(true);
            //OrderForm.currentOrder.updateDeliveryBoys(OrderForm.deliveryBoy);
            delivered=true;
            JOptionPane.showMessageDialog(this, "Your order is done", "Served", JOptionPane.OK_OPTION);
        }
        
        else
        {
            JOptionPane.showMessageDialog(this, "Your order is being cooked", "Loading...", JOptionPane.OK_OPTION);
        }
        OrderForm.currentOrder.updateDeliveryBoys(OrderForm.deliveryBoy);
        pressedStatusBtn=true;
    }//GEN-LAST:event_btn_orderStatusActionPerformed

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
            java.util.logging.Logger.getLogger(BillForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(BillForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(BillForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(BillForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new BillForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_backFromBill;
    private javax.swing.JButton btn_cancelOrder;
    private javax.swing.JButton btn_closeRating;
    private javax.swing.JButton btn_finishRate;
    private javax.swing.JButton btn_nextRate;
    private javax.swing.JButton btn_orderStatus;
    private javax.swing.JButton btn_rate;
    private javax.swing.JComboBox<String> cbx_ChooseItemToRate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_OrderType;
    private javax.swing.JLabel lbl_complain;
    private javax.swing.JLabel lbl_rate;
    private javax.swing.JPanel pnl_bill;
    private javax.swing.JPanel pnl_rate;
    private javax.swing.JTextArea txtArea_bill;
    private javax.swing.JTextField txt_complainMessege;
    private javax.swing.JTextField txt_getRate;
    // End of variables declaration//GEN-END:variables
}
