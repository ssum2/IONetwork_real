package net.day1.quiz_02;
import java.net.*;
import java.io.*;

public class ConsoleChatServer implements Runnable {
// 192.168.0.100
	ServerSocket serversocket;
	Socket socket;
	private final int port=20000;
		
	boolean stop = false;

	public ConsoleChatServer() {
		service();
	}
	
	
	public void service() {
		
		BufferedReader keyboardReader = null;
		PrintWriter	printWriter = null;		
		System.out.println("ConsoleChatServer 준비중...");
		
		try {
	
			serversocket = new ServerSocket(port);
			socket = serversocket.accept();

				InetAddress inetip = socket.getInetAddress();
				String ip = inetip.getHostAddress();
				System.out.println("### ["+ip+"]님이  접속하였습니다. ###");

			keyboardReader = new BufferedReader(new InputStreamReader(System.in));

			printWriter = new PrintWriter(socket.getOutputStream(), true);

				Thread thr = new Thread(this);
				thr.start();
				
				do {
					if(socket.isClosed())
						
						System.exit(0);
					else { 
						String myMsg = keyboardReader.readLine(); 

						
						if(myMsg.equalsIgnoreCase("exit")) {  
							stop = true;
							break;
						}
					
						printWriter.println(myMsg); 
					}
					
				} while (true);
				
			System.out.println(">>> 채팅서버가 종료합니다. <<<");
			System.exit(0);
			
			} catch (IOException e) {
				System.out.println("예외: "+e.getMessage());
			} 
		
	}
	
	@Override
	public void run() {
		InputStream instream = null;
		BufferedReader bufferReader = null; 
		try {
				instream = socket.getInputStream();

				bufferReader = new BufferedReader(new InputStreamReader(instream)); 

				String clientMsg = "";
			
				while(!stop) { 
					clientMsg = bufferReader.readLine(); 
					System.out.println("From Client>> "+clientMsg);
				}
				
			} catch (IOException e) {
				System.out.println(">> 클라이언트와의 대화연결이 끊어졌습니다.");
			} finally {
				try {
					if(socket != null)
						socket.close();
					
					if(serversocket != null)
						serversocket.close();
					
					System.exit(0);
					
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	public static void main(String[] args) {
		new ConsoleChatServer();
	}
}
