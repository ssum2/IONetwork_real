package io.day1;

import java.io.IOException;

/*
>>>>> System.in :
         부모클래스가 추상클래스 InputStream(기본 입력 스트림) 타입인 것으로서 
         접속점(빨대)이 키보드인 입력 스트림이다.
         
-- Node(접속점)가 키보드인 입력스트림이다.
-- 1 byte 기반 스트림이다.
-- 주요메소드 :
      public int read() throws IOException
      ==> 1byte 씩 데이터를 읽어서
          1byte 씩 반환하고
                      입력받은 키보드가 Ctrl+C(윈도우), Ctrl+D(유닉스,리눅스)
                      이라면 -1 을 반환해주는 메소드이다.
          read() 메소드의 리턴타입은 byte 가 아니라 int 이다.
                      데이터 입력의 끝을 나타내는 것으로 -1 을 사용하는데 (종료)
          Ctrl+C(윈도우), Ctrl+D(유닉스,리눅스)을 사용하면 된다.
                      또한 IOException 이 발생할수도 있으므로 반드시 예외처리를 꼭 해주어야 한다.              
                      그래서 현재 우리는 윈도우를 사용하고 있으므로 InputStream 작업을 
                      강제로 종료하려면  Ctrl+C(윈도우) 하면 된다.


>>>>> System.out :
               부모클래스가 추상클래스인 OutputStream(기본 출력 스트림) 타입인 것으로서
               접속점(빨대)이 콘솔화면(모니터)인 출력 스트림(PrintStream)이다.
               
  -- Node(접속점)가 콘솔화면(모니터)인 출력스트림이다.
  -- 1byte 기반 스트림이다.
  -- 주요 메소드 : println(String str),
                print(String str),
                write(int b)             
*/
public class InputStreamTest1 {

	public static void main(String[] args) throws IOException {
//										>> main메소드를 호출한 쪽에서 IOException을 처리 >> JVM이 호출 >> JVM이 처리한다!
		int input = 0;
		int totalByte = 0;	// 읽어온 byte의 수를 세는 변수
		
		while(true) {
		input = System.in.read(); 	
// 		>> InputStream.class의 메소드, 키보드에서 입력한 글자 중 첫 글자 1개만 입력받아서 int타입으로 반환, IOException 처리 해서 써야함
	
		/*#내가한 것
		if(input==13 || input==10) 	>> 이렇게 하면 다 걸려버릴 수 있음... 나는 되던데 왜죠?
		else {
			totalByte++;
			System.out.println("input: "+(char)input);
//			>> 글자 1개만 받아와서 int로 리턴하기 때문에, 글자1개만 변환하면 되기 때문에 'char'로 캐스팅
			if('X'==(char)input || 'x'==(char)input) {
				System.out.println(">> while문을 빠져나갑니다 <<");
				totalByte--;
				break;
			}
		}
		*/

//		#강사님이 하신 것
		if(input != 13 && input!=10) {
			if('X'==(char)input || 'x'==(char)input) {
				System.out.println(">> while문을 빠져나갑니다 <<");
				break;
			}
			totalByte++;
			System.out.println("input: "+(char)input);
		}
		/*	
		  cf)(char)를 안하고 input을 출력했을 때
		 	a 입력하고 엔터!	===> 3byte
			input: 97 == a
			input: 13 == \r (공백)
			input: 10 == \n (줄바꿈)
		 */

		}	// end of while
		System.out.println("입력받은 byte 수 : "+totalByte+"byte");
		
	} //  end of main()
} // end of class
