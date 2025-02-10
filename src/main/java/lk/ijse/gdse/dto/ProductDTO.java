package lk.ijse.gdse.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    private String product_id;
    private String product_name;
    private double price;
    private String description;
    private String category;
    private int inventory_count;

}
