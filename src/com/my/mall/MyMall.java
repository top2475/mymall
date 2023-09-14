package com.my.mall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMall implements IMall {
	//User[] users = new User[5];
	//users�� �迭���� List �� ����
	List<User> users = new ArrayList<>();
	String title;
	int selUser;
	Scanner scan = new Scanner(System.in);
	Product[] products = new Product[4];
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void start() {
		
		while (true) {
			System.out.println(title + " : Main - �������� ");
			System.out.println("========================");
			int i = 0;

			System.out.println("[u]ȸ�����");
			//System.out.println("[a]ȸ�����");
			
			for(User u : users) {
				// if (u == null) break;  -> �迭�϶� �ʿ�
				System.out.printf("[%d]%s(%s)\n",i++,
						u.getName(),u.getPayType());
			}
			//����� ���� �޴� �߰�(users �� ����Ʈ�϶��� size() �޼ҵ� ���)
			if(users.size() != 0) 
				System.out.println("[d]ȸ�� ����");
			
			System.out.println("[x]����");
			System.out.print("���� : ");
			String sel = scan.next();
			System.out.println("## " + sel + "���� ##\n");

			switch (sel) {
			case "u":
				genUser();
				break;
			case "a":
				for (User u : users) {
					//if (u == null)	break;
					System.out.println(u.getName() + "(" + 
						u.getPayType() + ")");
				}
				break;
			case "x":
				System.exit(0);   //main ����
				break;
			default:
				try{
				//ȸ�� ���� 
				if(users.size() != 0 && sel.equals("d")) {
					System.out.print("������ ����� ���� >> ");
					int s = scan.nextInt();
					//users �׸� ����
					users.remove(s);break;
				}
				
				//���⼭���ʹ� ����� ���� ��ȣ �Է� ó��
				
				selUser = Integer.parseInt(sel);   
				//����� ���� �����Է�(���ڿ�)�� ������ ��ȯ
				
				//selUser �� users�� ������ �������� �۾ƾ��մϴ�.
				//�迭�� ��ü�� null ����, List �� size�� ��.
				
				// if(users[selUser] == null ) {  //�迭 users
				if(users.get(selUser) == null ) {	
					System.out.println("������ �� ���� ����� �Դϴ�.");
					continue;    //while�� ó������ �ٽ� ����
				}
				productList();
				
				} catch (NumberFormatException e) {
					//Integer.parseInt(sel) ���� sel�� ���� �ƴ� ���ڰ� ������
					System.out.println("�߸��� �����Դϴ�.(Ȯ��!!)");
					// continue;
				} catch ( IndexOutOfBoundsException e) {
					System.out.println("������ �� ���� �׸� �Դϴ�.");
				} // ArrayIndexOutOfBoundsException �� ���� Exception
				
				//�߸��� �ε��� �ʰ�
				//�迭�� ArrayIndexOutOfBoundsException
				//List�� IndexOutOfBoundsException
			}
		}
	}
	
	
	public void genUser() {
		String sname;
		PayType ptype;

		int cnt = User.userCnt;   //static 

		while (true) {
			System.out.println(title + " : ȸ�� ��� ");
			System.out.print("���� >> ");
			sname = scan.next();  //Ű���� �Է�
			System.out.print("������� : [1]����  [2]�ſ�ī��  >>");
			switch (scan.next()) {  //Ű���� �Է�
			case "1":
				ptype = PayType.CASH;
				break;
			case "2":
				ptype = PayType.CARD;
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;  //while �ݺ� �������� ���� ���
			}
			//UserŬ���� ��ü�� �����Ͽ�, �������� �迭 users[]�� ����
			//users[i].sname=sname;   // ���� - private String sname;
			//users[i].setSname(sname);
			
			//�迭 users
			//users[cnt++] = new User(sname, ptype);  //�Էµ� �� 2���� ��ü ����

			users.add(new User(sname, ptype));  cnt++;
			
			System.out.println("[c]��� ��� ");
			System.out.println("[q]����");
			System.out.print("���� : ");
			String sel = scan.next();  //Ű���� �Է�
			if (sel.equals("c"))
				continue;   //while �� �������� �ٽ� ����.
			else if (sel.equals("q")) {
				User.userCnt = cnt;  //userCnt�� ������ ��ü ī��Ʈ ���� -> static
				break;    //while�� ���� �ݺ� ����
			} else {
				System.out.println("�߸��� �����Դϴ�.(����ڵ��)");
				System.out.print("��� �ұ��? (y / n)");
				if(scan.next().equals("n"))
					break;
				//"n" : genUser()����
				//"y" : ��� ȸ�����(while �ݺ�)
 			}	
		}
	}
	//
	void productList() {
		System.out.println(title+" : ��ǰ ��� - ��ǰ����");
		System.out.println("=========================");
		int i=0;
		
		for(Product p : products) { //��ǰ ����Ʈ(�迭) ���
			System.out.print("["+i+"]");
			p.printDetail();
			i++;
		}
		
		System.out.println();
		System.out.println("[h]���� ȭ��");
		System.out.println("[c]üũ(cart)");
		System.out.println("[p]PayType ����");
		System.out.print("���� : ");
		String sel = scan.next();
		System.out.println("## "+sel+"���� ##\n");

		switch(sel) {
			case "h":start();break;
			case "p":changePay(); productList(); break;
			case "c":checkOut();break;
			default:
				int psel = Integer.parseInt(sel);
				try{
				//users[selUser].cart.add(products[psel]);  //�迭
				
				users.get(selUser).cart.add(products[psel]);
				//user.get(selUser) -> User Ÿ�� ��ü
				
				}catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("�߸��� ��ǰ ����.(Ȯ��!!!)");
					
				}
				productList();
		}
	}
	
	public void genProduct() {  //��ǰ���� -> Product Ÿ�� �迭�� ����
		CellPhone cp = new CellPhone("������ ��Ʈ5",1000000,"SKT");
		products[0] = cp;
		cp = new CellPhone("���� ������ 7",980000,"KT");
		products[1] = cp;
		SmartTV st = new SmartTV("�Ｚ 3D Smart TV",5000000,"4K");
		products[2] = st;
		st = new SmartTV("LG Smart TV",2500000,"Full HD");
		products[3] = st;
	}
	
	void checkOut(){
	    System.out.println(title+" : üũ CART");
		System.out.println("=========================");
		int total=0;
		int i=0;
		
		//for(Product p : users[selUser].cart) { //�迭 users
		for(Product p : users.get(selUser).cart) { //
			System.out.printf("[%d]%s(%s)\n",i++,p.pname,p.price);
			total = total + p.price;
		}
		//if(users[selUser].cart.size() == 0)
		if(users.get(selUser).cart.size() == 0)
			System.out.println("��ٱ��� �����. ������ �����ϼ���.~");
		
		System.out.println("=========================");
		
		//System.out.println("�������: "+users[selUser].getPayType());
		System.out.println("�������: "+users.get(selUser).getPayType());

		System.out.println("�հ�: "+total+" �� �Դϴ�.");
		System.out.println("[d]��ǰ����,[p]��ٱ��� ������ ,[c]������� ���� ,[q]�����Ϸ�");
		System.out.print("����: ");
		String sel = scan.next();

		switch(sel) {
			case "q":System.out.println("## ������ �Ϸ�Ǿ����ϴ�. �����մϴ�. ##");
					 //System.exit(0);  //���α׷� ����
					 //���� �Ϸ��ϸ� ����� cart ����
				     //users[selUser].cart.clear();
					 users.get(selUser).cart.clear();
					 productList();   //�����Ϸ� �� ��ǰ����Ʈ�� �ٽ� �̵�
					 break;
			case "p":productList();break;
			case "c":changePay(); checkOut(); break;
			case "d":
				System.out.print("������ ��ǰ ���� >> ");
				int s = scan.nextInt();
				//users[selUser].cart.remove(s);
				users.get(selUser).cart.remove(s);
			default: checkOut();
		}		
	} //checkOut() end
	
	
	void changePay() {  //������� ���� 
		//���� ������ ������� ���� ���
		PayType pay = users.get(selUser).getPayType();
		PayType pay2;   //������ ���
		if(pay == PayType.CARD) pay2=PayType.CASH;
		else pay2 = PayType.CARD;
		
		System.out.println("���� ���� ��� : " + pay);
		System.out.print(pay2 + " �� �����ұ��(y/n)? ");
		
		String temp = scan.next();
		if(temp.equals("y")) {
			users.get(selUser).setPayType(pay2);  //����
			System.out.println("���� ��� : CARD �� ����Ǿ����ϴ�.");
		}else {
			System.out.println("��ҵǾ����ϴ�.");
		}
		
	}

}




