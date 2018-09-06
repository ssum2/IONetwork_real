package net.day1.quiz_02;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ConsoleChatClient implements Runnable {

	Socket socket;
	private final int port=20000;
	
	boolean stop = false;

	public ConsoleChatClient(String ip) {
		service(ip);
	}
	
	
	public void service(String ip) {
		
		try{
			Scanner sc = new Scanner(System.in);
//			DataInputStream dinstrm = new DataInputStream(System.in);
			socket = new Socket(ip, port);
			System.out.println("--- Server["+ip+"]와 연결됨 ---");

			DataOutputStream doutstrm = new DataOutputStream(socket.getOutputStream());

				Thread thr = new Thread(this);
				thr.start();

				do {
					if(socket.isClosed())
						System.exit(0);
					else {
						String myMsg = sc.nextLine();
						
						
						if(myMsg.equalsIgnoreCase("exit")) {
							stop = true;
							break;
						}
						
						doutstrm.writeUTF(myMsg);
						doutstrm.flush();
					}
					
				} while (true);
				
				System.out.println(">>> 채팅 클라이언트 종료 <<<");	
				System.exit(0);
			
		   } catch (IOException e) {
			   System.out.println("예외: "+e.getMessage());
		   } 
	}
	
	@Override
	public void run() {
		InputStream instream = null;
		DataInputStream dinstrm = null;

		try {
				instream = socket.getInputStream();
				dinstrm = new DataInputStream(instream); 

				String serverMsg = "";
				
				while(!stop) { 
					serverMsg = dinstrm.readUTF();
					System.out.println("From Server>> "+serverMsg);
				}
			
			} catch (IOException e) {
				System.out.println(">> 서버와의 대화연결이 끊어졌습니다. <<");
			} finally {
				try {
					if(socket != null)
						socket.close();
					
					System.exit(0);
					
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print(">> 아이피 입력 고(192.168.0.100 입력하기): ");
		String ip = sc.next();
		System.out.println(">> 192.168.0.100 Server에 접속합니다 <<");
		new ConsoleChatClient(ip);
	}

}
