package com.nourdine.httpserver.core;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerListnerThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListnerThread.class);

    private int port;
    private String webroot;
    ServerSocket serverSocket;

    public ServerListnerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Connection accepted from " + socket.getInetAddress());
                HttpConnectionWorkerThread worker = new HttpConnectionWorkerThread(socket, webroot);
                worker.start();

            }

        } catch (IOException e) {
            LOGGER.error("Error accepting connection", e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {

            }
        }
    }

}
