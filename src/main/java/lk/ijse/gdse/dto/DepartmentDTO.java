package lk.ijse.gdse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class DepartmentDTO {
    private String department_id;
    private String department_name;
    private String manager_name;
    private int number_of_employees;
    private String description;

    public DepartmentDTO(String text, String text1, String text2, String text3, String text4) {
    }
}