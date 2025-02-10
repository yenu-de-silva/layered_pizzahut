package lk.ijse.gdse.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DashBoardDTO {

    private String totalOrders;
    private double totalRevenue;
    private int totalCustomers;
    private int pendingOrders;
    private int completedOrders;


}
