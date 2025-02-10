package lk.ijse.gdse.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EmployeeTM {
    private String employee_id;
    private String position;
    private Date hire_date;
    private String department_id;
    private String name;

}