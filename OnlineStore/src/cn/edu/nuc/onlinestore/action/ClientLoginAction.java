package cn.edu.nuc.onlinestore.action;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.edu.nuc.onlinestore.model.User;

public class ClientLoginAction extends Thread{
	private Socket socket;
	private User user;
	private DataInputStream dis=null;
	private ObjectOutputStream oos=null;
	public ClientLoginAction(User u){
		try {
			socket=new Socket("127.0.0.1", 4000);
			this.user=u;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	try {
			oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(user);
			receive();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(oos!=null)
					oos.close();
			} catch (Exception e2) {
			    System.out.println(e2.getMessage());
			}
		}
    }
    public String  receive(){
    	try {
			dis=new DataInputStream(socket.getInputStream());
			String message=dis.readUTF();
			if(message!=null){
				return message;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(dis!=null)
				  dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
    	return "";
    }
}
