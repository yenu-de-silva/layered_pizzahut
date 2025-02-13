package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order> {

    List<Order> getAll() throws SQLException, ClassNotFoundException;

}
