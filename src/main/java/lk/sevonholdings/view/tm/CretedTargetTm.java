package lk.sevonholdings.view.tm;

public class CretedTargetTm {
    private int RTID;
    private String TargetId;
    private String RepId;
    private String RepName;
    private String OrderId;
    private String ShopId;
    private String TargetDate;

    public CretedTargetTm(int RTID, String targetId, String repId, String repName, String orderId, String shopId, String targetDate) {
        this.RTID = RTID;
        TargetId = targetId;
        RepId = repId;
        RepName = repName;
        OrderId = orderId;
        ShopId = shopId;
        TargetDate = targetDate;
    }

    public int getRTID() {
        return RTID;
    }

    public void setRTID(int RTID) {
        this.RTID = RTID;
    }

    public String getTargetId() {
        return TargetId;
    }

    public void setTargetId(String targetId) {
        TargetId = targetId;
    }

    public String getRepId() {
        return RepId;
    }

    public void setRepId(String repId) {
        RepId = repId;
    }

    public String getRepName() {
        return RepName;
    }

    public void setRepName(String repName) {
        RepName = repName;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getTargetDate() {
        return TargetDate;
    }

    public void setTargetDate(String targetDate) {
        TargetDate = targetDate;
    }
}
