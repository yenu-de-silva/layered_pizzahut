package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.CustomerBO;
import lk.ijse.gdse.bo.custom.SalaryBO;
import lk.ijse.gdse.dto.SalaryDTO;
import lk.ijse.gdse.dto.tm.SalaryTM;

import java.io.IOException;
import java.sql.SQLException;

public class SalaryController {

    SalaryBO salaryBO= (SalaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.SALARY);

    @FXML
    private Label baseSalary;

    @FXML
    private TextField baseSalaryField, deductionsField, employeeIdField, netSalaryField, salaryIdField, salary_date, bonusField;

    @FXML
    private ImageView salarymanagementimage;

    @FXML
    private TableView<SalaryTM> tblsalaryid;

    @FXML
    private TableColumn<SalaryTM, String> colSalaryId, colEmp_id, colempbasesalary, colempbonus, colempdeductions, colempnetsalary, colSalaryDate;

    @FXML
    private void initialize() throws ClassNotFoundException {
        setupTableColumns();
        try {
            loadTableData();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load salary data: " + e.getMessage()).show();
        }
    }

    private void setupTableColumns() {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salary_id"));
        colEmp_id.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colempbasesalary.setCellValueFactory(new PropertyValueFactory<>("basic_salary"));
        colempbonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        colempdeductions.setCellValueFactory(new PropertyValueFactory<>("deductions"));
        colempnetsalary.setCellValueFactory(new PropertyValueFactory<>("net_salary"));
        colSalaryDate.setCellValueFactory(new PropertyValueFactory<>("salary_date"));
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<SalaryTM> salaryList = FXCollections.observableArrayList(salaryBO.getAllSalary());
        tblsalaryid.setItems(salaryList);
    }

    @FXML
    private void refreshPage() throws ClassNotFoundException {
        try {
            loadTableData();
            resetFields();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to refresh page: " + e.getMessage()).show();
        }
    }

    private void resetFields() {
        salaryIdField.clear();
        employeeIdField.clear();
        baseSalaryField.clear();
        bonusField.clear();
        deductionsField.clear();
        netSalaryField.clear();
        salary_date.clear();
    }

    @FXML
    private void calculateNetSalary() {
        try {
            double baseSalary = Double.parseDouble(baseSalaryField.getText());
            double bonus = Double.parseDouble(bonusField.getText());
            double deductions = Double.parseDouble(deductionsField.getText());
            double netSalary = baseSalary + bonus - deductions;
            netSalaryField.setText(String.format("%.2f", netSalary));
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter valid numbers for salary, bonus, and deductions.").show();
        }
    }

    @FXML
    private void findByEmployeeId() {
    }

    @FXML
    private void saveSalary() throws ClassNotFoundException {
        try {
            String salaryId = salaryIdField.getText();
            String employeeId = employeeIdField.getText();
            double baseSalary = Double.parseDouble(baseSalaryField.getText());
            double bonus = Double.parseDouble(bonusField.getText());
            double deductions = Double.parseDouble(deductionsField.getText());
            double netSalary = Double.parseDouble(netSalaryField.getText());
            String date = salary_date.getText();

            SalaryDTO salaryDTO = new SalaryDTO(salaryId, employeeId, baseSalary, bonus, deductions, netSalary, date);
            boolean isSaved = salaryBO.save(salaryDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Salary saved successfully!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save salary.").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input. Please ensure all fields are filled with valid data.").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        }
    }

    public static void showSalaryView() throws IOException {
        FXMLLoader loader = new FXMLLoader(SalaryController.class.getResource("/path/to/SalaryView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Salary Management");
        stage.show();
    }
}

