package cn.edu.nuc.onlinestore.action;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServerCheck extends Thread {
	    private ServerSocket server=null;
	    private Socket socket=null;
	    List<Socket> sockets=new ArrayList<>();
	    private int size=0;
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
        public void run(){
        	try {
				while(true){
					socket=server.accept();
					sockets.add(socket);
					++size;
					new ServerCheckThread(socket);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        }

		public int getSize() {
			return size;
		}

		private void setSize(int size) {
			this.size = size;
		}
       
}
