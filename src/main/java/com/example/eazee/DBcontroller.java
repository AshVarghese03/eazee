package com.example.eazee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class DBcontroller {

    class BudgetStruct{
        String budgetAmt;
        String balanceAmt;
        String duration;
    }

    @FXML
    public void initialize() {

        UserInformation uinfo = UserInformation.getInstance();
        UserName.setText(uinfo.getUserName());

        BudgetStruct budgetStruct = getBudgetDetails();
        BalanceAmount.setText(budgetStruct.balanceAmt);

    }
    public static Connection databaseLink;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Label BalanceAmount;
    public Label UserName;



    public static Connection connectDb(){


            String databaseName = "pfm";
            String username= "root";
            String password= "dbit";

            String url = "jdbc:mysql://localhost:3306/" + databaseName;

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(url,username,password);
                System.out.println("connection-done");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
                System.out.println("connection-not-done");
            }
            return databaseLink;
        }
    public BudgetStruct getBudgetDetails(){
            String Sql ="SELECT * FROM budget WHERE b_id=1";
            DBcontroller db = new DBcontroller();
            Connection connectdb = db.connectDb();

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

    public void SwitchToCategory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/catmaster.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToTransactions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/trans.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToGoals(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/GOALS_2.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToBudget(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/budget.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToResult(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/report.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/login.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
