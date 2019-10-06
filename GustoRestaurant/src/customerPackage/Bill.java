package customerPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dell
 */
public class Bill
{

    private String Id, date;
    private double totalCash;
    private int totalCashAfter;
    private final int delieveryCharges = 15;
    Order customerOrder;

    //Voucher orderVoucher;
    public Bill(double totalCash, String date, String Id)
    {
        this.totalCash = totalCash;
        this.date = date;
        this.Id = Id;
    }

    public int getDelieveryCharges()
    {
        return delieveryCharges;
    }

    public int getDiscount(int percentage)
    {
        double discount = Double.valueOf(percentage) / 100;
        totalCashAfter = (int) (totalCash - Double.valueOf(totalCash * (Double.valueOf(percentage) / 100)));
        return totalCashAfter;
    }
}