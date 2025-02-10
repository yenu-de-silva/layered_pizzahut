package lk.ijse.gdse.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetails {
    private String orderDetail_id;
    private String order_id;
    private String product_id;
    private int quantity;
    private double price;

}
