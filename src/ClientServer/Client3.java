package ClientServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client3 {
	private Socket socket;
	private String userName;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	public Client3(Socket socket, String userName) {
		try {
			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.userName = userName;
		} catch (IOException e) {
			closeEverything(bufferedReader, bufferedWriter, socket);
		}
	}

	public void sendMessage() { // Message an ClientHandler
		try {
			bufferedWriter.write(userName);// sendet dem ClientHandler den username, bedient das readLine im Konstruktor
											// des Client Handler
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			Scanner scanner = new Scanner(System.in); // Senden weiterer Nachrichten
			while (socket.isConnected()) {
				String messageToSend = scanner.nextLine(); // wenn der User Enter drueckt
				String finalMessage = userName + ": " + messageToSend;
				bufferedWriter.write(byteLength(finalMessage)); // beschraenkt die Laenge der Nachrichten auf 255 Bytes
				bufferedWriter.newLine();
				bufferedWriter.flush();

			}
		} catch (IOException e) {
			closeEverything(bufferedReader, bufferedWriter, socket);

		}

	}

	public void listenForMessage(){// Hoert Nachrichten vom Server, d.h. die Nachrichten, die von anderen CLients gebroadcastet wurden => broadcastMessage bei ClientHandler
		new Thread(new Runnable(){//neuer Thread, da blocking operation
			@Override 
			public void run() {
				String messageFromGroupChat;
				while(socket.isConnected()) {
					try {
						messageFromGroupChat = bufferedReader.readLine(); // lese die Nachricht, die gebroadcastet wurde
						System.out.println(messageFromGroupChat);
					} catch (IOException e) {
						closeEverything (bufferedReader, bufferedWriter, socket);
					}
				}
			}
		}).start();
	}
	public void closeEverything(BufferedReader bufferedReader, BufferedWriter bufferedWriter, Socket socket) {
		
		try {
			if (bufferedReader != null) { // verhindert Nullpointerexception
				bufferedReader.close(); // die Streams werden mitgeschlossen
			}
			if (bufferedWriter != null) { // verhindert Nullpointerexception
				bufferedWriter.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String byteLength(String toTrim) {
		byte byteLength = (byte)toTrim.getBytes().length; // Ermittle die Laenge in Bytes
		toTrim = byteLength + toTrim; //Anzahl der Bytes vorne anfuegen
		
		if (byteLength > 255) { // Wenn der String gekuerzt werden muss
			byte [] convertByte = toTrim .getBytes(); // Erzeuge Byte-Array
			byte [] trimmedByte = new byte[255]; // Ziel-Array mit der Laenge 255
		
		for (int i = 0; i < 255; i++) {	
		
			trimmedByte[i] = convertByte[i]; 
		}
		toTrim =  new String (trimmedByte); // Konvertiere Byte-Array in String
		}
		return toTrim;
		
	}

	public static void main(String [] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username for the Groupchat: ");
		String userName = scanner.nextLine();
		Socket socket = new Socket ("localhost", 1996);
		Client3 client = new Client3(socket, userName);
		client.listenForMessage(); // blockieren, daher ueber Thread gesteuert, koennen daher zur gleichen Zeit laufen
		client.sendMessage();
		
		
		
	}
}
