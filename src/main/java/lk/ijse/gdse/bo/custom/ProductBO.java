package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.ProductDTO;
import lk.ijse.gdse.dto.tm.ProductTM;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO extends SuperBO {

    List<ProductTM> getAllProducts() throws SQLException, ClassNotFoundException;

    boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    boolean updateproduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    boolean deleteproduct(String productId) throws SQLException, ClassNotFoundException;
}
