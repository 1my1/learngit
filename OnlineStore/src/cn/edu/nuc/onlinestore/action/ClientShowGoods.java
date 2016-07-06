package cn.edu.nuc.onlinestore.action;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.edu.nuc.onlinestore.model.GoodsStore;
import cn.edu.nuc.onlinestore.vo.Message;
import cn.edu.nuc.onlinestore.vo.Result;

public class ClientShowGoods{
	
	private Socket socket;
	private Message<GoodsStore> message;
	private ObjectInputStream dis=null;
	private ObjectOutputStream oos=null;
	private Result<GoodsStore> tip;
	
	public ClientShowGoods(Message<GoodsStore> message){
		try {
			socket=new Socket("127.0.0.1", 4000);
			this.message=message;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//发送消息
    public void send() {
    	// TODO Auto-generated method stub
    	try {
			oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(message);
			set(receive());
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
    //接受消息
    public Result<GoodsStore>  receive(){
    	try {
			dis=new ObjectInputStream(socket.getInputStream());
			@SuppressWarnings("unchecked")
			Result<GoodsStore> result=(Result<GoodsStore>)dis.readObject();
			if(result!=null){
				System.out.println(result.getMsg());
				return result;
			}
		} catch (IOException | ClassNotFoundException e) {
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
    	return null;
    }
    public void set(Result<GoodsStore> result){
    	this.tip=result;
    }
    public Result<GoodsStore> get(){
    	return this.tip;
    }
}
