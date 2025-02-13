package lk.ijse.gdse.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDTO {
    private int order_id;
    private String order_date;
    private String status;
    private String total_price;
    private String customer_id;



}
