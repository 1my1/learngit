package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.edu.nuc.onlinestore.model.Admin;
import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;
import cn.edu.nuc.onlinestore.model.User;

public class Test {
    public Test(){
    	File file=new File("d:/home");
        if(!file.exists()){
        	 file.mkdir();
        	 try{
	   	   		  ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/home/goods.data"));
	   	       	  GoodsStore goodsStore=new GoodsStore();
	   	       	  goodsStore.setGs(new HashSet<Goods>());
	   			  oos.writeObject(goodsStore);
   	   	     }catch(IOException e){
	   	   		  e.printStackTrace();
	   	   		  System.out.println(e.getMessage());
   	   	     }
        }
        File file1=new File("d:/user");
        
        if(!file1.exists()){
        	file1.mkdir();
        	try {
    			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/home/admin.data"));
    			Admin admin=new Admin();
    			admin.setName("admin");
    			admin.setPass("admin");
    			oos.writeObject(admin);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
    }
}
