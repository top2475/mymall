package com.my.mall;
//Product �߻� Ŭ������ ��ӹ޾� ������ CellPhone Ŭ����
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
		System.out.println("��Ż�: "+company);
	}

}
