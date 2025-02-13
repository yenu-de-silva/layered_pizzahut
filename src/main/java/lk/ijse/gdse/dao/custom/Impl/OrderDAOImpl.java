package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.OrderDAO;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from orders");

        ArrayList<Order> orderDTOS = new ArrayList<>();

        while (rst.next()) {
            Order orderDTO = new Order(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into orders(order_id, order_date, status, total_price,customer_id) values (?,?,?,?,?)",order.getOrder_id(),order.getOrder_date(),order.getStatus(),order.getTotal_price(),order.getCustomer_id());
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update order set  order_date=?, status=?, total_price=?, customer_id=? where order_id=?",dto.getOrder_date(),dto.getStatus(),dto.getTotal_price(),dto.getCustomer_id(),dto.getOrder_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from orders where order_id=?",id);
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT max(order_id) from orders");

        if (rst.next()) {
            int lastId = rst.getInt(1);
            return lastId + 1;
        }
        return 1;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
