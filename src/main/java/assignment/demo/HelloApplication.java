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
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        // Create a Scene
        //HelloController controller = new HelloController();
        Scene scene = new Scene(root, 960, 540);
        // Set the Scene to the Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("蹦蹦炸彈");
        primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/images/bomb.png"));
        // Show the Stage
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}