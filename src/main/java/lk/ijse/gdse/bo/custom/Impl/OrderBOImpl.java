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
            OrderTM orderTM = new OrderTM(
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
    public boolean saveOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(order.getOrder_id(),order.getOrder_date(),order.getStatus(),order.getTotal_price(),order.getCustomer_id()));
    }

    @Override
    public int getNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

}

