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
import java.sql.*;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label invalidlogin;
    @FXML
    private Button SubmitButton;

    @FXML
    private Button signUpLinkLabel;

    private Stage stage;
    private Scene scene;
    public boolean res;
    private Parent root;

    public void Signin(ActionEvent event){
        String Sql ="SELECT * FROM user WHERE username = ? AND password = ?";
        DBcontroller db = new DBcontroller();
        Connection connectdb = db.connectDb();

        try{
            PreparedStatement prepare = connectdb.prepareStatement(Sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            ResultSet result = prepare.executeQuery();
            //Alert alert;
            res=false;
            while(result.next()){
                //System.out.println("-"+result.getString(2)+ "- : -"+ username.getText()+"-");
                if(result.getString(1).equals(username.getText()))
                {
                    UserInformation holder = UserInformation.getInstance();
                    holder.setUser(result.getString(1),result.getString(4),result.getString(3));
                    res=true;
                }
              //System.out.println(result.getString(2));
            }

            if(username.getText().isEmpty()||password.getText().isEmpty()) {
                System.out.println("Here ! - empty ");
                //alert = new Alert(Alert.AlertType.ERROR);
                //alert.setTitle("error message");
                //alert.setHeaderText("null");
                invalidlogin.setText("⚠ please fill all the details");

            }else if (res) {

                System.out.println("Here ! - success");
                //alert = new Alert(Alert.AlertType.INFORMATION);
                //alert.setTitle("INFORMATION MESSAGE");
                //alert.setHeaderText("null");
                //alert.setContentText("successfully login");
                invalidlogin.setText("⚠ login success");

                ((Node)(event.getSource())).getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("db.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Here ! - user not found ");
                //alert = new Alert(Alert.AlertType.ERROR);
                //alert.setTitle("error message");
                //alert.setHeaderText("null");
                invalidlogin.setText("⚠ wrong username/password");
                //alert.setContentText("wrong username/password");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }

    public void Signup(ActionEvent event) {
        try {
            FXMLLoader load1 = new FXMLLoader(getClass().getResource("Signup.fxml"));
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent root = load1.load();
            Stage scene1 =new Stage();
            scene1.setScene(new Scene((root)));
            scene1.show();
        }
        catch (Exception ep){
            ep.printStackTrace();
        }
    }

    public void signUpLink(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/register.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
