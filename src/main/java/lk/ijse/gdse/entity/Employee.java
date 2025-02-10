package lk.ijse.gdse.entity;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String employee_id;
    private String position;
    private Date hire_date;
    private String department_id;
    private String name;

}
