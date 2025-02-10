package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.CustomerBO;
import lk.ijse.gdse.bo.custom.InventoryBO;
import lk.ijse.gdse.dto.InventoryDTO;
import lk.ijse.gdse.dto.tm.InventoryTM;


import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class InventoryController {

    InventoryBO inventoryBO= (InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.INVENTORY);


    public TextField txtlast_updated;

    public TextField quantityId;

    public Button btnUpdated;

    public Label priceFeild;

    public TextField txtproductId;

    public TableColumn colLast_Updated;

    public TableColumn colinventoryId;

    public Button btnSave;

    @FXML
    private TableView<InventoryTM> inventorytable;

    @FXML
    private TableColumn<InventoryTM, String> colInventoryId;

    @FXML
    private TableColumn<InventoryTM, String> colProductId;

    @FXML
    private TableColumn<InventoryTM, String> colSupplierId;

    @FXML
    private TableColumn<InventoryTM, Integer> colQuantity;

    @FXML
    private TableColumn<InventoryTM, String> colLastUpdated;

    @FXML
    private TextField txtInventoryId;


    @FXML
    private TextField txtSupplierId;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    private static final Pattern QUANTITY_PATTERN = Pattern.compile("^[1-9][0-9]*$");

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        loadTableData();
    }
    private void setCellValue() {
        colinventoryId.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colLast_Updated.setCellValueFactory(new PropertyValueFactory<>("last_updated"));

    }
//    @FXML
//    public void initialize() throws SQLException {
//        colinventoryId.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
//        colProductId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
//        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
//        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        colLast_Updated.setCellValueFactory(new PropertyValueFactory<>("last_updated"));
//
//        loadTableData();
//        generateNextInventoryId();
//    }

    @FXML
    public void tblInventory(SortEvent<TableView> event) {
        System.out.println("Sort event triggered: " + event);
    }


    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<InventoryTM> inventoryTMS = FXCollections.observableArrayList();
        List<InventoryDTO> sList = inventoryBO.getAllInventoryItems();
        for (InventoryDTO inventoryDTO : sList) {
            InventoryTM inventoryTM = new InventoryTM(
                    inventoryDTO.getInventory_id(),
                    inventoryDTO.getProduct_id(),
                    inventoryDTO.getSupplier_id(),
                    inventoryDTO.getQuantity(),
                    inventoryDTO.getLast_updated()
            );
            System.out.println(inventoryTM);
            inventoryTMS.add(inventoryTM);
        }

        inventorytable.setItems(inventoryTMS);
    }

    private void generateNextInventoryId() throws SQLException, ClassNotFoundException {
        String nextId = inventoryBO.getNextInventoryId();
        txtInventoryId.setText(nextId);
    }

    @FXML
    public void AddOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        if (validateInputs()) {
            try {
                InventoryDTO inventoryDTO = new InventoryDTO(
                        txtInventoryId.getText(),
                        txtproductId.getText(),
                        txtSupplierId.getText(),
                        Integer.parseInt(quantityId.getText()),
                        txtlast_updated.getText()
                );

                if (inventoryBO.saveInventory(inventoryDTO)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Inventory item added successfully.");
                    refreshPage();
                } else {
                    showAlert(Alert.AlertType.WARNING, "Failure", "Failed to add inventory item.");
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            }
        }
    }

    @FXML
    public void UpdateOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        if (validateInputs()) {
            try {
                InventoryDTO inventoryDTO = new InventoryDTO(
                        txtInventoryId.getText(),
                        txtproductId.getText(),
                        txtSupplierId.getText(),
                        Integer.parseInt(quantityId.getText()),
                        txtlast_updated.getText()
                );

                if (inventoryBO.updateInventory(inventoryDTO)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Inventory item updated successfully.");
                    refreshPage();
                } else {
                    showAlert(Alert.AlertType.WARNING, "Failure", "Failed to update inventory item.");
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            }
        }
    }

    @FXML
    public void deleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        try {
            String inventoryId = txtInventoryId.getText();
            if (inventoryBO.deleteInventory(inventoryId)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Inventory item deleted successfully.");
                refreshPage();
            } else {
                showAlert(Alert.AlertType.WARNING, "Failure", "Failed to delete inventory item.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private boolean validateInputs() {
        if (!QUANTITY_PATTERN.matcher(quantityId.getText()).matches()) {
            showAlert(Alert.AlertType.WARNING, "Invalid Input", "Quantity must be a positive integer.");
            return false;
        }
        if (txtproductId.getText().isEmpty() || txtSupplierId.getText().isEmpty() || txtlast_updated.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Invalid Input", "All fields are required.");
            return false;
        }
        return true;
    }
    private void handleSave(ActionEvent actionEvent) throws ClassNotFoundException {
        try {
            String inventoryId = inventoryBO.getNextInventoryId();
            String productId = txtproductId.getText();
            String supplierId = txtSupplierId.getText();
            String quantity = quantityId.getText();
            String last_updated = txtlast_updated.getText();

            InventoryDTO inventoryDTO = new InventoryDTO(inventoryId, productId, supplierId, Integer.parseInt(quantity), last_updated);
            System.out.println(inventoryDTO);
            boolean isSaved = inventoryBO.saveInventory(inventoryDTO);

            if (isSaved) {
                showInfo("Success", "Inventory saved successfully!");
                loadTableData();
            } else {
                showError("Error", "Failed to save supplier.");
            }
        } catch (SQLException e) {
            showError("Error", "Error saving supplier: " + e.getMessage());
        }
    }


    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();
        generateNextInventoryId();
        txtproductId.clear();
        txtSupplierId.clear();
        quantityId.clear();
        txtlast_updated.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void SaveOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        handleSave(actionEvent);
    }


}

