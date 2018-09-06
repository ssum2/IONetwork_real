package net.day1.quiz_02;

import java.net.*;
import java.util.Date;
import java.io.*;

public class Quiz_NetServer {

	public static void main(String[] args) {
		final int port = 10000;
		ServerSocket srvSock = null;
		Socket sock = null;
		
		try {
			srvSock = new ServerSocket(port);
			System.out.println("--- Current Time Server Ready... ---");
			sock = srvSock.accept();
	
			InputStream instrm = sock.getInputStream();
			DataInputStream dinstrm = new DataInputStream(instrm);
			String clientMsg = "";

			OutputStream outstrm = sock.getOutputStream();
			DataOutputStream doutstrm = new DataOutputStream(outstrm);
				
			while((clientMsg = dinstrm.readUTF())!=null) {
				if(clientMsg.equalsIgnoreCase("time")) {
					Date now = new Date();
					String currentTime = String.format("%tT", now);
					doutstrm.writeUTF(currentTime);
					doutstrm.flush();
				}
				else {
					doutstrm.writeUTF("time만 입력하세요");
					doutstrm.flush();
				}
				
			}
			if(doutstrm != null) doutstrm.close();
			if(outstrm != null) outstrm.close();
			if(dinstrm != null) dinstrm.close();
			if(instrm != null) instrm.close();
			if(sock != null) sock.close();


		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("--- Current Time Server Closed ---");

	}

}
