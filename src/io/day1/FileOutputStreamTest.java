package io.day1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {
/*
 	### 키보드로부터 입력받은 것을 파일( c:\iotestdata\result.txt )에 기록(출력)하기
 	
 	1. 데이터소스: 키보드로부터 입력받음(노드스트림: System.in)
 	2. 데이터 목적지: 결과를 특정 파일에 출력함[노트스트림: FileOutputStream]
 	
 	
 	#FileOutputStream(file name, boolean append);
	만약에  탐색기에서 
	C:/iotestdata/result.txt 파일이 없으면 파일을 자동으로 생성
	단, 탐색기에서 C:/iotestdata 폴더는 존재해야 한다.
		 
	두번째 파라미터인 append 가 true 인 경우는
	첫번째 파라미터인 해당파일에 어떤 내용이 있을 때
	기존내용물은 그대로 두고 기존내용뒤에 새로운 내용을 덧붙여 추가 
		    
	두번째 파라미터인 append 가 false 인 경우는
	첫번째 파라미터인 해당파일에 어떤 내용이 있을 때
	기존내용물은 싹 지우고 새로운 내용을 새롭게 처음부터 (새로운 내용으로 덧씌움)
	
	BUT! 두번째 파라미터를 생략하면    
	>> FileOutputStream fost = new FileOutputStream(filename); 이라면
	자동적으로 false 로 인식한다.   
*/
	public static void main(String[] args) {

		System.out.print("내용을 입력하세요[입력하신 내용은 c:\\iotestdata\\result.txt에서 확인 가능]\n▷");
		
		String filename = "c:/iotestdata/result.txt";
//		>> 폴더는 있고 파일이 존재하지 않으면 입력한 파일명으로 자동생성됨
//		cf) 리눅스나 유닉스에서는 역슬래쉬가 아니라 슬래쉬(/)이기 때문에 호환을 위해 /로 통일
		boolean append = true;
		
		int input = 0;
		int totalByte=0;
		
		
		try {
			FileOutputStream fost = new FileOutputStream(filename, append);
//			>> FileNotFoundException: 실제 폴더에 파일이 없을 경우 발생하는 오류
//			>> FileOutputStream(파일경로 또는 파일명, 기존파일에 내용을 덧붙일지 아닐지를 boolean형으로 입력);
//			>> append: True인 경우 기존내용 뒤에 덧붙이고, False인 경우 기존내용를 지우고 새로운 내용으로 덧씌움
			while( (input=System.in.read()) != -1 ) {
				fost.write(input);
				fost.flush();
				totalByte++;
			}
			fost.close();
			System.in.close();
		} catch (FileNotFoundException e) {
			System.out.println(filename+" 파일이 없습니다.");
			e.printStackTrace();
		} catch(IOException e)	{
			e.printStackTrace();
		}

		System.out.println(filename+"에 쓰기 완료! 총 "+totalByte+"bytes 씀");
		System.out.close();
		

	}	// end of main()
}	// end of class
