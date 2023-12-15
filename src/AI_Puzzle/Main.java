package AI_Puzzle;

import AI_Puzzle.*;

public class Main {

	// Angaben zu Ihrer Person:
	public static String nachname = "Gauss"; // Tragen Sie hier Ihren Nachnamen ein
	public static String vorname  = "David"; // Tragen Sie hier Ihren Vornamen ein
	public static String matrikel = "1372488"; // Tragen Sie hier Ihre Matrikelnummer ein

	// Falls Sie mit einer weiteren Person zusammengarbeitet haben, tragen Sie hier den Namen dieser Person ein:
	public static String gruppe   = "";

	/* Vergessen Sie nicht, die nachfolgenden Behauptungen zu pruefen!
	 * Wahr:   qX = true;
	 * Falsch: qX = false;
	 */

	// Behauptung q1: "Greedy findet NICHT immer eine vorhandene Loesung, aber
	//                wenn eine Loesung gefunden wird, ist der Loesungsweg optimal."
	public static Boolean q1 = false; // zu beantworten mit true oder false

	// Behauptung q2: "Greedy findet immer eine Loesung."
	public static Boolean q2 = false; // zu beantworten mit true oder false

	// Behauptung q3: "Wenn A* einen Loesungsweg gefunden hat, ist dieser immer optimal."
	public static Boolean q3 = true; // zu beantworten mit true oder false

	// Behauptung q4: "Jedes Puzzle ist loesbar."
	public static Boolean q4 = false; // zu beantworten mit true oder false

	// Behauptung q5: "Die Methode countWrongTiles() in Puzzle.java ist KEINE zulaessige Heuristik."
	public static Boolean q5 = false; // zu beantworten mit true oder false


	// Hier ist Platz fuer Ihre Tests
	public static void main(String[] args) {
		Puzzle example = new Puzzle(1, 2, 3, 8, 4, 5, 6, 0, 7);
		Summary summary = PuzzleSolver.greedy(example, PuzzleSolver.Heuristic.WRONG_TILES, true, 50, 100000);
		System.out.println(summary);
	}
}
