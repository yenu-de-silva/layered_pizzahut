package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.CustomerBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.CustomerDAO;
import lk.ijse.gdse.dao.custom.Impl.CustomerDAOImpl;
import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.tm.CustomerTM;
import lk.ijse.gdse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setContact(customerDTO.getContact());
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());

        return customerDAO.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setContact(customerDTO.getContact());
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());

        return customerDAO.update(customer);
    }

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId);
    }

    @Override
    public List<CustomerTM> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> allData = customerDAO.getAll();

        List<CustomerTM> customerTMList = new ArrayList<>();

        for (Customer customer : allData) {
            CustomerTM customerTM = new CustomerTM(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getEmail(),
                    customer.getAddress()
            );
            customerTMList.add(customerTM);
        }
        return customerTMList;
    }
}
