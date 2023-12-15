package DataStructures;

import java.util.*;

public class List {
	public static void main(String args[]) {

// Speichern in ArrayListe
		ArrayList<Double> data = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			double toAdd = Math.random();
			data.add(toAdd);
		}

// Maximum mit for each
		double max = 0.0;
		for (double d : data) {
			if (max < d) {
				max = d;
			}
		}
		System.out.println(max);

// Minimum mit Iterator
		Iterator<Double> it = data.iterator();
		double min = 1.0;
		while (it.hasNext()) {
			double d = it.next();
			if (min > d) {
				min = d;
			}
		}
		System.out.println(min);

//Trennung in zwei Listen
		ArrayList<String> littleNum = new ArrayList<>();
		LinkedList<String> bigNum = new LinkedList<>();

		for (double d : data) {
			String string = Double.toString(d);
			if (d < 0.5)
				littleNum.add(string);
			else
				bigNum.add(string);
		}
		
		int anzahlElem = bigNum.size();
		String minLinkedList = bigNum.get(0); //Zugriff wie auf Array
		String maxLinkedList = bigNum.get(anzahlElem); //Zugriff wie auf Array
		System.out.println("Min der LinkedList: " + minLinkedList);
		System.out.println("Max der LinkedList: " + maxLinkedList);
		
		// Speichern in HashMap
		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
		int i = 0;
		for (double d : data) { // Hinzufügen aus Arrayliste in Hashmap hinzu
			map.put(i, d);
			i++;
		}
		double max2 = 0.0; // Bestimme das Maximum
		for (Map.Entry<Integer, Double> m : map.entrySet()) { // für jeden Eintrag
			double d = (double) m.getValue(); // Nimm den Double-Wert
			if (d > max2)
				max2 = d;
		}
		System.out.println("Hashmap-Maximum: " + max2);
	}
}

