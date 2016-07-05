package cn.edu.nuc.onlinestore.action;

import java.util.Iterator;

import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;
import cn.edu.nuc.onlinestore.utils.Utils;

public class AddGoodsService {
	public void addGood(Goods g){
    	Utils.write(g,Utils.getStringpath("goods"));
    }
    public void modifyGood(Goods g){
    	GoodsStore gs= Utils.read(GoodsStore.class,Utils.getStringpath("goods"));
    	Goods good=null;
    	Iterator<Goods> iter=gs.getGs().iterator();
    	while(iter.hasNext()){
    		if(iter.next().equals(g)){
    			
    		 good=g;
    		 System.out.println(good);
    		}
    	}
    	if(good.equals(g)){
    		gs.getGs().add(good);
    		
    	}
    	gs.setGs(gs.getGs());
    	for(Goods goo:gs.getGs()){
    		System.out.println(goo.getName());
    		System.out.println(goo.getPrice());
    		addGood(goo);
    	}
    }
    public GoodsStore showGoods(){
    	GoodsStore gs=Utils.read(GoodsStore.class,Utils.getStringpath("goods"));
    	return gs;
    }
    public void delete(Goods g){
    	GoodsStore gs=Utils.read(GoodsStore.class, Utils.getStringpath("goods"));
    	Iterator<Goods> iter=gs.getGs().iterator();
    	while(iter.hasNext()){
    		if(iter.next().equals(g)){
    			iter.remove();
    		}
    	}
    	for(Goods good:gs.getGs()){
    		Utils.write(good,Utils.getStringpath("goods"));
    	}
    }
    public Goods findGoodByName(String name){
    	GoodsStore gs=Utils.read(GoodsStore.class, Utils.getStringpath("goods"));
    	for(Goods g:gs.getGs()){
    		if(g.getName().equals(name)){
    			return g;
    		}
    	}
    	return null;
    }
}
