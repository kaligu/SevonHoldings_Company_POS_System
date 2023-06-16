package lk.sevonholdings.service.custom.impl;

import lk.sevonholdings.dao.DaoFactory;
import lk.sevonholdings.dao.DaoTypes;
import lk.sevonholdings.dao.QueryDAO;
import lk.sevonholdings.dao.custom.WarehouseDAO;
import lk.sevonholdings.db.DBConnection;
import lk.sevonholdings.dto.Biscuits_brand_size_qtyDTO;
import lk.sevonholdings.dto.WarehouseDTO;
import lk.sevonholdings.service.custom.WarehouseService;
import lk.sevonholdings.service.exception.DuplicateException;
import lk.sevonholdings.service.util.Convertor;
import lk.sevonholdings.view.tm.LiveStocksByRoomsUsingRoomIdTm1;
import lk.sevonholdings.view.tm.SubManagerWindowLiveStocksByExpireControllerTm1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseDAO warehouseDAO;
    private final QueryDAO queryDAO;
    private final Convertor convertor;
    private final Connection connection;

    public WarehouseServiceImpl() {
        connection= DBConnection.getDbConnection().getConnection();
        warehouseDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.WAREHOUSE );
        queryDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.QUERY );
        convertor=new Convertor();
    }

    @Override
    public boolean saveWarehouse(WarehouseDTO warehouseDTO) {
        //need to do some business validations
        if(warehouseDAO.existByPk(warehouseDTO.getRoom_Id())) throw new DuplicateException("warehouse Room already saved!");

        return warehouseDAO.save(convertor.toWarehouse(warehouseDTO));
    }

    @Override
    public List<WarehouseDTO> findAllWarehouse() {
        return warehouseDAO.findAll().stream().map(warehouse -> convertor.fromWarehouse(warehouse)).collect(Collectors.toList());
    }

    @Override
    public String findLastWarehouseRoomNo() {
        return warehouseDAO.findLastRoomNo();
    }

    @Override
    public boolean deleteWarehouseRoomPK(String pk) {
        return false;
    }

    @Override
    public ArrayList<LiveStocksByRoomsUsingRoomIdTm1> showLiveStocksByRoomsUsingRoomId(String roomid) {
        return queryDAO.showLiveStocksByRoomsUsingRoomId(roomid);
    }

    @Override
    public ArrayList<SubManagerWindowLiveStocksByExpireControllerTm1> showLiveStocksByExpire() {
        return queryDAO.showLiveStocksByExpire();
    }

    @Override
    public ArrayList<Biscuits_brand_size_qtyDTO> showLiveStocks_OnlyBiscuitNameAndQty() {
        return queryDAO.showLiveStocks_OnlyBiscuitNameAndQty();
    }

}
