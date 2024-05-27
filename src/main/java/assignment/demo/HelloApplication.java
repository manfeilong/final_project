package assignment.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        /*
        File file = new File("resources_112504506/up.mp3");
        if (file.exists())
            System.out.println("File exists");
        else
            System.out.println("File does not exist");
        */

        // Create a layout and add the play button to it
        //StackPane root = new StackPane();

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));


        // Create a Scene
        Scene scene = new Scene(root, 960, 540);

        // Set the Scene to the Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("demo");

        // Show the Stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}