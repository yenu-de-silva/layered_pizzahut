package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.dto.tm.UserTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {

    UserBO userBO= (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public TableView tbluser;
    @FXML
    private AnchorPane userpane;

    @FXML
    private Button btnDelete, btnSave, btnUpdate;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<UserTM, String> colUserId, colName, colPassword, colEmail, colRole;

    @FXML
    private Label lblUserId;

    @FXML
    private TextField txtEmail, txtName, txtPassword, txtRole;


    @FXML
    public void initialize() throws ClassNotFoundException {
        try {
            setCellValue();
            loadTableData();
        } catch (SQLException e) {
            showError("Initialization Error", "Failed to load user data: " + e.getMessage());
        }
    }

    private void setCellValue() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();
        ArrayList<UserDTO> userList = userBO.getAllUserIds();

        for (UserDTO userDTO : userList) {
            userTMS.add(new UserTM(
                    userDTO.getUser_id(),
                    userDTO.getUsername(),
                    userDTO.getPassword(),
                    userDTO.getEmail(),
                    userDTO.getRole()
            ));
        }

        tblUser.setItems(userTMS);
    }

    private boolean validateInputs() {
        if (txtName.getText().isEmpty()) {
            showAlert("Validation Error", "Name field cannot be empty.");
            return false;
        }

        if (txtEmail.getText().isEmpty()) {
            showAlert("Validation Error", "Email field cannot be empty.");
            return false;
        } else if (!txtEmail.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showAlert("Validation Error", "Please enter a valid email address.");
            return false;
        }

        if (txtPassword.getText().isEmpty()) {
            showAlert("Validation Error", "Password field cannot be empty.");
            return false;
        } else if (txtPassword.getText().length() < 6) {
            showAlert("Validation Error", "Password must be at least 6 characters long.");
            return false;
        }

        if (txtRole.getText().isEmpty()) {
            showAlert("Validation Error", "Role field cannot be empty.");
            return false;
        }

        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
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

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        if (!validateInputs()) return;

        try {
            String userId = lblUserId.getText();
            String username = txtName.getText();
            String password = txtPassword.getText();
            String email = txtEmail.getText();
            String role = txtRole.getText();

            UserDTO userDTO = new UserDTO(userId, username, password, email, role);
            boolean isSaved = userBO.saveUser(userDTO);

            if (isSaved) {
                showInfo("Success", "User saved successfully!");
                loadTableData();
                clearFields();
            } else {
                showError("Error", "Failed to save user.");
            }
        } catch (SQLException e) {
            showError("Error", "Error saving user: " + e.getMessage());
        }
    }

    @FXML
    public void updateOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        UserTM selectedUser = (UserTM) tblUser.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showError("Error", "Please select a user to update.");
            return;
        }

        if (!validateInputs()) return;

        try {
            String userId = selectedUser.getUser_id();
            String username = txtName.getText();
            String password = txtPassword.getText();
            String email = txtEmail.getText();
            String role = txtRole.getText();

            UserDTO userDTO = new UserDTO(userId, username, password, email, role);
            boolean isUpdated = userBO.updateUser(userDTO);

            if (isUpdated) {
                showInfo("Success", "User updated successfully!");
                loadTableData();
                clearFields();
            } else {
                showError("Error", "Failed to update user.");
            }
        } catch (SQLException e) {
            showError("Error", "Error updating user: " + e.getMessage());
        }
    }

    @FXML
    public void deleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        UserTM selectedUser = (UserTM) tblUser.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showError("Error", "Please select a user to delete.");
            return;
        }

        try {
            String userId = selectedUser.getUser_id();
            boolean isDeleted = userBO.deleteUser(Integer.parseInt(userId));

            if (isDeleted) {
                showInfo("Success", "User deleted successfully!");
                loadTableData();
                clearFields();
            } else {
                showError("Error", "Failed to delete user.");
            }
        } catch (SQLException e) {
            showError("Error", "Error deleting user: " + e.getMessage());
        }
    }

    private void clearFields() {
        lblUserId.setText("");
        txtEmail.clear();
        txtPassword.clear();
        txtRole.clear();
        txtName.clear();
    }
}
