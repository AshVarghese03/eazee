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

public class TransactionController implements Initializable {

    @FXML
    private TextField Amount;

    @FXML
    private ChoiceBox TransType;

    @FXML
    public ComboBox listCategories;

    @FXML
    public Button SaveButton;

    @FXML
    private String[] Type = {"Debit", "Credit"};

    @FXML
    private Label informationBanner;

    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TransType.getItems().addAll(Type);
        String[] categories = GetCategoryList();
        for (String category : categories) {
            listCategories.getItems().add(category);
        }



    }


    public void SwitchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/db.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void AddTransaction(ActionEvent event) {
        try {

            String validation_response = ValidateInputs();
            if(validation_response=="Valid"){
                //System.out.println("Validated");
                int save_success = SaveToDB();
                if(save_success>=1){
                    System.out.println("save success");
                    informationBanner.setText("Save Success");

                    root = FXMLLoader.load(getClass().getResource("/com/example/eazee/trans.fxml")); //pass scene name here
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                   System.out.println("save failed");
                    informationBanner.setText("⚠ Transaction Already exist");

                }
            }
            else{
                informationBanner.setText("⚠ "+validation_response);
            }

        }
        catch (Exception ep){
            ep.printStackTrace();
        }
    }private String ValidateInputs() {


        //return "Valid";
        String selectedType = (String) TransType.getValue();
        String selectedCat = (String) listCategories.getValue();
        if(Amount.getText().length()==0){
            return "Amount cannot be empty";
        }
        else if (selectedType.length()==0) {
            return "Type cannot be empty";
        }
        else if (selectedCat.length()==0) {
            return "Category cannot be empty";
        }
        else{
            return "Valid";
        }


    }

    private int SaveToDB() {

        try {
            String selectedAmount = Amount.getText();
            String selectedType = (String) TransType.getValue();
            String selectedCat = (String) listCategories.getValue();
            UserInformation uinfo = UserInformation.getInstance();
            String user_id = uinfo.getUserID();
            String data_fields = "'"+selectedType+"' , '"+selectedCat+"', '"+selectedAmount+"','"+user_id+"'";
            String Sql = "INSERT INTO transaction (t_type,t_category,t_amt,username) VALUES ("+data_fields+")";
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
    public String[] GetCategoryList(){
        String Sql ="SELECT * FROM cat_master WHERE 1";
        DBcontroller db = new DBcontroller();
        Connection connectdb = db.connectDb();
        String categories[]={"Add Items"};

        try{
            PreparedStatement prepare = connectdb.prepareStatement(Sql);
            ResultSet result = prepare.executeQuery();
            //Alert alert;

            while(result.next()){
                categories = Arrays.copyOf(categories, categories.length + 1);
                categories[categories.length - 1] =  result.getString(2);
                System.out.println(result.getString(2));
            }

            return categories;
        }
        catch (Exception e) {
            e.printStackTrace();
            return categories;
        }

    }
}

