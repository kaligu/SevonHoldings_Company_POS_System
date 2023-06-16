package lk.sevonholdings.service.custom.impl;

import lk.sevonholdings.dao.DaoFactory;
import lk.sevonholdings.dao.DaoTypes;
import lk.sevonholdings.dao.custom.ShopDAO;
import lk.sevonholdings.db.DBConnection;
import lk.sevonholdings.dto.ShopDTO;
import lk.sevonholdings.service.custom.ShopService;
import lk.sevonholdings.service.util.Convertor;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class ShopServiceImpl implements ShopService {
    private final ShopDAO shopDAO;
    private final Convertor convertor;
    private final Connection connection;

    public ShopServiceImpl() {
        this.connection = DBConnection.getDbConnection().getConnection();
        this.shopDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.SHOP);
        this.convertor = new Convertor();
    }

    @Override
    public String findLastShopNo() {
        return shopDAO.findLastProductNo();
    }

    @Override
    public List<ShopDTO> findAllShops() {
        return shopDAO.findAll().stream().map(shop -> convertor.fromShop(shop)).collect(Collectors.toList());
    }
}
