package lk.ijse.gdse.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Manage {
    private String manageId;
    private String inventoryId;
    private String supplierId;
    private String orderId;
    private int quantity;
    private String supplierName;
    private String supplierContactName;


}
