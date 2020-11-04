package controller;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFromController implements Initializable {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;

    @FXML
    private void onBtSaveAction(){
        System.out.println("funfou");

    }

    @FXML
    private void onBtCancelAction(){
        System.out.println("funfou");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeNodes();

    }

    private void initializeNodes(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }
}
