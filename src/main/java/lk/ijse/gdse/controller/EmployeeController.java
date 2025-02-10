package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.CustomerBO;
import lk.ijse.gdse.bo.custom.EmployeeBO;
import lk.ijse.gdse.dto.EmployeeDTO;
import lk.ijse.gdse.dto.tm.EmployeeTM;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeController {

    EmployeeBO employeeBO= (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);

    @FXML
    public TableView<EmployeeTM> tblEmployeeid;


    @FXML
    private TableColumn<EmployeeTM, Integer> colEmpId, colDeptId;

    @FXML
    private TableColumn<EmployeeTM, String> colName, colPosition;

    @FXML
    private TableColumn<EmployeeTM, Date> colHireDate;

    @FXML
    private TextField txtemployeeid, txtname, txtposition, txtdepartmentid, txthiredate;

    @FXML
    private Button btn_add, btn_edit, btn_delete, btn_clear;


    @FXML
    public void initialize(Object o, Object object) throws ClassNotFoundException {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colDeptId.setCellValueFactory(new PropertyValueFactory<>("department_id"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("hire_date"));

        try {
            loadTableData();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load employees: " + e.getMessage());
        }
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        List<EmployeeDTO> employees = employeeBO.getAllEmployees();
        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();
        for (EmployeeDTO employee : employees) {
            employeeTMS.add(new EmployeeTM(
                    employee.getEmployee_id(),
                    employee.getPosition(),
                    employee.getHire_date(),
                    employee.getDepartment_id(),
                    employee.getName()

            ));
        }
        tblEmployeeid.setItems(employeeTMS);
    }

    @FXML
    void addOnAction(ActionEvent event) {
        if (!validateInput()) return;

        try {
            String  empId = txtemployeeid.getText();
            String position = txtposition.getText();
            Date hireDate = Date.valueOf(txthiredate.getText());
            String deptId = (txtdepartmentid.getText());
            String name = txtname.getText();


            EmployeeDTO newEmployee = new EmployeeDTO(empId,position,hireDate, deptId,name);

            boolean success = employeeBO.saveEmployee(newEmployee);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee added successfully.");
                loadTableData();
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add employee.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid data or database issue.");
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws ClassNotFoundException {
        EmployeeTM selectedEmployee = (EmployeeTM) tblEmployeeid.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "No employee selected.");
            return;
        }

        Optional<ButtonType> confirmation = showAlertWithConfirmation("Confirmation", "Delete selected employee?");
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            try {
                boolean success = employeeBO.deleteEmployee(selectedEmployee.getEmployee_id());
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Employee deleted successfully.");
                    loadTableData();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete employee.");
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Database error: " + e.getMessage());
            }
        }
    }

    @FXML
    void editOnAction(ActionEvent event) {
        if (!validateInput()) return;

        try {
            String empId = txtemployeeid.getText();
            String name = txtname.getText();
            String position = txtposition.getText();
            String deptId = txtdepartmentid.getText();
            Date hireDate = Date.valueOf(txthiredate.getText());

            EmployeeDTO updatedEmployee = new EmployeeDTO(empId,position ,hireDate, deptId,name);

            boolean success = employeeBO.updateEmployee(updatedEmployee);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee updated successfully.");
                loadTableData();
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update employee.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid data or database issue.");
        }
    }

    @FXML
    void clearOnAction(ActionEvent event) {
        clearFields();
    }

    private boolean validateInput() {
        if (txtemployeeid.getText().isEmpty() || txtname.getText().isEmpty() || txtposition.getText().isEmpty() ||
                txtdepartmentid.getText().isEmpty() || txthiredate.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields are required.");
            return false;
        }
        try {
            Integer.parseInt(txtemployeeid.getText());
            Integer.parseInt(txtdepartmentid.getText());
            Date.valueOf(txthiredate.getText());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid input format.");
            return false;
        }
        return true;
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        initialize(null, null);
        loadTableData();

    }

    private void clearFields() {
        txtemployeeid.clear();
        txtname.clear();
        txtposition.clear();
        txtdepartmentid.clear();
        txthiredate.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Optional<ButtonType> showAlertWithConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
