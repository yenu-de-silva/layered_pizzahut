package lk.ijse.gdse.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderTM {
    private int order_id;
    private String order_date;
    private String status;
    private String total_price;
    private String customer_id;


}