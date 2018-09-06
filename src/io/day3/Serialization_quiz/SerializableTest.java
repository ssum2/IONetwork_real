package io.day3.Serialization_quiz;

import java.io.*;

public class SerializableTest {

	public static void objectToFileSave(Object obj, String saveFilename) { 
	 // 직렬화 
		
		// === 객체 obj 를 파일 saveFilename 로 저장하기 === //
		try {
			FileOutputStream fost = new FileOutputStream(saveFilename, false); 
			// 노드스트림(빨대꽂기)
			
			BufferedOutputStream bufOst = new BufferedOutputStream(fost, 1024);  
			// 필터스트림(노드스트림에 오리발장착하기)
			
			ObjectOutputStream objOst = new ObjectOutputStream(bufOst);
			// 객체 obj 를 파일 saveFilename 에 기록하는(저장하는) 스트림 생성하기 
			
			objOst.writeObject(obj);
			// objOst 을 사용하여 객체 obj 를 파일 saveFilename 에 기록하기(저장하기)
			
			objOst.close();
			bufOst.close();
			fost.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	public static Object getObjectFromFile(String fileName) {
	 // 역직렬화 	
		
		// === 파일 filename 을 읽어서 객체 obj 로 변환하기 === //
		
		try {
			FileInputStream finst = new FileInputStream(fileName); 
			// 노드스트림(빨대꽂기)
			
			BufferedInputStream bufInst = new BufferedInputStream(finst, 1024);
			// 필터스트림(노드스트림에 오리발장착하기)
			
			ObjectInputStream objInst = new ObjectInputStream(bufInst); 
			// 파일 filename 을 읽어서 객체로 만들어주는 스트림 생성하기
			
			Object obj = objInst.readObject();
			// objInst 을 사용하여 파일 filename 에 기록(저장)되어졌던것을 객체로 만들기 
			
			objInst.close();
			bufInst.close();
			bufInst.close();
			
			return obj;
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
