package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.ProductBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.ProductDAO;
import lk.ijse.gdse.dto.ProductDTO;
import lk.ijse.gdse.dto.tm.ProductTM;
import lk.ijse.gdse.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);

    public  boolean updateproduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setProduct_id(productDTO.getProduct_id());
        product.setProduct_name(productDTO.getProduct_name());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setInventory_count(productDTO.getInventory_count());

        return productDAO.update(product);
    }


    public boolean deleteproduct(String product_id) throws SQLException, ClassNotFoundException {
        return productDAO.delete(product_id);
    }


    @Override
    public ArrayList<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException {
        List<Product> allData = productDAO.getAll();

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : allData) {
            ProductDTO productDTO = new ProductDTO(
                    product.getProduct_id(),
                    product.getProduct_name(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getCategory(),
                    product.getInventory_count()
            );
            productDTOList.add(productDTO);
        }
        return (ArrayList<ProductDTO>) productDTOList;
    }

    public boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setProduct_id(productDTO.getProduct_id());
        product.setProduct_name(productDTO.getProduct_name());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setInventory_count(productDTO.getInventory_count());

        return productDAO.save(product);
    }



    public int getNextDepartmentId() throws SQLException, ClassNotFoundException {
        return productDAO.generateNewId();
    }



    public List<ProductTM> getProduct_id() throws SQLException, ClassNotFoundException {
        List<Product> allData = productDAO.getAll();

        List<ProductTM> productTMList = new ArrayList<>();

        for (Product product : allData) {
                ProductTM productTM = new ProductTM(
                        product.getProduct_id(),
                        product.getProduct_name(),
                        product.getPrice(),
                        product.getDescription(),
                        product.getCategory(),
                        product.getInventory_count()
            );
            productTMList.add(productTM);
        }
        return productTMList;
    }

}
