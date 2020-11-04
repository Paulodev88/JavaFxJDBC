package controller;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentServices;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemSeller, menuItemDepartment, menuItemAbout;

    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("Menu Ok 1");
    }

    @FXML
    public void onMenuItemDepartmentAction() {
        loadView2("/gui/DepartmentList.fxml");
    }

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/About.fxml");
        }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadView(String absoluteName){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVbox =(VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVbox.getChildren().get(0);
            mainVbox.getChildren().clear();
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVBox.getChildren());


        }
        catch (IOException e){
            Alerts.showAlert("IO Exception", "Error load view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void loadView2(String absoluteName){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVbox =(VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVbox.getChildren().get(0);
            mainVbox.getChildren().clear();
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVBox.getChildren());
            DepartmentListController controller = loader.getController();

            controller.setDepartmentService(new DepartmentServices());
            controller.updateTableView();

        }
        catch (IOException e){
            Alerts.showAlert("IO Exception", "Error load view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
