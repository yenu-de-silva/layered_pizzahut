package lk.ijse.gdse.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Rating {
    private String ratingId;
    private String customerId;
    private String orderId;
    private int ratingValue;
    private String comments;

}
