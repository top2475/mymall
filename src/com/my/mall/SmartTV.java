package com.my.mall;
public class SmartTV extends Product {
	String resolution;
	public SmartTV(String pname, int price,
			                    String resolution) {
		//Product 클래스에서 상속받은 인스턴스 변수 초기화
		this.pname = pname;
		this.price = price;
		//
		this.resolution = resolution;
	}

	@Override
	public void printExtra() {
		System.out.println("해상도 : "+resolution);
	}

}

