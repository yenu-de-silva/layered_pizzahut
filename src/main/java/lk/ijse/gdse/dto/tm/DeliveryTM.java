package lk.ijse.gdse.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryTM {
    private String delivery_id;
    private String order_id;
    private String delivery_Address;
    private Date delivery_Date;
    private String delivery_status;
    private String employee_id;

    }



