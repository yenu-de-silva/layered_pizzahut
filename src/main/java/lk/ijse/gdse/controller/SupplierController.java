package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.CustomerBO;
import lk.ijse.gdse.bo.custom.SupplierBO;
import lk.ijse.gdse.dto.SupplierDTO;
import lk.ijse.gdse.dto.tm.SupplierTM;

import java.sql.SQLException;
import java.util.List;

public class SupplierController {

    public TableColumn<?,?> colAdress;

    public TableColumn<?,?> colContactNumber;

    public TableColumn<?,?> colContactName;

    public TableColumn <?,?>colName;

    public TableColumn<?,?> colSupplierID;

    public TableView<SupplierTM> tblsupplier;

    public Button btnERefresh;

    public Button btnAdd;

    public TextField txtaddress;

    public Label sup_addId;

    public TextField txtcontactNumber;

    public Label SupplierContactNumber;

    public Label SupplierContactName;

    public TextField txtName;

    public Label SupplierName;

    public Label lblUserId;

    public Label supplierId;
    public TextField txtsupplierid;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtContactName;


    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    SupplierBO supplierBO= (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);

    @FXML
    public void initialize() throws ClassNotFoundException {
        try {
            setCellValue();
            loadSupplierTableData();
        } catch (SQLException e) {
            showError("Error", "Failed to load supplier data.");
        }
    }

    private void setCellValue() {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        colContactName.setCellValueFactory(new PropertyValueFactory<>("contact_name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        colAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));




    }

    private void loadSupplierTableData() throws SQLException, ClassNotFoundException {
       // ObservableList<SupplierDTO> suppliers = FXCollections.observableArrayList(supplierModel.getAllSuppliers());

        ObservableList<SupplierTM> supplierTMs = FXCollections.observableArrayList();
        List<SupplierTM> sList = supplierBO.getAllSuppliers();
        for (SupplierTM supplierDTO : sList) {
            SupplierTM supplierTM = new SupplierTM(
                    supplierDTO.getSupplier_id(),
                    supplierDTO.getSupplier_name(),
                    supplierDTO.getContact_name(),
                    supplierDTO.getContact_number(),
                    supplierDTO.getAddress()
            );
            supplierTMs.add(supplierTM);
        }

        tblsupplier.setItems(supplierTMs);
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

    @FXML
    public void onTableSelect() {
        SupplierTM selectedSupplier = tblsupplier.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            txtSupplierId.setText(selectedSupplier.getSupplier_id());
            txtName.setText(selectedSupplier.getSupplier_name());
            txtContactName.setText(selectedSupplier.getContact_name());
            txtcontactNumber.setText(selectedSupplier.getContact_number());
            txtaddress.setText(selectedSupplier.getAddress());
        }
    }

    @FXML
    public void saveOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        try {
            String supplierId = txtsupplierid.getText() ;
            String supplierName = txtName.getText();
            String contactName = txtContactName.getText();
            String contactNumber = txtcontactNumber.getText();
            String address = txtaddress.getText();
            System.out.println(supplierId);
            SupplierDTO supplierDTO = new SupplierDTO(supplierId, supplierName, contactName, contactNumber, address);

            boolean isSaved = supplierBO.saveSupplier(supplierDTO);

            if (isSaved) {
                showInfo("Success", "Supplier saved successfully!");
                loadSupplierTableData();
                clearForm();
            } else {
                showError("Error", "Failed to save supplier.");
            }
        } catch (SQLException e) {
            showError("Error", "Error saving supplier: " + e.getMessage());
        }
    }

    private void clearForm() {

    }

    @FXML
    public void updateOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        try {
            SupplierTM selectedSupplier = tblsupplier.getSelectionModel().getSelectedItem();

            if (selectedSupplier == null) {
                showError("Error", "Please add a supplier to update.");
                return;
            }

            String supplierName = txtName.getText();
            String contactName = txtContactName.getText();
            String contactNumber = txtcontactNumber.getText();
            String address = txtaddress.getText();

            selectedSupplier.setSupplier_name(supplierName);
            selectedSupplier.setContact_name(contactName);
            selectedSupplier.setContact_number(contactNumber);
            selectedSupplier.setAddress(address);

            boolean isUpdated = supplierBO.updateSupplier(selectedSupplier);

            if (isUpdated) {
                showInfo("Success", "Supplier updated successfully!");
                loadSupplierTableData();
                clearForm();
            } else {
                showError("Error", "Failed to update supplier.");
            }
        } catch (SQLException e) {
            showError("Error", "Error updating supplier: " + e.getMessage());
        }
    }

    @FXML
    public void deleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        try {
            SupplierTM selectedSupplier = tblsupplier.getSelectionModel().getSelectedItem();

            if (selectedSupplier == null) {
                showError("Error", "Please select a supplier to delete.");
                return;
            }

            String supplierId = selectedSupplier.getSupplier_id();

            boolean isDeleted = supplierBO.deleteSupplier(supplierId);

            if (isDeleted) {
                showInfo("Success", "Supplier deleted successfully!");
                loadSupplierTableData();
                clearForm();
            } else {
                showError("Error", "Failed to delete supplier.");
            }
        } catch (SQLException e) {
            showError("Error", "Error deleting supplier: " + e.getMessage());
        }
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        clearForm();
        int newSupplierId = supplierBO.getNextSupplierId();
        txtSupplierId.setText(String.valueOf(newSupplierId));
    }

    @FXML
    public void refreshOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        try {
            loadSupplierTableData();
        } catch (SQLException e) {
            showError("Error", "Error refreshing supplier data: " + e.getMessage());
        }
    }

}
