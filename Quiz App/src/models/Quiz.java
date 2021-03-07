/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.*;
import java.util.*;



public class Quiz {
    
    private Integer quizId;
    private String  title;

    public Quiz(String title) {
        this.title = title;
    }

    public Quiz() {
    }
    

    public Integer getQuizId() {
        return quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
    
    
    public static void createTable(){
        try{
             String query= "Create Table quiz ( id integer PRIMARY KEY AUTOINCREMENT,quiz varchar(50) );";
                String ConnectionURL ="jdbc:sqlite:quiz.db";   
                Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection(ConnectionURL)) {
                PreparedStatement ps= con.prepareStatement(query);
                boolean b=ps.execute();
            }
                System.out.println("Query run successfully");  
                
        }
        catch(ClassNotFoundException | SQLException e)
        {
        }
       
    }
    
    public int save(){
         String query="INSERT INTO QUIZ (quiz) VALUES (?)";
         
             String ConnectionURL ="jdbc:sqlite:quiz.db";   
               
        try{  Class.forName("org.sqlite.JDBC");
             try(Connection con =DriverManager.getConnection(ConnectionURL)){
            
                PreparedStatement ps= con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,this.title);
                int i=ps.executeUpdate();
               ResultSet key=ps.getGeneratedKeys();
               if(key.next())
               {
                   return key.getInt(1);
               }
                System.out.println("Query run successfully"); 
                
                
                
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            return -1;
        }
    
    return -1;
    }

    public boolean save(ArrayList<Question> questions){
        boolean flag=true;
        this.quizId=this.save();
        for(Question q: questions)
        {
          flag = flag &&  q.save();
          System.out.println(flag);
        }
        return flag;
    }
    
    public static Map<Quiz,List<Question>> getAll(){
        Map<Quiz,List<Question>> quizes=new HashMap<>();
        Quiz key=null;
        
         String query="SELECT quiz.id,quiz,question.id,question,option1,option2,option3,option4,answer from quiz join question where quiz.id=question.quiz_id;";
         
             String ConnectionURL ="jdbc:sqlite:quiz.db"; 
             
               
        try{  Class.forName("org.sqlite.JDBC");
             try(Connection con =DriverManager.getConnection(ConnectionURL)){
            
                PreparedStatement ps= con.prepareStatement(query);
               
                ResultSet result=ps.executeQuery();
              
               while(result.next())
               {
                   Quiz temp=new Quiz();
                   temp.setQuizId(result.getInt(1));
                   temp.setTitle(result.getString(2));
                   
                   Question tempQuestion=new Question();
                   tempQuestion.setQuestionId(result.getInt(3));
                   tempQuestion.setQuestion(result.getString(4));
                   tempQuestion.setOption1(result.getString(5));
                   tempQuestion.setOption2(result.getString(6));
                   tempQuestion.setOption3(result.getString(7));
                   tempQuestion.setOption4(result.getString(8));
                   tempQuestion.setAnswer(result.getString(9));
                   
                   if((key!=null) && (key.equals(temp))){
                       quizes.get(key).add(tempQuestion);
                   }
                   else
                   {
                       ArrayList<Question> value=new ArrayList<>();
                       value.add(tempQuestion);
                       quizes.put(temp,value);
                   }
                   
                   key=temp;
                   
                  
               }
             
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
           
        }
    return quizes;
        
    } 
    public static Map<Quiz,Integer> getAllWithQuestionCount(){
        Map<Quiz,Integer> quizes=new HashMap<>();
        Quiz key=null;
        
         String query="SELECT quiz,quiz.id,count(*) As question_count FROM  quiz INNER Join question Where question.quiz_id=quiz.id GROUP BY quiz.id ;";
         
             String ConnectionURL ="jdbc:sqlite:quiz.db"; 
             
               
        try{  Class.forName("org.sqlite.JDBC");
             try(Connection con =DriverManager.getConnection(ConnectionURL)){
            
                PreparedStatement ps= con.prepareStatement(query);
               
                ResultSet result=ps.executeQuery();
              
               while(result.next())
               {
                   Quiz temp=new Quiz();
                   temp.setTitle(result.getString(1)); 
                   temp.setQuizId(result.getInt(2));
                   int count=result.getInt(3);
                   quizes.put(temp, count);
                 
               }
             
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
           
        }
    return quizes;
        
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(!(obj instanceof Quiz)){
            return false;
        }
        Quiz t=(Quiz)obj;
        if(this.quizId == t.quizId) {
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        
        return Objects.hash(quizId,title);
    }

    //getquestions of quiz
     public  List<Question> getQuestions(){
      List<Question> quizes=new ArrayList<>();
        Quiz key=null;
        
         String query="SELECT id,question,option1,option2,option3,option4,answer from question where quiz_id=?;";
         
             String ConnectionURL ="jdbc:sqlite:quiz.db"; 
             
               
        try{  Class.forName("org.sqlite.JDBC");
             try(Connection con =DriverManager.getConnection(ConnectionURL)){
            
                PreparedStatement ps= con.prepareStatement(query);
                ps.setInt(1,this.quizId);
               
                ResultSet result=ps.executeQuery();
              
               while(result.next())
               { 
                   Question tempQuestion=new Question();
                   tempQuestion.setQuestionId(result.getInt(1));
                   tempQuestion.setQuestion(result.getString(2));
                   tempQuestion.setOption1(result.getString(3));
                   tempQuestion.setOption2(result.getString(4));
                   tempQuestion.setOption3(result.getString(5));
                   tempQuestion.setOption4(result.getString(6));
                   tempQuestion.setAnswer(result.getString(7));
                   tempQuestion.setQuiz(this);
                   quizes.add(tempQuestion);
                
                   
                  
               }
             
        }
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
    return quizes;
        
    }
   
   
    
    
}
