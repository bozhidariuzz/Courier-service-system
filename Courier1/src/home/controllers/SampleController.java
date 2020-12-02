package home.controllers;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.application.Application;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SampleController {



    @FXML
    private Button btn_client_login;

    @FXML
    private Button btn_admin_login;

    @FXML
    private Button btn_courier_login;


@FXML
    private void open_client_login() {
    Parent root1 = null;
    try {
        root1 = FXMLLoader.load(getClass().getResource("fxml/login_client.fxml"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    Stage stageTheButtonBelongs = (Stage) btn_client_login.getScene().getWindow();
    stageTheButtonBelongs.setScene(new Scene(root1, 765, 465));
}
@FXML
    private void open_admin_login() {
    }
@FXML
    private void open_courier_login() {
    }

}
