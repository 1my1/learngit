package cn.edu.nuc.onlinestore.action;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.edu.nuc.onlinestore.model.User;
import cn.edu.nuc.onlinestore.vo.Message;
import cn.edu.nuc.onlinestore.vo.Result;

public class ServerCheckThread extends Thread {
	    
	    private LoginActionProcess lap=null;
		private ObjectOutputStream dos=null;
		private ObjectInputStream ois=null;
		private Socket socket=null;
		
		public ServerCheckThread(Socket socket){
			this.socket=socket;
			this.start();
		}
	    
		@Override
	    public void run() {
	    	// TODO Auto-generated method stub
	    	try {
				ois=new ObjectInputStream(socket.getInputStream());
				@SuppressWarnings("unchecked")
				Message<User> message=(Message<User>)ois.readObject();
				lap=new LoginActionProcess();
				if(message.getMessage().equals("login")){
					System.out.println(message.getObj());
					Result<User> result_login=lap.login(message.getObj());
					dos=new ObjectOutputStream(socket.getOutputStream());
					dos.writeObject(result_login);
				}else if(message.getMessage().equals("regist")){
					Result<User> result_regist=lap.regist(message.getObj());
					dos=new ObjectOutputStream(socket.getOutputStream());
					dos.writeObject(result_regist);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					if(ois!=null)
					    ois.close();
					if(dos!=null)
						dos.close();
					if(socket!=null)
						socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    }
}
