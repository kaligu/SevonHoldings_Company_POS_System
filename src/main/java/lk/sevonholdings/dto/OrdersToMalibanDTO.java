package lk.sevonholdings.dto;

public class OrdersToMalibanDTO {
    private String Order_Id;
    private java.sql.Date Date;
    private java.sql.Time Time;
    private double TotalSupplierCost;
    private double TotalDistributorCost;
    private double TotalMRPrice;
    private String Avalability;

    public OrdersToMalibanDTO(String order_Id, java.sql.Date date, java.sql.Time time, double totalSupplierCost, double totalDistributorCost, double totalMRPrice, String avalability) {
        Order_Id = order_Id;
        Date = date;
        Time = time;
        TotalSupplierCost = totalSupplierCost;
        TotalDistributorCost = totalDistributorCost;
        TotalMRPrice = totalMRPrice;
        Avalability = avalability;
    }

    public String getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(String order_Id) {
        Order_Id = order_Id;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public java.sql.Time getTime() {
        return Time;
    }

    public void setTime(java.sql.Time time) {
        Time = time;
    }

    public double getTotalSupplierCost() {
        return TotalSupplierCost;
    }

    public void setTotalSupplierCost(double totalSupplierCost) {
        TotalSupplierCost = totalSupplierCost;
    }

    public double getTotalDistributorCost() {
        return TotalDistributorCost;
    }

    public void setTotalDistributorCost(double totalDistributorCost) {
        TotalDistributorCost = totalDistributorCost;
    }

    public double getTotalMRPrice() {
        return TotalMRPrice;
    }

    public void setTotalMRPrice(double totalMRPrice) {
        TotalMRPrice = totalMRPrice;
    }

    public String getAvalability() {
        return Avalability;
    }

    public void setAvalability(String avalability) {
        Avalability = avalability;
    }
}
