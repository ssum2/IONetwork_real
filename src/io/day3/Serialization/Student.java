package io.day3.Serialization;

import java.io.Serializable;

public class Student implements Serializable {	
//					>> 바이트 단위로 분쇄 가능 한 클래스라는 표시
	private static final long serialVersionUID = 2086567569369420911L;
//	>> 해당 클래스의 UID는 long타입 숫자, static final로 고정값으로 만들고 이를 기준으로 직렬화/역직렬화
	
	private String name;
	private int age;
	private String address;
	
	public Student() {}
	public Student(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "학생명: "+name +", 나이: "+age+", 주소: "+address;
	}
	
	
	
}
