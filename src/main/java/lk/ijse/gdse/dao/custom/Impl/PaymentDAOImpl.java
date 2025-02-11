package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.custom.PaymentDAO;
import lk.ijse.gdse.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        return Integer.parseInt("1");
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
