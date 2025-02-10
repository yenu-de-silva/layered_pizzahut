package lk.ijse.gdse.entity;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Delivery {

    private String delivery_id;
    private String order_id;
    private String delivery_address;
    private Date delivery_date;
    private String delivery_status;
    private String employee_id;

}
