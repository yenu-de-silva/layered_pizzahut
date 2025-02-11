package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.DepartmentBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.DepartmentDAO;
import lk.ijse.gdse.dto.DepartmentDTO;
import lk.ijse.gdse.entity.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentBOImpl implements DepartmentBO {

    DepartmentDAO departmentDAO = (DepartmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DEPARTMENT);

    @Override
    public boolean save(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException {
        Department department = new Department();
        department.getDepartment_id();
        department.setDepartment_name(departmentDTO.getDepartment_name());
        department.setManager_name(departmentDTO.getManager_name());
        department.setNumber_of_employees(departmentDTO.getNumber_of_employees());
        department.setDescription(departmentDTO.getDescription());

        return departmentDAO.save(department);


    }

    @Override
    public boolean deleteCustomer(String departmentIdFieldText) throws SQLException, ClassNotFoundException {
        return departmentDAO.delete(departmentIdFieldText);
    }

    @Override
    public boolean updateDepartment(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException {
        Department department = new Department();
        department.getDepartment_id();
        department.setDepartment_name(departmentDTO.getDepartment_name());
        department.setManager_name(departmentDTO.getManager_name());
        department.setNumber_of_employees(departmentDTO.getNumber_of_employees());
        department.setDescription(departmentDTO.getDescription());

        return departmentDAO.update(department);
    }

    @Override
    public ArrayList<DepartmentDTO> getAllDepartments() throws SQLException, ClassNotFoundException {
        List<Department> allData = departmentDAO.getAll();

        List<DepartmentDTO> departmentTMList = new ArrayList<>();

        for (Department department : allData) {
            DepartmentDTO departmentDTO = new DepartmentDTO(
                    department.getDepartment_id(),
                    department.getDepartment_name(),
                    department.getManager_name(),
                    department.getNumber_of_employees(),
                    department.getDescription()
            );
            departmentTMList.add(departmentDTO);
        }
        return (ArrayList<DepartmentDTO>) departmentTMList;
    }

    @Override
    public int getNextDepartmentId() throws SQLException, ClassNotFoundException {
        return departmentDAO.generateNewId();
    }
}

