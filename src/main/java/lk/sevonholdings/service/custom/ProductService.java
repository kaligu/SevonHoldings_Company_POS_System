package lk.sevonholdings.service.custom;

import lk.sevonholdings.dto.ProductDTO;
import lk.sevonholdings.service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    List<ProductDTO> findAllProducts();
    String findLastProductNo();

    boolean saveProduct(ProductDTO productDTO);
    boolean deleteProductPK(String pk);
    boolean updateProductPrices(String bid , String supprice , String disprice , String mrp);
}
