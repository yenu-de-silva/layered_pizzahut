package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.EmployeeBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dao.custom.EmployeeDAO;
import lk.ijse.gdse.dto.DepartmentDTO;
import lk.ijse.gdse.dto.EmployeeDTO;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Department;
import lk.ijse.gdse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);


    @Override
    public List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> allData = employeeDAO.getAll();

        List<EmployeeDTO> employeeTMList = new ArrayList<>();

        for (Employee employee : allData) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                   employee.getEmployee_id(),
                    employee.getPosition(),
                    employee.getHire_date(),
                    employee.getDepartment_id(),
                    employee.getName()
            );
            employeeTMList.add(employeeDTO);
        }
        return employeeTMList;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO newEmployee) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee();
        employee.setEmployee_id(newEmployee.getEmployee_id());
        employee.setPosition(newEmployee.getPosition());
        employee.setHire_date(newEmployee.getHire_date());
        employee.setDepartment_id(newEmployee.getDepartment_id());
        employee.setName(newEmployee.getName());

        return employeeDAO.save(employee);
    }

    @Override
    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employeeId);

    }

    @Override
    public boolean updateEmployee(EmployeeDTO updatedEmployee) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee();
           employee.setEmployee_id(updatedEmployee.getEmployee_id());
           employee.setPosition(updatedEmployee.getPosition());
           employee.setHire_date(updatedEmployee.getHire_date());
           employee.setDepartment_id(updatedEmployee.getDepartment_id());
           employee.setName(new String(updatedEmployee.getName()));


        return employeeDAO.update(employee);
    }

}


