package lk.sevonholdings.dao;

import lk.sevonholdings.dao.custom.impl.*;

import java.sql.Connection;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){
    }
    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }
    public <T> T getDAO(Connection connection , DaoTypes daoTypes){
        switch (daoTypes){
            case PRODUCT:
                return (T)new ProductDAOImpl(connection);
            case WAREHOUSE:
                return (T)new WarehouseDAOImpl(connection);
            case QUERY:
                return (T)new QueryDAOImpl(connection);
            case SHOP:
                return (T)new ShopDAOImpl(connection);
            case SUPPLY:
                return (T)new SupplyDAOImpl(connection);
            case Supplier_Order:
                return (T)new SupplierOrderDAOImpl(connection);
            case Supplier_Order_Detail:
                return (T)new SupplierOrderDetailDAOImpl(connection);
            default:
                return null;
        }
    }
}
