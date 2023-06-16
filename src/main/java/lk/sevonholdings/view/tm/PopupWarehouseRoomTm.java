package lk.sevonholdings.view.tm;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class PopupWarehouseRoomTm {
    private String Room_Id;
    private String Description;
    private Double Maximum_Room_Volume;
    private Double Filled_Room_Volume;
    private String Availability;
    private ProgressIndicator pbar;
    private Label lbl;

    public PopupWarehouseRoomTm(String room_Id, String description, Double maximum_Room_Volume, Double filled_Room_Volume, String availability, ProgressIndicator pbar, Label lbl) {
        Room_Id = room_Id;
        Description = description;
        Maximum_Room_Volume = maximum_Room_Volume;
        Filled_Room_Volume = filled_Room_Volume;
        Availability = availability;
        this.pbar = pbar;
        this.lbl = lbl;
    }

    public String getRoom_Id() {
        return Room_Id;
    }

    public void setRoom_Id(String room_Id) {
        Room_Id = room_Id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getMaximum_Room_Volume() {
        return Maximum_Room_Volume;
    }

    public void setMaximum_Room_Volume(Double maximum_Room_Volume) {
        Maximum_Room_Volume = maximum_Room_Volume;
    }

    public Double getFilled_Room_Volume() {
        return Filled_Room_Volume;
    }

    public void setFilled_Room_Volume(Double filled_Room_Volume) {
        Filled_Room_Volume = filled_Room_Volume;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    public ProgressIndicator getPbar() {
        return pbar;
    }

    public void setPbar(ProgressIndicator pbar) {
        this.pbar = pbar;
    }

    public Label getLbl() {
        return lbl;
    }

    public void setLbl(Label lbl) {
        this.lbl = lbl;
    }
}
