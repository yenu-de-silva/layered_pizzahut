package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Delivery;

import java.sql.SQLException;
import java.util.List;

public interface DeliveryDAO extends CrudDAO<Delivery> {
    List<Delivery> getAllDelivery() throws SQLException, ClassNotFoundException;
}
