package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dto.DeliveryDTO;
import lk.ijse.gdse.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryDAO extends CrudDAO<Delivery> {
    ArrayList<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException;
}
