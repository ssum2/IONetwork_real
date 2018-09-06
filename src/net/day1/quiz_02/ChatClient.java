package net.day1.quiz_02;
import java.net.*;
import java.util.Scanner;
import java.io.*;


public class ChatClient implements Runnable {

	Socket socket;
	private final int port=20000;
	
	boolean stop = false;

	public ChatClient(String ip) {
		service(ip);
	}
	
	
	public void service(String ip) {
		
		try{
			socket = new Socket(ip, port);
			System.out.println("--- Connected Server["+ip+"]... ---");

			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

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
				
				System.out.println("--- Closed Chat Client --- <<<");	
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
				
				String serverMsg = "";
				
				while(!stop) { 
					serverMsg = bufferReader.readLine();

					System.out.println("From Server: "+serverMsg);

				}
			
			} catch (IOException e) {
				System.out.println(">> 서버와의 대화연결이 끊어졌습니다.");
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
		String ip = "192.168.0.100";
		System.out.println(">> 192.168.0.100 Server에 접속합니다 <<");
		
		new ChatClient(ip);
	}

}
