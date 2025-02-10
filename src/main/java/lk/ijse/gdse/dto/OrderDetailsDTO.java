package lk.ijse.gdse.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailsDTO {
    private String orderDetail_id;
    private String order_id;
    private String product_id;
    private int quantity;
    private double price;

}
