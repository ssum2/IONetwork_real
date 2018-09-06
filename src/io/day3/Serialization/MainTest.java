package io.day3.Serialization;

import java.util.*;

public class MainTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
//	1. 직렬화
//		#객체 생성
		Student student1 = new Student("한석규", 21, "서울시 강동구");
		Student student2 = new Student("두석규", 22, "서울시 강서구");
		Student student3 = new Student("세석규", 23, "서울시 강남구");
		Student student4 = new Student("네석규", 24, "서울시 강북구");
		
//		#리스트 생성 및 리스트에 객체정보 담기
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		studentList.add(student4);
		
//		#정보가 담긴 객체를 파일로 저장(SerializableTest클래스를 통해 분쇄작업)
		SerializableTest serial = new SerializableTest();
		serial.objectToFileSave(studentList, "c:/iotestdata/serializable/students.dat");
//								객체, 파일이 저장될 경로
		
		System.out.println(">> 객체 -> 파일 내보내기 완료 <<");
		System.out.println("------------------------");
		
		
//	2. 역직렬화
//		#파일로부터 객체 추출하기(역직렬화)
		Object obj = serial.getObjectFromFile("c:/iotestdata/serializable/students.dat");
//		>> 파라미터로 추출할 파일을 받아서 Object를 반환하는 메소드
		
//		#파일에서 추출한 객체를 리스트에 저장
		if(obj != null) {
			List<Student> resultList = (List<Student>)obj;
			System.out.println(">> 파일에 저장된 객체 정보 출력하기 <<");
			for(Student student :resultList) {
				System.out.println(student);
			}
		}
		else {
			System.out.println(">> 파일에 저장된 객체 정보가 없습니다 <<");
		}
		System.out.println(">> 파일 -> 객체 불러오기 완료 <<");
		
	} // end of main()

} // end of class
