package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class PizzaHubHomeController {

    public Button start_btn;
    @FXML
    private ImageView PizzaChefImageId;

    @FXML
    private ImageView PizzaImageId;

    @FXML
    private Label THEPIZZAHUTId;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button startButton;

    /**
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/pizzahubsystemdb";
        String username = "root";
        String password = "1234";

        return DriverManager.getConnection(url, username, password);
    }
    @FXML
    void startOnClick(ActionEvent event) throws IOException {
        boolean validationPassed = validateFields();

        if (validationPassed) {
            showSuccessMessage();
            mainPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginView.fxml")));
            mainPane.getChildren().add(pane);

            startTransaction();
        } else {
            showErrorMessage();
        }
    }

    private boolean validateFields() {
        if (PizzaChefImageId.getImage() == null || PizzaImageId.getImage() == null) {
            return false;
        }
        return true;
    }

    private void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Failed");
        alert.setHeaderText("Error");
        alert.setContentText("Please check the input fields or required conditions.");
        alert.showAndWait();
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Hub Admin");
        alert.setHeaderText("Success");
        alert.setContentText("Welcome to Pizza Hut Admin Dashboard!");
        alert.showAndWait();
    }

    private void startTransaction() {
        System.out.println("Starting transaction...");

        try {
            commitTransaction();

        } catch (Exception e) {
            rollbackTransaction();
            showErrorMessage();
        }
    }

    private void commitTransaction() {
        System.out.println("Transaction committed successfully!");
    }

    private void rollbackTransaction() {
        System.out.println("Transaction rolled back due to an error.");
    }
}
