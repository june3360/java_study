package com.ssafy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BookServer {

	public static void main(String[] args) {
		try(ServerSocket ss = new ServerSocket(8888)){
			Socket s = ss.accept();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println(ois.readObject());
		}catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
