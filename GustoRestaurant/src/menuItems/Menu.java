package menuItems;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import menuItems.MainMenu;
import static starting.HomepageForm.offers;

public class Menu extends javax.swing.JFrame
{

    String itemInfo;
    String itemName;
    String itemPrice;
    String itemRate;
    String Dots = "";

    static JPanel pnl_tmp; // Panel as a Tab 
    static JLabel lbl_tmp; // label for background 
    static JScrollPane JscrlP_tmp;// ScrollPane for TextArea
    static JTextArea txtA_tmp; // TextArea to print text in
    static JLabel lbl_pic1; // lable for menu item image 
    static JLabel lbl_pic2; // ,,  ,,   ,,   ,,   ,, 
    static JLabel lbl_pic3; // ,,  ,,   ,,   ,,   ,, 
    static JLabel lbl_pic4; // ,,  ,,   ,,   ,,   ,, 

    void createPanel()
    {
        //Define new tab to hold  this tittle & add it to JTabbedPane
        pnl_tmp = new JPanel();
        pnl_tmp.setLayout(null);

        // setting label for background
        lbl_tmp = new JLabel();
        lbl_tmp.setBackground(new java.awt.Color(255, 255, 255));
        lbl_tmp.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tmp.setBounds(0, 0, 1216, 788); // ************************************************* to be changed
        lbl_tmp.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/MainMenubackground2.png")));
        pnl_tmp.add(lbl_tmp);

        // Define a JScrollPane to hold JTextarea
        JscrlP_tmp = new JScrollPane();
        JscrlP_tmp.setOpaque(false);
        JscrlP_tmp.getViewport().setOpaque(false);
        JscrlP_tmp.setBounds(0, 0, 1210, 780);// ************************************************* to be changed

        // Define the JTextarea to hold my text (list of menu items in this category )
        txtA_tmp = new JTextArea(10, 2);
        txtA_tmp.setEditable(false);
        txtA_tmp.setBackground(new Color(0, 0, 0, 0));
        txtA_tmp.setForeground(new java.awt.Color(255, 255, 255));
        txtA_tmp.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // ************************************************* to be changed // Rockwell Condensed
        txtA_tmp.setOpaque(false);

        //to make JscrlP_tmp transparent
        JscrlP_tmp.setViewportView(txtA_tmp);
        pnl_tmp.add(JscrlP_tmp);

        // Setting Menu Items Images as icons of labels (only three Items)
        lbl_pic1 = new JLabel();
        pnl_tmp.add(lbl_pic1);
        lbl_pic1.setBounds(jLabel1.getBounds());

        lbl_pic2 = new JLabel();
        pnl_tmp.add(lbl_pic2);
        lbl_pic2.setBounds(jLabel2.getBounds());

        lbl_pic3 = new JLabel();
        pnl_tmp.add(lbl_pic3);
        lbl_pic3.setBounds(jLabel3.getBounds());

        lbl_pic4 = new JLabel();
        pnl_tmp.add(lbl_pic4);
        lbl_pic4.setBounds(jLabel7.getBounds());

        //set priorty of components in the panel
        pnl_tmp.setComponentZOrder(lbl_pic1, 0);
        pnl_tmp.setComponentZOrder(lbl_pic2, 1);
        pnl_tmp.setComponentZOrder(lbl_pic3, 2);
        pnl_tmp.setComponentZOrder(lbl_pic4, 3);
        pnl_tmp.setComponentZOrder(JscrlP_tmp, 4);
    }

    void SetPic(String TabTittle)
    {
        if ("Drinks".equals(TabTittle))
        {
            lbl_pic1.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Drinks1.png")));
            lbl_pic2.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Drinks2.png")));
            lbl_pic3.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Drinks3.png")));
            lbl_pic4.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Drinks4.png")));

        }
        else if ("Appetizers".equals(TabTittle))
        {
            lbl_pic1.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/App1.png")));
            lbl_pic2.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/App2.png")));
            lbl_pic3.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/App3.png")));
            lbl_pic4.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Burger5.png")));

        }

        else if ("Burger".equals(TabTittle))
        {
            lbl_pic1.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Burger2.png")));
            lbl_pic2.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Burger3.png")));
            lbl_pic3.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Burger6.png")));
            lbl_pic4.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Burger4.png")));

        }
        else if ("Desserts".equals(TabTittle))
        {
            lbl_pic1.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Dess1.png")));
            lbl_pic2.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Dess2.png")));
            lbl_pic3.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Dess3.png")));
            lbl_pic4.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Dess5.png")));

        }
        else if ("Pizza".equals(TabTittle))
        {
            lbl_pic1.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Pizza1.png")));
            lbl_pic2.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Pizza2.png")));
            lbl_pic3.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Pizza3.png")));
            lbl_pic4.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Pizza4.png")));

        }
        else if ("Pasta".equals(TabTittle))
        {
            lbl_pic1.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Pasta1.png")));
            lbl_pic2.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Pasta2.png")));
            lbl_pic3.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Pasta3.png")));
            lbl_pic4.setIcon(new ImageIcon(getClass().getResource("/restaurant/Pic/Pasta4.png")));

        }
      

    }

