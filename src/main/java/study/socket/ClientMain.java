package study.socket;

import java.io.IOException;

public class ClientMain {

	public static void main(String[] args) {

		String message = "this is test message";
		try {
			/**
			 * 서버 스레드풀의 스레드 갯수보다 많은 소켓 연결을 하려고 하면 대기하게 된다.
			 */
			for(int i=0; i<100; i++) {
				SocketClient socketClient = new SocketClient(i);
				socketClient.connect();
				socketClient.send(message + i+"\n");
				socketClient.sendEOF();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
