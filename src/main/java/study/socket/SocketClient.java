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
	private static int port = 13579;
	private static String hostIp="127.0.0.1";
	private Socket socket;

	public SocketClient() {
		this.socket = new Socket();
	}

	public void connect() throws IOException {
		SocketAddress address = new InetSocketAddress(hostIp, port);
		socket.connect(address);
	}

	public void send() throws IOException {
		byte[] data = Files.readAllBytes(Paths.get("files/file1.txt"));
		System.out.println("file length : "+data.length);
		OutputStream os = socket.getOutputStream();
		os.write(data);
		os.flush();
	}

	public void receive() throws IOException {
		int maxBufferSize = 1024;
		byte[] recvBuffer = new byte[maxBufferSize];
		InputStream is = socket.getInputStream();
		int nReadSize = is.read(recvBuffer);
		while (nReadSize > 0) {
			String convertedMessage = new String(recvBuffer,0,nReadSize,StandardCharsets.UTF_8);
			System.out.println(convertedMessage);
			nReadSize = is.read(recvBuffer);
		}
	}
}
