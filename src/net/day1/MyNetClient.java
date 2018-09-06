package net.day1;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class MyNetClient {
/*
 	>> TCP 통신방식을 이용한 예제 (※반 드 시 암 기 !)
 	- 서버	 : ServerSocket, Socket 클래스 필요; IP주소:port
 	- 클라이언트 : Socket 클래스 필요
 	cf) ServerSocket; 클라이언트의 연결여부를 확인하는 것
 		Socket; 실제 대화가 오고가는 것(data가 오고가는 것)
 		
 	cf)  0 ~ 1024 사이의 값은 시스템이 내부적으로 사용하는 포트 번호이므로 사용하지 말아야 함.
 	
 	#명령프롬프터를 2개를 띄워서 MyNetServer 실행 > MyNetClient 실행 > 
 		>접속할 곳의 IP주소, hostName 입력 > MyNetServer창에서 연결 확인
 */
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print(">> 연결할 서버의 IP 주소 입력: ");
//		nextLine()은 엔터까지 읽어오기 때문에 ip주소를 입력할 때는 쓸 수 없음
		
		String serverIP = sc.next(); // 구분기준이 공백(엔터 전까지만 들어감)
		
		Socket sock = new Socket(serverIP, 7777);
//		>> 직접 아이피를 쓰거나 PC이름을 써도 괜찮음. 보통은 Host주소를 사용
//		>> Socket Sock이 생성되었으면 serverIP 서버와 연결됨
//		>> 연결이 안 될 때, UnknownHostException, IOException 오류 발생
		System.out.println(">>> "+serverIP+"서버와 연결되었습니다 <<<\n");
		
//		#서버에서 DataOutputStream을 통해 보낸 msg를 읽어서 출력
//		1. 서버에서 전송한 데이터를 읽어들이는 1byte기반 기본 입력스트림; InputStream 객체 생성
		InputStream instrm = sock.getInputStream();
//		2. 기본스트림에 필터스트림 DataInputStream 장착
		DataInputStream dinstrm = new DataInputStream(instrm);
		
//		3. 출력하기; DataInputStream으로 받아온 byte를 readUTF()메소드를 통해 UTF-8형태로 읽어서 인코딩 > String 타입으로 반환
		String serverMsg = dinstrm.readUTF();
		System.out.println(">> 서버로부터 온 메세지: "+serverMsg);
		
		
//		#클라이언트 -> 서버 메세지 전송; 
//		1. 기본출력스트림 생성 후  DataOutputStream 장착
		OutputStream otstrm = sock.getOutputStream();
		DataOutputStream dotstrm = new DataOutputStream(otstrm);
		
//		2. 접속한 서버에 메세지 전송
		String msg = "누구세요...? 제가 보이시나요...?";
		dotstrm.writeUTF(msg);
		dotstrm.flush();
		
		
//		#스트림 닫기, 소켓 닫기
		if(dotstrm != null) dotstrm.close();
		if(otstrm != null) otstrm.close();
		if(dinstrm != null) dinstrm.close();
		if(instrm != null) instrm.close();
		if(sock != null) sock.close();
		
		
		sc.close();
	}

}
