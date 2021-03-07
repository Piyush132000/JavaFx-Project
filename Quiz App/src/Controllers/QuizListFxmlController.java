  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Listener.NewScreenListener;
import java.net.URL;
import java.util.Map;
import java.util.*;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import models.Quiz;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class QuizListFxmlController implements Initializable {

    @FXML
    private FlowPane quizListContainer;
    Map<Quiz,Integer> Quizes=null;
    private NewScreenListener screenListener;
    private Set<Quiz> keys; 
    
    public void setScreenListener(NewScreenListener screenListener) { 
        this.screenListener = screenListener;
        setCard();
    }

    /**
     * Initializes the controller class.
     */
    private void setCard(){
        keys.forEach(new Consumer<Quiz>() {
          
            @Override
            public void accept(Quiz q) {
                try {
                    FXMLLoader root = new FXMLLoader(QuizListFxmlController.this.getClass().getResource("/fxml/QuizCardFxml.fxml"));
                    Node node=root.load();
                    QuizCardFxmlController QCFC = root.getController();
                   // QCFC.setTitle(q.getTitle());
                   QCFC.setQuiz(q);
                    QCFC.setNoq("No. of Questions: "+Quizes.get(q));
                  QCFC.setScreenListener(screenListener);
                    quizListContainer.getChildren().add(node);
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                    
                }
            }
        });
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Quizes=Quiz.getAllWithQuestionCount();
         keys=Quizes.keySet();  
    }

    
    
}
