package addressbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
    * Nathan Power - 101247770
    *
    * Roberto Borges - 101255891
    *
*/
public class AddressBook extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        //ensure when application opens, it is set to a certain width and height and cannot go below that
        stage.setWidth(1230);
        stage.setMinWidth(1230);
        stage.setHeight(740);
        stage.setMinHeight(740);
        stage.setTitle("My Address Book");
        stage.show();      
    }   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
