package lk.ijse.gdse.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String user_id;
    private String username;
    private String password;
    private String email;
    private String role;
}
