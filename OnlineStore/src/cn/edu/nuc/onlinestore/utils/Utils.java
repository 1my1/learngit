package cn.edu.nuc.onlinestore.utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import cn.edu.nuc.onlinestore.model.Admin;
import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;


public class Utils {
	 static Scanner sc=new Scanner(System.in);
	 static ObjectInputStream ois;
	 static ObjectOutputStream oos;
	 static Set<Goods> goodSet=new HashSet<Goods>();
	 public static boolean check(Admin a){
   	  Admin admin=null;
   	  try {
			ois=new ObjectInputStream(new FileInputStream("d:/home/admin.data"));
			admin=(Admin)ois.readObject();
		} catch (IOException|ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
   	  if(admin.equals(a)){
   		  return true;
   	  }
   	  return false;
     }
	 
	 public static String getStringpath(String name){
   	  return "D:/home/"+name+".data";
     }
	   @SuppressWarnings("unchecked")
		public static <T> T read(Class<T> clazz,String path){
	    	  T objs=null;
	    	  try
	    	  {
		    	  ois=new ObjectInputStream(new FileInputStream(path));
		    	  objs=(T)ois.readObject();
		    	  return objs;
	    	  }catch(IOException|ClassNotFoundException e){
	    		System.out.println(e.getMessage());  
	    	  }finally{
	    		  if(ois!=null){
	    			  try {
						ois.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		  }
	    	  }
			return objs;
	      }
	     
	      public static void write(GoodsStore goodsStore,String path){
	    	  try{
	    		  oos=new ObjectOutputStream(new FileOutputStream(path));
	        	  oos.writeObject(goodsStore);
	    	  }catch(IOException e){
	    		  e.printStackTrace();
	    		  System.out.println(e.getMessage());
	    	  }
	      }
}
