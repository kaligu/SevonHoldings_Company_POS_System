package lk.sevonholdings.dao.custom.impl;

import lk.sevonholdings.dao.custom.ShopOrdersDAO;
import lk.sevonholdings.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public class ShopOrdersDAOImpl implements ShopOrdersDAO {
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
}
