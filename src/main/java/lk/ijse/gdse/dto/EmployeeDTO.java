package lk.ijse.gdse.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private String employee_id;
    private String position;
    private Date hire_date;
    private String department_id;
    private String name;

}
