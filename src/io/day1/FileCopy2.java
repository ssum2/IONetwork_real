package io.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	>> 파일복사하기
	
		1. c:\iotestdata\korea2.txt을 읽어오기
		2. 동일한 경로에 korea2copy.txt파일 생성
		
		byte[64]씩 읽어들이는 형태로 하기
*/
public class FileCopy2 {

	public static void main(String[] args) {
		byte[] fileArr = new byte[64];
		
		String filename = "c:\\iotestdata\\korea2.txt";
		String copyfilename = "c:\\iotestdata\\korea2copy.txt";
		
		int inputlength = 0;
		boolean append = true;
		System.out.print("복사본파일에 저장할 내용을 입력하세요[c:\\iotestdata\\korea2copy.txt에서 확인]\n▷");
		
		try {
			FileInputStream fist = new FileInputStream(filename);  // >> fist는 접점(노드), FileNotFoundException 처리
			FileOutputStream fost = new FileOutputStream(copyfilename, append);
			while( (inputlength = System.in.read(fileArr)) != -1 ) {
		
				fost.write(fileArr, 0, inputlength);
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
