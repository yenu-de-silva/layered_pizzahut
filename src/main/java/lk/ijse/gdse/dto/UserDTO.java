package lk.ijse.gdse.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO  {
    private String user_id;
    private String username;
    private String password;
    private String email;
    private String role;
}
