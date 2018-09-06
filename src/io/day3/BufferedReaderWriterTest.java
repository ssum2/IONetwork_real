package io.day3;
import java.io.*;
public class BufferedReaderWriterTest {
/*
 	> 2byte기반 FilterStream; BufferedReader / BufferedWriter
 	>> 노드스트림의 속도를 올리고 싶을 때 사용
 	
 	> BufferedReaderWriterTest의 내용
 	 #파일 복사하기
 	 	- 소스: 입력노드스트림; 원본 파일(FileReader); "D:/iotestdata/MyProfile.txt"
 	 		> 필터스트림; BufferedReader
 	 	- 목적지: 출력노드스트림; 복사본 파일(FileWriter); "D:/iotestdata/MyProfileCopy.txt"
 	 		> 필터스트림; BufferedWriter
 		- 실행하기: cmd >> d:\IONetwork\bin>java io.day3.BufferedReaderWriterTest
 */
	public static void main(String[] args) {
		try {
//			# 지정 파일들(스캐너로 받아서 써도 되지만 귀찮으니까....)
			String srcFileName = "C:/iotestdata/MyProfile.txt";
			String targetFileName = "C:/iotestdata/MyProfileCopy.txt";
		
//			#노드 스트림 생성(빨대 꽂기); 2byte기반 노드스트림 File~
			FileReader fReader = new FileReader(srcFileName);
			FileWriter fWriter = new FileWriter(targetFileName);
			
//			#필터스트림 장착; 용량의 default값은 512byte
			BufferedReader bufReader = new BufferedReader(fReader, 1024);
			BufferedWriter bufWriter = new BufferedWriter(fWriter, 1024);			
			
//			#파일 복사 및 출력하기
//			bufReader.readLine() >> 문자열만 있을 때는 한 줄씩 읽어서 String으로 리턴해주는 readLine()메소드를 이용
//								 >> enter이전까지만 읽어옴(1줄)
//								 >> 문자열이 없을 때, 끝부분에 도달했을 때는 null을 반환
				
			String strLine = "";
			while( (strLine = bufReader.readLine()) != null) {
				bufWriter.write(strLine);
				
//				※write는 줄바꿈이 없음 -> 줄바꿈 필요
				bufWriter.newLine();	// == bufWrite.write("\r\n");
				
				bufWriter.flush();
			}
			
			System.out.println("\n>>> 파일 복사 완료 <<<");
			
//			#필터, 노드스트림 제거
			bufWriter.close();
			bufReader.close();
			
			fWriter.close();
			fReader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
	} // end of main()
} 	// end of class
