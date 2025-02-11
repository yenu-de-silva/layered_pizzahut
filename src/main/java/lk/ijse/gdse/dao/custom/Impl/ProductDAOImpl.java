package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.ProductDAO;
import lk.ijse.gdse.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from product");

        ArrayList<Product> productDTOS = new ArrayList<>();

        while (rst.next()) {
            Product productDTO = new Product(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6)
            );
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public boolean save(Product dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into product(product_id, Product_name, price ,description , category, inventory_count) values (?,?,?,?,?,?)",dto.getProduct_id(),dto.getProduct_name(),dto.getPrice(),dto.getDescription(),dto.getCategory(),dto.getInventory_count(),dto.getInventory_count());
    }

    @Override
    public boolean update(Product dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into product(product_id, Product_name, price ,description , category, inventory_count) values (?,?,?,?,?,?)",dto.getProduct_id(),dto.getProduct_name(),dto.getPrice(),dto.getDescription(),dto.getCategory(),dto.getInventory_count(),dto.getInventory_count());
    }


    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from product where product_id=?",id);
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("select max(product_id) from product");
    }

    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
