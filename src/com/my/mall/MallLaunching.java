package com.my.mall;

public class MallLaunching {

	public static void main(String[] args) {
		IMall i = new MyMall();
		
		i.setTitle("\n���ڷ��� ���̸�");
		i.genProduct();
		i.start();
	}

}
