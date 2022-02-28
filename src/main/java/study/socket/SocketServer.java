package study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketServer {
	private static int port = 13579;
	private ServerSocket server;

	public SocketServer() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("서버 소켓 생성 오류");
		}
	}

	public void listen() {
		System.out.println("-------접속 대기중------");
		Socket socket = null;  // 클라이언트가 접속할 때 까지 blocking
		try {
			socket = server.accept();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("클라이언트 소켓 연결 중 오류");
		}
		System.out.println(socket.getInetAddress() + "로 부터 연결요청이 들어옴");

		try {
			while (true) {

				InputStream is = socket.getInputStream();
				byte[] bytes = new byte[1024];

				int readByteCount = is.read(bytes);
				while (readByteCount > 0) {
					String convertedMessage = new String(bytes, 0, readByteCount, "UTF-8");
					System.out.println("클라이언트로 부터 데이터 수신");
					System.out.println("수신 데이터 : " + convertedMessage);
					sendEcho(convertedMessage.getBytes(StandardCharsets.UTF_8), socket);
					readByteCount = is.read(bytes);
				}

				System.out.println("****** 재전송 완료 ****");
			}
		} catch (IOException ex) {

		}
	}

	public void sendEcho(byte[] bytes, Socket socket) {
		try {
			OutputStream os = socket.getOutputStream();
			os.write(bytes);
			os.flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
