package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.PaymentBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.EmployeeDAO;
import lk.ijse.gdse.dao.custom.PaymentDAO;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);



}
