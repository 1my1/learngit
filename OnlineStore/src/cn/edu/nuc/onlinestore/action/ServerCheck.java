package cn.edu.nuc.onlinestore.action;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerCheck extends Thread {
	    private ServerSocket server=null;
	    private Socket socket=null;
	    public ServerCheck(){
	    	try {
				server=new ServerSocket(4000);
				this.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
        @Override
        public void run() {
        	try {
				while(true){
					socket=server.accept();
					new ServerCheckThread(socket);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        }
        
        public static void main(String[] args) {
			new ServerCheck();
		}
        
}
