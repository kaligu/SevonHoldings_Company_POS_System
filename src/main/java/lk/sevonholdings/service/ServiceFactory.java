package lk.sevonholdings.service;

import lk.sevonholdings.service.custom.impl.*;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;
    private ServiceFactory(){

    }
    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }
    public <T extends SuperService> T getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case PRODUCT:
                return (T)new ProductServiceImpl();

            case WAREHOUSE:
                return (T)new WarehouseServiceImpl();
            case SHOP:
                return (T)new ShopServiceImpl();
            case SUPPLY:
                return (T)new SupplierServiceImpl();
            case Supplier:
                return (T)new SupplierServiceImpl();
            case USERSERVICE:
                return (T)new UserServiceimpl();
            default:
                return null;
        }
    }
}
