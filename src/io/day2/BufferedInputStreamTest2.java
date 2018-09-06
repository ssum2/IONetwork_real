package io.day2;

import java.io.*;

public class BufferedInputStreamTest2 {
	public static void main(String[] args) {
		try {
//		1. 파일 > 파일 출력
//			#입력 노드스트림 => 파일(FileInputStream)
//			- 소스파일 객체 생성
			String srcFilename = "C:/iotestdata/Tulips.jpg";
			File srcFile = new File(srcFilename);
//			== File srcFile = new File("C:/iotestdata/Tulips.jpg");
			long srcFileSize = srcFile.length();
			
//			- 입력 노드스트림 생성
			FileInputStream fist = new FileInputStream(srcFile);

//			- 필터스트림(보조스트림; BufferedInputStream) 장착!
			BufferedInputStream bist = new BufferedInputStream(fist, 4096);

			
//			# 출력 노드스트림 => 파일(FileOutputStream)
//			- 목적파일 객체생성
			String targetFilename = "C:/iotestdata2/튤립3.jpg";
			File targetFile = new File(targetFilename);
			
//			- 출력 노드스트림 생성
			FileOutputStream fost = new FileOutputStream(targetFile);
			
//			- 필터스트림(보조스트림; BufferedOutputStream) 장착!
			BufferedOutputStream bost = new BufferedOutputStream(fost, 4096);
			
//			# byte배열 단위로 읽어 들이기
			byte[] dataArr = new byte[512];
			
			int inputLength = 0;
			int totalByte = 0;
			int whileCount = 0;
			
			
			while((inputLength = bist.read(dataArr)) != -1) {
				whileCount++;
//				# 모니터 출력
				bost.write(dataArr, 0, inputLength);
				bost.flush();
				
				totalByte+=inputLength;
				
//				# 속도 출력
				int percent = (int)((double)totalByte/srcFileSize*100);
				System.out.println(percent+"% 복사중...");
//				System.out.println((int)((double)totalByte/srcFileSize*100)+"% 복사 중...");
			}
			System.out.println("---------------------------------------------------");
			System.out.println("whileCount: "+whileCount+"회 반복");
			System.out.println("총 "+totalByte+"bytes 읽고, "+targetFilename+"파일에 씀");
			System.out.println("---------------------------------------------------");
			
//			# 닫는 순서: 보조스트림 > 노드스트림 순
			bost.close();
			fost.close();
	
			bist.close();
			fist.close();

		} catch (FileNotFoundException e) {
			System.out.println(">> 파일의 경로명이 올바르지 않습니다. <<");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
