package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	static Socket clientSocket;
	
	public Client() throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 1000);
		this.clientSocket = socket;
	}
	
	
	public static void main(String args[]) {
		// TODO Auto-generated method stub
		try {
			Client client = new Client(); // Initialisiere den Socket
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		new Thread(() -> { // Thread zum Schreiben
			try {
				writeToServer(clientSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> { // Thread zum Lesen
			try {
				readFromServer(clientSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}

	public static void writeToServer(Socket clientSocket) throws IOException {
		PrintWriter w = new PrintWriter(clientSocket.getOutputStream(), true);
		
		String line;
		Scanner scanner = new Scanner(System.in);
		boolean send = true;
		
		while (send) {
			line = scanner.nextLine();
			if (line.contains("-1")) {
				send = false;
			}
			w.println(line);
		}
	}
	
	public static void readFromServer(Socket clientSocket) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String line;
		do {
			line = r.readLine();
			if (line == null)
				break;
			System.out.println(line);
		} while (!line.contains("-1"));
		
	}

}