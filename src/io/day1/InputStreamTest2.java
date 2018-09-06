package io.day1;

import java.io.IOException;

public class InputStreamTest2 {

	
	public static void main(String[] args) throws IOException {
/*
 		#콘솔에서 Ctrl+C로 -1를 반환하고 종료하기 Test
		int input1 = 0;
		
		input1 = System.in.read();
		System.out.println("input1 : "+input1);

		int input2 = 0;
		input2 = System.in.read();
		System.out.println("input2 : "+input2);
*/	
		int input =0;
		
//		#키보드로 Ctrl+C를 누르지 않으면 계속 반복되도록 하기
//		- Ctrl+C를 하면 -1를 반환하기 때문에 -1을 이용하기
		while((input=System.in.read()) != -1) {
			System.out.println("\r\n=== 키보드로부터 데이터 입력 받기 ===");
			System.out.println("화면에 출력[println(input)] : "+input);
			
			System.out.print("화면에 출력[write(input)] : ");
			System.out.write(input);	// System.out.write(): 글자 그대로 찍어줌, 출력버퍼
			System.out.flush();			// write()에 담겨 있는 것을 flush()로 꺼내줌
			/*
			flush 란 ? 출력버퍼에 임시로 보관되어 스트림으로 출력될 때까지 
			대기중인 데이터를 스트림으로 내보내는 것을 flush 라고 한다.
			write() 메소드는 flush() 메소드와 함께 사용되어야만
			출력버퍼에 들어가 있던 내용이 모니터(또는 파일)에 출력된다.
			그런데  flush() 메소드를 사용하지 않아도 
			모니터(또는 파일)에 출력되는 경우이라면
			auto flush 가 적용되어졌기 때문이다. 
			 */
			
			
			System.out.println("");
			System.out.println("화면에 출력[println((char)input)] : "+(char)input);
		}
		/*
			=== 키보드로부터 데이터 입력 받기 ===
			화면에 출력[println(input)] : 97
			화면에 출력[write(input)] : a
			=== 키보드로부터 데이터 입력 받기 ===
			화면에 출력[println(input)] : 13
			화면에 출력[write(input)] :
			=== 키보드로부터 데이터 입력 받기 ===
			화면에 출력[println(input)] : 10
			화면에 출력[write(input)] :
		 */
		
//		#입력노드(키보드)연결 닫기(아무 의미 없음..이미 프로그램 끝났으니까)
		System.in.close();
		
//		#출력노드(모니터)연결 닫기(얘도 ㅠㅠ)
		System.out.close();
		

	}	// end of main()

}	// end of class
