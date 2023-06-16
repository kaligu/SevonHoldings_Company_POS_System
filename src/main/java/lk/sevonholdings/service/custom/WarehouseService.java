package lk.sevonholdings.service.custom;

import lk.sevonholdings.dto.Biscuits_brand_size_qtyDTO;
import lk.sevonholdings.dto.WarehouseDTO;
import lk.sevonholdings.service.SuperService;
import lk.sevonholdings.view.tm.LiveStocksByRoomsUsingRoomIdTm1;
import lk.sevonholdings.view.tm.SubManagerWindowLiveStocksByExpireControllerTm1;

import java.util.ArrayList;
import java.util.List;

public interface WarehouseService extends SuperService {
    boolean saveWarehouse(WarehouseDTO warehouseDTO);
    List<WarehouseDTO> findAllWarehouse();
    String findLastWarehouseRoomNo();
    boolean deleteWarehouseRoomPK(String pk);
    ArrayList<LiveStocksByRoomsUsingRoomIdTm1> showLiveStocksByRoomsUsingRoomId(String roomid);
    ArrayList<SubManagerWindowLiveStocksByExpireControllerTm1> showLiveStocksByExpire();

    ArrayList<Biscuits_brand_size_qtyDTO> showLiveStocks_OnlyBiscuitNameAndQty();
}
