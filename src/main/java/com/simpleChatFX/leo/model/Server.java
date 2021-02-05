package com.simpleChatFX.leo.model;

import com.simpleChatFX.leo.controller.server.ServerFacade;
import com.simpleChatFX.leo.view.utils.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static Server instance;
    private ServerSocket serverSocket;
    private ArrayList<ServerFacade> arrayList;
    private ExecutorService es;

    private Server() throws IOException {
        serverSocket = new ServerSocket(Constants.PORT);
        arrayList = new ArrayList<>();
        es = Executors.newCachedThreadPool();
    }

    public static Server getInstance() throws IOException {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void serverStart() throws IOException {

        for (int i = 0; i < Constants.NUMBER_CLIENTS; i++) {
            System.out.println("Thread #" + i + "starts");

            es.submit(() -> {
                try {
                    while (true) {
                        Socket socket = serverSocket.accept();
                        ServerFacade serverFacade = new ServerFacade(socket);
                        int idClient = arrayList.size();
                        arrayList.add(serverFacade);
                        String clientMessage;
                        while (true) {
                            clientMessage = Constants.NUN_MESSAGE;
                            clientMessage = serverFacade.getMessage();
                            if (clientMessage.equals(Constants.NUN_MESSAGE)) continue;

                            if (clientMessage.equals(Constants.CLIENT_CLOSE)) {
                                arrayList.remove(idClient);
                                serverFacade.close();
                                break;
                            }

                            for (int j = 0; j < arrayList.size(); j++) {
                                arrayList.get(j).setMassage(clientMessage);
                            }
                        }
                        serverFacade.close();
                    }
                } catch (Exception e) {
                    System.out.println(Constants.ERROR_START_CLIENT_FOR_SERVER);
                }
            });
        }

        try {
            Thread.sleep(Constants.LIVE_SERVER);
        } catch (InterruptedException e) {
            System.out.println(Constants.ERROR_THREAD_SLEEP);
        }
        serverSocket.close();
        System.out.println(Constants.SERVER_CLOSE);
    }
}