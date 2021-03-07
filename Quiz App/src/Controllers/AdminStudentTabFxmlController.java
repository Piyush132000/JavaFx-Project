/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import models.Student;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author nites
 */
public class AdminStudentTabFxmlController implements Initializable {

    @FXML
    private JFXTextField FirstName;
    @FXML
    private JFXTextField SecondName;
    @FXML
    private JFXTextField MobileNo;
    @FXML
    private JFXRadioButton Male;
    @FXML
    private JFXRadioButton Female;
    @FXML
    private JFXButton saveButton;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student,String> StudentIdColumn;
    @FXML
    private TableColumn<Student,String> FirstNameColumn;
    @FXML
    private TableColumn<Student,String> SecondNameColumn;
    @FXML
    private TableColumn<Student,String> MobileNameColumn;
    @FXML
    private TableColumn<Student,String> GenderColumn;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXPasswordField Password;
    @FXML
    private TableColumn<Student,String> EmailColumn;
    @FXML
    private TableColumn<Student,String> PasswordColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initAll();
        radioButtonSetUp();
        renderTable();
    }   
    private void renderTable(){
        
       List<Student> students = Student.getAll();
       studentTable.getItems().clear();
       this.StudentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
       this.FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
       this.SecondNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
       this.MobileNameColumn.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
       this.EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
       this.PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
       this.GenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
       
       studentTable.getItems().addAll(students);
    }

    private ToggleGroup  toggleGroup; 
    public AdminStudentTabFxmlController(){
       
        
    }
    private void resetForm(){
        FirstName.clear();
        SecondName.clear();
        MobileNo.clear();
        Email.clear();
        Password.clear();
    }
    private void radioButtonSetUp(){
       this.Male.setSelected(true);
        this.Male.setToggleGroup(toggleGroup);
        this.Female.setToggleGroup(toggleGroup);
    }
    private void initAll(){
        toggleGroup=new ToggleGroup();
    }
    private boolean ValidationData(){
          String firstName=FirstName.getText().trim();
         String Secondname=SecondName.getText().trim();
         String Mobile=MobileNo.getText().trim();
         String email=Email.getText().trim();
         String password=Password.getText().trim();
         Character Gender='M';
         JFXRadioButton gender=(JFXRadioButton)toggleGroup.getSelectedToggle();
         Pattern p=Pattern.compile("^(.+)@(.+)$");
         if(gender!=null)
         {
             if(gender==Female)
             {
                Gender='F';
             }
         }
             if(firstName.isEmpty())
             {
             Notifications Noti=Notifications.create();
             Noti.text("FirstName must be 4 Char Long"); 
              Noti.title("Fill Student form Correctly").darkStyle();
             Noti.position(Pos.CENTER);
             Noti.hideAfter(Duration.millis(2000));
             Noti.showError();
             return false;
             }
             else if(Secondname.isEmpty()){
               Notifications Noti=Notifications.create();
             Noti.text("Second Name must be 2 char long"); 
             Noti.position(Pos.CENTER);
             Noti.title("Fill Student form Correctly").darkStyle();
             Noti.hideAfter(Duration.millis(2000));
             Noti.showError();
             return false;
         }
             else if((Mobile.isEmpty())||(Mobile.length()<10))
         {
               Notifications Noti=Notifications.create();
             Noti.text("Mobile No. must be 10 digits");
              Noti.title("Fill Student form Correctly").darkStyle();
             Noti.position(Pos.CENTER);
             Noti.hideAfter(Duration.millis(2000));
             Noti.showError();
             return false;
         }
             else if((!p.matcher(email).matches()) || (email.isEmpty()) )
             {
                  Notifications Noti=Notifications.create();
             Noti.text("Enter Valid Email");
              Noti.title("Fill Student form Correctly").darkStyle();
             Noti.position(Pos.CENTER);
             Noti.hideAfter(Duration.millis(2000));
             Noti.showError();
             return false;
             }
             else if(password.length()<=6)
             {
                  Notifications Noti=Notifications.create();
             Noti.text("Passwrd must be grater than 6");
              Noti.title("Fill Student form Correctly");
             Noti.position(Pos.CENTER).darkStyle();
            
             Noti.hideAfter(Duration.millis(2000));
             Noti.showError();
             return false;
             
             }
        return true;
    }

    @FXML
    private void saveButton(ActionEvent event) throws Exception {
         System.out.println("Save Button");
         String firstName=FirstName.getText().trim();
         String Secondname=SecondName.getText().trim();
         String Mobile=MobileNo.getText().trim();
         String email=Email.getText().trim();
         String password=Password.getText().trim();
         Character Gender='M';
         JFXRadioButton gender=(JFXRadioButton)toggleGroup.getSelectedToggle();
         Pattern p=Pattern.compile("^(.+)@(.+)$");
         if(gender!=null)
         {
             if(gender==Female)
             {
                Gender='F';
             }
         }
         
       if(ValidationData()){
      
         Student s=new Student(firstName,Secondname,Mobile,email,password,Gender);
        if(s.isExists())
        {
             Notifications.create().darkStyle().title("Failed").text("Student Already Registered....").position(Pos.CENTER).showError();
            return;
        }
         
         boolean result = false;
        try {
           s= s.save();
           System.out.println(s);
              if(s!=null){
             Notifications.create().darkStyle().title("Success").text("Student Registered...").position(Pos.CENTER).showInformation();
             resetForm();
             
             studentTable.getItems().add(0,s);
         
              }
              else
              {
                 Notifications.create().darkStyle().title("Failed").text("Student Registration Failed...").position(Pos.CENTER).showError();
               
              }
        } catch (Exception ex) {
           ex.printStackTrace();
        }
       
         
    }
    }

    @FXML
    private void ResetFormData(ActionEvent event) {
        resetForm();
    }
    
}
