package lk.ijse.gdse.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerFeedbackTM {
    private String feedback_id;
    private String customer_id;
    private String feedback_text;
    private int rating;
    private Date feedback_date;
}
