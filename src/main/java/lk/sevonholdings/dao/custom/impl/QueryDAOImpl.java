package lk.sevonholdings.dao.custom.impl;

import lk.sevonholdings.dao.QueryDAO;
import lk.sevonholdings.dao.util.DBUtil;
import lk.sevonholdings.dto.Biscuits_brand_size_qtyDTO;
import lk.sevonholdings.view.tm.LiveStocksByRoomsUsingRoomIdTm1;
import lk.sevonholdings.view.tm.SubManagerWindowLiveStocksByExpireControllerTm1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class QueryDAOImpl implements QueryDAO {
    private final Connection connection;

    public QueryDAOImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public ArrayList<LiveStocksByRoomsUsingRoomIdTm1> showLiveStocksByRoomsUsingRoomId(String roomid) {
        ResultSet result = null;
        try {
            result = DBUtil.executeQuery("SELECT \n" +
                    "Biscuits.Biscuit_No, \n" +
                    "Biscuits.ImagePath, \n" +
                    "Biscuits.Biscuit_Brand, \n" +
                    "Biscuits.Size,\n" +
                    "UseWareHouseDetails_NewIn.AvailableQty, \n" +
                    "UseWareHouseDetails_NewIn.MFD,\n" +
                    "UseWareHouseDetails_NewIn.EXD \n" +
                    "FROM \n" +
                    "Biscuits JOIN UseWareHouseDetails_NewIn\n" +
                    "ON Biscuits.Biscuit_no= UseWareHouseDetails_NewIn.Biscuit_no\n" +
                    "WHERE \n" +
                    "UseWareHouseDetails_NewIn.AvailableQty!=0\n" +
                    "AND\n" +
                    "UseWareHouseDetails_NewIn.Room_id='"+roomid+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<LiveStocksByRoomsUsingRoomIdTm1> slist = new ArrayList<>();

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                slist.add( new LiveStocksByRoomsUsingRoomIdTm1(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        Integer.parseInt(result.getString(5)),
                        result.getDate(6),
                        result.getDate(7)
                ));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return slist;
    }

    @Override
    public ArrayList<SubManagerWindowLiveStocksByExpireControllerTm1> showLiveStocksByExpire() {
        ResultSet result = null;
        try {
            result = DBUtil.executeQuery("SELECT\n" +
                    "    Biscuits.Biscuit_No,\n" +
                    "   Biscuits.ImagePath,\n" +
                    "   Biscuits.Biscuit_Brand,\n" +
                    "    Biscuits.Size,\n" +
                    "    UseWareHouseDetails_NewIn.AvailableQty,\n" +
                    "     UseWareHouseDetails_NewIn.MFD,\n" +
                    "    UseWareHouseDetails_NewIn.EXD,\n" +
                    "     UseWareHouseDetails_NewIn.Room_id,\n" +
                    "(CASE WHEN datediff(EXD,CURDATE()) > 0 then datediff(EXD,CURDATE()) ELSE 'Expired'END) as Remaining_days_expired,\n" +
                    "(CASE WHEN datediff(EXD,CURDATE()) > 0 then datediff(CURDATE(),MFD)/datediff(EXD,MFD)  ELSE '1'END) as Remaining_Value_Pbar\n" +
                    "   FROM\n" +
                    "    Biscuits JOIN UseWareHouseDetails_NewIn\n" +
                    "     ON Biscuits.Biscuit_no= UseWareHouseDetails_NewIn.Biscuit_no\n" +
                    "    WHERE\n" +
                    "     UseWareHouseDetails_NewIn.AvailableQty!=0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<SubManagerWindowLiveStocksByExpireControllerTm1> slist = new ArrayList<>();

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                slist.add( new SubManagerWindowLiveStocksByExpireControllerTm1(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        Integer.parseInt(result.getString(5)),
                        result.getDate(6),
                        result.getDate(7),
                        result.getString(8),
                        result.getString(9),
                        Double.parseDouble(result.getString(10))
                ));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return slist;
    }

    @Override
    public ArrayList<Biscuits_brand_size_qtyDTO> showLiveStocks_OnlyBiscuitNameAndQty() {
        ResultSet result = null;
        try {
            result = DBUtil.executeQuery("SELECT\n" +
                    "     Biscuits.Biscuit_Brand,\n" +
                    "     Biscuits.Size,\n" +
                    "     UseWareHouseDetails_NewIn.AvailableQty,\n" +
                    "     SUM(CASE WHEN UseWareHouseDetails_NewIn.Biscuit_no=UseWareHouseDetails_NewIn.Biscuit_no THEN UseWareHouseDetails_NewIn.AvailableQty ELSE 0 END) AS total\n" +
                    "     FROM\n" +
                    "      Biscuits JOIN UseWareHouseDetails_NewIn\n" +
                    "      ON Biscuits.Biscuit_no= UseWareHouseDetails_NewIn.Biscuit_no\n" +
                    "     WHERE\n" +
                    "     UseWareHouseDetails_NewIn.AvailableQty!=0\n" +
                    "     GROUP BY UseWareHouseDetails_NewIn.Biscuit_no");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Biscuits_brand_size_qtyDTO> slist = new ArrayList<>();

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                slist.add( new Biscuits_brand_size_qtyDTO(
                        result.getString(1),
                        result.getString(2),
                        Integer.parseInt(result.getString(3))
                ));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return slist;
    }

    @Override
    public Optional<String> validateToLoginManagerWindow(String username, String password) {
        ResultSet result = null;
        try {
            result = DBUtil.executeQuery("SELECT Role FROM passwordchecktable WHERE Username='"+username+"' AND Password='"+password+"';");
        } catch (SQLException | ClassNotFoundException e) {
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
        if(Optional.ofNullable(id).isPresent()){
            return Optional.of(id);
        }else{
            return Optional.of("notmatched");
        }
    }
}
