package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.DepartmentDAO;
import lk.ijse.gdse.entity.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public List<Department> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM department");
        ArrayList<Department> departmentDTOS = new ArrayList<>();

        while (rst.next()) {
            Department departmentDTO = new Department(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5)
            );
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }

    @Override
    public boolean save(Department dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO department (department_id, department_name, manager_name, number_of_employees, description) VALUES (?, ?, ?, ?, ?)",dto.getDepartment_id(),dto.getDepartment_name(),dto.getManager_name(),dto.getNumber_of_employees(),dto.getDescription());
    }

    @Override
    public boolean update(Department dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Department \n" +
                "SET \n" +
                "    department_name = ?, \n" +
                "    manager_name = ?, \n" +
                "    number_of_employees = ?, \n" +
                "    description = ? \n" +
                "WHERE \n" +
                "    department_id = ?;\n",dto.getDepartment_name(),dto.getManager_name(),dto.getNumber_of_employees(),dto.getDescription(),dto.getDepartment_id());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM department WHERE department_id = ?", id);
    }

    @Override
    public int generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT department_id FROM department ORDER BY department_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            int newIdIndex = Integer.parseInt(lastId) + 1;
            return Integer.parseInt(String.valueOf(newIdIndex));
        }
        return Integer.parseInt("1");    }

    @Override
    public Department search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
