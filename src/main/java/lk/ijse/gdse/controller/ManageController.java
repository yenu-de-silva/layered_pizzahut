package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.ManageBO;
import lk.ijse.gdse.dto.ManageDTO;
import lk.ijse.gdse.dto.tm.ManageTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageController {

    ManageBO manageBO= (ManageBO) BOFactory.getInstance().getBO(BOFactory.BOType.MANAGE);

    public TextField manageIdField;

    public TextField inventoryIdField;

    public TextField supplierIdField;

    public TextField orderIdField;

    public TextField quantityField;

    public TextField supplierNameField;

    public TextField supplierContactNameField;

    public Button cancelable;

    public Button btnSave;

    public TableColumn manageIdCol;

    public TableColumn inventoryIdCol;

    public TableColumn supplierIdCol;

    public TableColumn orderIdCol;

    public TableColumn supplierContactNameCol;

    public TableColumn supplierNameCol;

    public TableColumn quantityCol;

    public ImageView imageManageId;

    @FXML private TextField txtManageId;
    @FXML private TextField txtInventoryId;
    @FXML private TextField txtOrderId;
    @FXML private TextField txtQuantity;
    @FXML private TextField txtSupplierId;
    @FXML private TextField txtSupplierName;
    @FXML private TextField txtSupplierContactName;

    @FXML private TableView<ManageTM> tblManage;
    @FXML private TableColumn<ManageDTO, String> colManageId;
    @FXML private TableColumn<ManageDTO, String> colInventoryId;
    @FXML private TableColumn<ManageDTO, String> colOrderId;
    @FXML private TableColumn<ManageDTO, Integer> colQuantity;
    @FXML private TableColumn<ManageDTO, String> colSupplierId;
    @FXML private TableColumn<ManageDTO, String> colSupplierName;
    @FXML private TableColumn<ManageDTO, String> colSupplierContactName;

//    private ManageModel manageModel;
//    private ObservableList<ManageDTO> getAllManageRecords;
//
//    public ManageController() {
//        try {
//            manageModel = new ManageModel();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        manageIdCol.setCellValueFactory(new PropertyValueFactory<>("manage_id"));
        inventoryIdCol.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        supplierNameCol.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        supplierContactNameCol.setCellValueFactory(new PropertyValueFactory<>("supplier_contact_name"));

        loadManageRecords();
    }

    private void loadManageRecords() throws SQLException, ClassNotFoundException {
        ObservableList<ManageTM> manageTMS = FXCollections.observableArrayList();
        List<ManageDTO> sList = manageBO.getAllManageRecords();
        for (ManageDTO manageDTO : sList) {
            ManageTM manageTM = new ManageTM(
                    manageDTO.getManageId(),
                    manageDTO.getInventoryId(),
                    manageDTO.getSupplierId(),
                    manageDTO.getOrderId(),
                    manageDTO.getQuantity(),
                    manageDTO.getSupplierName(),
                    manageDTO.getSupplierContactName()
            );
            System.out.println(manageTM);
            manageTMS.add(manageTM);
        }
        tblManage.setItems(manageTMS);
    }

    @FXML
    public void handleSave(ActionEvent event) throws ClassNotFoundException {
        String manageId = txtManageId.getText();
        String inventoryId = txtInventoryId.getText();
        String orderId = txtOrderId.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        String supplierId = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String supplierContactName = txtSupplierContactName.getText();

        ManageDTO manageDTO = new ManageDTO(manageId, inventoryId, supplierId, orderId, quantity, supplierName, supplierContactName);

        try {
            boolean isSaved = manageBO.saveManage(manageDTO);
            if (isSaved) {
                showAlert("Success", "Manage record saved successfully", AlertType.INFORMATION);
                clearFields();
                loadManageRecords();
            } else {
                showAlert("Error", "Failed to save manage record", AlertType.ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Database error occurred", AlertType.ERROR);
        }
    }

    private void clearFields() {
        manageIdField.clear();
        inventoryIdField.clear();
        supplierIdField.clear();
        orderIdField.clear();
        quantityField.clear();
        supplierNameField.clear();
        supplierContactNameField.clear();
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void btnCancleOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String manageIdFieldText = manageIdField.getText();
        String inventoryIdFieldText = inventoryIdField.getText();
        String supplierIdFieldText = supplierIdField.getText();
        String orderIdFieldText = orderIdField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String supplierNameFieldText = supplierNameField.getText();
        String supplierContactNameFieldText = supplierContactNameField.getText();

        ManageDTO manageDTO = new ManageDTO(manageIdFieldText, inventoryIdFieldText, supplierIdFieldText, orderIdFieldText, quantity, supplierNameFieldText, supplierContactNameFieldText);

        boolean isSaved = manageBO.saveManage(manageDTO);
        if (isSaved) {
            refreshPage();
            new Alert(AlertType.INFORMATION, "Manage saved...!").show();
        } else {
            new Alert(AlertType.ERROR, "Failed to save Manage...!").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();
        btnSave.setDisable(false);
        cancelable.setDisable(false);

        manageIdField.setText("");
        inventoryIdField.setText("");
        supplierIdField.setText("");
        orderIdField.setText("");
        quantityField.setText("");
        supplierNameField.setText("");
        supplierContactNameField.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<ManageTM> addManage = FXCollections.observableArrayList();
        ArrayList<ManageDTO> list = (ArrayList<ManageDTO>) manageBO.getAllManageRecords();

        for (ManageDTO manageDTO : list) {
            addManage.add(new ManageTM(
                    manageDTO.getManageId(),
                    manageDTO.getInventoryId(),
                    manageDTO.getSupplierId(),
                    manageDTO.getOrderId(),
                    manageDTO.getQuantity(),
                    manageDTO.getSupplierName(),
                    manageDTO.getSupplierContactName()
            ));
        }
        //tblManage.setItems(addManage);
    }

}
