package io.day3.Serialization;

import java.io.Serializable;

public class Member implements Serializable{
	private static final long serialVersionUID = -5066618821995456858L;
	
	private String id;
	private String pw;
	private String name;
	private String position;
	static int count;
	
	public Member() {}
	public Member(String id, String pw, String name, String position) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.position = position;
		count++;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	public String info() {
		return "ID: "+id+", PW: "+pw+", 이름: "+name+", 직무: "+position;
		
	}
	
}
