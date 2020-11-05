package controller;

import db.DbException;
import gui.listeners.DataChangeListener;
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
import model.entities.Seller;
import model.exceptions.ValidationException;
import model.services.SellerServices;

import java.net.URL;
import java.util.*;

public class SellerFromController implements Initializable {

    private Seller entity;

    private SellerServices services;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

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

    public void setSeller(Seller entity){
        this.entity = entity;
    }

    public void setSellerService(SellerServices service){
        this.services = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener){
        dataChangeListeners.add(listener);
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
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
        }
        catch (ValidationException e){
            setErrorMessages(e.getErrors());
        }
        catch (DbException e){
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners){
            listener.onDataChanged();
        }
    }

    private Seller getFormData() {
        Seller obj = new Seller();

        ValidationException exception = new ValidationException("validation error");

        if (txtName.getText() == null|| txtName.getText().trim().equals("")){
            exception.addError("name","Field can't be empty");
        }
        obj.setId(Utils.tryParseToInt( txtId.getText()));

        if (exception.getErrors().size() > 0){
            throw exception;
        }

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

    private void setErrorMessages(Map<String, String> error){

        Set<String> fields = error.keySet();

        if(fields.contains("name")){
            labelErrorName.setText(error.get("name"));
        }

    }
}
