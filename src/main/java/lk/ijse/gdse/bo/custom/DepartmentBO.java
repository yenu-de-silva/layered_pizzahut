package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.DepartmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DepartmentBO extends SuperBO {

    boolean save(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String departmentIdFieldText) throws SQLException, ClassNotFoundException;

    boolean updateDepartment(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException;

    ArrayList<DepartmentDTO> getAllDepartments() throws SQLException, ClassNotFoundException;

    int getNextDepartmentId() throws SQLException, ClassNotFoundException;
}
