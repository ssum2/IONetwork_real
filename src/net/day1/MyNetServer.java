package net.day1;

import java.net.*;
import java.io.*;
// 두개의 패키지를 반드시 import해와야 net을 구현 가능
/*
 	>> TCP 통신방식을 이용한 예제 (※반 드 시 암 기 !)
 	- 서버	 : ServerSocket, Socket 클래스 필요; IP주소:port
 	- 클라이언트 : Socket 클래스 필요
 	cf) ServerSocket; 클라이언트의 연결여부를 확인하는 것
 		Socket; 실제 대화가 오고가는 것(data가 오고가는 것)
 		
 	cf)  0 ~ 1024 사이의 값은 시스템이 내부적으로 사용하는 포트 번호이므로 사용하지 말아야 함.
 */

public class MyNetServer {
//	내 아이피: 192.168.50.48
//	혜원이 아이피: 192.168.50.54
	
	public static void main(String[] args) throws IOException {	// 오류가 발생되면 JVM에서 처리(main메소드이기 때문)
//		#ServerSocket 객체 생성; port번호 필요 => 7777 (0~1024 범위 밖의 임의의 번호를 지정함)
		ServerSocket serverSock = new ServerSocket(7777);
		System.out.println("--- 클라이언트 연결을 기다림... ---");

		while(true) {
			Socket sock = serverSock.accept();
		/*	>> ServerSocket.accept(socket, serversocket); 
		 	- return타입이 Socket인 메소드 
		 	- 메소드의 구조
				클라이언트가 접속할 때까지 블럭(대기상태)으로 머물고 있음
				-> 클라이언트 쪽에서 서버의 해당 포트 번호(현 7777)로 들어오는 시도 발생
				-> 서버에서 인지하는 순간 클라이언트와 서버 간 통신가능한 소켓 객체 생성됨
		*/
			System.out.println(">>> 클라이언트 접속 시도 <<<");

//			#접속한 클라이언트의 IP주소를 알아오는 메소드
//			1. InetAddress 얻어오기
			InetAddress clientInetAddress = sock.getInetAddress();
			
//			2. client의 IP Address 얻어오기
			String clientIP = clientInetAddress.getHostAddress();
			System.out.println("클라이언트의 IP 주소: "+clientIP);
	
	/*
	 	#서버~클라이언트 사이의 데이터 전송은 Stream객체를 통해서 이루어짐
	 	1. 소켓 객체로 입출력스트림(IOStream)객체를 얻어오기
	 	2. 접속한 클라이언트에게 서버가 인사말을 건네는 기능 구현; "어서오세요! 클라이언트IP주소 님 ^^ "
	 		- 입력받은 내용을 >>> OutputStream으로 출력
	 */
//			1. 소켓 객체로 입출력스트림(IOStream)객체를 얻어오기
			OutputStream otstrm = sock.getOutputStream();
//			- 필터스트림 DataOutputStream 장착
			DataOutputStream dotstrm = new DataOutputStream(otstrm);
			
//			2. 접속한 클라이언트에게 서버가 인사말을 건네는 기능 구현
			String msg = "어서오세요! "+clientIP+"님 ^^";
			dotstrm.writeUTF(msg);
//			>> 문자열 msg를 UTF-8 인코딩하여 사용 → 내용물을 DataOutputStream에 기록함
			dotstrm.flush();
//			>> DataOutputStream에 기록된 내용을 내보냄 → 클라이언트에 msg를 보냄

			
//		#클라이언트 -> 서버로 보낸 메세지를 읽어서 출력
			InputStream instrm = sock.getInputStream();
			DataInputStream dinstrm = new DataInputStream(instrm);
			String clientMsg = dinstrm.readUTF();
			System.out.println(">> "+clientIP+" 메세지: "+clientMsg);

//		#스트림 닫기, 소켓 닫기
			if(dotstrm != null) dotstrm.close();
			if(otstrm != null) otstrm.close();
			if(dinstrm != null) dinstrm.close();
			if(instrm != null) instrm.close();
			if(sock != null) sock.close();
//		※ 서버소켓은 닫으면 안됨! 
//			>> 서버소켓이 계속 존재해야 새로운 클라이언트의 연결을 받아줄 수 있기 때문(socket생성할 때 nullPointerException이 떨어짐)
			
			
		}
	} // end of main()
} // end of class
