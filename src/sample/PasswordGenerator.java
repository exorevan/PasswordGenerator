package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PasswordGenerator extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));

        primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.setTitle("Password generator");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
