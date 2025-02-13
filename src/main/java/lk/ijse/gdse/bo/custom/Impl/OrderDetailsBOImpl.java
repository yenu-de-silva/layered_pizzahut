package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.OrderDetailsBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.DepartmentDAO;
import lk.ijse.gdse.dao.custom.OrderDAO;
import lk.ijse.gdse.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.dto.OrderDetailsDTO;
import lk.ijse.gdse.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    @Override
    public boolean deleteOrderDetails(String orderDetailId) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.delete(orderDetailId);
    }

    @Override
    public boolean updateOrderDetails(OrderDetailsDTO orderDetailsDto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.update(new OrderDetails(orderDetailsDto.getOrderDetail_id(),orderDetailsDto.getOrder_id(),orderDetailsDto.getProduct_id(),orderDetailsDto.getQuantity(),orderDetailsDto.getPrice()));
    }

    @Override
    public boolean saveOrderDetails(OrderDetailsDTO orderDetailsDto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.save(new OrderDetails(orderDetailsDto.getOrderDetail_id(),orderDetailsDto.getOrder_id(),orderDetailsDto.getProduct_id(),orderDetailsDto.getQuantity(),orderDetailsDto.getPrice()));
    }

    @Override
    public List<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
        List<OrderDetails> orderDetails = orderDetailsDAO.getAll();
        for (OrderDetails orderDetail : orderDetails) {
            orderDetailsDTOS.add(new OrderDetailsDTO(orderDetail.getOrderDetail_id(),orderDetail.getOrder_id(),orderDetail.getProduct_id(),orderDetail.getQuantity(),orderDetail.getPrice()));
        }
        return orderDetailsDTOS;
    }
}
