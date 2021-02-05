package com.simpleChatFX.leo.controller.server;

import com.simpleChatFX.leo.model.Server;
import com.simpleChatFX.leo.view.utils.Constants;
import java.io.IOException;

public class MainServer {
    public static void main(String[] args) {
        try {
            Server.getInstance().serverStart();
        } catch (IOException e) {
            System.out.println(Constants.ERROR_START_SERVER);
        }
    }
}
