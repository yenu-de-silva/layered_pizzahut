package lk.ijse.gdse.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class SupplierDTO {
    private String supplier_id;
    private String supplier_name;
    private String contact_name;
    private String contact_number;
    private String address;

}
