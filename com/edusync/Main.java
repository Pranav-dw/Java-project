package com.edusync;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Tell Java exactly where the FXML drawing is located
        URL fxmlLocation = getClass().getResource("/fxml/Login.fxml");
        
        if (fxmlLocation == null) {
            System.out.println("Error: Could not find Login.fxml. Check your resources folder!");
            return;
        }

        // 2. Load the UI
        Parent root = FXMLLoader.load(fxmlLocation);

        // 3. Set up the window (Scene)
        Scene scene = new Scene(root, 400, 350);
        
        primaryStage.setTitle("EduSync - College ERP");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        
        // 4. Show the window!
        primaryStage.show();
    }

    public static void main(String[] args) {
    
        launch(args);
    }
}
