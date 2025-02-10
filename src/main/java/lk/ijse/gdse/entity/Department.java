package lk.ijse.gdse.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Department {
    private String department_id;
    private String department_name;
    private String manager_name;
    private int number_of_employees;
    private String description;

    public Department(String text, String text1, String text2, String text3, String text4) {
    }
}