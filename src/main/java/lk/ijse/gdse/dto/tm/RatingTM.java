package lk.ijse.gdse.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RatingTM {
    private String ratingId;
    private String customerId;
    private String orderId;
    private int ratingValue;
    private String comments;
}