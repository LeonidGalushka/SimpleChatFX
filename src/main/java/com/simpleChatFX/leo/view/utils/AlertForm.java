package com.simpleChatFX.leo.view.utils;

import javafx.scene.control.Alert;

public class AlertForm {

    public static void errorAlert(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Constants.TITLE_TO_ERROR);
        alert.setHeaderText(info);
        alert.showAndWait();
    }
}
