 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import models.Question;
import models.Quiz;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class QuestionScreenFxmlController implements Initializable {

    @FXML
    private Label Title;
    @FXML
    private Label Time;
    @FXML
    private Label Question;
    @FXML
    private JFXRadioButton Option1;
    @FXML
    private ToggleGroup Options;
    @FXML
    private JFXRadioButton Option2;
    @FXML
    private JFXRadioButton Option3;
    @FXML
    private JFXRadioButton Option4;
    @FXML
    private JFXButton Next;
    @FXML
    private JFXButton Submit;
    private Quiz quiz;
    private List<Question> questionList;
    int currentIndex=0;
    @FXML
    private FlowPane FlowPane;
    ProgressCircleFxmlController PCFC;
    

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        this.Title.setText(this.quiz.getTitle());
        this.getData(); 
    }
     private void getData(){
         if(quiz!=null)
         {
             this.questionList=quiz.getQuestions();
             renderProgess();
             setNextQuestion();
             
         }
     }
     private Question currentQuestion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        this.showNextQuestionButton();
        this.hideSubmitQuizButton();
         radioButtonSetUp();
         
       
       
    } 
   
    private void renderProgess(){
         for (int i=0;i<this.questionList.size();i++)
       {
           try{
           FXMLLoader root= new FXMLLoader(getClass().getResource("/fxml/ProgressCircleFxml.fxml"));
          
           Node node=root.load();
            PCFC=root.getController();
           PCFC.setNo(i+1);
           
           FlowPane.getChildren().add(node);
           }
           catch( Exception e){
               e.printStackTrace();
           }
           
       }
        
    }
    
    
     private void radioButtonSetUp(){
    Options= new ToggleGroup();
    Option1.setToggleGroup(Options);
    Option2.setToggleGroup(Options);
    Option3.setToggleGroup(Options);
    Option4.setToggleGroup(Options);
    }
      

    @FXML
    private void NextQuestion(ActionEvent event) {
       this.dataChecking();
       this.setNextQuestion();
       
        
    }
    
    private void dataChecking(){
        Toggle selected=Options.getSelectedToggle();
         System.out.println(selected);
        String ans=null;
        if(selected==Option1){
            ans=Option1.getText().trim();
        }
        else if(selected==Option2){
            ans=Option2.getText().trim();
        }
        else if(selected==Option3)
        {
            ans=Option3.getText().trim();
        }
        else if(selected==Option4)
        {
            ans=Option4.getText().trim();
        }
        System.out.println(ans);
       String Answer=this.currentQuestion.getAnswer();
       System.out.println(Answer);
            
          Node CircleNode =  this.FlowPane.getChildren().get(currentIndex-1);
         ProgressCircleFxmlController controller=(ProgressCircleFxmlController)CircleNode.getUserData();
       if(Answer.equals(ans))
       {
         controller.setRightAnswerColor();
           System.out.println( "Right answer "+ans);
           
       }
       else
       {
        controller.setWrongAnswerColor();
           System.out.println("Worong answer "+ans);
       }
       Option1.setSelected(false);
       Option2.setSelected(false);
       Option3.setSelected(false);
       Option4.setSelected(false);
     
    }
    
    
   
    
   

    private void setNextQuestion(){
        Collections.shuffle(questionList, new Random());
        if(!(currentIndex >= this.questionList.size()) ){
          
            
          Node CircleNode =  this.FlowPane.getChildren().get(currentIndex);
         ProgressCircleFxmlController controller=(ProgressCircleFxmlController)CircleNode.getUserData();
         controller.setCurrentQuestionColor();
       
            
            
        this.currentQuestion=this.questionList.get(currentIndex);
        List<String> options =new ArrayList<>();
        options.add(this.currentQuestion.getOption1());
        options.add(this.currentQuestion.getOption2());
        options.add(this.currentQuestion.getOption3());
        options.add(this.currentQuestion.getOption4());
        Collections.shuffle(options);
        this.Question.setText(this.currentQuestion.getQuestion());
        this.Option1.setText(options.get(0));
        this.Option2.setText(options.get(1));
        this.Option3.setText(options.get(2));
        this.Option4.setText(options.get(3));
        currentIndex++;
    }else
        {
            hideNextQuestionButton();
            showSubmitQuizButton();
           // Notifications.create().darkStyle().text("No Questions").position(Pos.CENTER).showInformation();
        }
        
    }
    
    
    private void hideNextQuestionButton(){
        this.Next.setVisible(false);
    }
      private void showNextQuestionButton()
      {
        this.Next.setVisible(true);
      }
        private void showSubmitQuizButton(){
        this.Submit.setVisible(true);
    }
      private void hideSubmitQuizButton(){
        this.Submit.setVisible(false);
      }
    
    
    @FXML
    private void SubmitQuiz(ActionEvent event) {
    }
    

   
}
