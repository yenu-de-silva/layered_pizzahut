package lk.ijse.gdse.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SalaryTM {
    private String salary_id;
    private String employee_id;
    private Double basic_salary;
    private Double bonus;
    private Double deductions;
    private Double net_salary;
    private String salary_date;

}