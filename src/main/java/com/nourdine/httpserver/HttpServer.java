package com.nourdine.httpserver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nourdine.httpserver.config.Configuration;
import com.nourdine.httpserver.config.ConfigurationManager;
import com.nourdine.httpserver.core.ServerListnerThread;

public class HttpServer {
   private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

   public static void main(String[] args) {
      LOGGER.info("Server Starting....");

      ConfigurationManager.getInstance().LoadConfigurationFile("src/main/resources/http.json");
      Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
      LOGGER.info("Using port: " + conf.getPort());
      LOGGER.info("Using webroot: " + conf.getWebroot());

      try {
         ServerListnerThread serverListnerThread = new ServerListnerThread(conf.getPort(), conf.getWebroot());
         serverListnerThread.start();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }
}
