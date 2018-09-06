package io.day3.Serialization_quiz;

import java.io.File;
import java.util.*;

public class MemberMain {

	String fileName = "C:/iotestdata/serializable/member.dat";
	
	@SuppressWarnings("unchecked")
	public void registerMember(Scanner sc) {
		System.out.print("▶ 아이디: ");
		String id = sc.nextLine();
		
		System.out.print("▶ 암호: ");
		String pwd = sc.nextLine();
		
		System.out.print("▶ 성명: ");
		String name = sc.nextLine();
		
		System.out.print("▶ 주소: ");
		String address = sc.nextLine();
		
		Member member = new Member(id, pwd, name, address);
		
		List<Member> memberList = null;
		
		File file = new File(fileName);
		
		if(!file.exists()) { // 최초로 회원가입시
			memberList = new ArrayList<Member>();
			memberList.add(member);
			SerializableTest.objectToFileSave(memberList, fileName); 	
		}
		else { // 두번째 이후로 회원가입시
			Object obj = SerializableTest.getObjectFromFile(fileName); 
			memberList = (List<Member>) obj;
			memberList.add(member);
			SerializableTest.objectToFileSave(memberList, fileName);
		}
		
		System.out.println(">>> 회원가입 성공!! <<<");
		
	}// end of registerMember(Scanner sc)------------
	
	
	@SuppressWarnings("unchecked")
	public void showAllMember() {
		
		Object obj = SerializableTest.getObjectFromFile(fileName); 
		
		if(obj != null) {
			System.out.println("\n======== 모든 회원정보 출력 ========\n");

			List<Member> memberList = (List<Member>) obj;	
			
			for(Member member : memberList) {
				System.out.println(member);
			}
		}
		else {
			System.out.println(">>> 가입한 회원이 없습니다. <<<"); 
		}
		
		
	}// end of showAllMember()-----------------------
	
	
	@SuppressWarnings("unchecked")
	public Member login(Scanner sc) {
		
		System.out.print("▶ 아이디: ");
		String id = sc.nextLine();
		
		System.out.print("▶ 암호: ");
		String pwd = sc.nextLine();
		
		Object obj = SerializableTest.getObjectFromFile(fileName); 
		
		if(obj != null) {
			List<Member> memberList = (List<Member>) obj;	
			
			for(int i=0; i<memberList.size(); i++) {
				Member member = memberList.get(i);
				String userid = member.getId();
				String userpasswd = member.getPwd(); 
				if(userid.equals(id) && userpasswd.equals(pwd)) {
					System.out.println(">>> 로그인 성공!!! <<<");
					return member;
				}
			}// end of for------------------
			
			System.out.println(">>> 로그인 실패!!! <<<");
		}
		else {
			System.out.println(">>> 가입한 회원이 없습니다. <<<"); 
		}
		
		return null;
	}// end of login(Scanner sc)--------------------
	
	
	@SuppressWarnings("unchecked")
	public Member searchMember(Scanner sc) {
		
		Member member = null;
		
		System.out.print("▶ 검색할 아이디: ");
		String searchId = sc.nextLine();
		
		Object obj = SerializableTest.getObjectFromFile(fileName); 
		
		if(obj != null) {
			List<Member> memberList = (List<Member>) obj;	
			
			for(int i=0; i<memberList.size(); i++) {
				if(memberList.get(i).getId().equals(searchId)) {
					member = memberList.get(i);
					return member;
				}
			}// end of for------------------
			
		}
		else {
			System.out.println(">>> 가입한 회원이 없습니다. <<<"); 
		}
		
		return member;
		
	}// end of searchMember(Scanner sc)---------------
	
	public static void menu() {
		String menu = "\n>>>> 메뉴 <<<<\n" + 
	                  "1. 회원가입\n" + 
	                  "2. 로그인\n" + 
	                  "3. 모든회원정보출력\n" + 
	                  "4. 특정ID정보검색하기\n" +
	                  "5. 종료\n\n" + 
	                  "=> 메뉴번호선택 : ";
		
		System.out.print(menu);
		
	}// end of void menu()-------------------------
	
	
	public static void main(String[] args) {
	
		MemberMain main = new MemberMain();
		
		Scanner sc = new Scanner(System.in);
		
		String menuno = "";
		Member loginMember = null;
		do {
			MemberMain.menu();
			menuno = sc.nextLine();
			
			switch (menuno) {
				case "1":  // 회원가입
					main.registerMember(sc);	
				break;
			
				case "2":  // 로그인
					loginMember = main.login(sc);
					break;
					
				case "3":  // 모든회원정보출력
					if(loginMember != null)
						main.showAllMember();
					else
						System.out.println(">>> 먼저 로그인부터 하세요!! <<<");
					break;
					
				case "4":  // 특정ID정보검색하기
					if(loginMember != null) {
						Member searchMember = main.searchMember(sc);
						if(searchMember != null) {
							System.out.println("==== 검색된 회원정보 ====");
							System.out.println(searchMember);
						}
						else 
							System.out.println("==== 검색하신 회원은 없습니다. ====");
					}
					else
						System.out.println(">>> 먼저 로그인부터 하세요!! <<<");
					break;
					
				case "5":  // 종료
					break;
	
				default:
					System.out.println(">>> 메뉴에 없는 번호 입니다. 다시 선택하세요!!");
					break;
			}// end of switch------------------
			
		} while (!"5".equals(menuno)); // end of while-----------------

		System.out.println(">>> 프로그램 종료합니다. <<<");
		sc.close();
	}// end of main()---------------------------------

}
