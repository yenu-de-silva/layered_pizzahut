package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.SalaryDTO;
import lk.ijse.gdse.dto.tm.SalaryTM;

import java.sql.SQLException;
import java.util.List;

public interface SalaryBO extends SuperBO {
    List<SalaryTM> getAllSalary() throws SQLException, ClassNotFoundException;

    boolean save(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException;
}
