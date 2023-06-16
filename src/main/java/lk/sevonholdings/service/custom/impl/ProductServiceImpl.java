package lk.sevonholdings.service.custom.impl;

import lk.sevonholdings.dao.DaoFactory;
import lk.sevonholdings.dao.DaoTypes;
import lk.sevonholdings.dao.custom.ProductDAO;
import lk.sevonholdings.db.DBConnection;
import lk.sevonholdings.dto.ProductDTO;
import lk.sevonholdings.service.custom.ProductService;
import lk.sevonholdings.service.exception.DuplicateException;
import lk.sevonholdings.service.exception.NotFoundException;
import lk.sevonholdings.service.util.Convertor;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final Convertor convertor;
    private final Connection connection;

    public ProductServiceImpl() {
        connection= DBConnection.getDbConnection().getConnection();
        productDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.PRODUCT );
        convertor=new Convertor();
    }
    @Override
    public boolean saveProduct(ProductDTO productDTO) throws DuplicateException {
        //need to do some business validations
        if(productDAO.existByPk(productDTO.getBiscuitsNo())) throw new DuplicateException("Product already saved!");

        return productDAO.save(convertor.toProduct(productDTO));
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productDAO.findAll().stream().map(product -> convertor.fromProduct(product)).collect(Collectors.toList());
    }

    @Override
    public String findLastProductNo() {
        return productDAO.findLastProductNo();
    }

    @Override
    public boolean deleteProductPK(String pk) {
        if (!productDAO.existByPk(pk)) throw new NotFoundException("Product not found");
        return productDAO.deleteByPk(pk);
    }

    @Override
    public boolean updateProductPrices(String bid, String supprice, String disprice, String mrp) {
        if (!productDAO.existByPk(bid)) throw new NotFoundException("Product not found");
        return productDAO.updateProductPrices(bid, supprice, disprice, mrp);
    }
}
