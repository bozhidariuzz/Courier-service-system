package Courier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminControllerCourier implements Initializable {
    @FXML
    private TextField name_c;
    @FXML
    private TextField phone_c;
    @FXML
    private TextField id_office;
    @FXML
    private TextField egn_c;
    @FXML
    private Button select_c;
    @FXML
    private TableView CourierView;
    @FXML
    private TableColumn<Admin, Integer> id_courier ;
    @FXML
    private TableColumn<Admin, String> name_courier ;
    @FXML
    private TableColumn<Admin,String> egn_courier;
    @FXML
    private TableColumn<Admin, String> phone_courier ;
    @FXML
    private TableColumn<Admin, String> location_office ;

    @FXML
    private void ShowCourier(ObservableList<Admin> crdata)throws SQLException,ClassNotFoundException
    {
        CourierView.setItems(crdata);
    }
    @FXML
    public void selectAllCouriers()throws SQLException,ClassNotFoundException
    {
        try {
            ObservableList<Admin> adData = AdminDAO.selectAllCouriers();
            ShowCourier(adData);
        }catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    @FXML
    private void insert_couriers()throws SQLException,ClassNotFoundException
    {
        try
        {
         AdminDAO.insertCourier(name_c.getText(),phone_c.getText(),id_office.getText(),egn_c.getText());
        }catch (SQLException e){
            throw e;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_courier.setCellValueFactory(cellData->cellData.getValue().CourierIdProperty().asObject());
        name_courier.setCellValueFactory(cellData->cellData.getValue().NameCourierProperty());
        egn_courier.setCellValueFactory(cellData->cellData.getValue().EgnCourierProperty());
        phone_courier.setCellValueFactory(cellData->cellData.getValue().PhoneCourierProperty());
        location_office.setCellValueFactory(cellData->cellData.getValue().LocationOfficeProperty());
       // egn_courier.setCellValueFactory(cellData->cellData.getValue().EgnCourierProperty());
    }
}
