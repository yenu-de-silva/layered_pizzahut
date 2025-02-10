package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.CustomerBO;
import lk.ijse.gdse.bo.custom.ItemBO;
import lk.ijse.gdse.dto.ItemDTO;
import lk.ijse.gdse.dto.OrderDetailsDTO;
import lk.ijse.gdse.dto.tm.ItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ItemController {

    public TableView iterable;

    public TableColumn itemIdColumn;

    public TableColumn itemNameColumn;

    public TableColumn itemQuantityColumn;

    public TableColumn itemPriceColumn;

    public ImageView imageItem;


    public TextField txtName;

    public TextField txtQuantity;

    public TextField txtPrice;

    public Button btnSave;

    public Button btnupdate;

    public Button btndelete;

    public Button btnreset;

    public TextField txtid;

    @FXML
    private TableView<ItemTM> itemTable;

    @FXML
    private TableColumn<ItemTM, String> colItemId;

    @FXML
    private TableColumn<ItemTM, String> colName;

    @FXML
    private TableColumn<ItemTM, Integer> colQuantity;

    @FXML
    private TableColumn<ItemTM, Double> colPrice;

    @FXML
    private TextField itemIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane itemPane;

    @FXML
    private ImageView imageView;

    ItemBO itemBO= (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOType.ITEM);


    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]{3,30}$");
    private static final Pattern QUANTITY_PATTERN = Pattern.compile("^[0-9]{1,5}$");
    private static final Pattern PRICE_PATTERN = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");

    @FXML
    public void saveItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemId = itemIdField.getText().trim();
        String name = nameField.getText().trim();
        String quantity = quantityField.getText().trim();
        String price = priceField.getText().trim();

        if (validateItemDetails()) {
            ItemDTO itemDTO = new ItemDTO(itemId, name, Integer.parseInt(quantity), Double.parseDouble(price));
            boolean isSaved = itemBO.saveItem(itemDTO);

            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Item saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save item...!").show();
            }
        }
    }

    @FXML
    public void updateItemOnAction(ActionEvent event) throws SQLException {
        String itemId = itemIdField.getText().trim();
        String name = nameField.getText().trim();
        String quantity = quantityField.getText().trim();
        String price = priceField.getText().trim();

        if (validateItemDetails()) {
            ItemDTO itemDTO = new ItemDTO(itemId, name, Integer.parseInt(quantity), Double.parseDouble(price));
            boolean isUpdated = itemBO.updateItem(itemDTO);

            if (isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Item updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update item...!").show();
            }
        }
    }

    @FXML
    public void deleteItemOnAction(ActionEvent event) throws SQLException {
        String itemId = itemIdField.getText().trim();

        if (itemId.isEmpty()) {
            showAlert("Error", "Item ID cannot be empty", Alert.AlertType.ERROR);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = itemBO.deleteItem(itemId);

            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Item deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete item...!").show();
            }
        }
    }

    @FXML
    public void clearFieldsOnAction(ActionEvent event) {
        itemIdField.clear();
        nameField.clear();
        quantityField.clear();
        priceField.clear();
    }

    private boolean validateItemDetails() {
        boolean isValid = true;

        if (!NAME_PATTERN.matcher(nameField.getText().trim()).matches()) {
            nameField.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        if (!QUANTITY_PATTERN.matcher(quantityField.getText().trim()).matches()) {
            quantityField.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        if (!PRICE_PATTERN.matcher(priceField.getText().trim()).matches()) {
            priceField.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        return isValid;
    }

    private void refreshPage() throws SQLException {
        loadTableData();
        clearFieldsOnAction(null);
        btnReset.setDisable(false);
        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btndelete.setDisable(true);
    }

    private void loadTableData() throws SQLException {
        ObservableList<ItemTM> itemList = FXCollections.observableArrayList();
        List<ItemDTO> items = itemBO.getAllItems();

        for (ItemDTO item : items) {
            itemList.add(new ItemTM(item.getItem_id(), item.getName(), item.getQuantity(), item.getPrice()));
        }

        iterable.setItems(itemList);
    }

    @FXML
    public void initialize() throws SQLException {
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        loadTableData();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void resetOnAction(ActionEvent actionEvent) {
        itemIdField.clear();
        txtName.clear();
        txtQuantity.clear();
        txtPrice.clear();

        btnSave.setDisable(true);
        btnupdate.setDisable(false);
        btndelete.setDisable(true);
        btnreset.setDisable(true);
    }
    @FXML
    public void DeleteOnAction(ActionEvent actionEvent) {
        String itemId = txtid.getText().trim();

        if (itemId.isEmpty()) {
            showAlert("Error", "Item ID cannot be empty", Alert.AlertType.ERROR);
            return;
        }

        try {
            boolean result = itemBO.deleteItem(itemId);
            if (result) {
                showAlert("Success", "Item deleted successfully", Alert.AlertType.INFORMATION);
                resetOnAction(actionEvent);
            } else {
                showAlert("Error", "Failed to delete item", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            showAlert("Error", "Error occurred while deleting the item: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    public void UpdateOnAction(ActionEvent actionEvent) {
        String itemId = txtid.getText().trim();
        String name = txtName.getText().trim();
        String quantityText = txtQuantity.getText().trim();
        String priceText = txtPrice.getText().trim();

        if (itemId.isEmpty() || name.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
            showAlert("Error", "All fields are required", Alert.AlertType.ERROR);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            double price = Double.parseDouble(priceText);

            ItemDTO updatedItem = new ItemDTO(itemId, name, quantity, price);

            boolean result = itemBO.updateItem(updatedItem);
            if (result) {
                showAlert("Success", "Item updated successfully", Alert.AlertType.INFORMATION);
                resetOnAction(actionEvent);
            } else {
                showAlert("Error", "Failed to update item", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid quantity or price", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Error occurred while updating the item: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void SaveOnAction(ActionEvent actionEvent) {
        String itemId = txtid.getText().trim();
        String name = txtName.getText().trim();
        String quantityText = txtQuantity.getText().trim();
        String priceText = txtPrice.getText().trim();

        if (itemId.isEmpty() || name.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
            showAlert("Error", "All fields are required", Alert.AlertType.ERROR);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            double price = Double.parseDouble(priceText);

            ItemDTO newItem = new ItemDTO(itemId, name, quantity, price);

            boolean result = itemBO.saveItem(newItem);
            if (result) {
                showAlert("Success", "Item saved successfully", Alert.AlertType.INFORMATION);
                resetOnAction(actionEvent);
            } else {
                showAlert("Error", "Failed to save item", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid quantity or price", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Error occurred while saving the item: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void reduceQuantityOnAction(ActionEvent actionEvent) {
        String itemId = txtid.getText().trim();
        String quantityText = txtQuantity.getText().trim();

        if (itemId.isEmpty() || quantityText.isEmpty()) {
            showAlert("Error", "Item ID and quantity cannot be empty", Alert.AlertType.ERROR);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            String someOtherString = "";
            String anotherString = "";
            double price = 0.0;

            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(itemId, someOtherString, anotherString, quantity, price);

            boolean result = itemBO.reduceQty(orderDetailsDTO);

            if (result) {
                showAlert("Success", "Item quantity reduced successfully", Alert.AlertType.INFORMATION);
                resetOnAction(actionEvent);
            } else {
                showAlert("Error", "Failed to reduce item quantity", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid quantity entered", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Error occurred while reducing item quantity: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML

    public void loadItemIdsOnAction(ActionEvent actionEvent) {
        try {
            ArrayList<String> itemIds = itemBO.getAllItemIds();

            if (itemIds.isEmpty()) {
                showAlert("Warning", "No items available", Alert.AlertType.WARNING);
            }
        } catch (SQLException e) {
            showAlert("Error", "Failed to load item IDs: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
//    public boolean addItem(ItemDTO newItem) {
//        try {
//            return CrudUtil.execute(
//                    "INSERT INTO item (item_id, name, quantity, price) VALUES (?, ?, ?, ?)",
//                    newItem.getItem_id(),
//                    newItem.getName(),
//                    newItem.getQuantity(),
//                    newItem.getPrice()
//            );
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

//    public class CrudUtil {
//        public static boolean execute(String query, Object... params) throws SQLException {
//
//            try (Connection connection = DBConnection.getConnection();
//                 PreparedStatement statement = connection.prepareStatement(query)) {
//                for (int i = 0; i < params.length; i++) {
//                    statement.setObject(i + 1, params[i]);
//                }
//
//                return statement.executeUpdate() > 0;
//            }
//        }
//    }

}
