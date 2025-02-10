package lk.ijse.gdse.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private String product_id;
    private String product_name;
    private double price;
    private String description;
    private String category;
    private int inventory_count;

}
