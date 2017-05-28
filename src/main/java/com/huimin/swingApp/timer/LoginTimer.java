package com.huimin.swingApp.timer;

public class LoginTimer {
	
	final long timeInterval = 1000;
	private   Runnable runnable;
	public LoginTimer() {
		  
		runnable = new Runnable() {  
            public void run() {  
                while (true) {  
                    // ------- code for task to run  
                     
                   
                	 try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}  
                }  
            }  
	        };  
	}
}
