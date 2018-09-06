package net.day1.quiz_02;
//>> NCS 퀴즈2 답안(1)
import java.net.*;
import java.io.*;
import java.util.Date;

public class QuizEchoServer {

	public static void main(String[] args) {
		   
			final int port = 10000;
			ServerSocket srvSock = null;
			Socket sock = null;
			
		   // 1. 서버소켓 생성
			try {
				srvSock = new ServerSocket(port);
				// 포트번호 10000 을 사용하는 서버소켓 객체를 생성한다.
			
				System.out.println("--- QuizEchoServer Ready...."); 
						
			// 2. 통신하기 위한 Socket 객체 생성하기
				sock = srvSock.accept();
				
			// 3. == Socket sock 을 사용하여 클라이언트에게 데이터를 보내는(출력) 연결통로인 출력스트림 생성하기  
				OutputStream outstrm = sock.getOutputStream(); 
				// 1byte 기반 기본 출력 스트림(OutputStream)객체를 얻어온다.
				
				DataOutputStream doutstrm = new DataOutputStream(outstrm); 
				// 위에서 얻어온 기본 출력 스트림(OutputStream)에 필터스트림(보조스트림, 오리발)장착한다.
			
                // == Socket sock 을 사용하여 클라이언트로 부터 데이터를 받는(입력) 연결통로인 입력스트림 생성하기 	
				InputStream instrm = sock.getInputStream();
				// 1byte 기반 기본 입력스트림(InputStream)객체를 얻어온다.
				
				DataInputStream dinstrm = new DataInputStream(instrm);
				// 위에서 얻어온 기본 입력스트림(InputStream)에 필터스트림(보조스트림, 오리발)을 장착한다. 
				
			// 4. 클라이언트쪽에서 보내온 메시지를 분석해서 응답하기	
				while(true) {	
					String clientMsg = dinstrm.readUTF();
					// dinstrm.readUTF(); 은 DataInputStream 을 통해 받은 byte 를 UTF-8 형식으로 읽어들여서 
					// 문자열(String) 타입으로 리턴시켜주는 메소드이다.
				
					if("time".equals(clientMsg) ) {
						Date now = new Date();
						String today = String.format("%tF %tT", now, now); // 2018-09-05 15:20:27 
						
						doutstrm.writeUTF(today); 
						// 문자열 today를 UTF-8 인코딩(== UTF-8 형태의 타입으로 변경)하여 문자열 today를 
						// 데이터 출력 스트림(DataOutputStream)에 기록한다.
						
						doutstrm.flush();
						// 데이터 출력 스트림(DataOutputStream)에 기록된 내용을 내보내는 작업을 하는 것이다.
						// 즉, 클라이언트 컴퓨터에 "2018-09-05 15:20:27" 라는 내용을 보내는 것이다. 
						// 클라이언트로 보내기
					}
					else if("exit".equalsIgnoreCase(clientMsg) ) {
						doutstrm.writeUTF(">>> 클라이언트님과의 채팅을 종료 합니다.");
						doutstrm.flush(); // 클라이언트로 보내기
						break;
					}
					else {
						doutstrm.writeUTF("=== time 만 물어봐주세요.^^");
						doutstrm.flush(); // 클라이언트로 보내기
					}
				}// end of while---------------------------
								
				if(dinstrm != null)     dinstrm.close();
				if(instrm != null)      instrm.close();
				if(doutstrm != null)    doutstrm.close();
				if(instrm != null)      instrm.close();
				if(doutstrm != null)    doutstrm.close();
				if(outstrm != null)     outstrm.close();
				if(sock != null)        sock.close();
				if(srvSock != null)     srvSock.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			System.out.println(">>>> QuizEchoServer 종료~~~");		

	}// end of main()-----------------------------------------------------

}
