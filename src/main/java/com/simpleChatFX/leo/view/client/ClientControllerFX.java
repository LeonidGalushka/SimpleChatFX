package com.simpleChatFX.leo.view.client;

import com.simpleChatFX.leo.controller.Client.Controller;
import com.simpleChatFX.leo.view.utils.AlertForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.simpleChatFX.leo.view.utils.Constants;

public class ClientControllerFX {
    @FXML
    public TextArea inputMessage;
    @FXML
    public TextArea outputMessage;
    @FXML
    public TextField nameField;

    private Controller controller;

    @FXML
    void initialize() {
        controller = Controller.getInstance();
        (new Thread(() -> {
            String serverMassage;
            while (true) {
                if (!(serverMassage = controller.getMessageClient()).equals(Constants.NUN_MESSAGE)) {
                    outputMessage.setText(((outputMessage.getText()).equals(Constants.NUN_MESSAGE) ?
                            Constants.NUN_MESSAGE : outputMessage.getText()) + serverMassage);
                }
                try {
                    Thread.sleep(Constants.PING);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).start();
    }

    public void clickButton(ActionEvent e) {
        try {
            if (!inputMessage.getText().equals(Constants.NUN_MESSAGE)) {
                controller.setMessageClient((nameField.getText().equals(Constants.NUN_MESSAGE) ?
                        Constants.INCOGNITO : nameField.getText()) + " : " + inputMessage.getText());
                inputMessage.setText(Constants.NUN_MESSAGE);
            }
        }catch (Exception ex){
            AlertForm.errorAlert(Constants.ERROR_CONNECT_SERVER);
        }
    }
}
