package lk.sevonholdings.dao.custom;

import lk.sevonholdings.dao.CrudDAO;
import lk.sevonholdings.dto.MalibanOrdersDTO;
import lk.sevonholdings.dto.OrdersToMalibanDTO;
import lk.sevonholdings.entity.Warehouse;

import java.util.List;

public interface SupplyDAO extends CrudDAO<Warehouse,String> {
    String findLastMalibanOrderId();

    boolean insertAllDataIntoMalibanOrdersDB(List<MalibanOrdersDTO> rarray, OrdersToMalibanDTO ot);
}
