package cn.edu.nuc.onlinestore.vo;

import java.io.Serializable;

public class Result<T> implements Serializable {
	
	
	private String msg;
	
	
	private T obj;


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public T getObj() {
		return obj;
	}


	public void setObj(T obj) {
		this.obj = obj;
	}
	
	

}
