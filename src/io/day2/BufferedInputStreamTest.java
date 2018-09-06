package io.day2;

import java.util.Scanner;
import java.io.*;

/*
	#FilterStream : NodeStream의 보조로 노드스트림의 속도를 향상시키는 등의 부가적인 기능을 함
				ex) 노드스트림: 실제 움직이는 발
					필터스트림: 오리발

	>>>>> BufferedInputStream 와 BufferedOutputStream <<<<<
	-- 1 byte 기반 스트림.
	-- 필터스트림(다른말로 보조스트림 이라고 부른다.)
	-- 단독으로 사용할 수 없고, 반드시 노드스트림에 장착되어 사용되는 것이다.
	        마치 수영장에서 오리발처럼 보조기구로 사용한다.
	        이것을 사용하면 오리발처럼 속도가 향상되므로 많이 사용한다.
	        
	-- 읽어올 데이터를 매번 1 byte 마다 읽고,쓰고 한다면 입.출력에 너무 많은 시간이 소요된다.
	        그래서 쓰는 작업없이 메모리 버퍼에 데이터를 한꺼번에 쭉~~ 읽기만 하여 모아두면
	        그만큼 쓰는 작업이 없으므로 읽기 속도는 빨라질 것이다.
	        그리고 나서 메모리 버퍼에 읽어서 모아두었던 내용을 한방에 쓰기를 하면 매번 1byte 씩 쓰는 것보다
	        속도가 빨라진다.
	        
	 BufferedInputStream 와  BufferedOutputStream 의 기본 버퍼크기는 512 byte 이다.
	 BufferedInputStream bist = new BufferedInputStream(in, size); 에서 size를 안쓰면 기본값 512로 들어감
	 
	 [예제]
	    필터스트림(보조스트림)을 이용해서 키보드로 부터 입력받고, 
	    입력받은 그 내용을 모니터(콘솔화면)에 출력하고,
	    또한 동시에 파일에도 출력해본다.
	    
	 >>> 데이터소스 : 키보드(System.in --> 노드스트림)
	              + 필터스트림(보조스트림)으로 BufferedInputStream 을 사용함.
	 
	 >>> 데이터목적지 : 모니터(System.out --> 노드스트림)
	               + 필터스트림(보조스트림)으로  BufferedOutputStream 을 사용함.
	               
	                                  파일(FileOutputStream --> 노드스트림)
	               + 필터스트림(보조스트림)으로  BufferedOutputStream 을 사용함.                                                 
*/

public class BufferedInputStreamTest {

	public static void main(String[] args) {
		try {
//		1. 키보드>화면 출력
//			#입력 노드스트림 => 키보드(System.in)
//			>> 여기에 +필터스트림(보조스트림; BufferedInputStream) 장착!
			BufferedInputStream bist = new BufferedInputStream(System.in, 1024);
//			BufferedInputStream bist = new BufferedInputStream(파일경로, buffer의 사이즈);
//			>> 정해둔 버퍼의 사이즈를 넘어가도록 입력하면 버퍼 안의 내용을 자동 flush해주고 버퍼를 비움
			
//			#출력 노드스트림 => 모니터(System.out)
//			>> 여기에 +필터스트림(보조스트림; BufferedOutputStream) 장착!
			BufferedOutputStream bost1 = new BufferedOutputStream(System.out, 1024);
			
//		2. 키보드>파일 출력
//			#출력하고 싶은 파일 경로	
			String filename = "c:/iotestdata/나의소개.txt";
//			#FileOutputStream 객체 만들기
			FileOutputStream fost = new FileOutputStream(filename);
//			#출력 노드스트림 => 파일(FileOutputStream)
//			>> 여기에 +필터스트림(보조스트림; BufferedOutputStream) 장착!
			BufferedOutputStream bost2 = new BufferedOutputStream(fost, 1024);
			
			int input = 0;
			int totalByte = 0;
			
			int whileCount = 0;
			
			while((input = bist.read()) != -1) {
				whileCount++;
				
//				# 모니터 출력
				bost1.write(input);
				bost1.flush();
				
//				# 파일 출력
				bost2.write(input);
				bost2.flush();

				totalByte++;
			}
			System.out.println("----------------------------------------------------------");
			System.out.println("whileCount: "+whileCount+"회 반복");
			System.out.println("총 "+totalByte+"bytes 읽고, "+filename+"파일에 씀");
			System.out.println("----------------------------------------------------------");
			
//			# 닫는 순서: 보조스트림 > 노드스트림 순
			bost2.close();
			fost.close();
			
			bost1.close();
			System.out.close();
			
			bist.close();
			System.in.close();

		} catch (FileNotFoundException e) {
			System.out.println(">> 파일의 경로명이 올바르지 않습니다. <<");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}
