package com.nourdine.httpserver.config;

public class HTttpConfigurationException extends RuntimeException {
    public HTttpConfigurationException(String message){ 
        super(message);
    }   
    
    public HTttpConfigurationException(Throwable cause){ 
        super(cause);
    }

    public HTttpConfigurationException(String message, Throwable cause){ 
        super(message, cause);
    }
}