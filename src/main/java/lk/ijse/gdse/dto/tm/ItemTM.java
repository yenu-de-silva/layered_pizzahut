package lk.ijse.gdse.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ItemTM {
    private String item_id;
    private String name;
    private int quantity;
    private double price;

}
