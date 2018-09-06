package io.day3.Serialization;

import java.util.*;

import java.io.*;

public class MemberMain {
	List<Member> memList = new ArrayList<Member>();
	SerializableTest serial = new SerializableTest();
	String filename = "c:/iotestdata/serializable/Members.dat";
	
	public static void menu() {
		System.out.println("\n========= MENU =========");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 모든 회원 출력");
		System.out.println("4. 특정 ID 검색");
		System.out.println("5. 나의 정보 보기");
		System.out.println("6. 나의 정보 변경");
		System.out.println("7. 종료");
		System.out.println("=========================");
		
		System.out.print(">>> 메뉴 번호 선택 => ");
	}
	
	@SuppressWarnings({ "unchecked" })
	public void join(Scanner sc) {
		System.out.print("▷ 사용하실 ID를 입력하세요: ");
		String id = sc.nextLine();
		System.out.print("▷ 사용하실 PW를 입력하세요: ");
		String pw = sc.nextLine();
		System.out.print("▷ 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("▷ 직책을 입력하세요: ");
		String position = sc.nextLine();
		Member mem = new Member(id, pw, name, position);
		
		File file = new File(filename);
		
		if(!file.exists()) {	// 최초 회원 가입 (파일이 없을 때)
			memList.add(mem);
			serial.objectToFileSave(memList, filename);
		}else {					// 2번째 이상부터 (파일이 존재할 때)
			Object obj = serial.getObjectFromFile(filename);
			memList = (List<Member>)obj;
			memList.add(mem);
			serial.objectToFileSave(memList, filename);
		}	
		System.out.println(">> 회원가입 성공 <<");
	}
	
	public Member login(Scanner sc) {
		System.out.print("▶ID: ");
		String userid = sc.nextLine();
		System.out.print("▶PW: ");
		String userpw = sc.nextLine();
		
		Member member = null;
		Object obj = serial.getObjectFromFile(filename);
		if(obj != null) {
			@SuppressWarnings("unchecked")
			List<Member> fileMemList = (List<Member>)obj;
			for(Member mem :fileMemList) {
				String id = mem.getId();
				String pw = mem.getPw();
				if(id.equals(userid) && pw.equals(userpw)) {
					System.out.println(">> 로그인 성공! <<");
					member = mem;
					break;
				}
			}
		}
		else {
			System.out.println(">> 가입한 회원이 없습니다 <<");
			member = null;
		}
		return member;
	}
	
	public void allInfo() {
		Object obj = serial.getObjectFromFile(filename);
		if(obj != null) {
			@SuppressWarnings("unchecked")
			List<Member> fileMemList = (List<Member>)obj;
			for(Member mem :fileMemList) {
				System.out.println(mem.info());
			}
		}
	}
	
	public void searchId(Scanner sc) {
		System.out.print(">> 검색하실 ID를 입력하세요: ");
		String searchId = sc.nextLine();
		Object obj = serial.getObjectFromFile(filename);
		if(obj != null) {
			@SuppressWarnings("unchecked")
			List<Member> fileMemList = (List<Member>)obj;
			for(Member mem :fileMemList) {
				if(searchId.equals(mem.getId())) {
					System.out.println(mem.info());
				}
			}
		}
	}
	
	
/*  #특정 객체를 삭제한 후 새로운 객체로 대체하기
	memberList.set(3, new Member("suji","asdf1234$","수지"));
	collection.set(index, element);
	
	System.out.println("=== 특정 객체 삭제후 새로운 객체로 대체시 출력 화면 ===\n");
	for(int i=0; i<memberList.size(); i++) {
		System.out.println(">> index: "+i);
		memberList.get(i).showInfo();
	}*/
	
	public void changeInfo(Member mem) {
		Scanner sc = new Scanner(System.in);
		System.out.print(">> 변경하실 정보를 선택하세요\n 1. 이름\n 2. 직무\n==> ");
		String changeNum = sc.nextLine();
		
		switch (changeNum) {
		case "1":	// 이름일 때
			System.out.print(">> 변경하실 이름을 입력하세요: ");
			mem.setName(sc.nextLine());
			break;
		case "2":	// 직무일 때
			System.out.print(">> 변경하실 직무를 입력하세요: ");
			mem.setPosition(sc.nextLine());
			break;

		default:
			System.out.println(">> 1, 2 중에서만 선택 가능합니다. <<");
			break;	
		}
		System.out.println(mem.info());
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String menuNum ="";
		MemberMain member = new MemberMain();
		Member memberObj = null;
		
	
		Outer: 
		while(!"7".equals(menuNum)) {
			MemberMain.menu();
			menuNum = sc.nextLine();
			
			switch (menuNum) {
			case "1":	// 회원가입
				member.join(sc);
				break;
				
			case "2":	// 로그인
				memberObj = member.login(sc);
				break;
				
			case "3":	// 모든 회원 출력
				if(memberObj != null) {
					member.allInfo();
					break;
				}
				else{
					System.out.println(">> 로그인이 필요한 기능입니다. <<");
					break;
				}
				
			case "4": // 특정 ID 검색
				if(memberObj != null) {
					member.searchId(sc);
					break;
				}
				else {
					System.out.println(">> 로그인이 필요한 기능입니다. <<");
					break;
				}
				
			case "5":	// 나의 정보 출력
				if(memberObj != null) {
					System.out.println(memberObj.info());
					break;
				}
				else {
					System.out.println(">> 로그인이 필요한 기능입니다. <<");
					break;
				}
				
			case "6":	// 나의 정보 변경
				if(memberObj != null) {
					member.changeInfo(memberObj);
					break;
				}
				else {
					System.out.println(">> 로그인이 필요한 기능입니다. <<");
					break;
				}
				
				
			case "7": // 종료
				System.out.println(">> 프로그램을 종료합니다 <<");
				break Outer;

			default:
				System.out.println(">> 메뉴 번호는 1~5사이의 정수만 입력 가능 합니다. 다시 입력하세요. <<");
				continue;
			}
		} 
		
		
	}

}
