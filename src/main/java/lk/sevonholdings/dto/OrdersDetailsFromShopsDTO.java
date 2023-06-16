package lk.sevonholdings.dto;

public class OrdersDetailsFromShopsDTO {
    private String Order_Id;
    private String Biscuit_no;
    private String Qty;
    private String Date;

    public OrdersDetailsFromShopsDTO(String order_Id, String biscuit_no, String qty, String date) {
        Order_Id = order_Id;
        Biscuit_no = biscuit_no;
        Qty = qty;
        Date = date;
    }

    public String getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(String order_Id) {
        Order_Id = order_Id;
    }

    public String getBiscuit_no() {
        return Biscuit_no;
    }

    public void setBiscuit_no(String biscuit_no) {
        Biscuit_no = biscuit_no;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
