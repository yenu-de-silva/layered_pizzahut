package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class RatingPizzaController {

    @FXML
    private Label CHOICEOFCRUSTPIZZAId;

    @FXML
    private ImageView EATCLUBimage;

    @FXML
    private ImageView Scanimage;

    @FXML
    private Label SizesId;

    @FXML
    private Label discountId;

    @FXML
    private ImageView imaageId5;

    @FXML
    private ImageView image18;

    @FXML
    private ImageView image19;

    @FXML
    private ImageView image1Id;

    @FXML
    private ImageView image20;

    @FXML
    private ImageView image21;

    @FXML
    private ImageView image22;

    @FXML
    private ImageView image23;

    @FXML
    private ImageView image24;

    @FXML
    private ImageView image25;

    @FXML
    private ImageView image2Id;

    @FXML
    private ImageView image3Id;

    @FXML
    private ImageView imageI13;

    @FXML
    private ImageView imageId10;

    @FXML
    private ImageView imageId11;

    @FXML
    private ImageView imageId12;

    @FXML
    private ImageView imageId14;

    @FXML
    private ImageView imageId15;

    @FXML
    private ImageView imageId16;

    @FXML
    private ImageView imageId17;

    @FXML
    private ImageView imageId18;

    @FXML
    private ImageView imageId19;

    @FXML
    private ImageView imageId20;

    @FXML
    private ImageView imageId4;

    @FXML
    private ImageView imageId6;

    @FXML
    private ImageView imageId8;

    @FXML
    private ImageView imageId9;

    @FXML
    private ImageView imageSizeId1;

    @FXML
    private ImageView imageSizeId2;

    @FXML
    private Label line1Id;

    @FXML
    private Label line2Id;

    @FXML
    private Button next_btn;

    @FXML
    private Label tagId;

    private void startTransaction() {
        System.out.println("Starting transaction...");
    }

    private void commitTransaction() {
        System.out.println("Transaction committed successfully!");
    }

    private void rollbackTransaction() {
        System.out.println("Transaction rolled back due to an error.");
    }

    private boolean validateFields() {
        if (CHOICEOFCRUSTPIZZAId.getText() == null || CHOICEOFCRUSTPIZZAId.getText().isEmpty()) {
            showAlert("Please select a choice of crust for the pizza.");
            return false;
        }

        if (SizesId.getText() == null || SizesId.getText().isEmpty()) {
            showAlert("Please select a size for the pizza.");
            return false;
        }

        if (discountId.getText() == null || discountId.getText().isEmpty()) {
            showAlert("Please enter a discount, if applicable.");
            return false;
        }

        if (EATCLUBimage.getImage() == null) {
            showAlert("EAT CLUB image is not set.");
            return false;
        }

        if (Scanimage.getImage() == null) {
            showAlert("Scan image is not set.");
            return false;
        }

        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void nextOnAction(ActionEvent event) {
        if (validateFields()) {
            startTransaction();

            try {
                System.out.println("Saving pizza rating...");

                commitTransaction();

                showSuccessMessage("Pizza rating saved successfully!");

            } catch (Exception e) {
                rollbackTransaction();
                showAlert("An error occurred while saving the pizza rating. Transaction rolled back.");
            }
        }
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Pizza Rating Saved");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
