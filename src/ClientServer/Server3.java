package ClientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
	
	private ServerSocket serverSocket; // wartet auf Clients und erzeugt fuer jeden Client ein Socket
	
	public Server3 (ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void startServer() {
		
		try {
			while(!serverSocket.isClosed()) {
				
				Socket socket = serverSocket.accept(); // warten, bis Client connected + Rueckgabe eines Socket-Objekts, damit Server mit Client kommunizieren kann
				System.out.println("A new client has connected!");
				ClientHandler clientHandler = new ClientHandler(socket); // kommuniziert mit dem Client, implementiert Runnable
				Thread thread = new Thread(clientHandler); // neuer Thread mit der Uebergabe eines clientHandler-Objekts
				thread.start();
			}
		}
		catch (IOException e) {
			
		}
	}
	public void closeServerSocket () {
		try {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static void main (String [] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket (1996); // Initialisierung eines ServerSockets ueber einem Port
		Server3 server = new Server3(serverSocket);
		server.startServer();
		
	}
	
}
