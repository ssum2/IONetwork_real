package io.day2;

import java.io.*;
/*
 	>> FileReader / FileWriter
 	- 노드스트림; 파일과 직접 연결
 	- 2byte기반	(char)문자 스트림 
 */
public class FileWriterTest {
//	file copy
//	cmd>> java 클래스파일경로웅앵 원본파일경로 복사본파일의경로
	public static void fileCopy(String srcFileName, String targetFileName) 
			throws FileNotFoundException, IOException {
		
//		#해당 소스 파일에 노드 연결(빨대 꽂기)
		FileReader fr = new FileReader(srcFileName);
		
//		#타겟 파일에 노드 연결해서 소스파일 내용 복사(빨대 꽂기)
		FileWriter fw = new FileWriter(targetFileName);
		
//		- char[] 배열 생성 (20byte, 10글자씩 읽어옴)
		char[] dataArr = new char[10];
		int dataLength = 0;
		
		do {
			dataLength = fr.read(dataArr);	
// 			char타입 배열로 10글자씩 읽어 글자수를 dataLength에 저장
//			읽어온 글자들은 dataArr 배열에 저장
			
			if(dataLength != -1) {
				fw.write(dataArr, 0, dataLength);
				fw.flush();
			}
		} while(dataLength != -1);
		
		System.out.println(">>> 파일 복사 완료 <<<");
		
//		# 빨대 제거
		fr.close();
		fw.close();
}	

	public static void main(String[] args) {
		try {
			FileWriterTest.fileCopy(args[0], args[1]);
//						>> args[0] : D:/sample.txt
//						>> args[1] : D:/sampleCopy.txt
			
		} catch (FileNotFoundException e) {
			System.out.println(args[0]+"이라는 파일은 없습니다.");
		} catch (IOException e) {
			System.out.println(args[0]+"파일이 손상되었습니다.");
		}
		
//		D:\IONetwork\bin>java io.day2.FileReaderTest2 D:/sample.txt
		
	}
}
