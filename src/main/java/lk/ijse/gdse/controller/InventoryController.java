package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.InventoryBO;
import lk.ijse.gdse.dto.InventoryDTO;
import lk.ijse.gdse.dto.tm.InventoryTM;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class InventoryController {

    private final InventoryBO inventoryBO = (InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.INVENTORY);

    @FXML
    private TextField txtInventoryId, txtProductId, txtSupplierId, txtLastUpdated, quantityId;

    @FXML
    private TableView<InventoryTM> inventoryTable;

    @FXML
    private TableColumn<InventoryTM, String> colInventoryId, colProductId, colSupplierId, colLastUpdated;

    @FXML
    private TableColumn<InventoryTM, Integer> colQuantity;

    @FXML
    private Button btnAdd, btnUpdate, btnDelete, btnSave;

    private static final Pattern QUANTITY_PATTERN = Pattern.compile("^[1-9][0-9]*$");

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadTableData();
        generateNextInventoryId();
    }

    private void setCellValueFactory() {
        colInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colLastUpdated.setCellValueFactory(new PropertyValueFactory<>("last_updated"));
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<InventoryTM> inventoryTMS = FXCollections.observableArrayList();
        List<InventoryDTO> sList = inventoryBO.getAllInventoryItems();
        for (InventoryDTO inventoryDTO : sList) {
            inventoryTMS.add(new InventoryTM(
                    inventoryDTO.getInventory_id(),
                    inventoryDTO.getProduct_id(),
                    inventoryDTO.getSupplier_id(),
                    inventoryDTO.getQuantity(),
                    inventoryDTO.getLast_updated()
            ));
        }
        inventoryTable.setItems(inventoryTMS);
    }

    private void generateNextInventoryId() throws SQLException, ClassNotFoundException {
        int nextId = inventoryBO.getNextInventoryId();
        txtInventoryId.setText(String.valueOf(nextId));
    }

    @FXML
    private void addOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        handleSaveOrUpdate(true);
    }

    @FXML
    private void updateOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        handleSaveOrUpdate(false);
    }

    @FXML
    private void deleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
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

    private void handleSaveOrUpdate(boolean isNew) throws ClassNotFoundException {
        if (!validateInputs()) return;

        try {
            InventoryDTO inventoryDTO = new InventoryDTO(
                    txtInventoryId.getText(),
                    txtProductId.getText(),
                    txtSupplierId.getText(),
                    Integer.parseInt(quantityId.getText()),
                    txtLastUpdated.getText()
            );

            boolean success = isNew ? inventoryBO.saveInventory(inventoryDTO) : inventoryBO.updateInventory(inventoryDTO);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Inventory item " + (isNew ? "added" : "updated") + " successfully.");
                refreshPage();
            } else {
                showAlert(Alert.AlertType.WARNING, "Failure", "Failed to " + (isNew ? "add" : "update") + " inventory item.");
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
        if (txtProductId.getText().isEmpty() || txtSupplierId.getText().isEmpty() || txtLastUpdated.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Invalid Input", "All fields are required.");
            return false;
        }
        return true;
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();
        generateNextInventoryId();
        txtProductId.clear();
        txtSupplierId.clear();
        quantityId.clear();
        txtLastUpdated.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
