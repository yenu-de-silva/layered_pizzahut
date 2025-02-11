package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.CustomerDAO;
import lk.ijse.gdse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from customer");

        ArrayList<Customer> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            Customer customerDTO = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into customer values (?,?,?,?,?)",dto.getCustomerId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getEmail());
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update customer set name=?, contact=?, email=?,address=? where customer_id=?",dto.getName(),dto.getContact(),dto.getEmail(),dto.getAddress(),dto.getCustomerId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from customer where customer_id=?",id);
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("select customer_id from customer order by customer_id desc limit 1");
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
