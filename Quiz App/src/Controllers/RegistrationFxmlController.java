/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class RegistrationFxmlController implements Initializable {

    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField MobileNo;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField Password;
    @FXML
    private JFXTextField Repassword;
    @FXML
    private JFXButton RegisterId;
    @FXML
    private JFXButton GoToLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GOTOLOGIN(ActionEvent event) throws IOException  {
        try{
        Parent root=FXMLLoader.load(getClass().getResource("/loginFxml.fxml"));
         Stage s=(Stage)GoToLogin.getScene().getWindow();
            Scene scene=new Scene(root);
            s.setScene(scene);
        
    
    }catch(Exception e){
    e.printStackTrace();
}
    
}
}