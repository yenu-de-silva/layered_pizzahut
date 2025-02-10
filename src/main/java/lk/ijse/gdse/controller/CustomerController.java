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
import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.tm.CustomerTM;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class CustomerController {

    CustomerBO customerBO= (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    public TableView tblcustomer_id;

    public TableColumn colContact;
@FXML
    private TextField ContactField;

    @FXML
    private Button Clear_btn;
    @FXML
    private Button Delete_btn;
    @FXML
    private Button Reset_btn;
    @FXML
    private Button Save_btn;
    @FXML
    private Button Update_btn;
    @FXML
    private TextField addressField;
    @FXML
    private TableColumn<CustomerTM, String> colAdress;
    @FXML
    private TableColumn<CustomerTM, String> colCustomerId;
    @FXML
    private TableColumn<CustomerTM, String> colEmail;
    @FXML
    private TableColumn<CustomerTM, String> colName;
    @FXML
    private TableColumn<CustomerTM, String> colPhone;
    @FXML
    private TextField customerIdFeild;
    @FXML
    private AnchorPane customerPane;
    @FXML
    private TextField emailFeild;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField nameFeild;
    @FXML
    private TableView<CustomerTM> tblCustomer_Id;


    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]{3,30}$");
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^[\\w\\s,.-]{5,50}$");

    @FXML
    void handleUpdateCustomer(ActionEvent event) throws IOException {
      /*  if (validateCustomerDetails()) {
            Connection connection = null;
            try {
                String sql = "UPDATE customers SET name = ?, email = ?, contact = ?, address = ? WHERE customer_id = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, nameFeild.getText());
                pstmt.setString(3, phoneField.getText());
                pstmt.setString(2, emailFeild.getText());
                pstmt.setString(4, addressField.getText());
                pstmt.setString(5, customerIdFeild.getText());

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    connection.commit();
                    showAlert("Success", "Customer updated successfully.");
                } else {
                    connection.rollback();
                    showAlert("Failure", "Customer update failed.");
                }
            } catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
                e.printStackTrace();
                showAlert("Error", "An error occurred while updating customer.");
            } finally {
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                        connection.close();
                    }
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }*/
    }

//    private void refreshPage() throws SQLException {
//        loadNextCustomerId();
//        loadTableData();
//
//        btnSave.setDisable(false);
//
//        btnUpdate.setDisable(true);
//        btnDelete.setDisable(true);
//
//        txtName.setText("");
//        txtContact.setText("");
//        txtEmail.setText("");
//        txtAddress.setText("");
//    }
//    private void loadTableData() throws SQLException {
//        ArrayList<CustomerDTO> customerDTOS = customerModel.getAllCustomers();
//
//        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
//        for (CustomerDTO customerDTO : customerDTOS) {
//            CustomerTM customerTM = new CustomerTM(
//                    customerDTO.getCustomerId(),
//                    customerDTO.getName(),
//                    customerDTO.getContact(),
//                    customerDTO.getEmail(),
//                    customerDTO.getAddress()
//            );
//            customerTMS.add(customerTM);
//        }
//
//        tblCustomer.setItems(customerTMS);
//    }
//
//    public void loadNextCustomerId() throws SQLException {
//        String nextCustomerId = customerModel.getNextCustomerId();
//        lblCustomerId.setText(nextCustomerId);
//    }



    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println("asdfghjkl");
        String customerId = customerIdFeild.getText();
        String name = nameFeild.getText();
        String contact = ContactField.getText();
        String email = emailFeild.getText();
        String address = addressField.getText();
/*
        customerIdFeild.setStyle(customerIdFeild.getStyle()+";-fx-border-color: #7367F0;");
        nameFeild.setStyle(nameFeild.getStyle()+";-fx-border-color: #7367F0;");
        emailFeild.setStyle(emailFeild.getStyle()+";-fx-border-color: #7367F0;");
        phoneField.setStyle(phoneField.getStyle()+";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?{|}~^-]+(?:\\.[\\w!#$%&'*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidcustomerIdFeild = customerId.matches(addressPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidcontact = contact.matches(contactPattern);

        if (!isValidName){
            System.out.println(nameFeild.getStyle());
            nameFeild.setStyle(nameFeild.getStyle()+";-fx-border-color: red;");
            System.out.println("Invalid name.............");
        }

        if (!isValidEmail){
            emailFeild.setStyle(emailFeild.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidcontact){
            phoneField.setStyle(phoneField.getStyle()+";-fx-border-color: red;");
        }


        if (isValidName && isValidEmail && isValidcontact){
            CustomerDTO customerDTO = new CustomerDTO(customerId, name, contact, email, address);

            boolean isSaved = customerModel.saveCustomer(customerDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
        }
    */

        CustomerDTO customerDTO = new CustomerDTO(customerId, name, contact, email, address);

        boolean isSaved = customerBO.saveCustomer(customerDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
        }
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        initialize(null, null);
        loadTableData();

    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
/*
        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }*/
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextCustomerId();
        loadTableData();


        Save_btn.setDisable(false);

        Update_btn.setDisable(false);
        Delete_btn.setDisable(false);

        nameFeild.setText("");
        addressField.setText("");
        emailFeild.setText("");
        ContactField.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTM> addCustomer = FXCollections.observableArrayList();

        List<CustomerTM> list = customerBO.getAllCustomers();

        for (CustomerTM customerTM : list) {
            System.out.println("qwertyu");
            addCustomer.add(customerTM);
        }
        tblcustomer_id.setItems(addCustomer);
    }

    private void loadNextCustomerId() {
    }




    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = customerIdFeild.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = customerBO.deleteCustomer(customerId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = customerIdFeild.getText();
        String name = nameFeild.getText();
        String contact = ContactField.getText();
        String email = emailFeild.getText();
        String address = addressField.getText();

        CustomerDTO customerDTO = new CustomerDTO(customerId, name, contact, email, address);

        boolean isUpdated = customerBO.updateCustomer(customerDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer updated...!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
        }


      /*  String customerId = customerIdFeild.getText();
        String name = nameFeild.getText();
        String contact = phoneField.getText();
        String email = emailFeild.getText();
        String address = addressField.getText();

        nameFeild.setStyle(nameFeild.getStyle()+";-fx-border-color: #7367F0;");
        phoneField.setStyle(phoneField.getStyle()+";-fx-border-color: #7367F0;");
        emailFeild.setStyle(emailFeild.getStyle()+";-fx-border-color: #7367F0;");
        addressField.setStyle(addressField.getStyle()+";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
//        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?{|}~^-]+(?:\\.[\\w!#$%&'*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
//        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidContact = contact.matches(phonePattern);

        if (!isValidName){
            System.out.println(nameFeild.getStyle());
            nameFeild.setStyle(nameFeild.getStyle()+";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }
//
//        if (!isValidContact){
//            phoneField.setStyle(phoneField.getStyle()+";-fx-border-color: red;");
////            return;
//        }

        if (!isValidEmail){
            emailFeild.setStyle(emailFeild.getStyle()+";-fx-border-color: red;");
        }
*//*
        if (!isValidAddress){
            addressField.setStyle(addressField.getStyle()+";-fx-border-color: red;");
        }*//*

        if (isValidName && isValidContact && isValidEmail && isValiAddress) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customerIdFeild,
                    nameFeild,
                    phoneField,
                    emailFeild,
                    addressField
            );

            boolean isUpdate = customerModel.updateCustomer(customerDTO);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer update...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
            }
        }*/
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }
   /* private boolean validateCustomerDetails() {
        if (!validateName()) {
            showAlert("Invalid Name", "Please enter a valid name (3-30 alphabetic characters).");
            return false;
        }
        if (!validateEmail()) {
            showAlert("Invalid Email", "Please enter a valid email address.");
            return false;
        }
        if (!validateContact()) {
            showAlert("Invalid Contact", "Please enter a valid 10-digit contact number.");
            return false;
        }
        if (!validateAddress()) {
            showAlert("Invalid Address", "Please enter a valid address (5-50 characters).");
            return false;
        }
        return true;
    }
*/
    private boolean validateName() {
        return NAME_PATTERN.matcher(nameFeild.getText()).matches();
    }

    private boolean validateEmail() {
        return EMAIL_PATTERN.matcher(emailFeild.getText()).matches();
    }

    private boolean validatePhone() {
        return PHONE_PATTERN.matcher(ContactField.getText()).matches();
    }

    private boolean validateAddress() {
        return ADDRESS_PATTERN.matcher(addressField.getText()).matches();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }}

