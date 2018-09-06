package net.day1;
import java.net.*;
import java.io.*;

public class InetAddressTest {
/*
 	C:\Windows\System32\drivers\etc 경로에서 hosts의 가장 하단 아이피주소 추가
 	본래 네임서버에 물어보는 게 아니라 dns에 물어보는게 일반적임
 */
	
	public static void main(String[] args) {
// 	#InetAddress 클래스: IP와 관련된 클래스
		try {
//		#InetAddress 사용하는 여러 방법
//			InetAddress inet_ip = InetAddress.getByName("DESKTOP-5GAMFFN");
//			>> InetAddress.getByName(String타입의 PC이름(PC속성 들어가서 볼 수 있음));
			
//			InetAddress inet_ip = InetAddress.getByName("127.0.0.1");
//			>> 127.0.0.1; 자기 자신의 IP주소(LoopBack Address)
				
//			InetAddress inet_ip = InetAddress.getByName("192.168.50.48");
//			>> 실제 pc의 아이피주소를 입력하면 pc이름 쓴 것 같이 나옴
			
//			InetAddress inet_ip = InetAddress.getByName("192.168.50.54");
//			>> 다른사람의 아이피 주소를 입력했을 때는 입력한 아이피주소만 나옴
			
			InetAddress inet_ip = InetAddress.getByName("www.sumi.com");
//			>> localhost 자기자신; myIp: 127.0.0.1 	myHostName: localhost
			
			String ip = inet_ip.getHostAddress();
//			>> return String타입 host의 주소
			System.out.println("myIp: "+ip);
			
			String hostName = inet_ip.getHostName();
//			>> return String타입 PC이름
			System.out.println("myHostName: "+hostName);
			
			System.out.println("----------------------------");
			InetAddress inet_ip2 = InetAddress.getByName("www.naver.com");
			String ip2 = inet_ip2.getHostAddress();
//			>> return String타입 host의 주소
			System.out.println("ip2: "+ip2);
			String hostName2 = inet_ip2.getHostName();
//			>> return String타입 PC이름
			System.out.println("hostName2: "+hostName2);
			/*
			 	ip2: 210.89.160.88
				hostName2: www.naver.com
			 */
			System.out.println("----------------------------");
			
			InetAddress[] inetAddArr = InetAddress.getAllByName("www.naver.com");
//			>> 1개의 host name(www.naver.com)에 여러개의 IP주소를 사용하는 서버의 경우(ex. 포털사이트 등)
//				InetAdderess[] 형태로 return
			System.out.println("▽ www.naver.com의 IP주소들 ▽");
			for(int i=0; i<inetAddArr.length; i++) {
				System.out.println(inetAddArr[i].getHostAddress());
			}
			System.out.println("----------------------------");
			
			InetAddress[] inetAddArr2 = InetAddress.getAllByName("www.google.com");
//			>> 1개의 host name(www.naver.com)에 여러개의 IP주소를 사용하는 서버의 경우(ex. 포털사이트 등)
//				InetAdderess[] 형태로 return
			System.out.println("▽ www.google.com의 IP주소들 ▽");
			for(int i=0; i<inetAddArr2.length; i++) {
				System.out.println(inetAddArr2[i].getHostAddress());
			}
			
			System.out.println("----------------------------");
			
			InetAddress[] inetAddArr3 = InetAddress.getAllByName("www.iei.or.kr");
//			>> 1개의 host name(www.naver.com)에 여러개의 IP주소를 사용하는 서버의 경우(ex. 포털사이트 등)
//				InetAdderess[] 형태로 return
			System.out.println("▽ www.iei.or.kr의 IP주소들 ▽");
			for(int i=0; i<inetAddArr3.length; i++) {
				System.out.println(inetAddArr3[i].getHostAddress());
			}
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
	}

}
