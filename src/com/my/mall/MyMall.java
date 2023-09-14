package com.my.mall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMall implements IMall {
	//User[] users = new User[5];
	//users를 배열에서 List 로 수정
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
			System.out.println(title + " : Main - 계정선택 ");
			System.out.println("========================");
			int i = 0;

			System.out.println("[u]회원등록");
			//System.out.println("[a]회원목록");
			
			for(User u : users) {
				// if (u == null) break;  -> 배열일때 필요
				System.out.printf("[%d]%s(%s)\n",i++,
						u.getName(),u.getPayType());
			}
			//사용자 삭제 메뉴 추가(users 가 리스트일때만 size() 메소드 사용)
			if(users.size() != 0) 
				System.out.println("[d]회원 삭제");
			
			System.out.println("[x]종료");
			System.out.print("선택 : ");
			String sel = scan.next();
			System.out.println("## " + sel + "선택 ##\n");

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
				System.exit(0);   //main 종료
				break;
			default:
				try{
				//회원 삭제 
				if(users.size() != 0 && sel.equals("d")) {
					System.out.print("삭제할 사용자 선택 >> ");
					int s = scan.nextInt();
					//users 항목 삭제
					users.remove(s);break;
				}
				
				//여기서부터는 사용자 선택 번호 입력 처리
				
				selUser = Integer.parseInt(sel);   
				//사용자 선택 숫자입력(문자열)을 정수로 변환
				
				//selUser 는 users의 데이터 갯수보다 작아야합니다.
				//배열은 객체가 null 인지, List 는 size와 비교.
				
				// if(users[selUser] == null ) {  //배열 users
				if(users.get(selUser) == null ) {	
					System.out.println("선택할 수 없는 사용자 입니다.");
					continue;    //while문 처음에서 다시 시작
				}
				productList();
				
				} catch (NumberFormatException e) {
					//Integer.parseInt(sel) 에서 sel이 숫자 아닌 문자가 있으면
					System.out.println("잘못된 선택입니다.(확인!!)");
					// continue;
				} catch ( IndexOutOfBoundsException e) {
					System.out.println("선택할 수 없는 항목 입니다.");
				} // ArrayIndexOutOfBoundsException 는 하위 Exception
				
				//잘못된 인덱스 초과
				//배열은 ArrayIndexOutOfBoundsException
				//List는 IndexOutOfBoundsException
			}
		}
	}
	
	
	public void genUser() {
		String sname;
		PayType ptype;

		int cnt = User.userCnt;   //static 

		while (true) {
			System.out.println(title + " : 회원 등록 ");
			System.out.print("성명 >> ");
			sname = scan.next();  //키보드 입력
			System.out.print("결제방법 : [1]현금  [2]신용카드  >>");
			switch (scan.next()) {  //키보드 입력
			case "1":
				ptype = PayType.CASH;
				break;
			case "2":
				ptype = PayType.CARD;
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				continue;  //while 반복 시작으로 가서 계속
			}
			//User클래스 객체를 생성하여, 참조값을 배열 users[]에 저장
			//users[i].sname=sname;   // 오류 - private String sname;
			//users[i].setSname(sname);
			
			//배열 users
			//users[cnt++] = new User(sname, ptype);  //입력된 값 2개로 객체 생성

			users.add(new User(sname, ptype));  cnt++;
			
			System.out.println("[c]계속 등록 ");
			System.out.println("[q]종료");
			System.out.print("선택 : ");
			String sel = scan.next();  //키보드 입력
			if (sel.equals("c"))
				continue;   //while 문 시작으로 다시 실행.
			else if (sel.equals("q")) {
				User.userCnt = cnt;  //userCnt는 생성된 객체 카운트 변수 -> static
				break;    //while문 무한 반복 종료
			} else {
				System.out.println("잘못된 선택입니다.(사용자등록)");
				System.out.print("계속 할까요? (y / n)");
				if(scan.next().equals("n"))
					break;
				//"n" : genUser()종료
				//"y" : 계속 회원등록(while 반복)
 			}	
		}
	}
	//
	void productList() {
		System.out.println(title+" : 상품 목록 - 상품선택");
		System.out.println("=========================");
		int i=0;
		
		for(Product p : products) { //상품 리스트(배열) 출력
			System.out.print("["+i+"]");
			p.printDetail();
			i++;
		}
		
		System.out.println();
		System.out.println("[h]메인 화면");
		System.out.println("[c]체크(cart)");
		System.out.println("[p]PayType 변경");
		System.out.print("선택 : ");
		String sel = scan.next();
		System.out.println("## "+sel+"선택 ##\n");

		switch(sel) {
			case "h":start();break;
			case "p":changePay(); productList(); break;
			case "c":checkOut();break;
			default:
				int psel = Integer.parseInt(sel);
				try{
				//users[selUser].cart.add(products[psel]);  //배열
				
				users.get(selUser).cart.add(products[psel]);
				//user.get(selUser) -> User 타입 객체
				
				}catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("잘못된 상품 선택.(확인!!!)");
					
				}
				productList();
		}
	}
	
	public void genProduct() {  //상품생성 -> Product 타입 배열이 참조
		CellPhone cp = new CellPhone("갤럭시 노트5",1000000,"SKT");
		products[0] = cp;
		cp = new CellPhone("애플 아이폰 7",980000,"KT");
		products[1] = cp;
		SmartTV st = new SmartTV("삼성 3D Smart TV",5000000,"4K");
		products[2] = st;
		st = new SmartTV("LG Smart TV",2500000,"Full HD");
		products[3] = st;
	}
	
	void checkOut(){
	    System.out.println(title+" : 체크 CART");
		System.out.println("=========================");
		int total=0;
		int i=0;
		
		//for(Product p : users[selUser].cart) { //배열 users
		for(Product p : users.get(selUser).cart) { //
			System.out.printf("[%d]%s(%s)\n",i++,p.pname,p.price);
			total = total + p.price;
		}
		//if(users[selUser].cart.size() == 0)
		if(users.get(selUser).cart.size() == 0)
			System.out.println("장바구니 비었음. 물건을 구입하세요.~");
		
		System.out.println("=========================");
		
		//System.out.println("결제방법: "+users[selUser].getPayType());
		System.out.println("결제방법: "+users.get(selUser).getPayType());

		System.out.println("합계: "+total+" 원 입니다.");
		System.out.println("[d]상품삭제,[p]장바구니 나가기 ,[c]결제방법 변경 ,[q]결제완료");
		System.out.print("선택: ");
		String sel = scan.next();

		switch(sel) {
			case "q":System.out.println("## 결제가 완료되었습니다. 종료합니다. ##");
					 //System.exit(0);  //프로그램 종료
					 //결제 완료하면 사용자 cart 비우기
				     //users[selUser].cart.clear();
					 users.get(selUser).cart.clear();
					 productList();   //결제완료 후 상품리스트로 다시 이동
					 break;
			case "p":productList();break;
			case "c":changePay(); checkOut(); break;
			case "d":
				System.out.print("삭제할 상품 선택 >> ");
				int s = scan.nextInt();
				//users[selUser].cart.remove(s);
				users.get(selUser).cart.remove(s);
			default: checkOut();
		}		
	} //checkOut() end
	
	
	void changePay() {  //결제방법 변경 
		//현재 선택한 사용자의 결재 방법
		PayType pay = users.get(selUser).getPayType();
		PayType pay2;   //변경할 방법
		if(pay == PayType.CARD) pay2=PayType.CASH;
		else pay2 = PayType.CARD;
		
		System.out.println("현재 결재 방법 : " + pay);
		System.out.print(pay2 + " 로 변경할까요(y/n)? ");
		
		String temp = scan.next();
		if(temp.equals("y")) {
			users.get(selUser).setPayType(pay2);  //변경
			System.out.println("결재 방법 : CARD 로 변경되었습니다.");
		}else {
			System.out.println("취소되었습니다.");
		}
		
	}

}




