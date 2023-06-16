package lk.sevonholdings.dao.custom.impl;

import lk.sevonholdings.dao.custom.SupplierOrderDAO;
import lk.sevonholdings.dao.util.DBUtil;
import lk.sevonholdings.entity.SupplierOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {
    private final Connection connection;

    public SupplierOrderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(SupplierOrder entity) {
        try {
            return
                    DBUtil.executeUpdate("INSERT INTO OrdersToMaliban VALUES('"+ entity.getOrder_Id()+"','"+entity.getDate()+"','"+entity.getTime()+"',"+entity.getTotalSupplierCost()+","+entity.getTotalDistributorCost()+","+entity.getTotalMRPrice()+",'"+entity.getAvalability()+"');");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SupplierOrder update(SupplierOrder entity) {
        return null;
    }

    @Override
    public List<SupplierOrder> findAll() {
        return null;
    }

    @Override
    public boolean deleteByPk(String pk) {
        return false;
    }

    @Override
    public Optional<SupplierOrder> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public String findLastMalibanOrderId() {
        ResultSet result = null;
        try {
            result = DBUtil.executeQuery("SELECT Order_id FROM OrdersToMaliban ORDER BY Order_Id DESC LIMIT 1;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String id = null;
        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                id=result.getString(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return id;
    }
}
