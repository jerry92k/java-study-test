package study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SocketClient {
	private int clientNo;
	private static int port = 13579;
	private static String hostIp = "127.0.0.1";
	private static String endMessage = "END";
	private Socket socket;

	public SocketClient(int clientNo) {
		this.socket = new Socket();
		this.clientNo = clientNo;
	}

	public void connect() throws IOException {
		SocketAddress address = new InetSocketAddress(hostIp, port);
		socket.connect(address);
		Thread receiveTread = new Thread(new Receiver(socket, clientNo));
		receiveTread.start();
	}

	public void send(String message) throws IOException {
		OutputStream os = socket.getOutputStream();
		os.write(message.getBytes(StandardCharsets.UTF_8));
		os.flush();
	}

	public void sendEOF() throws IOException {
		OutputStream os = socket.getOutputStream();
		os.write(endMessage.getBytes(StandardCharsets.UTF_8));
		os.flush();
	}

	public void sendFile(String filePath) throws IOException {
		byte[] data = Files.readAllBytes(Paths.get(filePath));
		System.out.println("file length : " + data.length);
		OutputStream os = socket.getOutputStream();
		os.write(data);
		os.flush();
	}

	private static class Receiver implements Runnable {

		private Socket socket;
		private int clientNo;

		public Receiver(Socket socket, int clientNo) {
			this.socket = socket;
			this.clientNo = clientNo;
		}

		@Override
		public void run() {
			receive();
		}

		public void receive() {
			int maxBufferSize = 1024;
			byte[] recvBuffer = new byte[maxBufferSize];
			try (InputStream is = socket.getInputStream()) {
				int nReadSize = is.read(recvBuffer);
				while (nReadSize > 0) {
					String convertedMessage = new String(recvBuffer, 0, nReadSize, StandardCharsets.UTF_8);
					System.out.println("clientNo[" + clientNo + "], echo from server : " + convertedMessage);
					nReadSize = is.read(recvBuffer);
				}
				System.out.println("receive finish");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("clientNo[" + clientNo + "] 소켓이 예상치 못하게 끊어졌습니다.");
			}
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("소켓 연결을 끊는중 오류가 발생하였습니다.");
				e.printStackTrace();
			}
		}
	}
}
