package com.my.mall;
public abstract class Product {
	//�߻�Ŭ���� : ��ü�� ���� �������� ����.
	String pname;
	int price;
	
	// ��ǰ��� ������ ����ϴ� �������̽� �޼���
	public void printDetail() {
		System.out.print("��ǰ��: "+pname+" , ");
		System.out.print("����: "+price+" , ");
		printExtra();
	}
	//�߻�޼ҵ�
	public abstract void printExtra();
}
