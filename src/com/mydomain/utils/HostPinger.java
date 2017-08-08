package com.mydomain.utils;

import java.io.IOException;


public class HostPinger {

    
    public boolean pingHost (String host) throws IOException, InterruptedException {
        
    	boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
        
    	ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows? "-n" : "-c", "1", "-w", "2", host);
        Process proc = processBuilder.start();

        int returnVal = proc.waitFor();
        return returnVal == 0;
    }
    	
	
}
