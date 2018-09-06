package io.day1;

import java.io.*;
import java.util.Scanner;

public class FileCopy3 {
/*
	>> 파일복사하기
		1. c:\iotestdata\korea2.txt을 읽어오기
		2. 동일한 경로에 korea2copy.txt파일 생성
		
		byte[64]씩 읽어들이는 형태로 하기

*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("복사할 원본파일명(절대경로) 입력: ");
		String srcFileName = sc.nextLine();
		
		System.out.print("목적 파일명(절대경로) 입력: ");
		String targetFileName = sc.nextLine();
		
		System.out.println("소스파일: "+srcFileName);
		System.out.println("목적파일: "+targetFileName);
		
		try {
			/*
			 	FileInputStream을 생성; 접속점(node)이 파일인 것으로, 특정 파일에 빨대를 꽂아 파일이 내용물을 1byte기반으로 빨아들이는 입력노드 스트림.
			 	FileOutputStream을 생성; 접속점이 파일, 특정 파일에 빨대를 꽂아 내용물을 1byte기반으로 기록해주는 출력노트 스트림
			 */
			FileInputStream fist = new FileInputStream(srcFileName);
			FileOutputStream fost = new FileOutputStream(targetFileName);
			
//			# 배열 생성
			byte[] dataArr = new byte[64];
			int inputLength = 0;
			int totalByte = 0;
			
//			#배열사이즈만큼 읽어오기(FileInputStream)
//			 >> 마지막값을 다 읽어오면 -1을 반환하기 때문에 *!=-1 조건 세워줌
//			 >> 읽어온 것의 길이만큼 inputLength에 넣어주고, 데이터는 dataArr[]에 저장
			while( (inputLength = fist.read(dataArr)) != -1 ) {
				
//				#파일에 쓰기(FileOutputStream)
				fost.write(dataArr, 0, inputLength);
				fost.flush();
				
//				#총 바이트 수 누적하기
				totalByte += inputLength;
			}
			System.out.println("\n복사완료! 총 "+totalByte+"byte 복사됨");
			fist.close();
			fost.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(srcFileName+"파일의 경로가 올바르지 않습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.close();
	}
}
