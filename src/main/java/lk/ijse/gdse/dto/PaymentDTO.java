package lk.ijse.gdse.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class
PaymentDTO {
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

    public PaymentDTO(int anInt, int anInt1, String string, Date date, double aDouble, int anInt2, String string1, int anInt3, String string2, String string3, String string4) {
    }
}
