package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.CustomerBO;
import lk.ijse.gdse.bo.custom.DepartmentBO;
import lk.ijse.gdse.dto.DepartmentDTO;
import lk.ijse.gdse.dto.tm.DepartmentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class DepartementController implements Initializable {

    DepartmentBO departmentBO= (DepartmentBO) BOFactory.getInstance().getBO(BOFactory.BOType.DEPARTMENT);

    public TableColumn deptdescriptionColumn;

    @FXML
    private ImageView imageview;

    @FXML
    private TableView<DepartmentTM> departmentTableView;

    @FXML
    private TableColumn<DepartmentTM, String> deptNameColumn, managerNameColumn;
    @FXML
    private TableColumn<DepartmentTM, String> deptDescriptionColumn;

    @FXML
    private TableColumn<DepartmentTM, Integer> deptIdColumn, employeeCountColumn;

    @FXML
    private TextField departmentIdField, departmentNameField, managerNameField, numEmployeesField, descriptionArea;

    @FXML
    private Button save_btn, delete_btn, cancel_btn;

    private static final Pattern ID_PATTERN = Pattern.compile("^[A-Za-z0-9]{3,10}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]{3,30}$");
    private static final Pattern EMPLOYEE_COUNT_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^[\\w\\s,.-]{5,100}$");


    public DepartementController() throws IOException {
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
        deptIdColumn.setCellValueFactory(new PropertyValueFactory<>("department_id"));
        deptNameColumn.setCellValueFactory(new PropertyValueFactory<>("department_name"));
        managerNameColumn.setCellValueFactory(new PropertyValueFactory<>("manager_name"));
        employeeCountColumn.setCellValueFactory(new PropertyValueFactory<>("number_of_employees"));
        deptdescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }


//    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DepartmentView.fxml"));
//    Parent root = loader.load();


    public void initialize() throws SQLException, ClassNotFoundException {
        //initialize(null, null);
        loadTableData();
        refreshPage();

    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextDepartmentId();
        loadTableData();
        resetButtonsAndFields();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        System.out.println("click to");
        List<DepartmentDTO> departmentDTOS = departmentBO.getAllDepartments();
        ObservableList<DepartmentTM> departmentTMS = FXCollections.observableArrayList();

        for (DepartmentDTO dto : departmentDTOS) {
            System.out.println(dto);
            departmentTMS.add(new DepartmentTM(
                    dto.getDepartment_id(),
                    dto.getDepartment_name(),
                    dto.getManager_name(),
                    dto.getNumber_of_employees(),
                    dto.getDescription()
            ));
        }
        departmentTableView.setItems(departmentTMS);
    }

    private void loadNextDepartmentId() throws SQLException, ClassNotFoundException {
        int nextDepartmentId = departmentBO.getNextDepartmentId();
        departmentIdField.setText(String.valueOf(nextDepartmentId));
    }

    private void resetButtonsAndFields() {
        save_btn.setDisable(false);
        delete_btn.setDisable(true);
        cancel_btn.setDisable(true);
        clearFields();
    }

    private void clearFields() {
        departmentIdField.clear();
        departmentNameField.clear();
        managerNameField.clear();
        numEmployeesField.clear();
        descriptionArea.clear();
    }


    private boolean validateAllFields() {
        if (validateDepartmentId()) {
            showAlert("Validation Error", "Invalid Department ID format.");
            return false;
        }
        if (validateDepartmentName()) {
            showAlert("Validation Error", "Invalid Department Name format.");
            return false;
        }
        if (validateManagerName()) {
            showAlert("Validation Error", "Invalid Manager Name format.");
            return false;
        }
        if (validateEmployeeCount()) {
            showAlert("Validation Error", "Invalid Employee Count format.");
            return false;
        }
        if (validateDescription()) {
            showAlert("Validation Error", "Invalid Description format.");
            return false;
        }
        return true;
    }


    private boolean validateDepartmentId() {
        return !ID_PATTERN.matcher(departmentIdField.getText()).matches();
    }

    private boolean validateDepartmentName() {

        return !NAME_PATTERN.matcher(departmentNameField.getText()).matches();
    }

    private boolean validateManagerName() {

        return !NAME_PATTERN.matcher(managerNameField.getText()).matches();
    }

    private boolean validateEmployeeCount() {
        return !EMPLOYEE_COUNT_PATTERN.matcher(numEmployeesField.getText()).matches();
    }

    private boolean validateDescription() {

        return !DESCRIPTION_PATTERN.matcher(descriptionArea.getText()).matches();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void cancleOnAction(ActionEvent actionEvent) {
        clearFields();
        resetButtonsAndFields();
    }

    public void saveOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        System.out.println("click");
        if (validateAllFields()) {
            try {
                DepartmentDTO departmentDTO = new DepartmentDTO(
                        departmentIdField.getText(),
                        departmentNameField.getText(),
                        managerNameField.getText(),
                        Integer.parseInt(numEmployeesField.getText()),
                        descriptionArea.getText()
                );

                if (departmentBO.save(departmentDTO)) {
                    showAlert("Success", "Department saved successfully.");
                    refreshPage();
                } else {
                    showAlert("Error", "Failed to save department.");
                }
            } catch (SQLException e) {
                showAlert("Error", "Database error: " + e.getMessage());
            }
        }
    }



    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String departmentIdFieldText = departmentIdField.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = departmentBO.deleteCustomer(departmentIdFieldText);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String departmentIdFieldText = departmentIdField.getText();
        String nameFieldText = departmentNameField.getText();
        String managerNameFieldText = managerNameField.getText();
        Integer numEmployeesFieldText = Integer.valueOf(numEmployeesField.getText());
        String descriptionAreaText = descriptionArea.getText();

        DepartmentDTO departmentDTO = new DepartmentDTO(departmentIdFieldText, nameFieldText, managerNameFieldText, numEmployeesFieldText, descriptionAreaText);

        boolean isUpdated = departmentBO.updateDepartment(departmentDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
        }

    }

    public void handledepartmentId(ActionEvent actionEvent) {
        if (validateDepartmentId()) {
            showAlert("Validation Error", "Invalid Department ID format.");
            departmentIdField.requestFocus();
        } else {
            departmentNameField.requestFocus();
        }
    }

    public void handleDepartmentName(ActionEvent actionEvent) {
        if (validateDepartmentName()) {
            showAlert("Validation Error", "Invalid Department Name format.");
            departmentNameField.requestFocus();
        } else {
            managerNameField.requestFocus();
        }
    }

    public void handlemanagername(ActionEvent actionEvent) {
        if (validateManagerName()) {
            showAlert("Validation Error", "Invalid Manager Name format.");
            managerNameField.requestFocus();
        } else {
            numEmployeesField.requestFocus();
        }
    }

    public void handleNumberOfEmployees(ActionEvent actionEvent) {
        if (validateEmployeeCount()) {
            showAlert("Validation Error", "Invalid Employee Count format.");
            numEmployeesField.requestFocus();
        } else {
            descriptionArea.requestFocus();
        }
    }

    public void handledescription(ActionEvent actionEvent) {
        if (validateDescription()) {
            showAlert("Validation Error", "Invalid Description format.");
            descriptionArea.requestFocus();
        } else {
            save_btn.requestFocus();
        }
    }
}