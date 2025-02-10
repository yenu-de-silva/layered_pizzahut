package lk.ijse.gdse.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerFeedback {
    private String feedback_id;
    private String customer_id;
    private String feedbackText;
    private Integer rating;
    private Date feedbackDate;


    public CustomerFeedback(int feedbackId, int customerId, String feedbackText, int rating, Date feedbackDate) {
    }
}
