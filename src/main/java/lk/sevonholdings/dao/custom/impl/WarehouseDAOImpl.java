package lk.sevonholdings.dao.custom.impl;

import lk.sevonholdings.dao.custom.WarehouseDAO;
import lk.sevonholdings.dao.exception.ConstraintViolationException;
import lk.sevonholdings.dao.util.DBUtil;
import lk.sevonholdings.entity.Warehouse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WarehouseDAOImpl implements WarehouseDAO {
    private final Connection connection;

    public WarehouseDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Warehouse warehouse) {
        try {
            boolean isadded = (DBUtil.executeUpdate("INSERT INTO WarehouseRoom VALUES(?,?,?,?,?)",
                    warehouse.getRoom_Id(),
                    warehouse.getDescription(),
                    warehouse.getMaximum_Room_Volume(),
                    warehouse.getFilled_Room_Volume(),
                    warehouse.getAvailability()
            ));{
                return isadded;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConstraintViolationException(e);  //throw runtime exception
        }
    }

    @Override
    public Warehouse update(Warehouse entity) {
        return null;
    }

    @Override
    public List<Warehouse> findAll() {
        ResultSet rst = null;
        try {
            rst = DBUtil.executeQuery("SELECT*FROM WarehouseRoom");
            return getMemberList(rst);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Warehouse> getMemberList(ResultSet result) throws SQLException {
        List<Warehouse> roomList= new ArrayList<>();
        while(result.next()){
            roomList.add(new Warehouse(
                    result.getString(1),
                    result.getString(2),
                    Double.parseDouble(result.getString(3)) ,
                    Double.parseDouble(result.getString(4)) ,
                    result.getString(5) ));
        }
        return roomList;
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
    public String findLastRoomNo() {
        ResultSet result = null;
        try {
            result = DBUtil.executeQuery("SELECT Room_Id FROM WarehouseRoom ORDER BY Room_Id DESC LIMIT 1;");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String id = null;
        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new ConstraintViolationException(e);
            }
            try {
                id=result.getString(1);
            } catch (SQLException e) {
                throw new ConstraintViolationException(e);
            }
        }
        return id;
    }
}
