package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ProduktlisteHash {
	HashMap <Integer, ProduktHash> map = new HashMap<>();

	ProduktlisteHash(File liste) throws IOException {
		befülleListe(liste);
	}

	public void befülleListe(File liste) throws IOException {
		InputStream inStream = new FileInputStream(liste);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
		String input;
		int pos = 0;
		boolean next = true;
		while (next) {
			input = bufferedReader.readLine();
			if (input == null) {
				next = false;
				break;
			}

			ProduktHash produkt = new ProduktHash(input);
			map.put(pos, produkt);
			pos++;
		}
	}
	
	public String berechneDurchschnitt() {
		int summe = 0;
		int anzahl = map.size();
		for (ProduktHash produkt : map.values()) {
			summe += produkt.preis;
		}
		return "Durchschnittspreis: " + summe/anzahl + " Euro";
	}
	
	public String berechnePreisspanne() {
		int min = Integer.MAX_VALUE;
		int max = 0;
		int aktuell = 0;
		
		for (ProduktHash produkt : map.values()) {
			aktuell = produkt.preis;
			if (aktuell > max) max = aktuell;
			if (aktuell < min) min = aktuell;
		}
		return "Preisspanne: von " + min+ " Euro bis " + max + " Euro";
	}
	public String toString() {
		String string = "";
		for (ProduktHash produkt : map.values()) {
			string += produkt.toString();
			string += "\n";
		}
		return string;
	}
	
	public void erzeugeBilligProdukte() throws IOException {
		File billigprodukte = new File("C:\\Users\\david\\Downloads\\Info_Nachhilfe\\Prog1\\Billigprodukte.txt");
		OutputStream outputStream = new FileOutputStream(billigprodukte);

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		
		// Gehe über die keys
		for (Integer key: map.keySet()) {
			ProduktHash produkt = map.get(key);
			if (produkt.preis <= 20) {
				outputStreamWriter.write("Posten " + key + ": "+ produkt.toString() );
				outputStreamWriter.write("\n");
			}
		}
		
		//Gehe über die values
//		for (ProduktHash produkt : map.values()) {
//			if (produkt.preis <= 10) {
//				outputStreamWriter.write(produkt.toString() );
//				outputStreamWriter.write("\n");
//			}
//		}
		outputStreamWriter.close();
	}
	
	public static void main(String args[]) throws IOException {
		File file = new File("C:\\Users\\david\\Downloads\\Info_Nachhilfe\\Prog1\\Produktliste.txt");
		ProduktlisteHash ProduktlisteHash = new ProduktlisteHash(file);
		System.out.println(ProduktlisteHash.toString());
		System.out.println(ProduktlisteHash.berechneDurchschnitt());
		System.out.println(ProduktlisteHash.berechnePreisspanne());
		ProduktlisteHash.erzeugeBilligProdukte();
		System.out.println("h");
	}
}
