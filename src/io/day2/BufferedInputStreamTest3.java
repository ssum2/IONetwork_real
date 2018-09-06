package io.day2;

import java.io.*;
import java.util.Scanner;

public class BufferedInputStreamTest3 {
//	C:/iotestdata/Tulips.jpg
	public static void main(String[] args) {
		try {
			
//		1. 파일 > 파일 출력
//			#입력 노드스트림 => 파일(FileInputStream)
//			- 소스파일 객체 생성
			Scanner sc = new Scanner(System.in);
			System.out.print(">> 복사할 원본파일의 경로를 입력하세요: ");
			String srcFilename = sc.nextLine();
			File srcFile = new File(srcFilename);
			long srcFileSize = srcFile.length();
			
//			- 입력 노드스트림 생성
			FileInputStream fist = new FileInputStream(srcFile);
//			- 필터스트림(보조스트림; BufferedInputStream) 장착!
			BufferedInputStream bist = new BufferedInputStream(fist, 10240);

			
//			# 출력 노드스트림 => 파일(FileOutputStream)
//			- 목적파일 객체생성
			System.out.print(">> 복사본파일의 경로를 입력하세요: ");
			String targetFilename = sc.nextLine();
			File targetFile = new File(targetFilename);
			
//			- 출력 노드스트림 생성
			FileOutputStream fost = new FileOutputStream(targetFile);
			
//			- 필터스트림(보조스트림; BufferedOutputStream) 장착!
			BufferedOutputStream bost = new BufferedOutputStream(fost, 10240);
			
//			# byte배열 단위로 읽어 들이기
			byte[] dataArr = new byte[1024];
			
			int inputLength = 0;
			int totalByte = 0;
			int sharpCount = 0;
			
			while((inputLength = bist.read(dataArr)) != -1) {
//				# 모니터 출력
				bost.write(dataArr, 0, inputLength);
				bost.flush();
				
				totalByte+=inputLength;

//				# 속도 출력 (내가 한 것)
//				String sharp = "";
//				if(inputLength==1024)
//					sharp+="#";
//				if(sharp.length()%50==0)
//					sharp+="\n#";	
//				
//				System.out.print(sharp);

//				# 강사님이 하신 것
				if(inputLength == 1024) {
					System.out.print("#");
					sharpCount++;
				}
				if(sharpCount%50 == 0)
					System.out.print("\n");
				
			}
			
			System.out.println("\n---------------------------------------------------");
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
