package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.OrderDetailsDTO;

import java.util.List;

public interface OrderDetailsBO extends SuperBO {

    boolean deleteOrderDetails(String orderDetailId);

    boolean updateOrderDetails(OrderDetailsDTO orderDetailsDto);

    boolean saveOrderDetails(OrderDetailsDTO orderDetailsDto);

    List<OrderDetailsDTO> getAllOrderDetails();
}
