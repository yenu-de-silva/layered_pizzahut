package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.OrderDetailsBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.DepartmentDAO;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsBO orderDetailsBO = (OrderDetailsBO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAILS);

}
