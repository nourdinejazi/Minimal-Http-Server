package com.nourdine.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nourdine.http.HttpParser;
import com.nourdine.http.HttpRequest;
import com.nourdine.httpserver.core.io.ReadFileException;
import com.nourdine.httpserver.core.io.WebRootHandler;
import com.nourdine.httpserver.core.io.WebRootNotFoundException;

public class HttpConnectionWorkerThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    private Socket socket;
    private String webroot;

    public HttpConnectionWorkerThread(Socket socket, String webroot) {
        this.socket = socket;
        this.webroot = webroot;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            final String CRLF = "\r\n";
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            HttpRequest req = new HttpParser().parseHttpRequest(inputStream);

            WebRootHandler webRootHandler = null;

            try {
                webRootHandler = new WebRootHandler(webroot);
            } catch (WebRootNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String mimeType = webRootHandler.getFileMimeType(req.getRequestTarget());

            byte[] fileBytes;
            try {
                fileBytes = webRootHandler.getFileByteArrayData(req.getRequestTarget());

                String response = "HTTP/1.1 200 OK" + CRLF
                        + "Content-Type: " + mimeType + CRLF + "Content-Length: "
                        + fileBytes.length + CRLF + CRLF;
                outputStream.write(response.getBytes());
                outputStream.write(fileBytes);
            } catch (ReadFileException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            LOGGER.info("Connection processing finished");

        } catch (Exception e) {
            LOGGER.error("Error processing request", e);
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }

        }
    }
}
