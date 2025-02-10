package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.custom.SalaryDAO;
import lk.ijse.gdse.entity.Salary;

import java.sql.SQLException;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {

    @Override
    public List<Salary> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(Salary dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Salary dto) throws SQLException, ClassNotFoundException {
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
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Salary search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
