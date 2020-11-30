package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
        primaryStage.setTitle("AB EXPRESS");
        primaryStage.setScene(new Scene(root, 765, 465));
        primaryStage.show();
        primaryStage.close();
        }

    public static void main(String[] args) {
        launch(args);
    }
}