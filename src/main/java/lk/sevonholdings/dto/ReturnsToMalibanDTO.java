package lk.sevonholdings.dto;

public class ReturnsToMalibanDTO {
    private String ReturnId;
    private java.sql.Date Date;
    private java.sql.Time Time;
    private double TotalSupplierCost;
    private double TotalDistributorCost;
    private double TotalMRPrice;
    private String Avalability;

    public ReturnsToMalibanDTO(String returnId, java.sql.Date date, java.sql.Time time, double totalSupplierCost, double totalDistributorCost, double totalMRPrice, String avalability) {
        ReturnId = returnId;
        Date = date;
        Time = time;
        TotalSupplierCost = totalSupplierCost;
        TotalDistributorCost = totalDistributorCost;
        TotalMRPrice = totalMRPrice;
        Avalability = avalability;
    }

    public String getReturnId() {
        return ReturnId;
    }

    public void setReturnId(String returnId) {
        ReturnId = returnId;
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
