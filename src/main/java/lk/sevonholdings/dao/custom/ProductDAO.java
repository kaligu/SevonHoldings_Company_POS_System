package lk.sevonholdings.dao.custom;

import lk.sevonholdings.dao.CrudDAO;
import lk.sevonholdings.entity.Product;

public interface ProductDAO extends CrudDAO<Product,String> {
    String findLastProductNo();
    boolean updateProductPrices(String bid , String supprice , String disprice , String mrp);

}
