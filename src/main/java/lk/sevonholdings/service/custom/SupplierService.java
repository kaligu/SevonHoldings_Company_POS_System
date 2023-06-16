package lk.sevonholdings.service.custom;

import lk.sevonholdings.dto.SupplierOrderDTO;
import lk.sevonholdings.dto.SupplierOrderDetailDTO;
import lk.sevonholdings.service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService extends SuperService {
    String GetLastMalibanOrderID();
    boolean insertAllDataIntoMalibanOrdersDB(List<SupplierOrderDetailDTO> rarray , SupplierOrderDTO ot) throws SQLException;
}
