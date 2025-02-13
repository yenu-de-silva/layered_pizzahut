package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsBO extends SuperBO {

    boolean deleteOrderDetails(String orderDetailId) throws SQLException, ClassNotFoundException;

    boolean updateOrderDetails(OrderDetailsDTO orderDetailsDto) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetails(OrderDetailsDTO orderDetailsDto) throws SQLException, ClassNotFoundException;

    List<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;
}
