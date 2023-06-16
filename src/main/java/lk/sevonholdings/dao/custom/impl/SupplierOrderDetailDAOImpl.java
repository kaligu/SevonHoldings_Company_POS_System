package lk.sevonholdings.dao.custom.impl;

import lk.sevonholdings.dao.custom.SupplierOrderDetailDAO;
import lk.sevonholdings.dao.util.DBUtil;
import lk.sevonholdings.entity.SupplierOrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SupplierOrderDetailDAOImpl implements SupplierOrderDetailDAO {
    private final Connection connection;

    public SupplierOrderDetailDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(SupplierOrderDetail entity) {
        return false;
    }

    @Override
    public SupplierOrderDetail update(SupplierOrderDetail entity) {
        return null;
    }

    @Override
    public List<SupplierOrderDetail> findAll() {
        return null;
    }

    @Override
    public boolean deleteByPk(String pk) {
        return false;
    }

    @Override
    public Optional<SupplierOrderDetail> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public boolean saveOrderDetailArray(List<SupplierOrderDetail> rarray) {
        boolean isadded=false;
        for(SupplierOrderDetail r:rarray){
            try {
                isadded = DBUtil.executeUpdate("INSERT INTO OrdersDetailsToMaliban (\n" +
                                "                                                 Order_id,\n" +
                                "                                                 Biscuit_no,\n" +
                                "                                                 Biscuit_Range,\n" +
                                "                                                 Biscuit_Brand,\n" +
                                "                                                 Size,\n" +
                                "                                                 Qty,\n" +
                                "                                                 SupplierCost,\n" +
                                "                                                 DistributorCost,\n" +
                                "                                                 MRPrice)\n" +
                                "                                              VALUES(?,?,?,?,?,?,?,?,?);",
                        r.getOrderId(),
                        r.getBiscuitNo(),
                        r.getRange(),
                        r.getBrand(),
                        r.getSize(),
                        r.getQTY(),
                        r.getSupplierCost(),
                        r.getDistributorCost(),
                        r.getMRP());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return isadded;
    }
}
