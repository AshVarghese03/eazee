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
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Arrays;


public class CategoryController {

    @FXML
    private TextField categoryName;

    @FXML
    private TextField newCategoryName;

    @FXML
    private Label informationBanner;

    @FXML
    private Button SubmitButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button Updatebutton;

    @FXML
    private ChoiceBox listCategories;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String[] categories = GetCategoryList();
        for (String category : categories) {
            listCategories.getItems().add(category);
        }
    }


    public void AddCategory(ActionEvent event) {
        try {

            String validation_response = ValidateInputs();
            if(validation_response=="Valid"){
                //System.out.println("Validated");
                int save_success = SaveToDB();
                if(save_success>=1){
                    System.out.println("save success");
                    informationBanner.setText("Save Success");

                    root = FXMLLoader.load(getClass().getResource("/com/example/eazee/catmaster.fxml")); //pass scene name here
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    System.out.println("save failed");
                    informationBanner.setText("⚠ Category Already exist");

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
        if(categoryName.getText().length()==0){
            return "Category cannot be empty";
        }
        else{
            return "Valid";
        }


    }

    private int SaveToDB() {

        try {
            String data_fields = "'"+categoryName.getText()+"'";
            String Sql = "INSERT INTO cat_master (catname) VALUES ("+data_fields+")";
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
            }
            System.out.println(categories);

            return categories;
        }
        catch (Exception e) {
            e.printStackTrace();
            return categories;
        }
    }

    public void UpdateCategory(ActionEvent event){
        try {

            String validation_response = "Valid";
            if(validation_response=="Valid"){
                //System.out.println("Validated");
                int save_success = UpdateCategoryInDB();
                if(save_success>=1){
                    System.out.println("save success");
                    informationBanner.setText("Save Success");

                    root = FXMLLoader.load(getClass().getResource("/com/example/eazee/catmaster.fxml")); //pass scene name here
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    System.out.println("save failed");
                    informationBanner.setText("⚠ Category Not Found");

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

    private int UpdateCategoryInDB() {

        try {
            String selectedVal = (String) listCategories.getValue();
            String Sql = "UPDATE cat_master SET catname= '"+newCategoryName.getText()+"' WHERE catname = '"+selectedVal+"'";
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

    public void DeleteCategory(ActionEvent event){
        try {

            String validation_response = "Valid";
            if(validation_response=="Valid"){
                //System.out.println("Validated");
                int save_success = DeleteCategoryInDB();
                if(save_success>=1){
                    System.out.println("save success");
                    informationBanner.setText("Save Success");

                    root = FXMLLoader.load(getClass().getResource("/com/example/eazee/catmaster.fxml")); //pass scene name here
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    System.out.println("save failed");
                    informationBanner.setText("⚠ Category Not Found");

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
    private int DeleteCategoryInDB() {

        try {
            String selectedVal = (String) listCategories.getValue();
            String Sql = "DELETE FROM cat_master WHERE catname = '"+selectedVal+"'";
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
    public void SwitchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/db.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
