package lk.sevonholdings.entity;

public class SupplierOrderDetail implements SuperEntity{
    private String OrderId;
    private String BiscuitNo;
    private String Range;
    private String Brand;
    private String Size;
    private int QTY;
    private double SupplierCost;
    private double DistributorCost;
    private double MRP;

    public SupplierOrderDetail(String orderId, String biscuitNo, String range, String brand, String size, int QTY, double supplierCost, double distributorCost, double MRP) {
        OrderId = orderId;
        BiscuitNo = biscuitNo;
        Range = range;
        Brand = brand;
        Size = size;
        this.QTY = QTY;
        SupplierCost = supplierCost;
        DistributorCost = distributorCost;
        this.MRP = MRP;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getBiscuitNo() {
        return BiscuitNo;
    }

    public void setBiscuitNo(String biscuitNo) {
        BiscuitNo = biscuitNo;
    }

    public String getRange() {
        return Range;
    }

    public void setRange(String range) {
        Range = range;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public double getSupplierCost() {
        return SupplierCost;
    }

    public void setSupplierCost(double supplierCost) {
        SupplierCost = supplierCost;
    }

    public double getDistributorCost() {
        return DistributorCost;
    }

    public void setDistributorCost(double distributorCost) {
        DistributorCost = distributorCost;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }
}
