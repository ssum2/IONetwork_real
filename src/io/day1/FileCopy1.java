package io.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 	>> 파일복사하기
 	
 		1. c:\iotestdata\korea.txt을 읽어오기
 		2. 동일한 경로에 koreacopy.txt파일 생성
 		
 		1byte씩 읽어들이는 형태로 하기
 */
public class FileCopy1 {
	public static void main(String[] args) {
		String filename = "c:\\iotestdata\\korea.txt";
		String copyfilename = "c:\\iotestdata\\koreacopy.txt";
		
		int input = 0;
		boolean append = false;
		System.out.print("복사본파일에 저장할 내용을 입력하세요[c:\\iotestdata\\koreacopy.txt에서 확인]\n▷");
		
		
		try {
			FileInputStream fist = new FileInputStream(filename);  // >> fist는 접점(노드), FileNotFoundException 처리
			FileOutputStream fost = new FileOutputStream(copyfilename, append);
			while( (input=fist.read())!= -1 ) {
				fost.write(input);
				fost.flush();
			}
			while((input=System.in.read())!= -1 ) {
				append = true;
				fost.write(input);
				fost.flush();
			}
			fist.close();
			fost.close();
		} catch (FileNotFoundException e) {
			System.out.println(filename+"파일은 없습니다.");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
