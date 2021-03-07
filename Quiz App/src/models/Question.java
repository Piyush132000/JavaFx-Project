/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author nites
 */
public class Question {
 
     private Quiz quiz;
     private String question;
     private String option1;
     private String option2;
     private String option3;
     private String option4;
     private String Answer;
     private Integer QuestionId;

    public void setQuestionId(Integer QuestionId) {
        this.QuestionId = QuestionId;
    }

    @Override
    public String toString() {
        return this.question; //To change body of generated methods, choose Tools | Templates.
    }
   

  
      public Question(Quiz quiz, String question, String option1, String option2, String option3, String option4, String Answer) {
        this.quiz = quiz;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.Answer = Answer;
        
        
         
    }

   
     public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Question() { 
    }

   

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

   

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    
    
   

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }
     public static void createTable(){
        String query="Create table question ( id integer PRIMARY key AUTOINCREMENT," +
"question varchar(1000)," +
"option1 varchar(500)," +
"option2 varchar(500)," +
"option3 varchar(500)," +
"option4 varchar(500)," +
"answer varchar(500), quiz_id integer," +
"FOREIGN key (quiz_id) REFERENCES quiz(id)	)";
         try{
        String ConnectionURL ="jdbc:sqlite:quiz.db";   
                Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection(ConnectionURL)) {
                PreparedStatement ps= con.prepareStatement(query);
                boolean b=ps.execute();
            }
                System.out.println("Query run"); 
                
                
                
        }
        catch(ClassNotFoundException | SQLException e)
        {
        }
    }
     
     public boolean save(){
               boolean flag=false;
             String ConnectionURL ="jdbc:sqlite:quiz.db";   
             try{  
                  Class.forName("org.sqlite.JDBC");
                try (Connection connect = DriverManager.getConnection(ConnectionURL)) {
                    String QUERY="INSERT INTO question (question,option1,option2,option3,option4,answer,quiz_id) VALUES (?,?,?,?,?,?,? ); ";
                    
                    PreparedStatement ps= connect.prepareStatement(QUERY);
                    ps.setString(1,this.question);
                    ps.setString(2,this.option1);
                    ps.setString(3,this.option2);
                    ps.setString(4,this.option3);
                    ps.setString(5,this.option4);
                    ps.setString(6,this.Answer);
                    ps.setInt(7,this.quiz.getQuizId());
                    int i=ps.executeUpdate();
                    System.out.println("Updated Rows :"+i);
                }
                flag=true;
             }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
             return flag;
     }

    public Integer getQuestionId() {
        return QuestionId;
    }

    

    
   
      
    
    
}
