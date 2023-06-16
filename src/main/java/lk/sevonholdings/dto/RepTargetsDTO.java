package lk.sevonholdings.dto;

import java.sql.Date;

public class RepTargetsDTO {
    private int RTID;
    private String Target_Id;
    private String Rep_Id;
    private String Rep_Name;
    private String Shop_Order_Id;
    private String Shop_Id;
    private Date CreatedDate;
    private Date ToDoDate;
    private String State;
    private String  isCheckNotified;

    public RepTargetsDTO(int RTID, String target_Id, String rep_Id, String rep_Name, String shop_Order_Id, String shop_Id, Date createdDate, Date toDoDate, String state, String isCheckNotified) {
        this.RTID = RTID;
        Target_Id = target_Id;
        Rep_Id = rep_Id;
        Rep_Name = rep_Name;
        Shop_Order_Id = shop_Order_Id;
        Shop_Id = shop_Id;
        CreatedDate = createdDate;
        ToDoDate = toDoDate;
        State = state;
        this.isCheckNotified = isCheckNotified;
    }

    public int getRTID() {
        return RTID;
    }

    public void setRTID(int RTID) {
        this.RTID = RTID;
    }

    public String getTarget_Id() {
        return Target_Id;
    }

    public void setTarget_Id(String target_Id) {
        Target_Id = target_Id;
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

    public String getShop_Order_Id() {
        return Shop_Order_Id;
    }

    public void setShop_Order_Id(String shop_Order_Id) {
        Shop_Order_Id = shop_Order_Id;
    }

    public String getShop_Id() {
        return Shop_Id;
    }

    public void setShop_Id(String shop_Id) {
        Shop_Id = shop_Id;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Date getToDoDate() {
        return ToDoDate;
    }

    public void setToDoDate(Date toDoDate) {
        ToDoDate = toDoDate;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getIsCheckNotified() {
        return isCheckNotified;
    }

    public void setIsCheckNotified(String isCheckNotified) {
        this.isCheckNotified = isCheckNotified;
    }
}
