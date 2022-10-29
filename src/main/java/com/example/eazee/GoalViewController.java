package com.example.eazee;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class GoalViewController implements Initializable{
    UserInformation uinfo = UserInformation.getInstance();

    @FXML
    public TableView<Goal> goalTable;

    @FXML
    public TableColumn slno;
    @FXML
    public TableColumn g_name;
    @FXML
    public TableColumn g_duration;
    @FXML
    public TableColumn g_amt;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        slno.setCellValueFactory(new PropertyValueFactory<>("slNo"));
        g_name.setCellValueFactory(new PropertyValueFactory<>("goalName"));
        g_duration.setCellValueFactory(new PropertyValueFactory<>("goalDuration"));
        g_amt.setCellValueFactory(new PropertyValueFactory<>("goalAmt"));

        slno.setStyle( "-fx-alignment: CENTER;");
        g_name.setStyle( "-fx-alignment: CENTER;");
        g_duration.setStyle( "-fx-alignment: CENTER;");
        g_amt.setStyle( "-fx-alignment: CENTER;");

        //goalTable.getItems().add(new Goal("John", "Doe","ss","ss"));
        //goalTable.getItems().add(new Goal("sa", "asdadsad","sasds","sasds"));
        final ObservableList<Goal> data = getGoalData();

        goalTable.setItems(data);
        goalTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //goalTable.getColumns().addAll(fileNameCol, pathCol, sizeCol, dateCol);
    }
    public void SwitchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/eazee/db.fxml")); //pass scene name here
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public class Goal {

        private String slNo = null;
        private String goalName = null;
        private String goalDuration= null;
        private String goalAmt = null;


        public Goal(String slNo,String goalName,String goalDuration, String goalAmt) {
            setSlNo(slNo);
            setGoalName(goalName);
            setGoalDuration(goalDuration);
            setGoalAmt(goalAmt);
            System.out.println(this.goalAmt);
        }

        public String getSlNo() {
            return slNo;
        }

        public void setSlNo(String slNo) {
            this.slNo = slNo;
        }

        public String getGoalName() {
            return goalName;
        }

        public void setGoalName(String goalName) {
            this.goalName = goalName;
        }

        public String getGoalDuration() {
            return goalDuration;
        }

        public void setGoalDuration(String goalDuration) {
            this.goalDuration = goalDuration;
        }

        public String getGoalAmt() {
            return goalAmt;
        }

        public void setGoalAmt(String goalAmt) {
            this.goalAmt = goalAmt;
        }
    }

    ObservableList<Goal> getGoalData(){
        ObservableList<Goal> goalData = FXCollections.observableArrayList();

        String Sql ="SELECT goal_id,g_name,g_amount,g_period from goals WHERE username= '"+uinfo.getUserID()+"' ;";
        DBcontroller db = new DBcontroller();
        Connection connectdb = db.connectDb();

        try{
            PreparedStatement prepare = connectdb.prepareStatement(Sql);
            ResultSet result = prepare.executeQuery();
            //Alert alert;
            int slno=1;
            while(result.next()){
                double cat_count = Double.valueOf(result.getString(1));
                goalData.add(new Goal(Integer.toString(slno), result.getString(2), result.getString(4), result.getString(3)));
                slno++;
            }
            return goalData;
        }
        catch (Exception e) {
            e.printStackTrace();
            return goalData;
        }
    }

}

