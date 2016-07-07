package cn.edu.nuc.onlinestore.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;
import cn.edu.nuc.onlinestore.utils.Utils;
import cn.edu.nuc.onlinestore.vo.Result;

public class ShowGoodsActionProcess {
     private AddGoodsService ags=null;
	
	 public Result<GoodsStore> showAllGoods(){
		 Result<GoodsStore> result=new Result<>();
		 GoodsStore gs=Utils.read(GoodsStore.class,Utils.getStringpath("goods"));
		 if(!gs.equals(null)){
			 result.setMsg("success");
			 result.setObj(gs);
		 }else{
			 result.setMsg("error");
			 result.setObj(null);
		 }
		 return result;
     }
	 public Result<GoodsStore> showOneGoods(String goodsName,String goodsId){
		Result<GoodsStore> result=new Result<>();
		Goods goods=new Goods();
		goods.setId(goodsId);
		goods.setName(goodsName);
		GoodsStore gs=Utils.read(GoodsStore.class,Utils.getStringpath("goods"));
		System.out.println("gs-->"+gs.getGs().isEmpty());
		int size=gs.getGs().size();
		System.out.println("goodsNum:"+size);
		for(Goods g:gs.getGs()){
			System.out.println(g.equals(goods));
			if(goods.equals(g)){
				System.out.println(111111);
				Set<Goods> set=new HashSet<>();
				set.add(g);
				GoodsStore goodsStore=new GoodsStore();
				goodsStore.setGs(set);
				result.setMsg("success");
				result.setObj(goodsStore);
				return result;
			}
		}
		result.setMsg("error");
		result.setObj(null);
		return result;
	 }
	
}
