package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

	static ArrayList <Socket> clientlist = new ArrayList<>();
	
	public static void main(String args[]) throws IOException {
		
		
		ServerSocket serversocket = new ServerSocket(1000);

		while (true) {
			Socket clientSocket = serversocket.accept();
			clientlist.add(clientSocket);
			new Thread(() -> {
				try {
					writeToClient(clientSocket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}
		
	}
	
	public static void writeToClient(Socket clientSocket) throws IOException { // Lese, was der Client dem Server schreibt und schicke es an die anderen Clients
		BufferedReader r = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String line;
		do {
			line = r.readLine();
			if (line == null)
				break;
			for (Socket socket : clientlist) {
				if (socket != clientSocket) {
					PrintWriter w = new PrintWriter(socket.getOutputStream(), true);
					w.println(line);
					}
				}
			}
		 while (!line.contains("-1"));
	}

}
