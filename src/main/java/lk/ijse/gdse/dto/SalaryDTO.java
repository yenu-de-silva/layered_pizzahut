package lk.ijse.gdse.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalaryDTO {
    private String salary_id;
    private String employee_id;
    private Double basic_salary;
    private Double bonus;
    private Double deductions;
    private Double net_salary;
    private String salary_date;


}
