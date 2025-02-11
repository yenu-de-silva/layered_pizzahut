package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.OrderDTO;
import lk.ijse.gdse.dto.tm.OrderTM;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    List<OrderTM> getAllOrders() throws SQLException, ClassNotFoundException;

    boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDTO order);

    String getNextOrderId();
}
