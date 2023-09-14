package com.my.mall;
public abstract class Product {
	//추상클래스 : 객체를 직접 생성하지 않음.
	String pname;
	int price;
	
	// 상품명과 가격을 출력하는 인터페이스 메서드
	public void printDetail() {
		System.out.print("상품명: "+pname+" , ");
		System.out.print("가격: "+price+" , ");
		printExtra();
	}
	//추상메소드
	public abstract void printExtra();
}
