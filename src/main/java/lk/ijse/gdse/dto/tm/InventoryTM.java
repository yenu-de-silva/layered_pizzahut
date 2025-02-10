package lk.ijse.gdse.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class InventoryTM {
    private String inventory_id;
    private String product_id;
    private String supplier_id;
    private Integer quantity;
    private String last_updated;
}
