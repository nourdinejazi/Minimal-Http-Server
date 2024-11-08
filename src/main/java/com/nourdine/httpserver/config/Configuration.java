package com.nourdine.httpserver.config;

public class Configuration {
    private int port;
    private String webroot;


    /**
     * @return int return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return String return the webroot
     */
    public String getWebroot() {
        return webroot;
    }

    /**
     * @param webroot the webroot to set
     */
    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

}
