package cn.edu.nuc.onlinestore.vo;

import java.io.Serializable;

public class Message<T> implements Serializable {
    private String message;
    private T obj;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
       
}
