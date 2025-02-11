package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.OrderDetailsBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.DepartmentDAO;
import lk.ijse.gdse.dto.OrderDetailsDTO;

import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsBO orderDetailsBO = (OrderDetailsBO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAILS);

    @Override
    public boolean deleteOrderDetails(String orderDetailId) {
        return false;
    }

    @Override
    public boolean updateOrderDetails(OrderDetailsDTO orderDetailsDto) {
        return false;
    }

    @Override
    public boolean saveOrderDetails(OrderDetailsDTO orderDetailsDto) {
        return false;
    }

    @Override
    public List<OrderDetailsDTO> getAllOrderDetails() {
        return List.of();
    }
}
