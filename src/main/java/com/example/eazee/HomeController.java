package com.example.eazee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;


public class HomeController {
    @FXML
    private Button LoginButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/login.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    /*
    public void SwitchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/dashboard.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    */
    public void SwitchToRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/register.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
