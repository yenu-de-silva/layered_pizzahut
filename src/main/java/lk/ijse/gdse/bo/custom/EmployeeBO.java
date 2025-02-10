package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO newEmployee) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO updatedEmployee) throws SQLException, ClassNotFoundException;
}