    void showMainMenu()
    {

        //remove the initiate tabs
        tpnl_ViewMainMenu.remove(0);

        String header = "   Dish  ...................................................................  Price";

        MainMenu.categoryList.keySet().forEach((String Category) ->
        {

            createPanel();
            tpnl_ViewMainMenu.addTab(Category, pnl_tmp);
            txtA_tmp.append("\n" + header + "\n\n");

            // filling this category tab with it's category Items
            for (int i = 0; i < MainMenu.categoryList.get(Category).size(); ++i)
            {
                //filling textarea with required text ( Menu Items : Name + Price + Rate)
                itemName = "  " + MainMenu.categoryList.get(Category).get(i).getItemName() + "  ";
                itemPrice = "  " + String.valueOf(MainMenu.categoryList.get(Category).get(i).getItemPrice());
                itemRate = "   Rate : " + String.valueOf(MainMenu.categoryList.get(Category).get(i).getItemRate()) + "/5 \n";
                for (int j = itemName.length(); j <= (header.length() - itemPrice.length()); ++j)
                {
                    Dots += '.';
                }
                itemInfo = itemName + Dots + itemPrice;

                txtA_tmp.append(itemInfo);
                txtA_tmp.append(itemRate);

                //Setting   images
                SetPic(Category);
                Dots = "";

            }
        });
        //adding offers tab 

        if (!offers.isEmpty())
        {
            createPanel();
            tpnl_ViewMainMenu.addTab(" Our Special Offers ", pnl_tmp);

            

           

            for (int i = 0; i < offers.size(); ++i)
            {
                txtA_tmp.append("\n  " + offers.get(i).getMessage() + "  ->   ");
                txtA_tmp.append("Price : "+ offers.get(i).getPrice()+ "\n");

                for (int j = 0; j < offers.get(i).getMenuItems().size(); ++j)
                {
                    txtA_tmp.append(offers.get(i).getMenuItems().get(j).getItemName() +" \n " ); 
                }
                txtA_tmp.append("   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");

            }

        }

    }

    public Menu()
    {

        initComponents();
        showMainMenu();
        this.setLocationRelativeTo(null); //center form in screen
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        pnl_LogoBar = new javax.swing.JPanel();
        lbl_RsturantName = new javax.swing.JLabel();
        tpnl_ViewMainMenu = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Main Menu");
        setMaximumSize(new java.awt.Dimension(1216, 788));
        setPreferredSize(new java.awt.Dimension(1217, 788));
        setSize(new java.awt.Dimension(1217, 788));

        pnl_LogoBar.setBackground(new java.awt.Color(255, 213, 0));

        lbl_RsturantName.setFont(new java.awt.Font("Rockwell Condensed", 0, 48)); // NOI18N
        lbl_RsturantName.setForeground(new java.awt.Color(102, 0, 102));
        lbl_RsturantName.setText("GUSTO");

        javax.swing.GroupLayout pnl_LogoBarLayout = new javax.swing.GroupLayout(pnl_LogoBar);
        pnl_LogoBar.setLayout(pnl_LogoBarLayout);
        pnl_LogoBarLayout.setHorizontalGroup(
            pnl_LogoBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_LogoBarLayout.createSequentialGroup()
                .addGap(530, 530, 530)
                .addComponent(lbl_RsturantName)
                .addContainerGap(588, Short.MAX_VALUE))
        );
        pnl_LogoBarLayout.setVerticalGroup(
            pnl_LogoBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_LogoBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_RsturantName)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tpnl_ViewMainMenu.setBackground(new java.awt.Color(255, 213, 0));
        tpnl_ViewMainMenu.setForeground(new java.awt.Color(102, 0, 102));
        tpnl_ViewMainMenu.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tpnl_ViewMainMenu.setToolTipText("");
        tpnl_ViewMainMenu.setAutoscrolls(true);
        tpnl_ViewMainMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tpnl_ViewMainMenu.setFont(new java.awt.Font("Rockwell", 3, 24)); // NOI18N
        tpnl_ViewMainMenu.setName(""); // NOI18N
        tpnl_ViewMainMenu.setPreferredSize(new java.awt.Dimension(1216, 70));

        jLabel1.setBackground(new java.awt.Color(0, 204, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(230, 230));

        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(230, 230));

        jLabel3.setFont(new java.awt.Font("Vladimir Script", 0, 11)); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setPreferredSize(new java.awt.Dimension(230, 230));

        jLabel7.setText("jLabel7");
        jLabel7.setPreferredSize(new java.awt.Dimension(230, 230));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        tpnl_ViewMainMenu.addTab("tab1", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_LogoBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tpnl_ViewMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl_LogoBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpnl_ViewMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tpnl_ViewMainMenu.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Menu().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_RsturantName;
    private javax.swing.JPanel pnl_LogoBar;
    public static javax.swing.JTabbedPane tpnl_ViewMainMenu;
    // End of variables declaration//GEN-END:variables
}
