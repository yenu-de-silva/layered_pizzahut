package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.OrderBO;
import lk.ijse.gdse.dto.OrderDTO;
import lk.ijse.gdse.dto.tm.OrderTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    private final OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);
    public Label orderDate;

    public Label txtorderdate;

    public TextField txtcustomerId1;

    public DatePicker datetxt;

    public TextField txtcustomerId;

    public TextField txttotalprice;

    public TextField txtcustomerId2;

    public TableView tblorder;

    public TableColumn colorderId;

    public TableColumn colorderDate;

    public TableColumn colcustomerId;

    public Button btnrest;

    public Button btnplaceorder;

    @FXML
    private Label lblOrderId;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private TextField txtStatus;

    @FXML
    private DatePicker dpOrderDate;

    @FXML
    private TableView<OrderTM> tblOrder;

    @FXML
    private TableColumn<OrderTM, String> colOrderId;

    @FXML
    private TableColumn<OrderTM, LocalDate> colOrderDate;

    @FXML
    private TableColumn<OrderTM, String> colCustomerId;

    @FXML
    private TableColumn<OrderTM, Double> colTotal;

    @FXML
    private TableColumn<OrderTM, String> colStatus;

    @FXML
    private Button btnPlaceOrder, btnReset;

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws ClassNotFoundException {
        try {
            String orderId = lblOrderId.getText();
            String customerId = txtCustomerId.getText();
            LocalDate orderDate = dpOrderDate.getValue();
            double totalPrice = Double.parseDouble(txtTotalPrice.getText());
            String status = txtStatus.getText();

            if (orderId.isEmpty() || customerId.isEmpty() || orderDate == null || status.isEmpty()) {
                showAlert("Validation Error", "Please fill in all fields correctly.", Alert.AlertType.WARNING);
                return;
            }

            OrderDTO order = new OrderDTO(orderId, orderDate.toString(), status, String.valueOf(totalPrice), customerId);
            boolean isSaved = orderBO.saveOrder(order);

            if (isSaved) {
                showAlert("Success", "Order placed successfully!", Alert.AlertType.INFORMATION);
                loadOrders();
                resetForm();
            } else {
                showAlert("Error", "Failed to place the order.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Please enter a valid total price.", Alert.AlertType.WARNING);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while saving the order.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        resetForm();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colorderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colorderDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        colcustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            loadOrders();
            generateOrderId();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load orders.", Alert.AlertType.ERROR);
        }
    }

    private void resetForm() {
        lblOrderId.setText("");
        txtCustomerId.clear();
        dpOrderDate.setValue(null);
        txtTotalPrice.clear();
        txtStatus.clear();
    }

    private void loadOrders() throws SQLException, ClassNotFoundException {
        ObservableList<OrderTM> orderTMS = FXCollections.observableArrayList();
        List<OrderTM> sList = orderBO.getAllOrders();
        orderTMS.addAll(sList);
        tblOrder.setItems(orderTMS);
    }

    private void generateOrderId() {
        String nextOrderId = orderBO.getNextOrderId();
        lblOrderId.setText(nextOrderId);
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}