package lk.ijse.gdse.entity;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DashBoard {

    private String totalOrders;
    private double totalRevenue;
    private int totalCustomers;
    private int pendingOrders;
    private int completedOrders;


}
