package io.day3.Serialization_quiz;
// 강사님이 하신 부분
import java.io.Serializable;

public class Member implements Serializable {
	
	private static final long serialVersionUID = -8498310755260614534L;

	private String id;
	private String pwd;
	private String name;
	private String address;

	public Member() { }
	
	public Member(String id, String pwd, String name, String address) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		String info = "▷ 아이디: " + id + "\n" +
					  "▷ 암호: " + pwd + "\n" +
					  "▷ 성명: " + name + "\n" +
					  "▷ 주소: " + address + "\n";
				
		return info;        
	}
	
}
