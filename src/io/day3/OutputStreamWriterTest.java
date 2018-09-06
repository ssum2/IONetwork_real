package io.day3;
import java.io.*;
public class OutputStreamWriterTest {
/*
 	> OutputStreamWriterTest 클래스의 테스트 내용
 	- 소스: 입력노드스트림; 파일(FileReader) "c:/iotestdata/오늘의날씨.txt"; 2byte
 		> 브릿지 스트림; OutputStreamWriter; 2byte -> 1byte변환
 	- 목적지: 출력노드스트림; 모니터(System.out); 1byte
 */
	public static void main(String[] args) {
		try {
			File fileName = new File("c:/iotestdata/오늘의날씨.txt");
			FileReader fReader = new FileReader(fileName);
			
			OutputStreamWriter osWriter = new OutputStreamWriter(System.out);
//			> 2byte기반의 fReader로 읽어온 내용을 1byte로 변경하여 System.out으로 출력
			
			int input = 0;
			while((input = fReader.read())!= -1) {
//							>> 2byte씩 읽어옴(char)
				osWriter.write(input);
				osWriter.flush();
			}
			System.out.println("\n>>> 종료 합니다 <<<");
			
			osWriter.close();
			fReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	} // end of main()
} // end of class
