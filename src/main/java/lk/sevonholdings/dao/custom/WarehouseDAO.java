package lk.sevonholdings.dao.custom;

import lk.sevonholdings.dao.CrudDAO;
import lk.sevonholdings.entity.Warehouse;

public interface WarehouseDAO extends CrudDAO<Warehouse,String> {
    String findLastRoomNo();
}
