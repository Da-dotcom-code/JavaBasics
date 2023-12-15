package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
	public static void main(String args[]) throws IOException {
		Socket socket = new Socket("127.0.0.1", 1000);
		
		
		PrintWriter w = new PrintWriter(socket.getOutputStream(), true);
		
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
		System.out.println("Nun gehts weiter");
		
		BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		do {
			line = r.readLine();
			if (line == null)
				break;
			System.out.println(line);
		} while (!line.equalsIgnoreCase("-1"));
		
		
		
		socket.close();
		scanner.close();

	}
}
