/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class AdminHomeScreenFxmlController implements Initializable {

    @FXML
    private JFXTabPane adminTabPane;
    @FXML
    private Tab addQuizTab;
    @FXML
    private Tab addStudentTab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        try {
             Parent root  = FXMLLoader.load(getClass().getResource("/fxml/AddQuizFxml.fxml"));
             addQuizTab.setContent(root);
             
              Parent StudentTab = FXMLLoader.load(getClass().getResource("/fxml/AdminStudentTabFxml.fxml"));
             addStudentTab.setContent(StudentTab);
        } catch (Exception e) {
           e.printStackTrace();
        }
        
      
       
        
    }    
    
}
 