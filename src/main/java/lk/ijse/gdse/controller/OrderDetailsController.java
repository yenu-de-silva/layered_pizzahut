package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.DeliveryBO;
import lk.ijse.gdse.bo.custom.OrderDetailsBO;
import lk.ijse.gdse.dto.OrderDetailsDTO;
import lk.ijse.gdse.dto.tm.OrderDetailsTM;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class OrderDetailsController {

    OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDERDETAILS);

    @FXML
    private TableColumn<OrderDetailsTM, String> ColOrderDetailsId;
    @FXML
    private TableColumn<OrderDetailsTM, String> ColOrderId;
    @FXML
    private TableColumn<OrderDetailsTM, String> ColItemId;
    @FXML
    private TableColumn<OrderDetailsTM, Integer> ColQty;
    @FXML
    private TableColumn<OrderDetailsTM, Double> ColUnitPrice;
    @FXML
    private TextField txtOrderDetailsId;
    @FXML
    private TextField txtOrderId;
    @FXML
    private TextField txtItemId;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private TableView<OrderDetailsTM> tblOrderdetails;


    @FXML
    public void initialize() {
        setCellValueFactory();
        loadOrderDetailsTableData();
    }

    private void setCellValueFactory() {
        ColOrderDetailsId.setCellValueFactory(new PropertyValueFactory<>("orderDetail_id"));
        ColOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        ColItemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        ColQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ColUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    private void loadOrderDetailsTableData() {
        ObservableList<OrderDetailsTM> orderDetailsTMList = FXCollections.observableArrayList();
        List<OrderDetailsDTO> orderDetailsList = orderDetailsBO.getAllOrderDetails();
        for (OrderDetailsDTO orderDetailsDto : orderDetailsList) {
            orderDetailsTMList.add(new OrderDetailsTM(
                    orderDetailsDto.getOrderDetail_id(),
                    orderDetailsDto.getOrder_id(),
                    orderDetailsDto.getProduct_id(),
                    orderDetailsDto.getQuantity(),
                    orderDetailsDto.getPrice()
            ));
        }
        tblOrderdetails.setItems(orderDetailsTMList);
    }

    @FXML
    void saveOrderDetails(ActionEvent event) {
        if (!validateOrderDetails()) {
            return;
        }
        OrderDetailsDTO orderDetailsDto = new OrderDetailsDTO(
                txtOrderDetailsId.getText(),
                txtOrderId.getText(),
                txtItemId.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );
        boolean isSaved = orderDetailsBO.saveOrderDetails(orderDetailsDto);
        if (isSaved) {
            showAlert("Success", "Order details saved successfully", Alert.AlertType.INFORMATION);
            loadOrderDetailsTableData();
            clearFields();
        } else {
            showAlert("Error", "Failed to save order details", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void updateOrderDetails(ActionEvent event) {
        if (!validateOrderDetails()) {
            return;
        }
        OrderDetailsDTO orderDetailsDto = new OrderDetailsDTO(
                txtOrderDetailsId.getText(),
                txtOrderId.getText(),
                txtItemId.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );
        boolean isUpdated = orderDetailsBO.updateOrderDetails(orderDetailsDto);
        if (isUpdated) {
            showAlert("Success", "Order details updated successfully", Alert.AlertType.INFORMATION);
            loadOrderDetailsTableData();
            clearFields();
        } else {
            showAlert("Error", "Failed to update order details", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void deleteOrderDetails(ActionEvent event) {
        String orderDetailId = txtOrderDetailsId.getText();
        if (orderDetailId.isEmpty()) {
            showAlert("Warning", "Please enter an order details ID", Alert.AlertType.WARNING);
            return;
        }
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this record?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = orderDetailsBO.deleteOrderDetails(orderDetailId);
            if (isDeleted) {
                showAlert("Success", "Order details deleted successfully", Alert.AlertType.INFORMATION);
                loadOrderDetailsTableData();
                clearFields();
            } else {
                showAlert("Error", "Failed to delete order details", Alert.AlertType.ERROR);
            }
        }
    }

    private void clearFields() {
        txtOrderDetailsId.clear();
        txtOrderId.clear();
        txtItemId.clear();
        txtQty.clear();
        txtUnitPrice.clear();
    }

    private boolean validateOrderDetails() {
        Pattern pattern = Pattern.compile("OD\\d{3}");
        if (!pattern.matcher(txtOrderDetailsId.getText()).matches()) {
            showAlert("Validation Error", "Invalid Order Details ID format", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
