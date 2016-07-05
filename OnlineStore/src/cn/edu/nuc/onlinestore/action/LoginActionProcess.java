package cn.edu.nuc.onlinestore.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.edu.nuc.onlinestore.model.Admin;
import cn.edu.nuc.onlinestore.model.User;
import cn.edu.nuc.onlinestore.vo.Result;

public class LoginActionProcess {
	   private ObjectInputStream ois=null;
	   private ObjectOutputStream oos=null;
       public Result<User>  login(User user){
    	   Result<User> result=new Result<User>();
		   if(check(user)){
			   result.setObj(user);
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
    				ois=new ObjectInputStream(new FileInputStream("d:/admin/admin.data"));
    				admin=(Admin)ois.readObject();
    			} catch (IOException|ClassNotFoundException e) {
    				// TODO Auto-generated catch block
    				System.out.println(e.getMessage());
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
    	   	  if(admin.equals(a)){
    	   		  return true;
    	   	  }
    	   	  return false;
      }
      public boolean check(User u){
    	   User user=null;
    	   try {
    		  File file=new File("d:/user/"+u.getName()+".data");
    		  if(!file.exists()){
    			  return false;
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
    		   return true;
    	   }
    	   return false;
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
}
