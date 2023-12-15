package AI_Puzzle;


import AI_Puzzle.PuzzleSolver.Heuristic;

public class Summary {
	public Summary(){}

	// Konfiguration
	public Puzzle startState;     // Puzzlezustand zu Beginn
	public String algorithm;      // "Greedy" or "A*"
	public PuzzleSolver.Heuristic heuristic;  // Indikator der verwendeten Heuristik
	public boolean detectDouble;  // Werden wiederkehrende Zustaende erkannt?
	public int maxDepthPermitted; // maximale Tiefe, bis zu dieser gesucht wird
	public int limit; 			  // maximale Anzahl der zu untersuchenden Knoten

	// Ergebnis
	public Puzzle finalState;     // letzter betrachteter Zustand
	public boolean isSolution;    // Handelt es sich dabei um den Zielzustand?
	public int numExpansions;     // Anzahl der Expandierten Knoten im Suchbaum
	public int maxDepthReached;   // maximal erreichte Tiefe im Suchbaum
	public int queueSize;         // Anzahl der verbleibenden Elemente, nachdem das zuletzt betrachtete entfernt wurde
	public int maxQueueSize;	  // maximale Anzahl an Elementen, die gleichzeitig in der Queue gespeichert wurden
	public int pathLength;        // Laenge des Loesungspfades = Kosten
	public String path;           // Loesungspfad

	
	public Summary(Puzzle startState, String algorithm, Heuristic heuristic, boolean detectDouble,
			int maxDepthPermitted, int limit, Puzzle finalState, boolean isSolution, int numExpansions,
			int maxDepthReached, int queueSize, int maxQueueSize, int pathLength, String path) {
		super();
		this.startState = startState;
		this.algorithm = algorithm;
		this.heuristic = heuristic;
		this.detectDouble = detectDouble;
		this.maxDepthPermitted = maxDepthPermitted;
		this.limit = limit;
		this.finalState = finalState;
		this.isSolution = isSolution;
		this.numExpansions = numExpansions;
		this.maxDepthReached = maxDepthReached;
		this.queueSize = queueSize;
		this.maxQueueSize = maxQueueSize;
		this.pathLength = pathLength;
		this.path = path;
	}


	// Output
	@Override
	public String toString() {
		return "Puzzle-Start:" 
				+ startState
				+ "Suchalgorithmus: " + algorithm
				+ "\nHeuristik: " + (heuristic==PuzzleSolver.Heuristic.MANHATTAN ? "Manhattan" : "Anzahl falscher Kacheln")
				+ "\nErkennung wiederkehrender Zustaende: " + (detectDouble ? "Ja" : "Nein")
				+ "\nMaximale, erlaubte Suchtiefe: " + maxDepthPermitted 
				+ "\nLimit: " + limit
				+ "\nSuch-Ende:"
				+ finalState
				+ "Geloest: " + (isSolution ? "Ja" : "Nein")
				+ "\nAnzahl expandierter Knoten: " + numExpansions
				+ "\nMaximale, erreichte Suchtiefe: " + maxDepthReached
				+ "\nAktuelle Queue-Groesse: " + queueSize
				+ "\nMaximal erreichte Queue-Groesse: " + maxQueueSize
				+ "\nLaenge des Loesungspafades: " + pathLength
				+ "\nLoesungspfad: " + path;
	}
}
