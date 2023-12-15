package ClientServer;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); // Verwaltet die Clients, static, da die
																				// Liste zur Klasse gehoert und nicht zu
																				// jedem Objekt der Klasse

	private Socket socket; // stellt Verbindung von Client und Server her
	private BufferedReader bufferedReader; // liest die Nachrichten, die ein Client schickt
	private BufferedWriter bufferedWriter; // sendet Nachrichten an den Client (von anderen Clients)
	private String clientUserName;

	public ClientHandler(Socket socket) {
		try {
			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));// character
																										// stream:
																										// character
																										// streams enden
																										// mit Writer,
																										// byte streams
																										// enden mit
																										// Stream /
																										// Puffer macht
																										// den Writer
																										// effizienter
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // hiermit
																										// sendet der
																										// Client
			this.clientUserName = bufferedReader.readLine(); // Eingabe des Namens
			clientHandlers.add(this); // Fuege Client der Arrayliste hinzu
			broadcastMessage("User " + clientUserName + " has entered the chat");

		} catch (IOException e) {
			closeEverything(bufferedReader, bufferedWriter, socket);
		}
	}

	@Override
	public void run() { // laeuft ueber seperaten Thread
		String messageFromClient;
		while (socket.isConnected()) { // solange der Client verbunden ist
			try {
				messageFromClient = bufferedReader.readLine(); // Programm wartet, bis es eine Message vom Client
																// bekommt
				broadcastMessage(messageFromClient);

			} catch (IOException e) {
				closeEverything(bufferedReader, bufferedWriter, socket);
				break; // wenn sich der Client abmeldet
			}
		}
	}

	public void broadcastMessage(String messageToSend) { //Schicke die Nachricht des Clients an alle anderen Clients
		for (ClientHandler clientHandler: clientHandlers) { 
				try {
					if (!clientHandler.clientUserName.equals(clientUserName)) { //Nur Nachrichten an andere Clienten
					clientHandler.bufferedWriter.write(messageToSend);
					clientHandler.bufferedWriter.newLine(); // Ende der Ausgabe
					clientHandler.bufferedWriter.flush(); // Entleeren des Puffers
					}
				} catch (IOException e) {
					closeEverything (bufferedReader, bufferedWriter, socket );
				}
			}
			
		}
	
	

	public void removeClientHandler() { // Entfernen eines Clients
		clientHandlers.remove(this); // der Client soll auch keine Nachrichten mehr bekommen
		broadcastMessage("SERVER " + clientUserName + " has left the chat");

	}

	public void closeEverything(BufferedReader bufferedReader, BufferedWriter bufferedWriter, Socket socket) {
		removeClientHandler(); // Client hat Chat verlassen
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

}
