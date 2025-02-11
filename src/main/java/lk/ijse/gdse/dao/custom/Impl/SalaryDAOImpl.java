package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.SalaryDAO;
import lk.ijse.gdse.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {

    @Override
    public List<Salary> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from salary");

        ArrayList<Salary> salaryDTOS = new ArrayList<>();

        while (rst.next()) {
            Salary salaryDTO = new Salary(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(7)
            );
            salaryDTOS.add(salaryDTO);
        }
        return salaryDTOS;
    }

    @Override
    public boolean save(Salary dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salary (salary_id, employee_id, basic_salary, bonus, deductions, net_salary, salary_date) VALUES (?, ?, ?, ?, ?, ?, ?)",dto.getSalary_id(),dto.getEmployee_id(),dto.getBasic_salary(),dto.getBonus(),dto.getDeductions(),dto.getNet_salary(),dto.getSalary_date(),dto.getNet_salary(),dto.getSalary_date());
    }

    @Override
    public boolean update(Salary dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("");
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
        return Integer.parseInt("");
    }

    @Override
    public Salary search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
