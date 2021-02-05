package com.simpleChatFX.leo.controller.Client;

import com.simpleChatFX.leo.view.utils.AlertForm;
import com.simpleChatFX.leo.view.utils.Constants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.PATH_TO_CLIENT_FORM));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();


        primaryStage.setOnCloseRequest(event -> {
            try {
                Controller.getInstance().setMessageClient("close");
                Controller.getInstance().close();
            } catch (Exception ex) {
             //AlertForm.errorAlert(Constants.ERROR_CONNECT_SERVER);
            } finally {
                Platform.exit();
                System.exit(0);
            }
        });


    }
}
