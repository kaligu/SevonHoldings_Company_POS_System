package lk.sevonholdings.dao.custom;

import lk.sevonholdings.dao.CrudDAO;
import lk.sevonholdings.entity.SupplierOrder;

public interface SupplierOrderDAO extends CrudDAO<SupplierOrder,String> {
    String findLastMalibanOrderId();
}
