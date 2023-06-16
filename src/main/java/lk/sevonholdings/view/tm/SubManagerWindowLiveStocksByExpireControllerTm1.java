package lk.sevonholdings.view.tm;

import java.util.Date;

public class SubManagerWindowLiveStocksByExpireControllerTm1 {
    private String Biscuit_No;
    private String ImagePath;
    private String Biscuit_Brand;
    private String Size;
    private int AvailableQty;
    private Date MFD;
    private Date EXD;
    private String Room_Id;
    private String Remaining_days_expired;
    private double Remaining_Value_Pbar;

    public SubManagerWindowLiveStocksByExpireControllerTm1(String biscuit_No, String imagePath, String biscuit_Brand, String size, int availableQty, Date MFD, Date EXD, String room_Id, String remaining_days_expired, double remaining_Value_Pbar) {
        Biscuit_No = biscuit_No;
        ImagePath = imagePath;
        Biscuit_Brand = biscuit_Brand;
        Size = size;
        AvailableQty = availableQty;
        this.MFD = MFD;
        this.EXD = EXD;
        Room_Id = room_Id;
        Remaining_days_expired = remaining_days_expired;
        Remaining_Value_Pbar = remaining_Value_Pbar;
    }

    public String getBiscuit_No() {
        return Biscuit_No;
    }

    public void setBiscuit_No(String biscuit_No) {
        Biscuit_No = biscuit_No;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getBiscuit_Brand() {
        return Biscuit_Brand;
    }

    public void setBiscuit_Brand(String biscuit_Brand) {
        Biscuit_Brand = biscuit_Brand;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public int getAvailableQty() {
        return AvailableQty;
    }

    public void setAvailableQty(int availableQty) {
        AvailableQty = availableQty;
    }

    public Date getMFD() {
        return MFD;
    }

    public void setMFD(Date MFD) {
        this.MFD = MFD;
    }

    public Date getEXD() {
        return EXD;
    }

    public void setEXD(Date EXD) {
        this.EXD = EXD;
    }

    public String getRoom_Id() {
        return Room_Id;
    }

    public void setRoom_Id(String room_Id) {
        Room_Id = room_Id;
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
