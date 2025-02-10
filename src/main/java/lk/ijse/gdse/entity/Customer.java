package lk.ijse.gdse.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Customer extends Order {
    private String customerId;
    private String name;
    private String contact;
    private String email;
    private String address;

}