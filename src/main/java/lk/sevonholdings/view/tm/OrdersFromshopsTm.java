package lk.sevonholdings.view.tm;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;

public class OrdersFromshopsTm {
    private String Order_Id;
    private String Shop_Id;
    private String Shop_Name;
    private String Rep_Id;
    private String Rep_Name;
    private String OrderedDate;
    private String ExpectDate;
    private String Avalability;
    private String Remaining_days_expired;
    private ProgressIndicator pindi;
    private CheckBox cb;

    public OrdersFromshopsTm(String order_Id, String shop_Id, String shop_Name, String rep_Id, String rep_Name, String orderedDate, String expectDate, String avalability, String remaining_days_expired, ProgressIndicator pindi, CheckBox cb) {
        Order_Id = order_Id;
        Shop_Id = shop_Id;
        Shop_Name = shop_Name;
        Rep_Id = rep_Id;
        Rep_Name = rep_Name;
        OrderedDate = orderedDate;
        ExpectDate = expectDate;
        Avalability = avalability;
        Remaining_days_expired = remaining_days_expired;
        this.pindi = pindi;
        this.cb = cb;
    }

    public String getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(String order_Id) {
        Order_Id = order_Id;
    }

    public String getShop_Id() {
        return Shop_Id;
    }

    public void setShop_Id(String shop_Id) {
        Shop_Id = shop_Id;
    }

    public String getShop_Name() {
        return Shop_Name;
    }

    public void setShop_Name(String shop_Name) {
        Shop_Name = shop_Name;
    }

    public String getRep_Id() {
        return Rep_Id;
    }

    public void setRep_Id(String rep_Id) {
        Rep_Id = rep_Id;
    }

    public String getRep_Name() {
        return Rep_Name;
    }

    public void setRep_Name(String rep_Name) {
        Rep_Name = rep_Name;
    }

    public String getOrderedDate() {
        return OrderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        OrderedDate = orderedDate;
    }

    public String getExpectDate() {
        return ExpectDate;
    }

    public void setExpectDate(String expectDate) {
        ExpectDate = expectDate;
    }

    public String getAvalability() {
        return Avalability;
    }

    public void setAvalability(String avalability) {
        Avalability = avalability;
    }

    public String getRemaining_days_expired() {
        return Remaining_days_expired;
    }

    public void setRemaining_days_expired(String remaining_days_expired) {
        Remaining_days_expired = remaining_days_expired;
    }

    public ProgressIndicator getPindi() {
        return pindi;
    }

    public void setPindi(ProgressIndicator pindi) {
        this.pindi = pindi;
    }

    public CheckBox getCb() {
        return cb;
    }

    public void setCb(CheckBox cb) {
        this.cb = cb;
    }
}
