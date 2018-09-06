package io.day3;
import java.io.*;
public class InputStreamReaderTest {
/*
 	1byte기반의 노드스트림 <--- 		가교역할 		 ---> 2byte기반 노드스트림
 	<InputStream, 		   <Bridge Stream>		  <Reader, Writer>
 	OutputStream>
 	--------------------------------------------------------------
 	System.in			"InputStreamReader"		  FileReader												
 	System.out			"OutputStreamWriter" 	  FileWriter
 	
 	
 	> InputStreamReaderTest 클래스의 테스트 내용
 	- 소스: 입력노드스트림; 키보드(System.in); 1byte
 		> 브릿지 스트림; InputStreamReader; 1byte -> 2byte변환
 	- 목적지: 출력노드스트림; 파일(FileWriter); 2byte
 	
 */
	public static void main(String[] args) {
		try {
//			#InputStreamReader로 System.in을 2byte기반의 스트림으로 변경 
			InputStreamReader istReader = new InputStreamReader(System.in);
//			> 1byte기반인 입력 노드스트림 System.in을 2byte기반의 스트림으로 변경
			
//			#출력노드스트림 FileWriter 생성
			String fileName = "c:/iotestdata/오늘의날씨.txt";
			FileWriter fWriter = new FileWriter(fileName);
			
//			#문자 읽어오기
			int input = 0;
			while( (input=istReader.read())!=-1 ) {
				fWriter.write(input);
//				> 글자 그대로 출력, int형태의 input을 문자 그대로 출력됨
				fWriter.flush();
			}
			System.out.println("\n>>> 종료 합니다 <<<");
			
//			#노드스트림 닫기
			fWriter.close();
			istReader.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	} // end of main()
} // end of class
