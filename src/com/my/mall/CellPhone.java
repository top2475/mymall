package com.my.mall;
//Product 추상 클래스를 상속받아 구현한 CellPhone 클래스
public class CellPhone extends Product {
	String company;
	
	public CellPhone(String pname, int price, 
			            String company) {
		this.pname = pname;
		this.price = price;
		this.company = company;
	}

	@Override   
	public void printExtra() {
		System.out.println("통신사: "+company);
	}

}
