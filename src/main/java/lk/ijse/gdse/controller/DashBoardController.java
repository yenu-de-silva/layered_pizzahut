
package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashBoardController {

    public AnchorPane rootNode;

    public ImageView dashboardimageview1;

    public Button btnManage;

    public Button btnItem;

    public Button btnPayment;

    @FXML
    private Label ProductId;
    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button btnCustomer, btnCustomerFeedBack, btnDelivery, btnDepartment, btnEmployee,
            btnInventory, btnLogin, btnOrder, btnOrderDetails, btnPayments,
            btnProduct, btnSalary, btnSupplier, btnUser , getBtnRatingPizza;

    @FXML
    private ImageView dashboardimageview;

    @FXML
    private AnchorPane dashboardmainpane, maindashboardPane;

    @FXML
    private Label titleId;

    private Node VBox;

    private boolean validateAccess() {
        if (/* condition to check if user is logged in */ true) {
            return true;
        }
        showAlert("Access Denied", "You must log in first!");
        return false;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void navigateToPage(String fxmlFile, String pageTitle) {
        try {
            URL fxmlLocation = getClass().getResource(fxmlFile);
            if (fxmlLocation == null) {
//                showAlert("File Not Found", "The file " + fxmlFile + " could not be found.");
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            AnchorPane root = (AnchorPane) loader.load();
            rootNode.getChildren().clear();
            rootNode.getChildren().add(root);

        } catch (IOException e) {
//            showAlert("Navigation Error", "An error occurred while loading the page: " + pageTitle);
            e.printStackTrace();
        } catch (Exception e) {
//            showAlert("Unexpected Error", "An unexpected error occurred.");
            e.printStackTrace();
        }
    }

    @FXML
    void navigateToCustomerFeedBackPage(ActionEvent event) {
        navigateToPage("/view/CustomerFeedbackView.fxml", "Customer Feedback");
    }

    @FXML
    void navigateToCustomerPage(ActionEvent event) {
        navigateToPage("/view/CustomerView.fxml", "Customer Management");
    }

    @FXML
    void navigateToDeliveryPage(ActionEvent event) {
        navigateToPage("/view/DeliveryView.fxml", "Delivery Management");
    }

    @FXML
    void navigateToDepartmentPage(ActionEvent event) {
        navigateToPage("/view/DepartmentView.fxml", "Department Management");
    }

    @FXML
    void navigateToEmployeePage(ActionEvent event) {
        navigateToPage("/view/EmployeeView.fxml", "Employee Management");
    }

    @FXML
    void navigateToInventoryPage(ActionEvent event) {
        navigateToPage("/view/InventoryView.fxml", "Inventory Management");
    }

    @FXML
    void navigateToItemPage(ActionEvent event) {
        navigateToPage("/view/ItemView.fxml", "Item Management");
    }

    @FXML
    void navigateToLogInPage(ActionEvent event) {
        navigateToPage("/view/LoginView.fxml", "Login");
    }

    @FXML
    void navigateToManagePage(ActionEvent event) {
        navigateToPage("/view/ManageView.fxml", "Management");
    }

    @FXML
    void navigateToOrderDetailsPage(ActionEvent event) {
        navigateToPage("/view/OrderDetailsView.fxml", "Order Details");
    }

    @FXML
    void navigateToOrderPage(ActionEvent event) {
        navigateToPage("/view/Orderview.fxml", "Order Management");
    }

    @FXML
    void navigateToPaymentPage(ActionEvent event) {
        navigateToPage("/view/PaymentView.fxml", "Payment Processing");
    }

    @FXML
    void navigateToProductPage(ActionEvent event) {
        navigateToPage("/view/ProductView.fxml", "Product Management");
    }

    @FXML
    void navigateToSalaryPage(ActionEvent event) {
        navigateToPage("/view/SalaryView.fxml", "Salary Management");
    }

    @FXML
    void navigateToSupplierPage(ActionEvent event) {
        navigateToPage("/view/SupplierView.fxml", "Supplier Management");
    }

    @FXML
    void navigateToUserPage(ActionEvent event) {
        navigateToPage("/view/UserView.fxml", "User Management");
    }

//    @FXML
//    void navigateToRatingPizzaPage(ActionEvent event) {
//        navigateToPage("/view/RatingPizzaView.fxml", "Rating Pizza");
//    }
    @FXML
    void navigateToIRatingPizzaPage(ActionEvent event) {
        navigateToPage("/view/RatingPizzaView.fxml", "Rating Pizza");
    }
}
