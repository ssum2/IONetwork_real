package net.day1.quiz_02;
// >> NCS 퀴즈2 답안(2)
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class QuizEchoClient {

	public static void main(String[] args) {

		final int port = 10000;
		Socket sock = null;
		
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.print(">> 연결할 서버의 IP 주소 입력 => "); 
			String serverIP = sc.next();
			
		// 1. 통신하기 위한 Socket 객체 생성하기
			sock = new Socket(serverIP, port);
//							>> 서버IP를 정확히 입력해야함 ; ConnectException
		// 2. == Socket sock 을 사용하여 서버에게 데이터를 보내는(출력)연결통로인 출력스트림 생성하기  
			OutputStream outstrm = sock.getOutputStream(); 
			// 1byte 기반 기본 출력 스트림(OutputStream)객체를 얻어온다.
			
			DataOutputStream doutstrm = new DataOutputStream(outstrm); 
			// 위에서 얻어온 기본 출력 스트림(OutputStream)에 필터스트림(보조스트림, 오리발)장착한다.
		
            // == Socket sock 을 사용하여 서버로 부터 데이터를 받는(입력) 연결통로인 입력스트림 생성하기 	
			InputStream instrm = sock.getInputStream();
			// 1byte 기반 기본 입력스트림(InputStream)객체를 얻어온다.
			
			DataInputStream dinstrm = new DataInputStream(instrm);
			
			// 위에서 얻어온 기본 입력스트림(InputStream)에 필터스트림(보조스트림, 오리발)을 장착한다. 
			
			
			// === 클라이언트가 서버쪽으로 키보드로 입력한 내용을 보내도록 한다. ====//
			// 키보드로 입력받는 스트림을 생성해야 한다.
			BufferedReader kbBufReader = new BufferedReader(new InputStreamReader(System.in));  
//			DataInputStream kbBufReader = new DataInputStream(System.in);
//			>> 채팅시스템에서는 DataInputStream으로 설정하면 입력이 안됨 >> 2byte로 입력하는 문자를 1byte로 받아서 1byte형태로 보내기 때문에
//				2byte로 출력해야하는 문자열을 사용할 때는 굳이.....
						
			while(true) {
				// == 클라이언트에서 서버로 문자열 보내기 
				String clientSendMsg = kbBufReader.readLine();
				// 키보드로 부터 입력받은 문자열 1줄을 읽어서 문자열(String) 타입으로 리턴시켜주는 메소드이다.
								
				doutstrm.writeUTF(clientSendMsg);
				// 문자열 clientSendMsg 를 UTF-8 인코딩(== UTF-8 형태의 타입으로 변경)하여 
				// 문자열 clientSendMsg를  데이터 출력 스트림(DataOutputStream)에 기록한다.
				
				doutstrm.flush();
				// 데이터 출력 스트림(DataOutputStream)에 기록된 메시지 내용을 내보내는 작업을 하는 것이다.
								
				// == 서버가 보낸 문자열 읽어들이기
				String serverMsg = dinstrm.readUTF();
				// dinstrm.readUTF(); 은 DataInputStream 을 통해 받은 byte 를 UTF-8 형식으로 읽어들여서 
				// 문자열(String) 타입으로 리턴시켜주는 메소드이다.
				
				System.out.println(serverIP+">> "+serverMsg);
				// 서버쪽에서 보내온 메시지를 읽어와서(받아서) 화면에 출력해주기
				
				if("exit".equalsIgnoreCase(clientSendMsg)) break;
				
			}// end of while---------------------
			
			if(kbBufReader != null)   kbBufReader.close();
			if(dinstrm != null)  	  dinstrm.close();
			if(instrm != null) 		  instrm.close();
			if(doutstrm != null) 	  doutstrm.close();
			if(outstrm != null)       outstrm.close();
			if(sock != null)          sock.close();
			
			sc.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(">>>> QuizEchoClient 종료~~~");		

	}// end of main()----------------------------------------------

}
