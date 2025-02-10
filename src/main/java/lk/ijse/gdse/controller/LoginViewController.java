package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

public class LoginViewController {

    public TextField usernametxt;
    public TextField passtxt;
    @FXML
    private Label loginId;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Button login_btn;
    @FXML
    private Label password;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label showpassword;
    @FXML
    private Label username;
    @FXML
    private TextField usernameField;

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "123";

    private Connection connection;

/*    public LoginViewController() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzahubsystem", "root", "1234");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @FXML
    void loginOnClick(ActionEvent event) throws IOException {

        String enteredUsername = usernametxt.getText();
        String enteredPassword = passtxt.getText();

        if (enteredUsername.equals(VALID_USERNAME) && enteredPassword.equals(VALID_PASSWORD)) {
            navigateToUserView();
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
        }

    }

    private boolean validateLogin(String username, String password) {
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }

    private void navigateToUserView() throws IOException {
        loginPane.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashBoardView.fxml")));
        loginPane.getChildren().add(pane);
    }

    private void logLoginAttempt(String username, boolean success) {
        String sql = "INSERT INTO login_attempts (username, attempt_time, status) VALUES (?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            pst.setString(3, success ? "success" : "failure");

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public void cancleonclick(ActionEvent actionEvent) {
        usernameField.clear();
        passwordField.clear();
    }

/*    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
