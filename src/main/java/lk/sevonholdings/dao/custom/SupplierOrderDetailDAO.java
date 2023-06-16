package lk.sevonholdings.dao.custom;

import lk.sevonholdings.dao.CrudDAO;
import lk.sevonholdings.entity.SupplierOrderDetail;

import java.util.List;

public interface SupplierOrderDetailDAO extends CrudDAO<SupplierOrderDetail,String> {
    boolean saveOrderDetailArray(List<SupplierOrderDetail> rarray);
}
