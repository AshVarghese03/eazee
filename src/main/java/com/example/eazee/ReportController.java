package com.example.eazee;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportController {

    @FXML
    private Button backButton;

    @FXML
    private PieChart pieChart;



    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){
        //Preparing ObservbleList object
        ObservableList<PieChart.Data> pieChartData = getReportData();

        //Creating a Pie chart
        pieChart.setData(pieChartData);
        UserInformation holder = UserInformation.getInstance();
        // Step 3
    }

    public void SwitchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/login.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToDashBoard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/db.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    ObservableList<PieChart.Data> getReportData(){

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        String Sql ="SELECT SUM(t_amt),t_category FROM pfm.transaction WHERE 1 GROUP BY t_category;";
        DBcontroller db = new DBcontroller();
        Connection connectdb = db.connectDb();


        try{
            PreparedStatement prepare = connectdb.prepareStatement(Sql);
            ResultSet result = prepare.executeQuery();
            //Alert alert;

            while(result.next()){
                double cat_count = Double.valueOf(result.getString(1));
                pieChartData.add(new PieChart.Data(result.getString(2),cat_count ));
            }

            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), "(₹ ", data.pieValueProperty(), " )"
                            )
                    )
            );
            return pieChartData;
        }
        catch (Exception e) {
            e.printStackTrace();
            return pieChartData;
        }
    }
}
