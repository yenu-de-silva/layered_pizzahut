package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.DepartmentBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.dto.OrderDetailsDTO;
import lk.ijse.gdse.dto.tm.OrderDetailsTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsController {

    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAILS);

    public TableColumn <?,?>ColOrderDetailsId;

    public Button btnadd_order_details;

    public Button btnupdate_order_details;

    public Button btndelete_order_details;

    public ImageView imageid;

    public TextField txtOrderDetailsId;
    @FXML
    private TableView<OrderDetailsTM> tblOrderdetails;


    @FXML
    private TableColumn<OrderDetailsTM, String> colOrderId;

    @FXML
    private TableColumn<OrderDetailsTM, String> colProductId;

    @FXML
    private TableColumn<OrderDetailsTM, Integer> colQuantity;

    @FXML
    private TableColumn<OrderDetailsTM, Double> colPrice;

    @FXML
    private TextField txtOrderDetailId;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    public void initialize() {
        try {
            setCellValue();
            loadOrderDetailsTableData();
        } catch (SQLException e) {
            showError("Error", "Failed to load orderdetails data.");
        }
    }

    private void setCellValue() {
        ColOrderDetailsId.setCellValueFactory(new PropertyValueFactory<>("order_details_id"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    private void loadOrderDetailsTableData() throws SQLException {
        ArrayList<OrderDetailsDTO> orderDetailsList = orderDetailsDAO.getAllOrderDetails();
        ObservableList<OrderDetailsTM> orderDetailsTMList = FXCollections.observableArrayList();

        for (OrderDetailsDTO orderDetailsDTO : orderDetailsList) {
            System.out.println(orderDetailsDTO);
            orderDetailsTMList.add(new OrderDetailsTM(
                    orderDetailsDTO.getOrderDetail_id(),
                    orderDetailsDTO.getOrder_id(),
                    orderDetailsDTO.getProduct_id(),
                    orderDetailsDTO.getQuantity(),
                    orderDetailsDTO.getPrice()
            ));
        }

        tblOrderdetails.setItems(orderDetailsTMList);
    }

    @FXML
    public void saveOrderDetails(ActionEvent event) {
        System.out.println("click");
        try {
            String orderDetailId = txtOrderDetailsId.getText();
            String orderId = txtOrderId.getText();
            String productId = txtProductId.getText();
            int quantity = Integer.parseInt(txtQuantity.getText());
            double price = Double.parseDouble(txtPrice.getText());

            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(orderDetailId, orderId, productId, quantity, price);
            System.out.println(orderDetailsDTO);
            boolean isSaved = orderDetailsDAO.saveOrderDetails(orderDetailsDTO);

            if (isSaved) {
                showInfo("Success", "Order details saved successfully!");
                loadOrderDetailsTableData();
                clearForm();
            } else {
                showError("Error", "Failed to save order details.");
            }
        } catch (SQLException | NumberFormatException e) {
            showError("Error", "Error saving order details: " + e.getMessage());
        }
    }

    @FXML
    public void updateOrderDetails(ActionEvent event) {
        try {
            OrderDetailsTM selectedOrder = tblOrderdetails.getSelectionModel().getSelectedItem();

            if (selectedOrder == null) {
                showError("Error", "Please select an order to update.");
                return;
            }

            String orderDetailId = txtOrderDetailsId.getText();
            String orderId = txtOrderId.getText();
            String productId = txtProductId.getText();
            int quantity = Integer.parseInt(txtQuantity.getText());
            double price = Double.parseDouble(txtPrice.getText());

            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(orderDetailId, orderId, productId, quantity, price);

            boolean isUpdated = orderDetailsDAO.updateOrderDetails(orderDetailsDTO);

            if (isUpdated) {
                showInfo("Success", "Order details updated successfully!");
                loadOrderDetailsTableData();
                clearForm();
            } else {
                showError("Error", "Failed to update order details.");
            }
        } catch (SQLException | NumberFormatException e) {
            showError("Error", "Error updating order details: " + e.getMessage());
        }
    }

    @FXML
    public void deleteOrderDetails(ActionEvent event) {
        try {
            OrderDetailsTM selectedOrder = tblOrderdetails.getSelectionModel().getSelectedItem();

            if (selectedOrder == null) {
                showError("Error", "Please select an order to delete.");
                return;
            }

            String orderDetailId = selectedOrder.getOrder_details_id();

            boolean isDeleted = orderDetailsDAO.deleteOrderDetails(orderDetailId);

            if (isDeleted) {
                showInfo("Success", "Order details deleted successfully!");
                loadOrderDetailsTableData();
                clearForm();
            } else {
                showError("Error", "Failed to delete order details.");
            }
        } catch (SQLException e) {
            showError("Error", "Error deleting order details: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtOrderDetailsId.clear();
        txtOrderId.clear();
        txtProductId.clear();
        txtQuantity.clear();
        txtPrice.clear();
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
        OrderDetailsTM selectedOrder = tblOrderdetails.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            txtOrderDetailsId.setText(selectedOrder.getOrder_details_id());
            txtOrderId.setText(selectedOrder.getOrder_id());
            txtProductId.setText(selectedOrder.getProduct_id());
            txtQuantity.setText(String.valueOf(selectedOrder.getQuantity()));
            txtPrice.setText(String.valueOf(selectedOrder.getPrice()));
        }
    }

    @FXML
    public void handleAddOrderDetail(ActionEvent actionEvent) {
        System.out.println("click");
        if (!validateOrderDetails()) {
            try {
                String orderDetailId = txtOrderDetailsId.getText();
                String orderId = txtOrderId.getText();
                String productId = txtProductId.getText();
                int quantity = Integer.parseInt(txtQuantity.getText());
                double price = Double.parseDouble(txtPrice.getText());

                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(orderDetailId, orderId, productId, quantity, price);
                System.out.println(orderDetailsDTO);
                boolean isSaved = orderDetailsDAO.saveOrderDetails(orderDetailsDTO);

                if (isSaved) {
                    showInfo("Success", "Order detail added successfully!");
                    loadOrderDetailsTableData();
                    clearForm();
                } else {
                    showError("Error", "Failed to add order detail.");
                }
            } catch (SQLException | NumberFormatException e) {
                showError("Error", "Error adding order detail: " + e.getMessage());
            }
        }
    }


    @FXML
    public void handleUpdateOrderDetail(ActionEvent actionEvent) {
        if (!validateOrderDetails()) {
            try {
                OrderDetailsTM selectedOrder = tblOrderdetails.getSelectionModel().getSelectedItem();

                if (selectedOrder == null) {
                    showError("Error", "Please select an order detail to update.");
                    return;
                }

                String orderDetailId = txtOrderDetailsId.getText();// selectedOrder.getOrder_details_id();
                String orderId = txtOrderId.getText();
                String productId = txtProductId.getText();
                int quantity = Integer.parseInt(txtQuantity.getText());
                double price = Double.parseDouble(txtPrice.getText());

                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(orderDetailId, orderId, productId, quantity, price);

                boolean isUpdated = orderDetailsDAO.updateOrderDetails(orderDetailsDTO);

                if (isUpdated) {
                    showInfo("Success", "Order detail updated successfully!");
                    loadOrderDetailsTableData();
                    clearForm();
                } else {
                    showError("Error", "Failed to update order detail.");
                }
            } catch (SQLException | NumberFormatException e) {
                showError("Error", "Error updating order detail: " + e.getMessage());
            }
        }
    }

    private boolean validateOrderDetails() {
        String orderDetailId = txtOrderDetailsId.getText().trim();
        String orderId = txtOrderId.getText().trim();
        String productId = txtProductId.getText().trim();
        String quantityText = txtQuantity.getText().trim();
        String priceText = txtPrice.getText().trim();

        if (orderDetailId.isEmpty() || orderId.isEmpty() || productId.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
            showError("Validation Error", "All fields must be filled out.");
            return false;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                showError("Validation Error", "Quantity must be a positive integer.");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Validation Error", "Quantity must be a valid number.");
            return false;
        }

        try {
            double price = Double.parseDouble(priceText);
            if (price <= 0) {
                showError("Validation Error", "Price must be a positive number.");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Validation Error", "Price must be a valid number.");
            return false;
        }

        return true;
    }



    @FXML
    public void handleDeleteOrderDetail(ActionEvent actionEvent) {
        try {
            OrderDetailsTM selectedOrder = (OrderDetailsTM) tblOrderdetails.getSelectionModel().getSelectedItem();

            if (selectedOrder == null) {
                showError("Error", "Please select an order detail to delete.");
                return;
            }

            String orderDetailId = selectedOrder.getOrder_details_id();

            boolean isDeleted = orderDetailsDAO.deleteOrderDetails(orderDetailId);

            if (isDeleted) {
                showInfo("Success", "Order detail deleted successfully!");
                loadOrderDetailsTableData();
                clearForm();
            } else {
                showError("Error", "Failed to delete order detail.");
            }
        } catch (SQLException e) {
            showError("Error", "Error deleting order detail: " + e.getMessage());
        }
    }
    public String getNextOrderId() throws SQLException {
        ResultSet rst = Util.CrudUtil.execute("select order_id from order_details order by order_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);

            String substring = lastId.substring(1);

            int i = Integer.parseInt(substring);

            int newIdIndex = i + 1;

            return String.format("O%03d", newIdIndex);
        }


        return "O001";
    }

}












































