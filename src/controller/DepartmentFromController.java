package controller;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentServices;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFromController implements Initializable {

    private Department entity;

    private DepartmentServices services;

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

    public void setDepartment(Department entity){
        this.entity = entity;
    }

    public void setDepartmentService(DepartmentServices service){
        this.services = service;
    }

    @FXML
    private void onBtSaveAction(ActionEvent event){
        if ( entity == null){
            throw new IllegalStateException("Entity was null");
        }
        if(services == null){
            throw  new IllegalStateException("Service was null");
        }
        try {
            entity = getFormData();
            services.saveOrUpdate(entity);
            Utils.currentStage(event).close();
        }
        catch (DbException e){
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private Department getFormData() {
        Department obj = new Department();
        obj.setId(Utils.tryParseToInt( txtId.getText()));
        obj.setName(txtName.getText());
        return obj;
    }

    @FXML
    private void onBtCancelAction(ActionEvent event){
        Utils.currentStage(event).close();    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeNodes();

    }

    private void initializeNodes(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }

    public void updateFormDate(){
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(entity.getName());
    }
}
