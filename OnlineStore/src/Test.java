import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;

public class Test {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 /*try{
   		  ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/home/goods.data"));
       	  GoodsStore goodsStore=new GoodsStore();
       	  goodsStore.setGs(new HashSet<Goods>());
		oos.writeObject(goodsStore);
   	  }catch(IOException e){
   		  e.printStackTrace();
   		  System.out.println(e.getMessage());
   	  }*/
		File file=new File("d:/uuu/1.txt");
	}

}
