package io.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileInputStream2 {
// byte[] 저장소를 만들어서 배열사이즈만큼 읽어오기!
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("읽을 파일의 이름(절대경로)을 입력: ");
		
		String filename = sc.nextLine();
//		>> 파일명 입력 => 데이터소스
//		c:\iotestdata\korea2.txt
		byte[] dataArr = new byte[64];	// >> 되도않게 큰 사이즈로 써버리면 메모리만 많이 차지해서 별로임 적절한 사이즈로 쓰자
		int inputLength = 0;
		int totalByte = 0;
		
		try {
			FileInputStream fist = new FileInputStream(filename);  // >> fist는 접점(노드), FileNotFoundException 처리
			while( (inputLength = fist.read(dataArr))!= -1 ) {
				/*
				 	#fist.read(dataArr)
				 	- 데이터를 dataArr[]의 크기만큼(64byte) 읽어서 int타입으로 리턴
						-> 리턴 값 inputLength에 넣어줌
						-> 읽어온 내용물은 dataArr[]에 저장
				 	- 파일 내용물에서 더이상 읽을 데이터가 없으면 -1을 리턴
				 	- while문의 조건은 데이터가 없을 때까지(-1을 받을 때까지)반복 실행
				 */
				System.out.write(dataArr, 0, inputLength);
				System.out.flush();
				
				totalByte+=inputLength;
				
			}
			fist.close();
		} catch (FileNotFoundException e) {
			System.out.println(filename+"파일은 없습니다.");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("\n===================");
		System.out.println("총 "+totalByte+"bytes");
		System.out.println("===================");

		sc.close();
		
	}

}
