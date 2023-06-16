package lk.sevonholdings.dao;

import lk.sevonholdings.dto.Biscuits_brand_size_qtyDTO;
import lk.sevonholdings.view.tm.LiveStocksByRoomsUsingRoomIdTm1;
import lk.sevonholdings.view.tm.SubManagerWindowLiveStocksByExpireControllerTm1;

import java.util.ArrayList;
import java.util.Optional;

public interface QueryDAO extends SuperDAO{
    ArrayList<LiveStocksByRoomsUsingRoomIdTm1> showLiveStocksByRoomsUsingRoomId(String roomid);
    ArrayList<SubManagerWindowLiveStocksByExpireControllerTm1> showLiveStocksByExpire();
    ArrayList<Biscuits_brand_size_qtyDTO> showLiveStocks_OnlyBiscuitNameAndQty();
    Optional<String> validateToLoginManagerWindow(String username, String password);
}
