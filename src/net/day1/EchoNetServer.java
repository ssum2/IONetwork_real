package net.day1;

import java.net.*;
import java.util.Date;
import java.io.*;

/*
	>> TCP 통신방식을 이용한 예제 (※반 드 시 암 기 !)
	- 서버	 : ServerSocket, Socket 클래스 필요; IP주소:port
	- 클라이언트 : Socket 클래스 필요
	
	>> EchoNetServer에서 구현할 내용
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
public class EchoNetServer {

	public static void main(String[] args) {
//		1. 서버소켓 생성
		int port = 6000;
		ServerSocket srvSock = null;
		Socket sock = null;
		
		try {
			srvSock = new ServerSocket(port);
//			>> 포트번호 6000을 사용하는 서버소켓 객체 생성
			System.out.println("--- EchoNetServer Ready... ---");

//		2. 통신을 위한 소켓 객체 생성 
			sock = srvSock.accept();
//			>>> 클라이언트 연결이 되었을 때 이 줄 아래의 내용을 실행함
			
			
//		3. 소켓객체를 이용하여 클라이언트에게 데이터를 보내는 Stream 생성(OutputStream)
//		       소켓객체를 이용하여 클라이언트에게 데이터를 받는 Stream 생성(InputStream)	
			OutputStream ostrm = sock.getOutputStream();
			InputStream istrm = sock.getInputStream();
			
			OutputStreamWriter ostrmWriter = new OutputStreamWriter(ostrm);
			InputStreamReader istrmReader = new InputStreamReader(istrm);
//			>> 1byte기반인 IOStream 객체를 2byte기반으로 호환해줌(Bridge Stream)
			
			PrintWriter printWriter = new PrintWriter(ostrmWriter,true); // Auto Flush > true
			BufferedReader bufReader = new BufferedReader(istrmReader);
//			>> buffered Stream 장착
			
//			# 클라이언트 IP를 추적하는 기능
			InetAddress clientInetAddress = sock.getInetAddress();
			String clientIP = clientInetAddress.getHostAddress();
			
//			# 받아온 내용 출력 >> 클라이언트와 연결되어있는 쪽으로 내보내고 있음
			String serverMsg = "안녕하세요? "+clientIP+" 님...";
			printWriter.println(serverMsg);
			
//		※클라이언트에서 보낸 메세지를 받아 조건에 맞게 응답
			String clientMsg = "";
			
			while((clientMsg = bufReader.readLine())!=null) {
//				>> println으로 보내왔기 때문에 읽어올 때도 readLine()
				System.out.println(">> Client("+clientIP+"): "+clientMsg);
				if(clientMsg.startsWith("안녕") || clientMsg.startsWith("하이") ) {
//					>> Stirng.startsWith(문자열); 문자열의 내용처럼 시작하는 경우 true, 아니면 false
					printWriter.println(clientIP+" 님 반갑습니다. ^^");
				}
				else if(clientMsg.startsWith("오늘날짜") || clientMsg.startsWith("오늘 날짜")) {
					Date now = new Date();
					String today = String.format("%tF %tT %tA", now, now ,now);
//												>> 2018-09-05 15:43:50 수요일
					printWriter.println(today);
				}
				else {
					printWriter.println(clientIP+" 님 얼른 나가세요~~~!!");
				}
			}
			if(bufReader != null) bufReader.close();
			if(istrmReader != null) istrmReader.close();
			if(printWriter != null) printWriter.close();
			if(ostrmWriter != null) ostrmWriter.close();
			if(ostrm != null) ostrm.close();
			if(istrm != null) istrm.close();
			if(sock != null) sock.close();
			if(srvSock != null) srvSock.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(">> EchoNetServer 종료 <<");
		
		
		
	}

}
