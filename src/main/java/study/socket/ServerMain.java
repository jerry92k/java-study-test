package study.socket;

public class ServerMain {

	public static void main(String[] args) {
		SocketServer socketServer = new SocketServer();
		socketServer.listen();
	}
}
