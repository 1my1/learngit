package cn.edu.nuc.onlinestore.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import cn.edu.nuc.onlinestore.model.Admin;
import cn.edu.nuc.onlinestore.model.Cart;
import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;
import cn.edu.nuc.onlinestore.model.User;
import cn.edu.nuc.onlinestore.utils.Utils;
import cn.edu.nuc.onlinestore.vo.Result;
import test.Test;

public class LoginActionProcess {
	   private AddGoodsService ags=null;
	   private ObjectInputStream ois=null;
	   private ObjectOutputStream oos=null;
       public Result<User>  login(User user){
    	   Result<User> result=new Result<User>();
    	   User uu=check(user);
		   if(uu!=null){
			   result.setObj(uu);
			   result.setMsg("success");
			   return result;
		   }
		   result.setObj(null);
		   result.setMsg("error");
		   return result;
    	   
       }
       public boolean  login(Admin admin){
    	   
		   if(check(admin)){
			   return true;
		   }
		   return false;
    	   
       }
       public boolean check(Admin a){
    	   	  Admin admin=null;
    	   	  try {
    				ois=new ObjectInputStream(new FileInputStream("d:/home/admin.data"));
    				admin=(Admin)ois.readObject();
    			} catch (IOException|ClassNotFoundException e) {
    				// TODO Auto-generated catch block
    				Test test=new Test();
    			}finally{
    				try {
    					if(ois!=null)
						ois.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    	   	  if(admin.equals(a)){
    	   		  return true;
    	   	  }
    	   	  return false;
      }
      public User check(User u){
    	   User user=null;
    	   try {
    		  File file=new File("d:/user/"+u.getName()+".data");
    		  if(!file.exists()){
    			  return null;
    		  }
			  ois=new ObjectInputStream(new FileInputStream(file));
			  user=(User)ois.readObject();
		   } catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		   }finally{
			   try {
				if(ois!=null)
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
    	   if(user.equals(u)){
    		   return user;
    	   }
    	   return null;
      }
      public Result<User> regist(User u){
    	  Result<User> result=null;
    	  if(u!=null){
    		  result=new Result<>();
    		  try {
    			File file=new File("d:/user/"+u.getName()+".data");
    			if(file.exists()){
    				result.setMsg("exist");
    				return result;
    			}
				oos=new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(u);
				result.setMsg("success");
				return result;
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(oos!=null)
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
    	  }
    	  System.out.println("用户为空！");
    	  result.setMsg("error");
          return result;
      }
      public Result<User> save(User user){
    	  Result<User> result=new Result<User>();
    	  
    	  try {
    		   if(check(user)!=null){
    			   oos=new ObjectOutputStream(new FileOutputStream("d:/user/"+user.getName()+".data"));
    			   oos.writeObject(user);
    			   result.setMsg("success");
    		   }else{
    			   result.setMsg("error");
    		   }
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
    	  
		  return result;
    	  
      }
      public Result<User> changeGoodsNum(User user){
 		 GoodsStore gs=Utils.read(GoodsStore.class,Utils.getStringpath("goods"));
 		 Map<Goods, Integer> map=user.getCart().getMaps();
 		 Cart cart=new Cart();
 		 user.setCart(cart);
 		 System.err.println("气温气温气温气温气温"+user.getCart().getMaps()==null);
		 ags=new AddGoodsService();
		 for(Goods g:gs.getGs()){
			 for(Map.Entry<Goods, Integer> entry:map.entrySet()){
				 if(entry.getKey().equals(g)){
					 g.setNum(g.getNum()-entry.getValue());
					 ags.modifyGood(g);
				 }
			 }
		 }
		 save(user);
		 Result<User> result=new Result<>();
		 result.setObj(user);
		 result.setMsg("success");
 		 return result;
 	 }
}
