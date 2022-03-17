package com.iucosoft.beautysalon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * 
 * @author iucosoftmain
 */
public class JavaFXLoghin extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLAuthentification.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        //stage.getIcons().add(new Image(this.getClass().getResource("image/logo.png").toString()));
        stage.show();
    }
    /**
     * @param args the command line^ arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
