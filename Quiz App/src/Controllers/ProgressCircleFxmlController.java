/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class ProgressCircleFxmlController implements Initializable {

    @FXML
    private Circle Circle;
    @FXML
    private Label No;

    public void setNo(Integer No) {
        this.No.setText(No.toString());
        setDefaultColor();
    }
    
    public void setDefaultColor(){
        Circle.setFill(Color.web("#F3B431"));
        No.setTextFill(Color.valueOf("black"));
    }
    
    public void setCurrentQuestionColor(){
        Circle.setFill(Color.web("#0ABDE3"));
        No.setTextFill(Color.valueOf("white"));
    }
     public void setWrongAnswerColor(){
        Circle.setFill(Color.web("#EC4849"));
        No.setTextFill(Color.valueOf("white"));
    }
      public void setRightAnswerColor(){
        Circle.setFill(Color.web("#75DA8B"));
        No.setTextFill(Color.valueOf("white"));
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
