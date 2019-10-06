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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Voucher
{

    private String id;
    private int percentage; //discount percentage amount
    private LocalDate releaseDate;
    private LocalDate expirationDate;
    static HashMap<String, Voucher> voucherList = new HashMap<>();

    public Voucher(String id, int percentage)
    {
        this.id = id;
        this.percentage = percentage;
    }

    public Voucher()
    {
    }

    //Getters
    public String getId()
    {
        return id;
    }

    public int getPercentage()
    {
        return percentage;
    }

    public LocalDate getReleaseDate()
    {
        return releaseDate;
    }

    public LocalDate getExpirationDate()
    {
        return expirationDate;
    }

    // Setters
    public void setId(String id)
    {
        this.id = id;
    }

    public void setPercentage(int percentage)
    {
        this.percentage = percentage;
    }

    public void setReleaseDate(LocalDate releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public void setExpirationDate(LocalDate expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public int getPercentage(String Id)
    {
        // loop on Voucher map 
        if (Voucher.voucherList.containsKey(Id))
        {
            // check the date range
            if ((Voucher.voucherList.get(Id).releaseDate.isBefore(LocalDate.now()) || Voucher.voucherList.get(Id).releaseDate.equals(LocalDate.now())
                    && Voucher.voucherList.get(Id).expirationDate.isAfter(LocalDate.now())))
            {

                System.out.println(" Hellooo from the other side ");
                System.out.println(Voucher.voucherList.get(Id).percentage);

                return Voucher.voucherList.get(Id).percentage;
            }

        }
        // if  Voucher Expiered
        System.out.println(" NOT Hellooo from the other side ");

        return 0;

    }

    public void setVouchers()
    {
        Voucher voucher;
        try
        {

            PreparedStatement preparedStmt = MyConnection.getConnection().prepareStatement("select * from menu_item.vouchers");
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next())
            {

                voucher = new Voucher();
                voucher.setId(rs.getString("vouchers_id"));
                voucher.setPercentage(rs.getInt("vouchers_percentage"));
                voucher.setReleaseDate((LocalDate.parse(rs.getString("vouchers_release_date"))));
                voucher.setExpirationDate((LocalDate.parse(rs.getString("vouchers_exp_date"))));

                voucherList.put(voucher.getId(), voucher);

            }
        } catch (SQLException ex)
        {
            Logger.getLogger(java.awt.Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
