package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.CustomerBO;
import lk.ijse.gdse.bo.custom.ProductBO;
import lk.ijse.gdse.dao.SQLUtil;
import lk.ijse.gdse.dto.ProductDTO;
import lk.ijse.gdse.dto.tm.ProductTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {

    ProductBO productBO= (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);

    public TableView <ProductTM> tblProducts;

    public TableColumn <?,?> colProductId;

    public TableColumn <?,?>colName;

    public TableColumn <?,?>colCategory;

    public TableColumn <?,?>colInventoryCount;

    public TableColumn <?,?>colDescription;

    public TableColumn <?,?>colPrice;
    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtInventoryCount;

    @FXML
    private TableView<ProductTM> productTable;

    @FXML
    private TableColumn<ProductTM, String> productId;

    @FXML
    private TableColumn<ProductTM, String> name;

    @FXML
    private TableColumn<ProductTM, String> description;

    @FXML
    private TableColumn<ProductTM, String> inventorycount;

    @FXML
    private TableColumn<ProductTM, String> category;

    @FXML
    private TableColumn<ProductTM, Double> price;



    public boolean validateProductId(String productId) {
        return productId != null && !productId.trim().isEmpty();
    }

    public boolean validateProductName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 50;
    }

    public boolean validateProductDescription(String description) {
        return description == null || description.length() <= 200;
    }

    public boolean validateInventoryCount(int inventoryCount) {
        return inventoryCount >= 0;
    }

    public boolean validateCategory(String category) {
        return category != null && !category.trim().isEmpty();
    }

    public boolean validatePrice(double price) {
        return price > 0;
    }

    public boolean validateProduct(String productId, String name, String description, int inventoryCount, String category, double price) {
        return validateProductId(productId) &&
                validateProductName(name) &&
                validateProductDescription(description) &&
                validateInventoryCount(inventoryCount) &&
                validateCategory(category) &&
                validatePrice(price);
    }
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
            setCellValue();
            loadTableData();

    }

    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void setCellValue() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colInventoryCount.setCellValueFactory(new PropertyValueFactory<>("inventory_count"));
    }
    private void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Product Operation Failed");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Product Operation Successful");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> producta = productBO.getAllProducts();
        ObservableList<ProductTM> productTMS = FXCollections.observableArrayList();
        for (ProductDTO product : producta) {
            productTMS.add(new ProductTM(
                    product.getProduct_id(),
                    product.getProduct_name(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getCategory(),
                    product.getInventory_count()

            ));
        }
        tblProducts.setItems(productTMS);
    }
    @FXML
    public void saveOnClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        String productId = txtProductId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        int inventoryCount = Integer.parseInt(txtInventoryCount.getText());
        String category = txtCategory.getText();
        double price = Double.parseDouble(txtPrice.getText());

        ProductDTO productDTO = new ProductDTO(productId, name, price, description, category, inventoryCount);
        boolean issaved = productBO.save(productDTO);
        if (issaved){
            new Alert(AlertType.INFORMATION, "Product saved successfully!").show();
        }else{
            new Alert(AlertType.ERROR, "Failed to save product.").show();
        }
    }

    @FXML
    public void updateOnClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        String productId = txtProductId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        int inventoryCount = Integer.parseInt(txtInventoryCount.getText());
        String category = txtCategory.getText();
        double price = Double.parseDouble(txtPrice.getText());

        ProductDTO productDTO = new ProductDTO(productId, name, price, description, category, inventoryCount);
        boolean isUpdated = productBO.updateproduct(productDTO);
        if (isUpdated) {

            new Alert(AlertType.INFORMATION, "Customer updated...!").show();
        }else {
            new Alert(AlertType.ERROR, "Fail to update customer...!").show();
        }

    }

    @FXML
    public void deleteOnClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        String productId = txtProductId.getText();
        boolean isdelete = productBO.deleteproduct(productId);

        if (isdelete){
            new Alert(AlertType.CONFIRMATION,"product deleted successfully!").show();
        }else {
            new Alert(AlertType.ERROR,"Fail to delete product...!").show();
        }
    }

    @FXML
    public void handleSubmitAction(ActionEvent event) {

        System.out.println("Submit button clicked!");
    }

    @FXML
    public void addToCartOnClick(ActionEvent event) {

        System.out.println("Add to Cart button clicked!");
    }

    public String getNextProductId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT product_id FROM product ORDER BY product_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);

            String substring = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(substring);

            int newIdIndex = lastIdNumber + 1;
            return String.format("P%03d", newIdIndex);
        }

        return "P001";
    }

}
