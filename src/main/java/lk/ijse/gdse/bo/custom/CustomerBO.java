package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.tm.CustomerTM;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;

    List<CustomerTM> getAllCustomers() throws SQLException, ClassNotFoundException;
}
