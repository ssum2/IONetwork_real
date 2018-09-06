package net.day1;
import java.net.*;
import java.util.Scanner;
import java.io.*;
public class EchoNetClient {
	/*
	>> TCP 통신방식을 이용한 예제 (※반 드 시 암 기 !)
	- 서버	 : ServerSocket, Socket 클래스 필요; IP주소:port
	- 클라이언트 : Socket 클래스 필요
	
	>> EchoServer에서 구현할 내용
		#Client가 Server에 접속 했을 때
		1. 서버 -> 클라이언트 : "안녕하세요? IP주소 님..." 이라는 인삿말을 보냄
		2. 클라이언트 <-> 서버 : 클라이언트는 키보드 입력(System.in)을 통해 서버로 메시지 전송
			a) 클 -> 서; "안녕하세요?" 또는 "하이"
				서 -> 클; "IP주소 님 반갑습니다.^^"
			b) 클 -> 서; "오늘 날짜는?"
				서 -> 클; 오늘의 날짜 전송
			c) 클 -> 서; 기타 메시지 전송
				서 -> 클; "IP주소 님 얼른 나가세요~~!!"
*/
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print(">> 연결할 서버의 IP 주소 입력: ");
			String serverIP = sc.next();
			
//			# 통신하기 위한 객체 생성
			final int port = 6000;
			Socket sock = new Socket(serverIP, port);
			
//		1. 서버로부터 받아오기
//			# 스트림 생성
			OutputStream ostrm = sock.getOutputStream();
			InputStream istrm = sock.getInputStream();
			
			OutputStreamWriter ostrmWriter = new OutputStreamWriter(ostrm);
			InputStreamReader istrmReader = new InputStreamReader(istrm);
			
			PrintWriter printWriter = new PrintWriter(ostrmWriter,true); // Auto Flush > true
			BufferedReader bufReader = new BufferedReader(istrmReader);
//			>> buffered Stream 장착

//			# 받아온 것 출력하기
			String serverMsg = bufReader.readLine();
//			>> 서버에서 보낸 메시지를 한 줄씩 읽어옴(서버에서 println으로 보냈기 때문에 한 줄씩 보내고, 읽음)
			System.out.println(">> Server("+serverIP+"): "+serverMsg);
			
//		2. 서버로 메시지 보내기
//			# 키보드로 입력받는 스트림 생성
			InputStreamReader kbIstrmReader = new InputStreamReader(System.in);
//			>> 1byte System.in >> kbIstrmReader 2byte
			BufferedReader kbBufReader = new BufferedReader(kbIstrmReader);
//			>> bufferedStream 장착
			
			
			String clientSendMsg = ""; // ※null이 아니어야 함
			while( (clientSendMsg = kbBufReader.readLine()) != null ) {
//					>> 키보드 ctrl+c -> null 반환 -> while 탈출
				
				printWriter.println(clientSendMsg);
//				>> 서버로 클라이언트 메시지 송출
				
				serverMsg = bufReader.readLine();
//				>> 서버에서 메세지 받아오기
				System.out.println(">> Server("+serverIP+"): "+serverMsg);

			}
			
			if( kbBufReader != null) kbBufReader.close();
			if( kbIstrmReader != null) kbIstrmReader.close();
			if( bufReader != null) bufReader.close();
			if( istrmReader != null) istrmReader.close();
			if( printWriter != null) printWriter.close();
			if( ostrmWriter != null) ostrmWriter.close();
			if( ostrm != null) ostrm.close();
			if( istrm != null) istrm.close();
			if( sock != null) sock.close();
			
			
			sc.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(">> EchoNetClient 종료 <<");
	
	}

}
