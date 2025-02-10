package lk.ijse.gdse.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupplierTM {
    private String supplier_id;
    private String supplier_name;
    private String contact_name;
    private String contact_number;
    private String address;
}