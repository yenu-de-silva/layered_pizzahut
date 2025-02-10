package lk.ijse.gdse.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

    public class ManageTM {
    private String manage_id;
    private String inventory_id;
    private String supplier_id;
    private String order_id;
    private int quantity;
    private String supplier_name;
    private String supplier_contact_name;

}