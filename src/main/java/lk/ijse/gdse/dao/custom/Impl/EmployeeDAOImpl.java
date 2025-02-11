package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.EmployeeDAO;
import lk.ijse.gdse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {


    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");

        ArrayList<Employee> employeeDTOS = new ArrayList<>();

        while (rst.next()) {
            Employee employeeDTO = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into employee(employee_id, position , hire_date , department_id , name) values (?,?,?,?,?)",dto.getEmployee_id(),dto.getPosition(),dto.getHire_date(),dto.getDepartment_id(),dto.getName());
    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update employee set name=?, department_id=?, position=? , hire_date=? where employee_id=?",dto.getName(),dto.getDepartment_id(),dto.getPosition(),dto.getHire_date(),dto.getEmployee_id());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from employee where employee_id = ?", id);
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT max(employee_id) from employee");
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    //after pushed
}
