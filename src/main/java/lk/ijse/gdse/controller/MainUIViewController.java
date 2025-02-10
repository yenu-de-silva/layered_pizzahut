package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainUIViewController {

    @FXML
    private AnchorPane loadPanel;

    @FXML
    private Button startBtn;

    @FXML
    void OnClickStart(ActionEvent event) throws IOException {
        loadPanel.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/PizzaHubHomeView.fxml.fxml"));
        loadPanel.getChildren().add(pane);

    }

}
