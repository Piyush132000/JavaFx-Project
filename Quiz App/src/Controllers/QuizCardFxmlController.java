/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Listener.NewScreenListener;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import models.Quiz;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class QuizCardFxmlController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Label noq;
    @FXML
    private JFXButton StartQuiz;
    private Quiz quiz;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
       this.title.setText(this.quiz.getTitle());
    }
   
    
    private NewScreenListener screenListener;
    
     public void setScreenListener(NewScreenListener screenListener) {
        this.screenListener = screenListener;
        }
    


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    
    @FXML
    private void StartQuiz(ActionEvent event) {
        FXMLLoader root=new FXMLLoader(getClass().getResource("/fxml/QuestionScreenFxml.fxml"));
        try{
          
              Node node=root.load();
                QuestionScreenFxmlController QSFC=root.getController();
            QSFC.setQuiz(this.quiz);
             
          
             
              this.screenListener.ChangeScreen(node);
        }
        catch(IOException e)
                {
                    e.printStackTrace();
                }
      
       
    }

   

    public void setNoq(String Value) {
        this.noq.setText(Value);
    }

   

}
