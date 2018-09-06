package io.day2;

import java.io.*;
import java.util.Scanner;

/*
 	>> File 클래스
 		- File클래스의 객체는 파일 또는 폴더(디렉토리) 두 가지 모두 가능
 */
public class FileTest {

	public static void main(String[] args) {
//		C:/iotestdata/Tulips.jpg	
		Scanner sc = new Scanner(System.in);
		System.out.print("탐색기에 존재하는 파일명을 입력하세요: ");
		String filename = sc.nextLine();
		
		File file1 = new File(filename);
		
		System.out.println("파일명: "+file1.getName());
//		>> 파일명 알려주는 메소드
		
		long fileSize = file1.length();
		System.out.println("파일크기: "+fileSize);
		
		System.out.println("파일의 절대경로: "+file1.getAbsolutePath());
		
		System.out.println("파일의 경로: "+file1.getPath());
//		>> 얘를 더 자주 씀
/*
 		파일명: Tulips.jpg
		파일크기: 620888
		파일의 절대경로: C:\iotestdata\Tulips.jpg
		파일의 경로: C:\iotestdata\Tulips.jpg
 */
		
		System.out.println("=======================================");
		System.out.println(">> 디렉토리 생성하기 <<");
		
		File dir = new File("MyDir");
//		File dir = new File("D:\IONetwork\MyDir"); 와 같음 (작업중인 경로를 따라감)
		
		String result = dir.exists()? "존재함":"존재하지 않음";	
//						file객체.exist() -> boolean타입 return
		
		System.out.println(dir.getName()+"은 "+result);
		
		boolean bool = false;
//		# 해당 디렉토리가 없을 때
		if( !dir.exists() ) {
			bool = dir.mkdir();
//			>>make directory 메소드>> mkdir(); boolean형 return(생성되면 true, 생성 안되면 false)
			
			result = bool?"디렉토리 생성 성공":"디렉토리 생성 실패 ㅜㅜ";
			System.out.println("MyDir "+result);
		}
		
//		# 해당 디렉토리가 있을 때(처음부터 있거나 or 위에서 만들어서 생겼거나)
		if(dir.exists()) {
			System.out.println("MyDir 디렉토리의 상대경로: "+dir.getPath());
			System.out.println("MyDir 디렉토리의 절대경로: "+dir.getAbsolutePath());
			/*
			 	MyDir 디렉토리의 상대경로: MyDir ==> 프로젝트 밑의 해당 디렉토리 폴더명만 나옴
				MyDir 디렉토리의 절대경로: D:\IONetwork\MyDir ==> 전체 경로 나옴
			 */
		}
		
		System.out.println("=======================================");
		System.out.println(">> 디렉토리(폴더) 삭제하기 <<");
		/*
		 	삭제하려는 디렉토리가 비어있을 때만 가능(빈 폴더 일 때)
		 	폴더 속에 서브 폴더 or 파일이 있는 경우에는 디렉토리 삭제 불가능
		 	
		 	1. D:/IONetwork 아래에 TestDir 빈 폴더 생성
		 	2. 아래 내용과 같이 삭제 시도
		 */
		
		File dir2 = new File("MyDir");
		if(dir2.exists()) {
			bool = dir2.delete();
//			>> boolean타입 리턴, true일때 삭제, false일때 삭제 안됨
			result = bool?"디렉토리 삭제 성공":"디렉토리 삭제 실패";
			System.out.println("MyDir "+result);
		}
		
		System.out.println("=======================================");
		System.out.println(">> 파일 생성하기 <<");
		File file2 = new File("테스트.txt");
		
//		# 해당 파일이 존재하지 않을 때
		bool = false;
		if( !file2.exists() ) {
			try {
				bool = file2.createNewFile();
//				파일객체명.createNewFile(); 파일 생성 메소드, return은 boolean, true일 때 생성, false일때 생성실패
				
				result = bool? "파일 생성 성공":"파일 생성 실패";
				System.out.println(file2+result);
				System.out.println("테스트.txt의 상대경로: "+file2.getPath());
				System.out.println("테스트.txt의 절대경로: "+file2.getAbsolutePath());
				
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		
		System.out.println("=======================================");
		System.out.println(">> 파일 삭제하기 <<");
		bool = file2.delete();
//		파일객체명.delete(); 파일 삭제 메소드, return은 boolean, true일 때 삭제, false일때 삭제실패
		
		result = bool? "파일삭제성공":"파일삭제실패";
		System.out.println("테스트.txt "+result);
		
		
		sc.close();
	}
}
