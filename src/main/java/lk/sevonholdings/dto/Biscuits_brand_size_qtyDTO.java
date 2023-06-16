package lk.sevonholdings.dto;

public class Biscuits_brand_size_qtyDTO {
    private String Biscuit_Brand;
    private String Size;
    private int AvailableQty;

    public Biscuits_brand_size_qtyDTO(String biscuit_Brand, String size, int availableQty) {
        Biscuit_Brand = biscuit_Brand;
        Size = size;
        AvailableQty = availableQty;
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
}
