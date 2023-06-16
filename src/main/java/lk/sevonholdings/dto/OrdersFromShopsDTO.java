package lk.sevonholdings.dto;

public class OrdersFromShopsDTO {
    private String Order_Id;
    private String Shop_Id;
    private String Shop_Name;
    private String Rep_Id;
    private String Rep_Name;
    private String OrderedDate;
    private String OrderedTime;
    private String ExpectDate;
    private String Avalability;
    private String Remaining_days_expired;
    private double Remaining_Value_Pbar;

    public OrdersFromShopsDTO(String order_Id, String shop_Id, String shop_Name, String rep_Id, String rep_Name, String orderedDate, String orderedTime, String expectDate, String avalability, String remaining_days_expired, double remaining_Value_Pbar) {
        Order_Id = order_Id;
        Shop_Id = shop_Id;
        Shop_Name = shop_Name;
        Rep_Id = rep_Id;
        Rep_Name = rep_Name;
        OrderedDate = orderedDate;
        OrderedTime = orderedTime;
        ExpectDate = expectDate;
        Avalability = avalability;
        Remaining_days_expired = remaining_days_expired;
        Remaining_Value_Pbar = remaining_Value_Pbar;
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

    public String getOrderedTime() {
        return OrderedTime;
    }

    public void setOrderedTime(String orderedTime) {
        OrderedTime = orderedTime;
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

    public double getRemaining_Value_Pbar() {
        return Remaining_Value_Pbar;
    }

    public void setRemaining_Value_Pbar(double remaining_Value_Pbar) {
        Remaining_Value_Pbar = remaining_Value_Pbar;
    }
}
