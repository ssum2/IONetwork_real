package io.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileInputStream1 {
/*
    === c:\iotestdata\korea.txt 파일을 읽어서
                그 내용을 모니터(콘솔화면)에 출력하는 예제 ===
                
    1. 데이터소스 : 파일로 부터 읽어들임(노드스트림: FileInputStream)  > 노드(node): 접점, 읽어들이는 지점
    2. 데이터목적지 : 결과물을 모니터에 출력(노드스트림: System.out)
    
    >>>> FileInputStream
       - Node 스트림(접속점이 파일인 입력스트림)
       - 1byte 기반 스트림.
      
    >>>>> System.out :
                  부모클래스가 추상클래스 OutputStream(기본 출력 스트림) 타입인 것으로서
                  접속점(빨대)이 콘솔화면(모니터)인 출력 스트림(PrintStream)이다.
                  
     -- Node(접속점)가 콘솔화면(모니터)인 출력스트림이다.
     -- 1byte 기반 스트림이다.
     -- 주요 메소드 : println(String str),
                  print(String str),
                  write(int b)                  
*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("읽을 파일의 이름(절대경로)을 입력: ");
		
		String filename = sc.nextLine();
//		>> 파일명 입력 => 데이터소스
//		c:\iotestdata\korea.txt
		
		int input = 0;
		int totalByte = 0;
		
		try {
			FileInputStream fist = new FileInputStream(filename);  // >> fist는 접점(노드), FileNotFoundException 처리
			while( (input=fist.read())!= -1 ) {
				/*
				 	#fist.read()
				 	데이터를 1바이트씩 읽어서 int타입으로 리턴
				 	파일 내용물에서 더이상 읽을 데이터가 없으면 -1을 리턴
				 	while문의 조건은 데이터가 없을 때까지(-1을 받을 때까지)반복 실행
				 */
				System.out.write(input);
				System.out.flush();
				
				totalByte++;
				
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
