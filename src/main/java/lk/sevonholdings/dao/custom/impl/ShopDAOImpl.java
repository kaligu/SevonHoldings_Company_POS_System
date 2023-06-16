package lk.sevonholdings.dao.custom.impl;

import lk.sevonholdings.dao.custom.ShopDAO;
import lk.sevonholdings.dao.util.DBUtil;
import lk.sevonholdings.entity.Shop;
import lk.sevonholdings.entity.Warehouse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopDAOImpl implements ShopDAO {
    private final Connection connection;
    public ShopDAOImpl(Connection connection) {
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
    public boolean save(Shop entity) {
        return false;
    }

    @Override
    public Shop update(Shop entity) {
        return null;
    }

    @Override
    public List<Shop> findAll() {
        ResultSet rst = null;
        try {
            rst = DBUtil.executeQuery("SELECT*FROM Shops;");
            return getMemberList(rst);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Shop> getMemberList(ResultSet result) {
        ArrayList<Shop> slist = new ArrayList<>();

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                slist.add( new Shop(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        Date.valueOf(result.getString(6)),
                        result.getString(7)
                ));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return slist;
    }

    @Override
    public boolean deleteByPk(String pk) {
        return false;
    }

    @Override
    public Optional<Shop> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public String findLastProductNo() {
        ResultSet result = null;
        try {
            result = DBUtil.executeQuery("SELECT Shop_Id FROM Shops ORDER BY Shop_Id DESC LIMIT 1;");
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
