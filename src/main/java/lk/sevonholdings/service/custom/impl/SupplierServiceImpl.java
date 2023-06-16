package lk.sevonholdings.service.custom.impl;

import lk.sevonholdings.dao.DaoFactory;
import lk.sevonholdings.dao.DaoTypes;
import lk.sevonholdings.dao.custom.SupplierOrderDAO;
import lk.sevonholdings.dao.custom.SupplierOrderDetailDAO;
import lk.sevonholdings.db.DBConnection;
import lk.sevonholdings.dto.SupplierOrderDTO;
import lk.sevonholdings.dto.SupplierOrderDetailDTO;
import lk.sevonholdings.service.custom.SupplierService;
import lk.sevonholdings.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierServiceImpl implements SupplierService {
    private final SupplierOrderDAO supplierOrderDAO;
    private  final SupplierOrderDetailDAO supplierOrderDetailDAO;
    private final Convertor convertor;
    private final Connection connection;

    public SupplierServiceImpl() {
        connection= DBConnection.getDbConnection().getConnection();
        supplierOrderDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.Supplier_Order );
        supplierOrderDetailDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.Supplier_Order_Detail );
        convertor=new Convertor();
    }


    @Override
    public String GetLastMalibanOrderID() {
        return supplierOrderDAO.findLastMalibanOrderId();
    }

    @Override
    public boolean insertAllDataIntoMalibanOrdersDB(List<SupplierOrderDetailDTO> rarray, SupplierOrderDTO ot) {
        try {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (supplierOrderDAO.save(convertor.toSupplierOrder(ot))) {
                if (supplierOrderDetailDAO.saveOrderDetailArray(rarray.stream().map(supplierOrderDetail -> convertor.toOrderDetail(supplierOrderDetail)).collect(Collectors.toList()))) {
                    try {
                        connection.commit();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                }
            }
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return false;
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
