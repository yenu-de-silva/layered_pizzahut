package lk.ijse.gdse.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class DepartmentTM {
    private String department_id;
    private String department_name;
    private String manager_name;
    private int number_of_employees;
    private String description;
}
