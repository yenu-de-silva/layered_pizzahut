package lk.ijse.gdse.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentTM {
    private String payment_id;
    private String order_id;
    private String payment_method;
    private String payment_date;
    private double amount;
    private String customer_id;
    private String order_name;
    private int quantity;
    private String payment_method1;
    private String payment_method2;
    private String payment_method3;
}