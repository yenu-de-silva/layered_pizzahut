package lk.ijse.gdse.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RatingDTO {
    private String ratingId;
    private String customerId;
    private String orderId;
    private int ratingValue;
    private String comments;

}
