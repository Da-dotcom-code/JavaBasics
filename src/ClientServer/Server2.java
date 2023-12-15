package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server2 {
	
	public static void main (String args[]) throws IOException {
		

	ServerSocket serversocket = new ServerSocket(1000);
	Socket clientSocket = serversocket.accept();

	BufferedReader r = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	String line;
	do {
		line = r.readLine();
		if (line == null)
			break;
		System.out.println(line);
	} while (!line.contains("-1"));
	
	System.out.println("Nun gehts weiter");
	
	PrintWriter w = new PrintWriter(clientSocket.getOutputStream(), true);
	
	Scanner scanner = new Scanner(System.in);

	while (scanner.hasNext()) {

		line = scanner.nextLine();
		w.println(line);
	}
	serversocket.close();

}
	
	
}
