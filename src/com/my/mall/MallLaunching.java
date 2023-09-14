package com.my.mall;

public class MallLaunching {

	public static void main(String[] args) {
		IMall i = new MyMall();
		
		i.setTitle("\n전자랜드 아이몰");
		i.genProduct();
		i.start();
	}

}
