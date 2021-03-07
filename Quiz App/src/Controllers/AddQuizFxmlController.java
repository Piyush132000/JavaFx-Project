/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.util.Duration;
import models.Question;
import models.Quiz;


import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class AddQuizFxmlController implements Initializable {

    @FXML
    private JFXTextField quizName;
    @FXML
    private JFXTextArea question;
    @FXML
    private JFXTextField option1;
    @FXML
    private JFXTextField option2;
    @FXML
    private JFXTextField option3;
    @FXML
    private JFXTextField option4;
    @FXML
    private JFXRadioButton RB1;
    @FXML
    private JFXRadioButton RB2;
    @FXML
    private JFXRadioButton RB3;
    @FXML
    private JFXRadioButton RB4;
    @FXML
    private JFXButton addNextQuestion;
    @FXML
    private JFXButton submitQuiz;

    private ToggleGroup radioGroup;
    @FXML
    private JFXButton setTitle;
    
    /**
     * Initializes the controller class.
     */
    
    private Quiz quiz=null;
    private final ArrayList<Question> questions=new ArrayList<>();
    @FXML
    private JFXTreeView<?> TreeView;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        radioButtonSetUp();
        renderTreeView();
        
        
    }    
    private void renderTreeView(){
        // here first we create map for getting data from getAll() method place in Quiz.java
        Map<Quiz,List<Question>> data=Quiz.getAll();
        Set<Quiz> quizzes=data.keySet();
        TreeItem root = new TreeItem("quizzes");
       
        for(Quiz q:quizzes)
        {
           TreeItem quizTreeItem=new TreeItem(q);  //here We are calling toString method
           List<Question> questions=data.get(q);
           for(Question question:questions)
               
           {
               TreeItem QuestionTreeItem=new TreeItem(question);
               QuestionTreeItem.getChildren().add(new TreeItem("A:"+question.getOption1()));
               QuestionTreeItem.getChildren().add(new TreeItem("B:"+question.getOption2()));
               QuestionTreeItem.getChildren().add(new TreeItem("C:"+question.getOption3()));
               QuestionTreeItem.getChildren().add(new TreeItem("D:"+question.getOption4()));
               QuestionTreeItem.getChildren().add(new TreeItem("Answer:"+question.getAnswer()));
               quizTreeItem.getChildren().add(QuestionTreeItem);
           }
           root.getChildren().add(quizTreeItem);
           
        }
        this.TreeView.setRoot(root);
        
        
    }
    
    private void radioButtonSetUp(){
    radioGroup= new ToggleGroup();
    RB1.setToggleGroup(radioGroup);
    RB2.setToggleGroup(radioGroup);
    RB3.setToggleGroup(radioGroup);
    RB4.setToggleGroup(radioGroup);
    }

    @FXML
    private void setQuizTitle(ActionEvent event) {
        String QuizTitle=quizName.getText();
        System.out.print("Hello"+QuizTitle+"Bye");
        
         if(QuizTitle.trim().isEmpty())
         {
             Notifications Noti=Notifications.create();
             Noti.text("Enter Valid Quiz Title"); 
             Noti.position(Pos.CENTER);
             Noti.hideAfter(Duration.millis(2000));
             Noti.showError();
        }
        else
        {
            quizName.setEditable(false);
           this.quiz=new Quiz(QuizTitle);
            
        }
        
       
    }
    
    private boolean ValidateFields(){
        String QUIZTITLE=quizName.getText();
         String QU =question.getText();
        String OP1=option1.getText();
        String OP2=option2.getText();
        String OP3=option3.getText();
        String OP4=option4.getText();
        Toggle SelectRadio =radioGroup.getSelectedToggle();
        System.out.print(SelectRadio);
        if(QUIZTITLE.trim().isEmpty())
        { Notifications.create().darkStyle().title("Quiz Title").text("Please Enter Quiz Name").position(Pos.CENTER).hideAfter(Duration.millis(2000)).showError();
        return(false);
        }
        else if(QU.trim().isEmpty()){
        Notifications.create().darkStyle().title("Question").text("Please enter Question").position(Pos.CENTER).hideAfter(Duration.millis(2000)).showError();
        return(false);
        }
        else if(OP1.trim().isEmpty() || OP2.trim().isEmpty() || OP3.trim().isEmpty() || OP4.trim().isEmpty())
        {
         Notifications.create().darkStyle().title("Options").text("Please Enter Options").position(Pos.CENTER).hideAfter(Duration.millis(2000)).showError();
         return(false);
        }
        else if(SelectRadio == null)
        {
        Notifications.create().darkStyle().text("Select A Right Option Please").darkStyle().position(Pos.CENTER).hideAfter(Duration.millis(2000)).showError();
        return(false);
        }
       System.out.println("true");
      return(true);
      
    }

    @FXML
    private void AddNextQuestion(ActionEvent event) {
        addQuestion();
       
    }
    private boolean addQuestion(){
         boolean Valid =ValidateFields();
        Question Qu=new Question();
        if(Valid)
        {
           Qu.setOption1(option1.getText().trim());
           Qu.setOption2(option2.getText().trim());
           Qu.setOption3(option3.getText().trim());
           Qu.setOption4(option4.getText().trim());
           Toggle selected=radioGroup.getSelectedToggle();
           System.out.println(selected);
           String ans=null;
           if(selected==RB1)
           {
               ans=option1.getText().trim();
           }
           else if(selected==RB2) 
           {
               ans=option2.getText().trim();
           }else if(selected==RB3)
           {
               ans=option3.getText().trim();
           }else
           {
               ans=option4.getText().trim();
           }
           Qu.setAnswer(ans);
          Qu.setQuestion(question.getText().trim());
           
         question.clear();
         option1.clear();
         option2.clear();
         option3.clear();
         option4.clear();
         RB1.setSelected(false);
         RB2.setSelected(false);
         RB3.setSelected(false);
         RB4.setSelected(false);
        
        questions.add(Qu);
        Qu.setQuiz(quiz);
        System.out.println(questions);
        System.out.println(quiz);
        }
        return Valid;   
    }

    @FXML
    private void SubmitQuiz(ActionEvent event) {
        boolean flag=addQuestion();
        if(flag){
      flag=  quiz.save(questions);
      if(flag){
           Notifications.create().darkStyle().title("Success").text("Quiz successfully saved.....").position(Pos.CENTER).hideAfter(Duration.millis(2000)).showInformation();
        quizName.clear();
        renderTreeView();
          
      }else{
           Notifications.create().darkStyle().title("Error").text("Quiz not saved.... ").position(Pos.CENTER).hideAfter(Duration.millis(2000)).showError();
        
      }
        }
    }
    
}
