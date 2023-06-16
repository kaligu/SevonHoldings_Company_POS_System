package lk.sevonholdings.service.util;

import lk.sevonholdings.dto.*;
import lk.sevonholdings.entity.*;

public class Convertor {
    public ProductDTO fromProduct(Product product){
        return new ProductDTO(
                product.getBiscuitsNo(),
                product.getImagePath(),
                product.getBiscuitRange(),
                product.getBiscuitBrand(),
                product.getSize(),
                product.getCBFVolume(),
                product.getSupplierPrice(),
                product.getDistributorPrice(),
                product.getMRPrice()
        );
    }

    public Product toProduct(ProductDTO productDTO){
        return new Product(
                productDTO.getBiscuitsNo(),
                productDTO.getImagePath(),
                productDTO.getBiscuitRange(),
                productDTO.getBiscuitBrand(),
                productDTO.getSize(),
                productDTO.getCBFVolume(),
                productDTO.getSupplierPrice(),
                productDTO.getDistributorPrice(),
                productDTO.getMRPrice()
        );
    }
    public WarehouseDTO fromWarehouse(Warehouse warehouse){
        return new WarehouseDTO(
                warehouse.getRoom_Id(),
                warehouse.getDescription(),
                warehouse.getMaximum_Room_Volume(),
                warehouse.getFilled_Room_Volume(),
                warehouse.getAvailability()
        );
    }

    public Warehouse toWarehouse(WarehouseDTO warehouseDTO) {
        return new Warehouse(
                warehouseDTO.getRoom_Id(),
                warehouseDTO.getDescription(),
                warehouseDTO.getMaximum_Room_Volume(),
                warehouseDTO.getFilled_Room_Volume(),
                warehouseDTO.getAvailability()
        );
    }
    public ShopDTO fromShop(Shop shop){
        return new ShopDTO(
                shop.getShop_Id(),
                shop.getShop_Name(),
                shop.getShop_Contact(),
                shop.getShop_Location(),
                shop.getReg_Rep_Id(),
                shop.getReg_Date(),
                shop.getCheck_Active()
        );
    }

    public Shop toShop(ShopDTO shopDTO) {
        return new Shop(
                shopDTO.getShop_Id(),
                shopDTO.getShop_Name(),
                shopDTO.getShop_Contact(),
                shopDTO.getShop_Location(),
                shopDTO.getReg_Rep_Id(),
                shopDTO.getReg_Date(),
                shopDTO.getCheck_Active()
        );
    }
    public SupplierOrderDTO fromSupplierOrder(SupplierOrder supplierOrder) {
        return new SupplierOrderDTO(
                supplierOrder.getOrder_Id(),
                supplierOrder.getDate(),
                supplierOrder.getTime(),
                supplierOrder.getTotalSupplierCost(),
                supplierOrder.getTotalDistributorCost(),
                supplierOrder.getTotalMRPrice(),
                supplierOrder.getAvalability()
        );
    }
    public SupplierOrder toSupplierOrder(SupplierOrderDTO supplierOrderDTO){
        return new SupplierOrder(
                supplierOrderDTO.getOrder_Id(),
                supplierOrderDTO.getDate(),
                supplierOrderDTO.getTime(),
                supplierOrderDTO.getTotalSupplierCost(),
                supplierOrderDTO.getTotalDistributorCost(),
                supplierOrderDTO.getTotalMRPrice(),
                supplierOrderDTO.getAvalability()
        );
    }
    public SupplierOrderDetailDTO fromSupplierOrder(SupplierOrderDetail supplierOrderDetail){
        return new SupplierOrderDetailDTO(
                supplierOrderDetail.getOrderId(),
                supplierOrderDetail.getBiscuitNo(),
                supplierOrderDetail.getRange(),
                supplierOrderDetail.getBrand(),
                supplierOrderDetail.getSize(),
                supplierOrderDetail.getQTY(),
                supplierOrderDetail.getSupplierCost(),
                supplierOrderDetail.getDistributorCost(),
                supplierOrderDetail.getMRP()
        );
    }
    public SupplierOrderDetail toOrderDetail(SupplierOrderDetailDTO supplierOrderDetailDTO){
        return new SupplierOrderDetail(
                supplierOrderDetailDTO.getOrderId(),
                supplierOrderDetailDTO.getBiscuitNo(),
                supplierOrderDetailDTO.getRange(),
                supplierOrderDetailDTO.getBrand(),
                supplierOrderDetailDTO.getSize(),
                supplierOrderDetailDTO.getQTY(),
                supplierOrderDetailDTO.getSupplierCost(),
                supplierOrderDetailDTO.getDistributorCost(),
                supplierOrderDetailDTO.getMRP()
        );
    }
}
