package lk.ijse.gdse.bo.custom.Impl;

import lk.ijse.gdse.bo.custom.SalaryBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.SalaryDAO;
import lk.ijse.gdse.dto.SalaryDTO;
import lk.ijse.gdse.dto.tm.SalaryTM;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {

    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SALARY);


    @Override
    public List<SalaryTM> getAllSalary() throws SQLException, ClassNotFoundException {
        List<Salary> allData = salaryDAO.getAll();

        List<SalaryTM> salaryTMList = new ArrayList<>();

        for (Salary salary : allData) {
            SalaryTM salaryTM = new SalaryTM(
                    salary.getSalary_id(),
                    salary.getEmployee_id(),
                    salary.getBasic_salary(),
                    salary.getBonus(),
                    salary.getDeductions(),
                    salary.getNet_salary(),
                    salary.getSalary_date()
            );
            salaryTMList.add(salaryTM);
        }
        return salaryTMList;
    }

    @Override
    public boolean save(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        Salary salary = new Salary();
        salary.setSalary_id(salaryDTO.getSalary_id());
        salary.setEmployee_id(salaryDTO.getEmployee_id());
        salary.setBasic_salary(salaryDTO.getBasic_salary());
        salary.setBonus(salaryDTO.getBonus());
        salary.setDeductions(salaryDTO.getDeductions());
        salary.setNet_salary(salaryDTO.getNet_salary());
        salary.setSalary_date(salaryDTO.getSalary_date());

        return salaryDAO.save(salary);
    }
}

