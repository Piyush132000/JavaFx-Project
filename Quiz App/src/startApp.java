


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Question;
import models.Quiz;
import models.Student;


public class startApp extends Application {

  
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("/fxml/RegistrationFxml.fxml"));  //loginFxml.fxml
        Stage s= new Stage();
        Scene scene=new Scene(root);
        s.setScene(scene);
        s.show();

    }
    
       
    
    
    
      public static void main(String[] args){
        launch(args);
}
    private void createTables(){
        Quiz.createTable();
        Question.createTable();
        Student.createTable();
        
        
    }
    
}
