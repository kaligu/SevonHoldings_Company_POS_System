package lk.sevonholdings.dto;

public class ProductDTO {
    private String BiscuitsNo;
    private String ImagePath;
    private String BiscuitRange;
    private String BiscuitBrand;
    private String size;
    private double CBFVolume;
    private double SupplierPrice;
    private double DistributorPrice;
    private double MRPrice;

    public ProductDTO(String biscuitsNo, String imagePath, String biscuitRange, String biscuitBrand, String size, double CBFVolume, double supplierPrice, double distributorPrice, double MRPrice) {
        this.BiscuitsNo = biscuitsNo;
        this.ImagePath = imagePath;
        this.BiscuitRange = biscuitRange;
        this.BiscuitBrand = biscuitBrand;
        this.size = size;
        this.CBFVolume = CBFVolume;
        this.SupplierPrice = supplierPrice;
        this.DistributorPrice = distributorPrice;
        this.MRPrice = MRPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "BiscuitsNo='" + BiscuitsNo + '\'' +
                ", ImagePath='" + ImagePath + '\'' +
                ", BiscuitRange='" + BiscuitRange + '\'' +
                ", BiscuitBrand='" + BiscuitBrand + '\'' +
                ", size='" + size + '\'' +
                ", CBFVolume=" + CBFVolume +
                ", SupplierPrice=" + SupplierPrice +
                ", DistributorPrice=" + DistributorPrice +
                ", MRPrice=" + MRPrice +
                '}';
    }

    public String getBiscuitsNo() {
        return BiscuitsNo;
    }

    public void setBiscuitsNo(String biscuitsNo) {
        BiscuitsNo = biscuitsNo;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getBiscuitRange() {
        return BiscuitRange;
    }

    public void setBiscuitRange(String biscuitRange) {
        BiscuitRange = biscuitRange;
    }

    public String getBiscuitBrand() {
        return BiscuitBrand;
    }

    public void setBiscuitBrand(String biscuitBrand) {
        BiscuitBrand = biscuitBrand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getCBFVolume() {
        return CBFVolume;
    }

    public void setCBFVolume(double CBFVolume) {
        this.CBFVolume = CBFVolume;
    }

    public double getSupplierPrice() {
        return SupplierPrice;
    }

    public void setSupplierPrice(double supplierPrice) {
        SupplierPrice = supplierPrice;
    }

    public double getDistributorPrice() {
        return DistributorPrice;
    }

    public void setDistributorPrice(double distributorPrice) {
        DistributorPrice = distributorPrice;
    }

    public double getMRPrice() {
        return MRPrice;
    }

    public void setMRPrice(double MRPrice) {
        this.MRPrice = MRPrice;
    }
}
