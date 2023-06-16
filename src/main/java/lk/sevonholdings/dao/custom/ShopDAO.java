package lk.sevonholdings.dao.custom;

import lk.sevonholdings.dao.CrudDAO;
import lk.sevonholdings.entity.Shop;
import lk.sevonholdings.entity.Warehouse;

public interface ShopDAO extends CrudDAO<Shop,String> {
     boolean save(Warehouse entity);

     Warehouse update(Warehouse entity);

     String findLastProductNo();
}
