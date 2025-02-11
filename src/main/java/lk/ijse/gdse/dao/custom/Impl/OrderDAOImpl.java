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
        ResultSet rst = SQLUtil.execute("SELECT * from order");

        ArrayList<Order> orderDTOS = new ArrayList<>();

        while (rst.next()) {
            Order orderDTO = new Order(
                    rst.getString(1),
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
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into orders(order_id, customer_id, order_date, total_price, status) values (?,?,?,?,?)");
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update order set name=?, contact=?, email=?,address=? where customer_id=?");
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT max(order_id) from orders");
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
