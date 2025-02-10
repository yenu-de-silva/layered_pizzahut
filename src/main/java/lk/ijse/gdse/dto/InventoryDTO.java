package lk.ijse.gdse.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryDTO {
    private String inventory_id;
    private String product_id;
    private String supplier_id;
    private Integer quantity;
    private String last_updated;


    public InventoryDTO(String text, String text1, String text2, int quantity, String text3) {
    }
}
