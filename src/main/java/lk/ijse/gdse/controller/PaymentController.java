package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.CustomerBO;
import lk.ijse.gdse.bo.custom.PaymentBO;
import lk.ijse.gdse.bo.custom.ProductBO;
import lk.ijse.gdse.db.DBConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static lk.ijse.gdse.db.DBConnection.dbConnection;

public class PaymentController {

    PaymentBO paymentBO= (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);

    @FXML
    private TextField AmountId;

    @FXML
    private TextField CashIdField;

    @FXML
    private Label CustomerDetailsId;

    @FXML
    private TextField OnlinePaymentIdField;

    @FXML
    private Label OrderNameId;

    @FXML
    private Label PayementMethod2;

    @FXML
    private TextField PaymentDateId;

    @FXML
    private TextField PaymentMethodId;

    @FXML
    private Label PaymentsMethodId;

    @FXML
    private Label QuantityId;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField creditCardIdField;

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField debitCardIdField;

    @FXML
    private ImageView imageId;

    @FXML
    private TextField nameField;

    @FXML
    private Label orderDetailsId;

    @FXML
    private TextField orderIdField;

    @FXML
    private Label paymentDetailsId;

    @FXML
    private TextField paymentId;

    @FXML
    private Label paymentMethod1;

    @FXML
    private Label paymentMethod3;

    @FXML
    private Label paymentMethod4;

    @FXML
    private TextField quantityField;

    @FXML
    private CheckBox saveCardCheckBox;

    @FXML
    private Button submitButton;

//    private final DBConnection dbConnection;
//
//    {
//        try {
//            dbConnection = new DBConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @FXML
    void handleCancleOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void handleCheckBoxAction(ActionEvent event) {
        if (saveCardCheckBox.isSelected()) {
            System.out.println("Card will be saved for future payments.");
        } else {
            System.out.println("Card details will not be saved.");
        }
    }

    @FXML
    void handleOrderIdField(ActionEvent event) {
        String orderId = orderIdField.getText();
        System.out.println("Order ID entered: " + orderId);
    }

    @FXML
    void handleSubmitOnAction(ActionEvent event) {
        if (isValidInput()) {
            if (processPaymentTransaction()) {
                System.out.println("Payment details successfully submitted.");
            } else {
                showAlert("Transaction Failed", "An error occurred while processing the payment.");
            }
        }
    }

    private boolean isValidInput() {
        if (!isValidAmount()) {
            showAlert("Invalid Amount", "Please enter a valid amount.");
            return false;
        }
        if (!isValidCashAmount()) {
            showAlert("Invalid Cash Amount", "Please enter a valid cash amount.");
            return false;
        }
        if (!isValidQuantity()) {
            showAlert("Invalid Quantity", "Please enter a valid quantity.");
            return false;
        }
        if (isCardPaymentSelected() && !isValidCardDetails()) {
            showAlert("Invalid Card Details", "Please enter valid credit/debit card details.");
            return false;
        }
        if (isOnlinePaymentSelected() && !isValidOnlinePayment()) {
            showAlert("Invalid Online Payment", "Please enter valid online payment details.");
            return false;
        }
        if (!isValidCustomerId()) {
            showAlert("Invalid Customer ID", "Please enter a valid customer ID.");
            return false;
        }
        return true;
    }

    private boolean isValidAmount() {
        try {
            double amount = Double.parseDouble(AmountId.getText());
            return amount > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidCashAmount() {
        try {
            double cashAmount = Double.parseDouble(CashIdField.getText());
            return cashAmount >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidQuantity() {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            return quantity > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidCardDetails() {
        String cardNumber = creditCardIdField.getText();
        return cardNumber.length() == 16 && cardNumber.matches("[0-9]+");
    }

    private boolean isValidOnlinePayment() {
        return !OnlinePaymentIdField.getText().trim().isEmpty();
    }

    private boolean isCardPaymentSelected() {
        return !creditCardIdField.getText().trim().isEmpty() || !debitCardIdField.getText().trim().isEmpty();
    }

    private boolean isValidCustomerId() {
        return !customerIdField.getText().trim().isEmpty();
    }

    private boolean isOnlinePaymentSelected() {
        return !OnlinePaymentIdField.getText().trim().isEmpty();
    }

    private boolean processPaymentTransaction() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dbConnection.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO payments (customer_id, amount, payment_method, quantity, payment_date) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, customerIdField.getText());
            preparedStatement.setDouble(2, Double.parseDouble(AmountId.getText()));
            preparedStatement.setString(3, PaymentMethodId.getText());
            preparedStatement.setInt(4, Integer.parseInt(quantityField.getText()));
            preparedStatement.setString(5, PaymentDateId.getText());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        AmountId.clear();
        CashIdField.clear();
        OnlinePaymentIdField.clear();
        PaymentDateId.clear();
        PaymentMethodId.clear();
        quantityField.clear();
        creditCardIdField.clear();
        debitCardIdField.clear();
        customerIdField.clear();
        orderIdField.clear();
    }
}
