package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.OrderBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.OrderDAO;
import lk.ijse.gdse.dto.OrderDTO;
import lk.ijse.gdse.dto.tm.CustomerTM;
import lk.ijse.gdse.dto.tm.OrderTM;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {

     OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);


    @Override
    public List<OrderTM> getAllOrders() throws SQLException, ClassNotFoundException {
        List<Order> allData = orderDAO.getAll();

        List<OrderTM> orderTMList = new ArrayList<>();

        for (Order order : allData) {
            CustomerTM orderTM = new CustomerTM(
                    order.getOrder_id(),
                    order.getOrder_date(),
                    order.getStatus(),
                    order.getTotal_price(),
                    order.getCustomer_id()
            );
            orderTMList.add(orderTM);
        }
        return orderTMList;
    }
    @Override
    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Order order = new Order();
        order.setOrder_id(orderDTO.getOrder_id());
        order.setOrder_date(orderDTO.getOrder_date());
        order.setStatus(orderDTO.getStatus());
        order.setTotal_price(orderDTO.getTotal_price());
        order.setCustomer_id(orderDTO.getCustomer_id());

        return orderDAO.save((Order) order);
    }

    @Override
    public boolean saveOrder(OrderDTO order) {
        return false;
    }

    @Override
    public String getNextOrderId() {
        return "";
    }

}

