package com.my.mall;
public class SmartTV extends Product {
	String resolution;
	public SmartTV(String pname, int price,
			                    String resolution) {
		//Product Ŭ�������� ��ӹ��� �ν��Ͻ� ���� �ʱ�ȭ
		this.pname = pname;
		this.price = price;
		//
		this.resolution = resolution;
	}

	@Override
	public void printExtra() {
		System.out.println("�ػ� : "+resolution);
	}

}

