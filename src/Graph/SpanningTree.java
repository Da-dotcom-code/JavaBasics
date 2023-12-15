package Graph;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpanningTree {
	
	public static boolean besucht[];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int knotenzahl = scanner.nextInt();
		int kantenzahl = scanner.nextInt();
		List <List <Integer>> adjazenzliste = new ArrayList <List <Integer>> ();
// Erstelle fuer jeden Knoten eine Liste	
		for (int i = 0; i < knotenzahl; i++) {
			List <Integer> kantenliste = new ArrayList<>();
			adjazenzliste.add(kantenliste);
		}
// Fuege Kanten hinzu	
		for (int i = 0; i < kantenzahl; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			adjazenzliste.get(u).add(v);
		}
		
		System.out.println(zusammenhang(knotenzahl, adjazenzliste));
		
	}
	
// Test auf Erreichbarkeit	
		public static String zusammenhang(int knotenzahl, List <List <Integer>> adjazenzliste) {
			boolean zusammenhang = false;
			for (int i = 0; i < knotenzahl; i++) {
				besucht = new boolean[knotenzahl];
				zusammenhang = tiefensucheStart(i, knotenzahl, adjazenzliste);
				if (zusammenhang == true) return "Yes";
			}
			if (zusammenhang) return "Yes";
			else return "No";
		}

		public static boolean tiefensucheStart(int wurzel, int knotenzahl, List <List <Integer>> adjazenzliste) {
			boolean tiefensuche = true;
			tiefensuche(wurzel, knotenzahl, adjazenzliste);
			for (int i = 0; i < knotenzahl; i++) {
				if (besucht[i] == false)
					tiefensuche = false;
			}
			return tiefensuche;
		}

		public static void tiefensuche(int knoten, int knotenzahl, List <List <Integer>> adjazenzliste) {
			besucht[knoten] = true;
			for (int nachbar : adjazenzliste.get(knoten)) {
				if ( !besucht[nachbar]) {
					tiefensuche(nachbar, knotenzahl, adjazenzliste);
				}
			}
		}
}
