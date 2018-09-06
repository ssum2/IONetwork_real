package net.day1.quiz_02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Quiz_NetClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print(">> 연결할 서버의 IP 주소 입력: ");
//		nextLine()은 엔터까지 읽어오기 때문에 ip주소를 입력할 때는 쓸 수 없음
		
		String serverIP = sc.next(); // 구분기준이 공백(엔터 전까지만 들어감)
		
		Socket sock = new Socket(serverIP, 10000);
		System.out.println(">>> "+serverIP+"서버와 연결되었습니다 <<<\n");

//		#클라이언트 -> 서버 메세지 전송; 
//		1. 기본출력스트림 생성 후  DataOutputStream 장착
		OutputStream otstrm = sock.getOutputStream();
		DataOutputStream dotstrm = new DataOutputStream(otstrm);
		
//		2. 접속한 서버에 메세지 전송
		System.out.print(">> 서버로 보낼 메시지: ");
		String msg = sc.next();
		dotstrm.writeUTF(msg);
		dotstrm.flush();
		
		InputStream instrm = sock.getInputStream();
		DataInputStream dinstrm = new DataInputStream(instrm);

		String serverMsg = dinstrm.readUTF();
		System.out.println(">> 서버로부터 온 메세지: "+serverMsg);
		
//		#스트림 닫기, 소켓 닫기
		if(dotstrm != null) dotstrm.close();
		if(otstrm != null) otstrm.close();
		if(dinstrm != null) dinstrm.close();
		if(instrm != null) instrm.close();
		if(sock != null) sock.close();
		
		
		sc.close();
	}

}
