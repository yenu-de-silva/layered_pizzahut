package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.DeliveryBO;
import lk.ijse.gdse.dto.DeliveryDTO;
import lk.ijse.gdse.dto.tm.DeliveryTM;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryController {
    public TextField deliveryorderIdfeild;
    public TextField deliverydateField;
    public TextField deliveryaddressField;
    public Button btnReset;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public TableView <DeliveryDTO> tbldelivery;
    public TextField deliveryStatusField1;
    public ImageView imageview;
    public TextField EmployeeIdField1;
    public Label titleId;
    DeliveryBO deliveryBO = (DeliveryBO) BOFactory.getInstance().getBO(BOFactory.BOType.DELIVERY);

    @FXML
    private TextField deliveryIdField;
    @FXML
    private TableColumn<DeliveryTM, Integer> colDeliveryId, colOrderId;
    @FXML
    private TableColumn<DeliveryTM, String> colDeliveryAddress, colDeliveryStatus;
    @FXML
    private TableColumn<DeliveryTM, LocalDate> colDeliveryDate;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        loadDeliveryData();
        setCellValue();
    }

    private void setCellValue() {
        colDeliveryId.setCellValueFactory(new PropertyValueFactory<>("delivery_id"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("delivery_Date"));
        colDeliveryAddress.setCellValueFactory(new PropertyValueFactory<>("delivery_Address"));
        colDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("delivery_status"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (!deliveryIdField.getText().isEmpty()) {
            int deliveryId = Integer.parseInt(deliveryIdField.getText());
            boolean deleted = deliveryBO.deleteDelivery(deliveryId);
            showResultAlert(deleted, "deleted");
        } else {
            showAlert("Validation Error", "Please select a delivery to delete.", Alert.AlertType.ERROR);
        }
    }

    private void loadDeliveryData() throws SQLException, ClassNotFoundException {
        List<DeliveryDTO> deliveries = deliveryBO.getAllDelivery();
        ObservableList<DeliveryDTO> deliveryList = FXCollections.observableArrayList(deliveries);
        tbldelivery.setItems(deliveryList);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        DeliveryDTO selectedDelivery = tbldelivery.getSelectionModel().getSelectedItem();
        if (selectedDelivery != null) {
            deliveryIdField.setText(String.valueOf(selectedDelivery.getDelivery_id()));
            deliveryorderIdfeild.setText(String.valueOf(selectedDelivery.getOrder_id()));
            deliveryaddressField.setText(selectedDelivery.getDelivery_address());
            deliverydateField.setText(selectedDelivery.getDelivery_date().toString());
            deliveryStatusField1.setText(selectedDelivery.getDelivery_status());
            EmployeeIdField1.setText(selectedDelivery.getEmployee_id());
        }
    }

    private boolean validateInputs() {
        try {
            if (deliveryIdField.getText().isEmpty() || deliveryorderIdfeild.getText().isEmpty() ||
                    deliveryaddressField.getText().isEmpty() || deliverydateField.getText().isEmpty() ||
                    deliveryStatusField1.getText().isEmpty() || EmployeeIdField1.getText().isEmpty()) {
                showAlert("Validation Error", "All fields are required.", Alert.AlertType.ERROR);
                return false;
            }
            Integer.parseInt(deliveryIdField.getText());
            Integer.parseInt(deliveryorderIdfeild.getText());
            LocalDate.parse(deliverydateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (NumberFormatException | DateTimeParseException e) {
            showAlert("Validation Error", "Please enter valid data.", Alert.AlertType.ERROR);
            return false;
        }
    }

    private DeliveryDTO getDeliveryDTOFromFields() {
        LocalDate deliveryDate = LocalDate.parse(deliverydateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new DeliveryDTO(
                deliveryIdField.getText(),
                deliveryorderIdfeild.getText(),
                deliveryaddressField.getText(),
                Date.valueOf(deliveryDate),
                deliveryStatusField1.getText(),
                EmployeeIdField1.getText()
        );
    }

    private void showResultAlert(boolean success, String action) {
        if (success) {
            showAlert("Success", "Delivery " + action + " successfully.", Alert.AlertType.INFORMATION);
            resetFields();
            try {
                loadDeliveryData();
            } catch (Exception e) {
                showAlert("Error", "Failed to reload data.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Error", "Failed to " + action + " delivery.", Alert.AlertType.ERROR);
        }
    }

    private void resetFields() {
        deliveryIdField.clear();
        deliveryorderIdfeild.clear();
        deliveryaddressField.clear();
        deliverydateField.clear();
        deliveryStatusField1.clear();
        EmployeeIdField1.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (validateInputs()) {
            DeliveryDTO deliveryDTO = getDeliveryDTOFromFields();
            boolean updated = deliveryBO.updateDelivery(deliveryDTO);
            showResultAlert(updated, "updated");
        }else {
            resetFields();
        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (validateInputs()) {
            DeliveryDTO deliveryDTO = getDeliveryDTOFromFields();
            boolean saved = deliveryBO.saveDelivery(deliveryDTO);
            showResultAlert(saved, "saved");
        }else {
            resetFields();
        }
    }
}