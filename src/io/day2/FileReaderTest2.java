package io.day2;
import java.io.*;
public class FileReaderTest2 {
// FileReaderTest와 다른점: 예외처리를 throws로 해서 메인에서 try-catch함
//						 char[]배열사이즈 만큼 읽어서 저장 -> 출력
	
	public static String reading(String fileName) 
			throws FileNotFoundException, IOException {
		
//		#해당 파일과 노드 연결 생성(빨대 꽂기)
		FileReader fr = new FileReader(fileName);
		
//		- char[] 배열 생성 (20byte, 10글자씩 읽어옴)
		char[] dataArr = new char[10];
		int dataLength = 0;
		String returnData = "";
		
		do {
			dataLength = fr.read(dataArr);	
// 			char타입 배열로 10글자씩 읽어 글자수를 dataLength에 저장
//			읽어온 글자들은 dataArr 배열에 저장
			
			if(dataLength != -1) {
				String str = new String(dataArr, 0, dataLength);
//				>> char타입 배열 dataArr의 0번부터 읽어들여온 글자갯수인 dataLength까지 글자를 읽어서 str에 저장
//				>> 
				returnData += str;
//				>> 그 값을 returnData에 누적
			}
		} while(dataLength != -1);
		
//		# 빨대 제거
		fr.close();
		
		return returnData;	
}	

	public static void main(String[] args) {
		try {
			String str = FileReaderTest2.reading(args[0]);
			System.out.println(str);
			
		} catch (FileNotFoundException e) {
			System.out.println(args[0]+"이라는 파일은 없습니다.");
		} catch (IOException e) {
			System.out.println(args[0]+"파일이 손상되었습니다.");
		}
		
//		D:\IONetwork\bin>java io.day2.FileReaderTest2 D:/sample.txt
		
	}
}
