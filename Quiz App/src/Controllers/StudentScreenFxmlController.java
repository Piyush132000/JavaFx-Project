/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Listener.NewScreenListener;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class StudentScreenFxmlController implements Initializable {

    @FXML
    private JFXButton BackButton;
    @FXML
    private StackPane MainStackPane;

    /**
     * Initializes the controller class.
     */
    
    private void AddScreenToStackPane(Node node){
       this.MainStackPane.getChildren().add(node);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            FXMLLoader  root= new FXMLLoader(getClass().getResource("/fxml/QuizListFxml.fxml"));
            Node node=root.load();
             
            QuizListFxmlController QLFC=root.getController();
            QLFC.setScreenListener(new NewScreenListener(){
                @Override
                public void ChangeScreen(Node node) {
                    AddScreenToStackPane(node);
                     }

                @Override
                public void handle(Event event) {
                     }
            });
          
              MainStackPane.getChildren().add(node);   
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // TODO
    }    

    @FXML
    private void backButton(ActionEvent event) {
        ObservableList<Node> nodes=this.MainStackPane.getChildren();
        if(nodes.size()==1)
        {
            return;
            
        }
        
        this.MainStackPane.getChildren().remove(nodes.size()-1);
    }
    
}
