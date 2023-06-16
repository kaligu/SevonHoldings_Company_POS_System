package lk.sevonholdings.dao.custom.impl;

import lk.sevonholdings.dao.custom.SupplyDAO;
import lk.sevonholdings.dao.util.DBUtil;
import lk.sevonholdings.db.DBConnection;
import lk.sevonholdings.dto.MalibanOrdersDTO;
import lk.sevonholdings.dto.OrdersToMalibanDTO;
import lk.sevonholdings.entity.Warehouse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SupplyDAOImpl implements SupplyDAO {
    private final Connection connection;

    public SupplyDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Warehouse entity) {
        return false;
    }

    @Override
    public Warehouse update(Warehouse entity) {
        return null;
    }

    @Override
    public List<Warehouse> findAll() {
        return null;
    }

    @Override
    public boolean deleteByPk(String pk) {
        return false;
    }

    @Override
    public Optional<Warehouse> findByPk(String pk) {
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

    @Override
    public boolean insertAllDataIntoMalibanOrdersDB(List<MalibanOrdersDTO> rarray, OrdersToMalibanDTO ot) {
        try {

            try {
                DBConnection.getDbConnection().getConnection().setAutoCommit(false); //off autocommit
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (addedtoOrderDetailsToMaliban(rarray)) {  //
                if (addedtoOrdersToMaliban(ot)) { //

                    try {
                        DBConnection.getDbConnection().getConnection().commit(); //commit
                        return true;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

            try {
                DBConnection.getDbConnection().getConnection().rollback();//rollback
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return false;//

        }finally {

            try {
                DBConnection.getDbConnection().getConnection().setAutoCommit(true); //on autocommit
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private boolean addedtoOrdersToMaliban(OrdersToMalibanDTO ot) {
        try {
            return
                    DBUtil.executeUpdate("INSERT INTO OrdersToMaliban VALUES('"+ ot.getOrder_Id()+"','"+ot.getDate()+"','"+ot.getTime()+"',"+ot.getTotalSupplierCost()+","+ot.getTotalDistributorCost()+","+ot.getTotalMRPrice()+",'"+ot.getAvalability()+"');");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean addedtoOrderDetailsToMaliban(List<MalibanOrdersDTO> rarray) {
        boolean isadded=false;
        for(MalibanOrdersDTO r:rarray){
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
