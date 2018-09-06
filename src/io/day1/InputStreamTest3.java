package io.day1;

import java.io.IOException;

public class InputStreamTest3 {

	public static void main(String[] args) throws IOException {
		int input = 0;
		int totalByte = 0;
		
		while((input=System.in.read()) != -1) {
			if(input != 13 && input!=10) {	// 입력값이 엔터가 아닐 때
//				if('X'==(char)input || 'x'==(char)input) {
//					System.out.println(">> while문을 빠져나갑니다 <<");
//					break;
//				}
				totalByte++;
				System.out.write(input);
				System.out.flush();		// auto flush!
			} // end of outer if
			else {	// 입력값이 엔터일 때
				System.out.println("");
			}
		} // end of while
		System.out.println("총 "+totalByte+"byte 입력함.");
		

	} // end of main()
} // end of class
