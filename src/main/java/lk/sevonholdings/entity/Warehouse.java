package lk.sevonholdings.entity;

import java.io.Serializable;

public class Warehouse implements Serializable, SuperEntity {
    private String Room_Id;
    private String Description;
    private Double Maximum_Room_Volume;
    private Double Filled_Room_Volume;
    private String Availability;

    public Warehouse() {
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "Room_Id='" + Room_Id + '\'' +
                ", Description='" + Description + '\'' +
                ", Maximum_Room_Volume=" + Maximum_Room_Volume +
                ", Filled_Room_Volume=" + Filled_Room_Volume +
                ", Availability='" + Availability + '\'' +
                '}';
    }

    public Warehouse(String room_Id, String description, Double maximum_Room_Volume, Double filled_Room_Volume, String availability) {
        Room_Id = room_Id;
        Description = description;
        Maximum_Room_Volume = maximum_Room_Volume;
        Filled_Room_Volume = filled_Room_Volume;
        Availability = availability;
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
}
