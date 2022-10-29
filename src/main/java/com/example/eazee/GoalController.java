package com.example.eazee;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.*;

import static javax.swing.text.html.HTML.Tag.SELECT;
public class GoalController implements Initializable{
    @FXML
    private TextField GoalAmount;
    @FXML
    private TextField GoalName;
    @FXML
    private ComboBox<String> GoalDuration;
    @FXML
    public Button SaveButton;

    @FXML
    private String[] duration = {"Monthly", "Weekly"};

    @FXML
    private Label informationBanner;

    private Stage stage;
    private Scene scene;
    private Parent root;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GoalDuration.getItems().addAll(duration);

    }
    public void SwitchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/db.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   public void AddGoal(ActionEvent event) {
        try {

            String validation_response = ValidateInputs();
            if(validation_response=="Valid"){
                //System.out.println("Validated");
                int save_success = SaveToDB();
                if(save_success>=1){
                    System.out.println("save success");
                    informationBanner.setText("Save Success");

                    root = FXMLLoader.load(getClass().getResource("/com/example/eazee/GOALS_2.fxml")); //pass scene name here
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    System.out.println("save failed");
                    informationBanner.setText("⚠ Goal exist");

                }
            }
            else{
                informationBanner.setText("⚠ "+validation_response);
            }

        }
        catch (Exception ep){
            ep.printStackTrace();
        }
    }
    public void ViewGoal(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/GoalsView.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private String ValidateInputs() {


        //return "Valid";
        String selectedAmount = (String) GoalAmount.getText();
        String selectedGoalName = (String) GoalName.getText();
        String selectedGoalDuration = (String) GoalDuration.getValue();
        if(selectedAmount.length()==0){
            return "Amount cannot be empty";
        }
        else if (selectedGoalName.length()==0) {
            return "Name cannot be empty";
        }
        else if (selectedGoalDuration.length()==0) {
            return "Duration cannot be empty";
        }
        else{
            return "Valid";
        }


    }
    private int SaveToDB() {

        try {
            String selectedAmount = (String) GoalAmount.getText();
            String selectedGoalName = (String) GoalName.getText();
            String selectedGoalDuration = (String) GoalDuration.getValue();
            UserInformation uinfo = UserInformation.getInstance();
            String user_id = uinfo.getUserID();
            String data_fields = "'"+selectedGoalName+"' , "+selectedAmount+", '"+selectedGoalDuration+"','"+user_id+"'";

            String Sql = "INSERT INTO goals (g_name,g_amount,g_period,username) VALUES ("+data_fields+")";
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

}

