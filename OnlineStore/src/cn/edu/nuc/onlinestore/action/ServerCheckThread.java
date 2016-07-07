package cn.edu.nuc.onlinestore.action;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import cn.edu.nuc.onlinestore.frame.AdminStore;
import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;
import cn.edu.nuc.onlinestore.model.User;
import cn.edu.nuc.onlinestore.vo.Message;
import cn.edu.nuc.onlinestore.vo.Result;

public class ServerCheckThread extends Thread {
	    private LoginActionProcess lap=null;
		private ObjectOutputStream dos=null;
		private ObjectInputStream ois=null;
		private ShowGoodsActionProcess sgap=null;
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
				System.err.println("接受到的信息购物车--"+message.getMessage());
				lap=new LoginActionProcess();
				sgap=new  ShowGoodsActionProcess();
				if(message.getMessage().equals("login")){
					System.out.println(message.getObj());
					Result<User> result_login=lap.login(message.getObj());
					dos=new ObjectOutputStream(socket.getOutputStream());
					dos.writeObject(result_login);
					return;
				}else if(message.getMessage().equals("regist")){
					Result<User> result_regist=lap.regist(message.getObj());
					dos=new ObjectOutputStream(socket.getOutputStream());
					dos.writeObject(result_regist);
					return;
				}else if(message.getMessage().equals("save")){
					Result<User> result_save=lap.save(message.getObj());
					System.out.println("保存操作："+result_save.getMsg());
					dos=new ObjectOutputStream(socket.getOutputStream());
					dos.writeObject(result_save);
				}else if(message.getMessage().equals("viewGoodsStore")){
					Result<GoodsStore> result_showGoods=sgap.showAllGoods();
					dos=new ObjectOutputStream(socket.getOutputStream());
					dos.writeObject(result_showGoods);
					return;
				}else if(message.getMessage().equals("checkOut")){
					User user=message.getObj();
					System.out.println("按时尽量快点击发送来的快放假"+user);
					Result<User> result_checkOut=lap.changeGoodsNum(user);
					dos=new ObjectOutputStream(socket.getOutputStream());
					dos.writeObject(result_checkOut);
				}else if(message.getMessage().contains(",")){
					System.out.println(message.getMessage());
					String mess[]=message.getMessage().split(",");
					System.out.println(mess[2]);
					System.out.println(mess[1]);
					Result<GoodsStore> result_showOneGoods=sgap.showOneGoods(mess[2],mess[1]);
					dos=new ObjectOutputStream(socket.getOutputStream());
					dos.writeObject(result_showOneGoods);
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
