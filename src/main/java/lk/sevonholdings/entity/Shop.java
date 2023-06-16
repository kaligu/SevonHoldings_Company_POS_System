package lk.sevonholdings.entity;

import java.sql.Date;

public class Shop implements SuperEntity {
    private String Shop_Id;
    private String Shop_Name;
    private String Shop_Contact;
    private String Shop_Location;
    private String Reg_Rep_Id;
    private Date Reg_Date;
    private String Check_Active;

    public Shop(String shop_Id, String shop_Name, String shop_Contact, String shop_Location, String reg_Rep_Id, Date reg_Date, String check_Active) {
        Shop_Id = shop_Id;
        Shop_Name = shop_Name;
        Shop_Contact = shop_Contact;
        Shop_Location = shop_Location;
        Reg_Rep_Id = reg_Rep_Id;
        Reg_Date = reg_Date;
        Check_Active = check_Active;
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

    public String getShop_Contact() {
        return Shop_Contact;
    }

    public void setShop_Contact(String shop_Contact) {
        Shop_Contact = shop_Contact;
    }

    public String getShop_Location() {
        return Shop_Location;
    }

    public void setShop_Location(String shop_Location) {
        Shop_Location = shop_Location;
    }

    public String getReg_Rep_Id() {
        return Reg_Rep_Id;
    }

    public void setReg_Rep_Id(String reg_Rep_Id) {
        Reg_Rep_Id = reg_Rep_Id;
    }

    public Date getReg_Date() {
        return Reg_Date;
    }

    public void setReg_Date(Date reg_Date) {
        Reg_Date = reg_Date;
    }

    public String getCheck_Active() {
        return Check_Active;
    }

    public void setCheck_Active(String check_Active) {
        Check_Active = check_Active;
    }
}
