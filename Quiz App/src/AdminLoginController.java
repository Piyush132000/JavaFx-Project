/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Student;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class AdminLoginController implements Initializable {

    @FXML
    private TextField AdminEmail;
    @FXML
    private PasswordField AdminPassword;
    @FXML
    private Button AdminLogin;
    @FXML
    private TextField StudentEmail;
    @FXML
    private PasswordField StudentPassword;
    @FXML
    private Button StudentLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoginAdmin(ActionEvent event) {
           try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminHomeScreenFxml.fxml"));
            Stage s=(Stage)AdminLogin.getScene().getWindow();
            Scene scene=new Scene(root);
            s.setScene(scene);
       
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    @FXML
    private void LoginStudent(ActionEvent event) {
        String Email=StudentEmail.getText().trim();
        String Password=StudentPassword.getText().trim();
        Student s=new Student(Email,Password);
        try{
            if(s.isExists())
            {
               Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentScreenFxml.fxml"));
            Stage se=(Stage)StudentLogin.getScene().getWindow();
            Scene scene=new Scene(root);
            se.setScene(scene);
           
                
             } 
            else{
                Notifications.create().darkStyle().title("Error").text("Incorrect Email and Password").position(Pos.CENTER).hideAfter(Duration.millis(3000)).showError();
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
