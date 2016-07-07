package cn.edu.nuc.onlinestore.action;

import java.util.Set;

import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;
import cn.edu.nuc.onlinestore.utils.Utils;

public class AddGoodsService {
	static GoodsStore gs=new GoodsStore();
	public void addGood(Goods g){
		GoodsStore gs= Utils.read(GoodsStore.class,Utils.getStringpath("goods"));
		Set<Goods> goodsSet=gs.getGs();
		System.out.println(goodsSet.add(g));
		gs.setGs(goodsSet);
    	Utils.write(gs,Utils.getStringpath("goods"));
    }
    public boolean modifyGood(Goods g){
    	GoodsStore gs= Utils.read(GoodsStore.class,Utils.getStringpath("goods"));
    	Set<Goods> goodSet=gs.getGs();
    	Goods goods=findGoodByName(g.getName());
    	goodSet.remove(goods);
    	goodSet.add(g);
//    	System.out.println(goodSet.add(g));
    	gs.setGs(goodSet);
    	Utils.write(gs, Utils.getStringpath("goods"));
    	/*Goods good=null;
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
    	}*/
		return true;
    }
    public GoodsStore showGoods(){
    	GoodsStore gs=Utils.read(GoodsStore.class,Utils.getStringpath("goods"));
    	return gs;
    }
    public void delete(Goods g){
    	GoodsStore gs=Utils.read(GoodsStore.class, Utils.getStringpath("goods"));
    	Set<Goods> goodSet=gs.getGs();
    	if(goodSet.contains(g)){
    		goodSet.remove(g);
    	}
    	gs.setGs(goodSet);
    	Utils.write(gs, Utils.getStringpath("goods"));
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
