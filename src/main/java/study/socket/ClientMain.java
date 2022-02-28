package study.socket;

import java.io.IOException;

public class ClientMain {

	public static void main(String[] args) {
		SocketClient socketClient = new SocketClient();
		try {
			socketClient.connect();
			socketClient.send();
			// socketClient.receive();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
