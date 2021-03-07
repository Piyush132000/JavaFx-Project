/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nites
 */

public class Student {
    
    private Integer Id;
    private String FirstName;
    private String LastName;
    private String Mobile;
    private Character gender;
    private String Email;
    private String Password;

    public Student(String FirstName, String LastName, String Mobile, String Email, String Password, Character gender) {
       
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Mobile = Mobile;
        this.gender = gender;
        this.Email = Email;
        this.Password = Password;
    }
    public Student(){
        
    } 

    public Student(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }

    public Student(Integer Id, String FirstName, String LastName, String Mobile, Character gender, String Email, String Password) {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Mobile = Mobile;
        this.gender = gender;
        this.Email = Email;
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "Student{" + "Id=" + Id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Mobile=" + Mobile + ", gender=" + gender + ", Email=" + Email + ", Password=" + Password + '}';
    }

    
    
    public Integer getId() {
        return Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getMobile() {
        return Mobile;
    }

    public Character getGender() {
        return gender;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public static void createTable(){
        String query=" CREATE table if not EXISTS students(" +
"  id integer  PRIMARY KEY AUTOINCREMENT," +
"  FirstName varchar(20)," +
"  lastname varchar(20)," +
"  Mobile varchar(20) UNIQUE," +
"  Email varchar(20) UNIQUE," +
"  password varchar(20)," +
"  gender char);";
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
    
    
    
    
    public Student save() throws Exception{
       
        try{
              String ConnectionURL ="jdbc:sqlite:quiz.db";   
              
                  Class.forName("org.sqlite.JDBC");
                try (Connection connect = DriverManager.getConnection(ConnectionURL)) {
                     String QUERY="Insert Into students ( firstname,lastname,mobile,email,password,gender) VALUES ( ?,?,?,?,?,?);";
                    
                    PreparedStatement ps= connect.prepareStatement(QUERY,Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, this.FirstName);
                    ps.setString(2, this.LastName);
                    ps.setString(3, this.Mobile);
                    ps.setString(4, this.Email);
                    ps.setString(5, this.Password);
                    ps.setString(6,String.valueOf(this.gender));
                    
                   
                    int i=ps.executeUpdate();
                    ResultSet key=ps.getGeneratedKeys();
               if(key.next())
               {
                   this.Id= key.getInt(1);
               }
                
                return this;
                }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean isExists() throws Exception{
       
        try{
              String ConnectionURL ="jdbc:sqlite:quiz.db";   
              
                  Class.forName("org.sqlite.JDBC");
                try (Connection connect = DriverManager.getConnection(ConnectionURL)) {
                     String QUERY="SELECT * FROM STUDENTS WHERE EMAIL=? AND Password=?;";
                    
                    PreparedStatement ps= connect.prepareStatement(QUERY);
                    ps.setString(1, this.Email);
                    ps.setString(2,this.Password);
                 
                    
                   
                    ResultSet result=ps.executeQuery();
                   
               if(result.next())
               {
                  return true;
               }
             }
           }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public static  ArrayList<Student> getAll(){
        ArrayList<Student> students=new ArrayList<>(); 
        try{
              String ConnectionURL ="jdbc:sqlite:quiz.db";   
              
                  Class.forName("org.sqlite.JDBC");
               Connection connect = DriverManager.getConnection(ConnectionURL);
                     String QUERY="SELECT id,FIrstName,LastName,Mobile,Email,Password,gender FROM STUDENTS;";
                    
                    PreparedStatement ps= connect.prepareStatement(QUERY);
                    ResultSet result=ps.executeQuery();
                   
               while(result.next())
                {
                   Student s=new Student();
                   s.setId(result.getInt(1));
                   s.setFirstName(result.getString(2));
                   s.setLastName(result.getString(3));
                   s.setMobile(result.getString(4));
                   s.setEmail(result.getString(5));
                   s.setPassword(result.getString(6));
                   s.setGender(result.getString(7).charAt(0));
                   students.add(s);
            }
             
           }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       return students;
}
    
}
