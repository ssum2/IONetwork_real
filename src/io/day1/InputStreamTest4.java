package io.day1;

import java.io.IOException;

public class InputStreamTest4 {

	public static void main(String[] args) throws IOException {
		byte[] dataArr = new byte[10];	// 10바이트씩 끊어서 읽어오겠다!
//		그냥 바이트로 다 읽어와도 돌아가지만 너무 오래걸릴 수 있음
//		>> 이를 개선하고자 바이트타입의 배열로 설정해 읽어온 값을 배열에 넣어주고, 이를 뽑아오는 형태로 바꿈
		
		
		int inputLength = 0;
		int totalByte = 0;	// 10+10+4 = 24
		
		while( (inputLength=System.in.read(dataArr)) != -1 ) {	// 배열사이즈가 -1이 아닐 때 반복
			/*
		 	>> System.in.read(dataArr);
		 		-입력한 데이터가 "대한민국서울시동대문구"+enter 일 때,
		 		  입력값에서 byte형 배열 dataArr의 사이즈(10)만큼 읽어들임
		 		 => "대한민국서"까지 (한글은 2byte)
		 		-읽어온 입력값을 그대로 dataArr 배열에 저장, 읽어온 사이즈를 int타입으로 return
		 		-반복문의 첫머리에서 dataArr에는 "대한민국서"가 들어가고, inputLength에는 10이 들어감 
		 		-반복문 두번째에는 dataArr에 "울시동대문" 들어가고 inputLength에 10 들어감
		 		-반복문 세번째 일때 "구엔터"가 들어가고 >> input:lenght에는 4가 들어감(한글+\r\n)
			 */
			
			System.out.write(dataArr, 0, inputLength);
//			System.out.write(byte[], int형 시작값(배열 인덱스), 읽어올 문자 길이);
			System.out.flush();
			
			totalByte+=inputLength;
			
			
		} // end of while
		System.out.println("총 "+(totalByte-2)+"byte 입력함.");
		

	} // end of main()
}
