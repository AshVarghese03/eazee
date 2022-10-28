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

public class BudgetController implements Initializable {
    class BudgetStruct{
        String budgetAmt;
        String balanceAmt;
        String duration;
    }


    @FXML
    private TextField BudgetAmount;

    @FXML
    private TextField BalanceAmount;

    @FXML
    public ChoiceBox DurationList;

    @FXML
    public Button SaveButton;

    @FXML
    private Label informationBanner;

    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BudgetStruct budgetStruct = getBudgetDetails();
        BudgetAmount.setText(budgetStruct.budgetAmt);
        BalanceAmount.setText(budgetStruct.balanceAmt);
        String[] categories = {"Monthly","Weekly"};
        for (String category : categories) {
            DurationList.getItems().add(category);
        }
    }


    public void SwitchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/db.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SaveBudget(ActionEvent event) {
        try {

            String validation_response = ValidateInputs();
            if(validation_response=="Valid"){
                //System.out.println("Validated");
                int save_success = SaveToDB();
                if(save_success>=1){
                    System.out.println("save success");
                    informationBanner.setText("Save Success");

                    root = FXMLLoader.load(getClass().getResource("/com/example/eazee/budget.fxml")); //pass scene name here
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    System.out.println("save failed");
                    informationBanner.setText("⚠ Something Went Wrong");

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
    private String ValidateInputs() {


        //return "Valid";
        String selectedDur = (String) DurationList.getValue();
        String selectedBud = (String) BudgetAmount.getText();
        String selectedBal = (String) BalanceAmount.getText();
        if(selectedDur.length()==0){
            return "Duration cannot be empty";
        }
        else if (selectedBud.length()==0) {
            return "Budget cannot be empty";
        }
        else if (selectedBal.length()==0) {
            return "Balance cannot be empty";
        }
        else{
            return "Valid";
        }


    }

    private int SaveToDB() {

        try {
            String selectedAmount = BudgetAmount.getText();
            String selectedBalance = BalanceAmount.getText();

            String selectedDuration = (String) DurationList.getValue();

            String Sql = "UPDATE budget SET b_amount ="+selectedAmount+", b_balance = "+selectedBalance+", b_duration = '"+selectedDuration+"' WHERE b_id=1;";
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


    public BudgetStruct getBudgetDetails(){
        String Sql ="SELECT * FROM budget WHERE b_id=1";
        DBcontroller db = new DBcontroller();
        Connection connectdb = db.connectDb();
        String[] categories;

        BudgetStruct budgetData=new BudgetStruct();
        try{
            PreparedStatement prepare = connectdb.prepareStatement(Sql);
            ResultSet result = prepare.executeQuery();
            //Alert alert;

            while(result.next()){
                budgetData.budgetAmt=result.getString(2);
                budgetData.balanceAmt=result.getString(3);
                budgetData.duration=result.getString(4);
            }
            return budgetData;

        }
        catch (Exception e) {
            e.printStackTrace();
            return budgetData;
        }
    }

}