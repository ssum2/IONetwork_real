package net.day2;
import java.net.*;
import java.util.Scanner;
import java.io.*;

/* ====== *** 구현내용 *** =======
   -- 채팅 서버와 클라이언트가 일대일 채팅하는 프로그램 - 키보드 입력, 콘솔에 출력
*/

public class ConsoleChatClient implements Runnable {

	Socket socket;
	private final int port=5555;
	
	boolean stop = false;
	
	// 생성자 
	public ConsoleChatClient(String ip) {
		service(ip);
	}// end of 생성자 ------------------------------
	
	
	public void service(String ip) {
		
		try{
			// === Socket 생성 (ip, port) ===
			socket = new Socket(ip, port);
			System.out.println("### 서버["+ip+"]와 연결됨. ###");
		
			// === 키보드 입력 스트림 필요(사용자가 키보드로 부터 입력한 메시지를 읽어들여 서버로 보내기 위해) - System.in 
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
			// 1btye 기반 System.in(키보드입력) 을 InputStreamReader 브릿지 스트림을 사용하여 2byte 기반으로 바꾸어준다.
			// 그런 다음에 속도 향상을 위해서 필터(보조)스트림인 BufferedReader 를 장착한다.		
			
			
			// === 소켓을 통해 서버에게 보낼 스트림 연결 ===
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			//출력스트림(OutputStream)을 PrintWriter 클래스로 감싸았음.
			//PrintWriter를 사용하는 이유는 주로 printXXX (printf, println, 등) Method 들을 사용하기 위해서 이다.
			//println() 메소드가 호출되면 자동으로 flush() 메소드를 호출한다.
			
			// === 서버에서 보내오는 메시지를 계속 듣는 스레드 생성하여 동작시키기 ===
			Thread thr = new Thread(this);
			thr.start();
//			cf) main스레드는 클라이언트가 입력한 값을 서버로 보냄
			
			// === 클라이언트에서 서버쪽으로 메시지를 보내는 작업
			do {
				if(socket.isClosed())  // 소켓이 닫힌 상태이라면
					System.exit(0);
//				cf) 만약 여기서 break를 사용했을 때, 반복문만 빠져나갈 뿐 프로그램이 종료되지는 않음
//					return의 경우에도 생성자에서는 종료 역할X(메인메소드 내에서만 가능)
				
				else { // 소켓이 열려진 상태이라면
					String myMsg = keyboardReader.readLine(); 
					// 키보드로 내(클라이언트) 메시지 입력받기
					
					if(myMsg.equalsIgnoreCase("exit")) {
						stop = true;
						break; // exit 또는 EXIT 누르면 while 문 종료
					}
					
					printWriter.println(myMsg); // 소켓을 통해 서버에게 내(클라이언트) 메시지를 전송하기
				}
				
			} while (true);
				System.out.println(">>> 채팅클라이언트가 종료합니다. <<<");	
				System.exit(0);
			
		   } catch (IOException e) {
			   System.out.println("예외: "+e.getMessage());
		   }
	
	}//end of service()----------------------------------------------
	
	@Override
	public void run() {
		// 서버에서 보내오는 메시지를 계속 받아서 자기 콘솔에 출력 -- listener(리스너) 역할
		
		InputStream instream = null;
		// 서버에서 보내온 데이터를 읽어들이기 위한 노드 스트림.
		
		BufferedReader bufferReader = null; 
		// 서버에서 보내온 데이터를 읽어들이기 위한 스트림으로 부터 읽어들이기위한 필터(보조) 스트림.
		try {
				instream = socket.getInputStream();	
				// >> 상대방(socket)이 끊기면 입출력 스트림을 꽂을 수 없으니까 IOException
				
				bufferReader = new BufferedReader(new InputStreamReader(instream)); 
				// 1btye 기반 instream 을 InputStreamReader 브릿지 스트림을 사용하여 2byte 기반으로 바꾸어준다.
				// 그런 다음에 속도 향상을 위해서 필터(보조)스트림인 BufferedReader 를 장착한다.
				
				String serverMsg = "";
				
				while(!stop) { // >> stop은 service()내에서 클라이언트가 exit을 입력했을 때 true값을 반환해서 가져온 것
					serverMsg = bufferReader.readLine();
					// bufferReader 를 통해 서버가 보내준 메시지를 한줄씩 읽어온다.
					
					System.out.println("From Server>> "+serverMsg);
					// 콘솔창에 출력해준다.
				}// end of while--------------------------------------------
			
			} catch (IOException e) {
			// - 서버 > socket.close(); System.exit(0); 발생(프로그램 종료)
			// - 클라이언트 > socket.getInputStream(); || bufferReader.readLine(); >> IOException 발생
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
		
	}//end of run() @Override---------------------------------------------------------

	public static void main(String[] args) {
		System.out.print("채팅서버 IP주소 입력 => ");
		Scanner sc = new Scanner(System.in);
		String ip = sc.next();
		
		new ConsoleChatClient(ip);
//		>> 기본생성자 객체 생성
		
		sc.close();
	}//end of main()------------------------------------

}//end of class ConsoleChatClient //////////////////////////////////////////////////////
