package com.my.mall;

import java.util.ArrayList;
import java.util.List;

import com.my.mall.PayType;

public class User {
	private String name;
	private PayType payType;
	static int userCnt;   //클래스변수,객체들이 공유하는 값
	List<Product> cart = new ArrayList<Product>();
	
	public User(String name, PayType payType) {
		this.name = name;
		this.payType = payType;
	}
	
	public String getName() {
		return name;
	}
	public PayType getPayType() {
		return payType;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public static void incUserCnt() {
		userCnt += 1;
	}
	
	
	
}
