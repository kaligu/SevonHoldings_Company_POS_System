package lk.sevonholdings.dao.custom.impl;

import lk.sevonholdings.dao.custom.ProductDAO;
import lk.sevonholdings.dao.exception.ConstraintViolationException;
import lk.sevonholdings.dao.util.DBUtil;
import lk.sevonholdings.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO  {
    private final Connection connection;
    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Product product) {
        try {
            boolean isadded = (DBUtil.executeUpdate("INSERT INTO Biscuits VALUES(?,?,?,?,?,?,?,?,?)",
                    product.getBiscuitsNo(),
                    product.getImagePath(),
                    product.getBiscuitRange(),
                    product.getBiscuitBrand(),
                    product.getSize(),
                    product.getCBFVolume(),
                    product.getSupplierPrice(),
                    product.getDistributorPrice(),
                    product.getMRPrice()
            ));{
                return isadded;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConstraintViolationException(e);  //throw runtime exception
        }
    }

    @Override
    public Product update(Product entity) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        ResultSet rst = null;
        try {
            rst = DBUtil.executeQuery("SELECT * FROM Biscuits");
            return getMemberList(rst);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> getMemberList(ResultSet result) throws SQLException {
        List<Product> productList= new ArrayList<>();
        while (result.next()) {
            productList.add( new Product(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    Double.parseDouble(result.getString(6)),
                    Double.parseDouble(result.getString(7)),
                    Double.parseDouble(result.getString(8)),
                    Double.parseDouble(result.getString(9)
                    )));
        }
        return productList;
    }

    @Override
    public boolean deleteByPk(String pk) {
        try {
            return DBUtil.executeUpdate("DELETE FROM Biscuits WHERE Biscuit_No=?",pk);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public Optional findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String Biscuit_No){
        ResultSet rst = null;
        try {
            rst = DBUtil.executeQuery("SELECT * FROM Biscuits WHERE Biscuit_No=?", Biscuit_No);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findLastProductNo() {
        ResultSet result = null;
        try {
            result = DBUtil.executeQuery("SELECT Biscuit_No FROM Biscuits ORDER BY Biscuit_No DESC LIMIT 1;");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String id = null;
        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new ConstraintViolationException(e);
            }
            try {
                id=result.getString(1);
            } catch (SQLException e) {
                throw new ConstraintViolationException(e);
            }
        }
        return id;
    }

    @Override
    public boolean updateProductPrices(String bid , String supprice , String disprice , String mrp) {
        Double ddisprice = Double.parseDouble(disprice);
        Double dsupprice = Double.parseDouble(supprice);
        Double dmrp = Double.parseDouble(mrp);
        try {
            return DBUtil.executeUpdate("UPDATE Biscuits SET SupplierPrice='"+dsupprice+"' , DistributorPrice='"+ddisprice+"', MRPrice='"+dmrp+"' WHERE Biscuit_No='"+bid+"' ;");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConstraintViolationException(e);
        }
    }
}
