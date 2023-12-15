package Graph;

public class Eulerkreistest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

// Beispiel: Eingabe eines ungerichteten Beispielgraphen
		int knotenzahl = 2;
		
		int knoten[] = new int[knotenzahl];
		boolean adjMatrix[][] = new boolean[knotenzahl][knotenzahl];
		
		adjMatrix[0][1] = true;
		adjMatrix[0][2] = true;
		
		System.out.println(zusammenhang(knotenzahl,adjMatrix, knoten));
		System.out.println(geraderGrad(adjMatrix));
		
		if (zusammenhang(knotenzahl,adjMatrix, knoten) && geraderGrad(adjMatrix)) {
			System.out.println("ja");
		}
		else System.out.println("nein");
	
	}
// Test auf Zusammenhang	
	public static boolean zusammenhang(int knotenzahl, boolean adjMatrix [] [], int knoten []) {
		boolean zusammenhang = false;
		for (int i = 0; i < knotenzahl; i++) {
			zusammenhang = tiefensucheErfolgreich(i, knotenzahl, adjMatrix);
			if (zusammenhang == true) return zusammenhang;
		}
		return zusammenhang;
	}

	public static boolean tiefensucheErfolgreich(int wurzel, int knotenzahl, boolean adjMatrix [] []) {
		boolean tiefensuche = true;
		boolean besucht[] = new boolean[knotenzahl];
		tiefensuche(wurzel, knotenzahl, besucht, adjMatrix);
		for (int i = 0; i < knotenzahl; i++) {
			if (besucht[i] == false)
				tiefensuche = false;
		}
		return tiefensuche;
	}

	public static void tiefensuche(int knoten, int knotenzahl, boolean besucht[], boolean adjMatrix [] []) {
		besucht[knoten] = true;
		for (int i = 0; i < knotenzahl; i++) {
			if (adjMatrix[knoten][i] == true && !besucht[i]) {
				tiefensuche(i, knotenzahl, besucht, adjMatrix);
			}
		}
	}
// Test auf geraden Grad	
	public static boolean geraderGrad(boolean adjMatrix[][]) {
		boolean gerade = true;
		for (int i = 0; i < adjMatrix.length; i++) {
			int zaehler = 0;
			for (int j = 0; j < adjMatrix[i].length; j++) {
				if (adjMatrix[i][j] == true) zaehler ++;
			}
			System.out.println(i + " hat den Grad " + zaehler);
			if (zaehler % 2 != 0) return false;
		}
		return gerade;
	}
}
