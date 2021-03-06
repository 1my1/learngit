package cn.edu.nuc.onlinestore.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = -5523647580332725112L;
	
	
	private Map<Goods, Integer> maps = new HashMap<Goods, Integer>();
	//购物车商品总价格
	private double totalPrice = 0;
	//购物车商品总数量
	private int totalQuantity = 0;
	/**
	 * 添加商品到购物车
	 * @param goods
	 * @param quantity
	 * @return 返回该商品在购物车内总数量
	 */
	public int add(Goods goods, int quantity){
		
		if( maps.get( goods ) == null ){
			//如果maps 里没有该商品,直接添加
			maps.put(goods, quantity);
		} else {
			//如果maps 里已存在当前添加商品,则累加商品数量
			for(Map.Entry<Goods,Integer> entry:maps.entrySet()){
				if(entry.getKey().equals(goods)){
					entry.setValue(entry.getValue()+quantity);
				}
			}
		}
		
		//添加商品后调用计算购物车总金额、总价格方法
		this.total();
		//返回该商品在购物车内总数量
		return 0;
	}
	
	/**
	 * 将某件商品从购物车内移除
	 * @param goods 商品对象
	 * @return 返回移除数量
	 */
	public int remove(Goods goods){
		
		int q = maps.remove( goods );
		
		//移除商品后调用计算购物车总金额、总价格方法
		total();
		
		return q;
	}
	
	/**
	 * 根据商品id将商品从购物车内移除
	 * @param gid
	 * @return
	 */
	public int remove(String gid){
		Goods goods = new Goods();
		goods.setId(gid);
		
		return this.remove(goods);
	}
	
	
	/**
	 * 计算购物车总金额、总数量方法
	 */
	private void total(){
		//先将总金额、总数量归零
		this.totalPrice = 0;
		this.totalQuantity = 0;
		//遍历购物车 maps 重新计算购物车总价格、总数量
		for(Map.Entry<Goods,Integer> entry:maps.entrySet()){
			this.totalQuantity++;
			this.totalPrice+=(entry.getKey().getPrice())*(entry.getValue());
		}
	}

	/**
	 * 返回购物车商品总金额(此处不应提供set方法,因为修改购物车商品金额不能由外面修改)
	 * @return
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * 返回购物车商品总数量(此处不应提供set方法,因为修改购物车商品数量不能由外面修改)
	 * @return
	 */
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public Map<Goods,Integer> getMaps(){
		return maps;
	}
	

}
