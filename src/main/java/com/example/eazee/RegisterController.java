package com.example.eazee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.regex.*;

public class RegisterController {

    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField re_enter;
    @FXML
    private TextField email;
    @FXML
    private Label registrationHelp;
    @FXML
    private Button SubmitButton;



    private Stage stage;
    private Scene scene;
    private Parent root;


    public void Register(ActionEvent event) {
        try {

            String validation_response = ValidateInputs();
            if(validation_response=="Valid"){
                System.out.println("Validated");
                int save_success = SaveToDB();
                if(save_success>=1){
                    System.out.println("save success");
                    registrationHelp.setText("Save Success");

                    root = FXMLLoader.load(getClass().getResource("/com/example/eazee/login.fxml")); //pass scene name here
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    System.out.println("save failed");
                    registrationHelp.setText("⚠ User Already exist");

                }
            }
            else{
                registrationHelp.setText("⚠ "+validation_response);
            }

        }
        catch (Exception ep){
            ep.printStackTrace();
        }
    }
    private String ValidateInputs() {

        Pattern p = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
        Matcher m = p.matcher(email.getText());
        boolean valid_email = false;
        if (m.find()){
            valid_email = true;
        }

        //return "Valid";
        if((password.getText().length()==0)||(re_enter.getText().length()==0)){
            return "Password cannot be empty";
        }
        else if(username.getText().length()==0) {
            return "Username cannot be empty";
        }
        else if(name.getText().length()==0) {
            return "Name cannot be empty";
        }
        else if(email.getText().length()==0) {
            return "E-mail cannot be empty";
        }
        else if(!(password.getText().equals(re_enter.getText()))){
            return "Passwords must be the same";
        }
        else if(!valid_email){
            return "Please enter valid email";
        }
        else{
            return "Valid";
        }


    }

    private int SaveToDB() {

        try {
            String data_fields = "'"+username.getText()+"','"+password.getText()+"','"+email.getText()+"','"+name.getText()+"'";;
            String Sql = "INSERT INTO user VALUES ("+data_fields+")";
            System.out.println(Sql);

            DBcontroller db = new DBcontroller();
            Connection connectdb = db.connectDb();
            PreparedStatement prepare = connectdb.prepareStatement(Sql);
            return prepare.executeUpdate(Sql);

        }
        catch (Exception ep){
            ep.printStackTrace();
            return 0;
        }
    }
    public void SwitchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/login.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
