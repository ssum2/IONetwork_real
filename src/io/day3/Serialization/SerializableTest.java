package io.day3.Serialization;
import java.io.*;
public class SerializableTest {
// >> 객체가 들어오면 바이트 타입으로 분쇄해 직렬화 할 클래스
	
//	#객체 obj를 파일 saveFileName으로 저장(직렬화)
	public void objectToFileSave(Object obj, String saveFileName) {
//								>> 조상클래스인 Object로 받음(어떤 객체형태가 들어올 지 모르기 때문에)
		try {
//			#파일 출력 노드스트림, 필터스트림 생성
			FileOutputStream fost = new FileOutputStream(saveFileName, false);
			BufferedOutputStream bufOst = new BufferedOutputStream(fost, 1024);
			
//			#ObjectOutputstream (객체 출력 노드스트림) 
//			 > 객체obj를 파일saveFileName에 기록(저장)하는 스트림 생성
			ObjectOutputStream objOst = new ObjectOutputStream(bufOst);
			
//			# 객체obj를 파일saveFileName에 저장
			objOst.writeObject(obj);
//			>> 파라미터로 넘겨받은 객체obj를 분쇄해서 bufOst > fost > saveFileName에 저장
			
			objOst.close();
			bufOst.close();
			fost.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // end of objectToFileSave();	
	
	
//	#파일 fileName을 객체 obj로 저장(역직렬화)
	public Object getObjectFromFile(String fileName) {
	
		try {
//			#파일 입력 노드스트림, 필터스트림 생성
			FileInputStream fist = new FileInputStream(fileName);
			BufferedInputStream bufIst = new BufferedInputStream(fist, 1024);
			
//			#읽어온 파일을 객체로 읽어와야함(ObjectInputStream)
			ObjectInputStream objIst = new ObjectInputStream(bufIst);
			
//			#파일fileName에 저장된 내용을 객체로 반환
			Object obj = objIst.readObject();
			
			objIst.close();
			bufIst.close();
			fist.close();
			
			return obj;
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
}
