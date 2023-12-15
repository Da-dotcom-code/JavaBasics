package AI_Puzzle;

public class Puzzle implements PuzzleInterface {
	// gesuchter Zielzustand
	public static Puzzle goal = new Puzzle(new int[][] {
			{1, 2, 3},
			{8, 0, 4},
			{7, 6, 5}
	});

	public int[][] state;
	
	public int pathcost;
	
	public String pathString ="";

	public String getPathString() {
		return pathString;
	}

	public void setPathString(String pathString) {
		this.pathString = pathString;
	}

	public int getPathcost() {
		return pathcost;
	}

	public void setPathcost(int pathcost) {
		this.pathcost = pathcost;
	}

	// initialisiere Puzzle mit gegebenen Werten
	public Puzzle (int a, int b, int c, int d, int e, int f, int g, int h, int i) {
		this.state = new int[][]{
			{a, b, c},
			{d, e, f},
			{g, h, i}
		};
	}

	// initialisiere Puzzle mit 2D-Array
	public Puzzle (int[][] state) {
		this.state = state;
	}
	
	// Zaehlung der falsch platzierten Kacheln 1 bis 8
	@Override
	public int countWrongTiles() {
		int countWrongTiles = 0;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (state [i][j] != goal.state[i][j] ) {
					countWrongTiles++;
				}
			}
		}
		return countWrongTiles;
	}
	
	// Berechnung der Summe aller (vertikalen und horizontalen) Distanzen der Kacheln 1 bis 8 zur jeweiligen Zielposition
	
	
	
	public int[] searchDist(int number) {
		int distVector [] = new int[2];
		for (int i = 0; i < goal.state.length; i++) {
			for (int j = 0; j < goal.state[i].length; j++) {
				if (number == goal.state[i][j]) {
					distVector[0] = i;
					distVector[1] = j;
				}
			}
		}
		return distVector;
	}
	@Override
	public int manhattanDist() {
		int countDist = 0;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				int currentDist = 0;
				if (state[i][j] != goal.state[i][j] && state[i][j] != 0 ) { //leere Kachel wird nicht mitgezaehlt
					int [] distVector = searchDist(state[i][j]);
					currentDist = Math.abs(distVector[0] - i ) + Math.abs(distVector[1] -j);
				}
				countDist += currentDist;	
			}
		}
		return countDist;
	}

	
	
	public int [] [] copyState() {
		int [] [] newState = new int [3] [3];
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				newState[i][j] = state[i][j];
			}
		}
		return newState;
	}
	
	public boolean canMoveLeft() {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (state[i][j] == 0) 
					if (j > 0) return true;
			}
		}
	return false;
	}
	
	
	public boolean canMoveRight() {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (state[i][j] == 0) 
					if (j < 2) return true;
			}
		}
	return false;
	}
	
	@Override
	public boolean canMoveUp() {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (state[i][j] == 0) 
					if (i > 0) return true;
			}
		}
	return false;
	}
	
	@Override
	public boolean canMoveDown() {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (state[i][j] == 0) 
					if (i < 2) return true;
			}
		}
	return false;
	}
	
	@Override
	public Puzzle moveLeft() {
		int [] [] newState = copyState();
		boolean done = false;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (!done) {
				if (newState[i][j] == 0 ) {
					newState[i][j] = newState[i][j-1];
					newState[i][j-1] = 0;
					done = true;
				}
				}
			}
		}
		Puzzle puzzle = new Puzzle(newState);
		return puzzle;
	}
	
	public Puzzle moveRight() {
		int [] [] newState = copyState();
		boolean done = false;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (!done) {
				if (newState[i][j] == 0 ) {
					newState[i][j] = newState[i][j+1];
					newState[i][j+1] = 0;
					done = true;
				}
				}
			}
		}
		Puzzle puzzle = new Puzzle(newState);
		return puzzle;
	}
	
	@Override
	public Puzzle moveUp() {
		int [] [] newState = copyState();
		boolean done = false;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (!done) {
				if (newState[i][j] == 0 ) {
					newState[i][j] = newState[i-1][j];
					newState[i-1][j] = 0;
					done = true;
				}
				}
			}
		}
		Puzzle puzzle = new Puzzle(newState);
		return puzzle;
	}
	
	@Override
	public Puzzle moveDown() {
		int [] [] newState = copyState();
		boolean done = false;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (done != true) {
				if (newState[i][j] == 0 ) {
					newState[i][j] = newState[i+1][j];
					newState[i+1][j] = 0;
					done = true;
				}
				}
			}
		}
		Puzzle puzzle = new Puzzle(newState);
		return puzzle;
	}

	@Override
	public boolean equals(Puzzle p) {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (p.state[i][j]!= state[i][j]) return false;
			}
		}
	return true;
	}

	// Ausgabe des Zustands als String
	@Override
	public String toString() {
		String str = "\n";
		for(int r=0; r<3; r++) {
			str += "[";
			for(int c=0; c<3; c++) {
				str += state[r][c];
				if(c<2) str += ", ";
			}
			str += "]\n";
		}
		return str;
	}
}