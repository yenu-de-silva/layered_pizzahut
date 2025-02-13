package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.dto.OrderDetailsDTO;
import lk.ijse.gdse.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO  {
    @Override
    public List<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orderdetails");

        ArrayList<OrderDetails> orderDetailsDTOS = new ArrayList<>();

        while (rst.next()) {
            OrderDetails orderDetailsDTO = new OrderDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)
             );
            orderDetailsDTOS.add(orderDetailsDTO);
        }
        return orderDetailsDTOS;
    }

    @Override
    public boolean save(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into orderdetails(order_details_id , order_id, product_id, quantity, price) values (?,?,?,?,?)", dto.getOrderDetail_id(),dto.getOrder_id(),dto.getProduct_id(),dto.getQuantity(),dto.getPrice());
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update orderdetails set order_id=?, product_id=?, quantity=?, price=? where order_details_id=?",dto.getOrder_id(),dto.getProduct_id(),dto.getQuantity(),dto.getPrice(),dto.getOrderDetail_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from orderdetails where order_details_id=?",id);
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT max(order_details_id) from orderdetails");

        if (rst.next()) {
            int lastId = rst.getInt(1);
            return lastId + 1;
        }
        return 1;
    }

    @Override
    public OrderDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
