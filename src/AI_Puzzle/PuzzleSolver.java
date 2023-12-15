package AI_Puzzle;

import AI_Puzzle.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PuzzleSolver {

// GREEDY
// Auswahl naechster Knoten
	public static Puzzle selectGreedyWrongTiles(List<Puzzle> greedyList) {
		int minPos = 0;
		int countList = 0;
		int minWrongTiles = 1000;
		for (Puzzle puzzle : greedyList) {
			if (puzzle.countWrongTiles() < minWrongTiles) {
				minWrongTiles = puzzle.countWrongTiles();
				minPos = countList;
			}
			countList++;
		}
		Puzzle minPuzzle = greedyList.remove(minPos);
		return minPuzzle;
	}

	public static Puzzle selectGreedyManhattan(List<Puzzle> greedyList) {
		int minPos = 0;
		int countList = 0;
		int ManhattanSum = 1000;
		for (Puzzle puzzle : greedyList) {
			if (puzzle.countWrongTiles() < ManhattanSum) {
				ManhattanSum = puzzle.countWrongTiles();
				minPos = countList;
			}
			countList++;
		}
		Puzzle minPuzzle = greedyList.remove(minPos);
		return minPuzzle;
	}

// Suche mit Greedy	
	public static Summary greedy(Puzzle puzzle, Heuristic heuristic, boolean detectDouble, int maxDepth, int limit) {
		Puzzle startPuzzle = puzzle;
		Puzzle finalPuzzle;
		Puzzle currentPuzzle = puzzle;
		boolean solutionFound = false;
		boolean doubleDetected = false;
		int numExpansions = 0;
		int currentDepth = 0;
		int maxQueueSize = 0;
		int maxDepthReached = 0;
		int pathLength = 0;
		if (maxDepth == 0) {
			maxDepth = Integer.MAX_VALUE;
		}
		String path = "";
		List<Puzzle> greedyList = new ArrayList<>();
		List<Puzzle> visitedList = new ArrayList<>();
		greedyList.add(puzzle);
// Greedy mit Wrong Tiles (exemplarisch kommentiert)
		if (heuristic == PuzzleSolver.Heuristic.WRONG_TILES) {
			while (!solutionFound && (numExpansions < limit) && (!greedyList.isEmpty())) { // Abbruchbedingung
				currentPuzzle = selectGreedyWrongTiles(greedyList); // Waehle naechsten Knoten
				for (Puzzle visitedPuzzles : visitedList) { // Check auf besucht
					if (currentPuzzle.equals(visitedPuzzles)) {
						System.out.println("Puzzle schon besucht!");
						doubleDetected = true;
					}
				}
				if (detectDouble && doubleDetected) { // Wenn besucht und Algo doppelte Knoten erkennen soll
					doubleDetected = false;
					continue;
				}
				visitedList.add(currentPuzzle);
				currentDepth++;
				numExpansions++;
				System.out.println(currentPuzzle.toString());
				System.out.println("aktuelle Tiefe: " + currentDepth + ", " + "Ext.: " + numExpansions);
				if (currentPuzzle.equals(puzzle.goal)) { // Abfrage Zielzustand
					solutionFound = true;
					finalPuzzle = currentPuzzle;
				}
				if (currentDepth > maxDepthReached) // Abfrage maximale Tiefe
					maxDepthReached = currentDepth;
				if (currentDepth >= maxDepth) {
					currentDepth--;
					System.out.println("Tiefe erreicht => zureuck zum Vaterknoten");
					continue;
				}

				HashMap<String, Integer> selectBestMove = new HashMap<>(); // Auswahlliste fuer besten Knoten

				if (currentPuzzle.canMoveLeft()) { // Wenn links moeglich, gehe nach links
					Puzzle leftMovePuzzle = currentPuzzle.moveLeft();
					greedyList.add(leftMovePuzzle); // Fuege Puzzle der Liste hinzu
					selectBestMove.put("L", leftMovePuzzle.countWrongTiles());
				}
				if (currentPuzzle.canMoveRight()) {
					Puzzle rigthMovePuzzle = currentPuzzle.moveRight();
					greedyList.add(rigthMovePuzzle);
					selectBestMove.put("R", rigthMovePuzzle.countWrongTiles());
				}
				if (currentPuzzle.canMoveUp()) {
					Puzzle upMovePuzzle = currentPuzzle.moveUp();
					greedyList.add(upMovePuzzle);
					selectBestMove.put("U", upMovePuzzle.countWrongTiles());
				}
				if (currentPuzzle.canMoveDown()) {
					Puzzle downMovePuzzle = currentPuzzle.moveDown();
					greedyList.add(downMovePuzzle);
					selectBestMove.put("D", downMovePuzzle.countWrongTiles());
				}
				if (greedyList.size() > maxQueueSize) // Abfrage maximale Queuesize
					maxQueueSize = greedyList.size();
				String minMoveString = "";
				int minMoveInt = 10000;
				for (Map.Entry<String, Integer> entry : selectBestMove.entrySet()) { // Waehle besten Knoten fuer Pfad
					if (entry.getValue() < minMoveInt) {
						minMoveInt = entry.getValue();
						minMoveString = entry.getKey();
					}
				}
				path += minMoveString; // bester Move wird an Pfad gehaengt
			}

			finalPuzzle = currentPuzzle;
			pathLength = path.length() - 1; // letzter Move ist zu viel
			path = path.substring(0, pathLength);
			if (!solutionFound) {
				pathLength = -1;
				path = null;
			}
			Summary wrongTilesGreedySummary = new Summary(startPuzzle, "Greedy", PuzzleSolver.Heuristic.WRONG_TILES,
					detectDouble, maxDepth, limit, finalPuzzle, solutionFound, numExpansions, maxDepthReached,
					greedyList.size(), maxQueueSize, pathLength, path);
			return wrongTilesGreedySummary;
// Greedy mit Manhattan Distance
		} else
			while (!solutionFound && (numExpansions < limit) && (!greedyList.isEmpty())) {
				currentPuzzle = selectGreedyManhattan(greedyList);
				currentDepth++;
				numExpansions++;
				if (currentPuzzle.equals(puzzle.goal)) {
					solutionFound = true;
					finalPuzzle = currentPuzzle;
				}
				if (currentDepth > maxDepthReached)
					maxDepthReached = currentDepth;
				if (currentDepth >= maxDepth) {
					currentDepth--;
					continue;
				}
				HashMap<String, Integer> selectBestMove = new HashMap<>();

				if (currentPuzzle.canMoveLeft()) {
					Puzzle leftMovePuzzle = currentPuzzle.moveLeft();
					greedyList.add(leftMovePuzzle);
					selectBestMove.put("L", leftMovePuzzle.countWrongTiles());
				}
				if (currentPuzzle.canMoveRight()) {
					Puzzle rigthMovePuzzle = currentPuzzle.moveRight();
					greedyList.add(rigthMovePuzzle);
					selectBestMove.put("R", rigthMovePuzzle.countWrongTiles());
				}
				if (currentPuzzle.canMoveUp()) {
					Puzzle upMovePuzzle = currentPuzzle.moveUp();
					greedyList.add(upMovePuzzle);
					selectBestMove.put("U", upMovePuzzle.countWrongTiles());
				}
				if (currentPuzzle.canMoveDown()) {
					Puzzle downMovePuzzle = currentPuzzle.moveDown();
					greedyList.add(downMovePuzzle);
					selectBestMove.put("D", downMovePuzzle.countWrongTiles());
				}
				if (greedyList.size() > maxQueueSize)
					maxQueueSize = greedyList.size();
				String minMoveString = "";
				int minMoveInt = 10000;
				for (Map.Entry<String, Integer> entry : selectBestMove.entrySet()) {
					if (entry.getValue() < minMoveInt) {
						minMoveInt = entry.getValue();
						minMoveString = entry.getKey();
					}
				}
				path += minMoveString;
				
			}

		finalPuzzle = currentPuzzle;
		pathLength = path.length() - 1;
		path = path.substring(0, pathLength);
		if (!solutionFound) {
			pathLength = -1;
			path = null;
		}
		Summary ManhattanGreedySummary = new Summary(startPuzzle, "Greedy", PuzzleSolver.Heuristic.MANHATTAN, detectDouble,
				maxDepth, limit, finalPuzzle, solutionFound, numExpansions, maxDepthReached,
				greedyList.size(), maxQueueSize, pathLength, path);
		return ManhattanGreedySummary;

	}

//A STAR
// Auswahl naechster Knoten
	public static Puzzle selectAstarWrongTiles(List<Puzzle> AStarList) {
		int minPos = 0;
		int countList = 0;
		int costEstimation = Integer.MAX_VALUE;
		for (Puzzle puzzle : AStarList) {
			if ((puzzle.countWrongTiles() + puzzle.getPathcost()) < costEstimation) {
				costEstimation = puzzle.countWrongTiles() + puzzle.getPathcost();
				minPos = countList;
			}
			System.out.println("h: " + puzzle.countWrongTiles() + ", g: " + puzzle.getPathcost());
			countList++;
		}
		Puzzle minPuzzle = AStarList.remove(minPos);
		return minPuzzle;
	}

	public static Puzzle selectAstarManhattan(List<Puzzle> AStarList) {
		int minPos = 0;
		int countList = 0;
		int ManhattanSum = 1000;
		for (Puzzle puzzle : AStarList) {
			if (puzzle.manhattanDist() < ManhattanSum) {
				ManhattanSum = puzzle.manhattanDist();
				minPos = countList;
			}
			// System.out.println("h: "+ puzzle.manhattanDist() +", g: "+
			// puzzle.getPathcost());
			countList++;
		}
		Puzzle minPuzzle = AStarList.remove(minPos);
		return minPuzzle;
	}

// Suche mit A Star
	public static Summary AStar(Puzzle puzzle, Heuristic heuristic, boolean detectDouble, int maxDepth, int limit) {
		Puzzle startPuzzle = puzzle;
		Puzzle finalPuzzle;
		Puzzle currentPuzzle = puzzle;
		boolean solutionFound = false;
		boolean doubleDetected = false;
		int numExpansions = 0;
		int currentDepth = 0;
		int maxQueueSize = 0;
		int maxDepthReached = 0;
		int pathLength = 0;
		if (maxDepth == 0) {
			maxDepth = Integer.MAX_VALUE;
		}
		String path = "";
		List<Puzzle> AStarList = new ArrayList<>();
		List<Puzzle> visitedList = new ArrayList<>();
		AStarList.add(puzzle);
// A Star mit Wrong Tiles
		if (heuristic == PuzzleSolver.Heuristic.WRONG_TILES) {
			while (!solutionFound && (numExpansions < limit) && (!AStarList.isEmpty())) {
				System.out.println("aktuelle Listengroesse: " + AStarList.size());
				currentPuzzle = selectAstarWrongTiles(AStarList);

				System.out.println("aktuelle Pfadkosten Puzzle: " + currentPuzzle.getPathcost());
				for (Puzzle visitedPuzzles : visitedList) {
					if (currentPuzzle.equals(visitedPuzzles)) {
						System.out.println("Puzzle schon besucht!");
						doubleDetected = true;
					}
				}
				currentDepth = currentPuzzle.pathcost;
				if (currentDepth > maxDepthReached)
					maxDepthReached = currentDepth;
				if (currentDepth >= maxDepth) {
					System.out.println("Tiefe erreicht => zureuck zum Vaterknoten");
					continue;
				}
				if (detectDouble && doubleDetected) {
					doubleDetected = false;
					continue;
				}
				visitedList.add(currentPuzzle);
				numExpansions++;
				System.out.println(currentPuzzle.toString());
				System.out.println("aktuelle Tiefe: " + currentDepth + ", " + "Ext.: " + numExpansions);
				System.out.println("WrongTiles: " + currentPuzzle.countWrongTiles());
				if (currentPuzzle.equals(puzzle.goal)) {
					solutionFound = true;
					finalPuzzle = currentPuzzle;
				}

				HashMap<String, Integer> selectBestMove = new HashMap<>();

				currentPuzzle.setPathcost(currentPuzzle.getPathcost() + 1);
				if (currentPuzzle.canMoveLeft()) {
					Puzzle leftMovePuzzle = currentPuzzle.moveLeft();
					AStarList.add(leftMovePuzzle);
					leftMovePuzzle.setPathcost(leftMovePuzzle.getPathcost() + currentPuzzle.getPathcost());
					leftMovePuzzle.setPathString(currentPuzzle.getPathString() + "L");
					selectBestMove.put("L", leftMovePuzzle.countWrongTiles() + leftMovePuzzle.getPathcost());
				}
				if (currentPuzzle.canMoveRight()) {
					Puzzle rigthMovePuzzle = currentPuzzle.moveRight();
					AStarList.add(rigthMovePuzzle);
					rigthMovePuzzle.setPathcost(rigthMovePuzzle.getPathcost() + currentPuzzle.getPathcost());
					rigthMovePuzzle.setPathString(currentPuzzle.getPathString() + "R");
					selectBestMove.put("R", rigthMovePuzzle.countWrongTiles() + rigthMovePuzzle.getPathcost());
				}
				if (currentPuzzle.canMoveUp()) {
					Puzzle upMovePuzzle = currentPuzzle.moveUp();
					AStarList.add(upMovePuzzle);
					upMovePuzzle.setPathcost(upMovePuzzle.getPathcost() + currentPuzzle.getPathcost());
					upMovePuzzle.setPathString(currentPuzzle.getPathString() + "U");
					selectBestMove.put("U", upMovePuzzle.countWrongTiles() + upMovePuzzle.getPathcost());
				}
				if (currentPuzzle.canMoveDown()) {
					Puzzle downMovePuzzle = currentPuzzle.moveDown();
					AStarList.add(downMovePuzzle);
					downMovePuzzle.setPathcost(downMovePuzzle.getPathcost() + currentPuzzle.getPathcost());
					downMovePuzzle.setPathString(currentPuzzle.getPathString() + "D");
					selectBestMove.put("D", downMovePuzzle.countWrongTiles() + currentPuzzle.getPathcost());
				}
				String minMoveString = "";
				int minMoveInt = 10000;
				for (Map.Entry<String, Integer> entry : selectBestMove.entrySet()) {
					if (entry.getValue() < minMoveInt) {
						minMoveInt = entry.getValue();
						minMoveString = entry.getKey();
					}
				}

				path += minMoveString;
				if (AStarList.size() > maxQueueSize)
					maxQueueSize = AStarList.size();
				System.out.println("-------------------");
			}

			finalPuzzle = currentPuzzle;
			path = finalPuzzle.getPathString();
			pathLength = path.length();
			if (!solutionFound) {
				pathLength = -1;
				path = null;
			}
			Summary wrongTilesAstarSummary = new Summary(startPuzzle, "AStar", PuzzleSolver.Heuristic.WRONG_TILES,
					detectDouble, maxDepth, limit, finalPuzzle, solutionFound, numExpansions, maxDepthReached,
					AStarList.size(), maxQueueSize, pathLength, path);
			return wrongTilesAstarSummary;
		}

// A Star mit Manhattan Distance
		else {

			while (!solutionFound && (numExpansions < limit) && (!AStarList.isEmpty())) {
				System.out.println("aktuelle Listengroesse: " + AStarList.size());
				currentPuzzle = selectAstarManhattan(AStarList);

				System.out.println("aktuelle Pfadkosten Puzzle: " + currentPuzzle.getPathcost());
				for (Puzzle visitedPuzzles : visitedList) {
					if (currentPuzzle.equals(visitedPuzzles)) {
						System.out.println("Puzzle schon besucht!");
						doubleDetected = true;
					}
				}
				currentDepth = currentPuzzle.pathcost;
				if (currentDepth > maxDepthReached)
					maxDepthReached = currentDepth;
				if (currentDepth >= maxDepth) {
					System.out.println("Tiefe erreicht => zureuck zum Vaterknoten");
					continue;
				}
				if (detectDouble && doubleDetected) {
					doubleDetected = false;
					continue;
				}
				visitedList.add(currentPuzzle);
				numExpansions++;
				System.out.println(currentPuzzle.toString());
				System.out.println("aktuelle Tiefe: " + currentDepth + ", " + "Ext.: " + numExpansions);
				System.out.println("ManhattanDist: " + currentPuzzle.manhattanDist());
				if (currentPuzzle.equals(puzzle.goal)) {
					solutionFound = true;
					finalPuzzle = currentPuzzle;
				}

				HashMap<String, Integer> selectBestMove = new HashMap<>();

				currentPuzzle.setPathcost(currentPuzzle.getPathcost() + 1);
				if (currentPuzzle.canMoveLeft()) {
					Puzzle leftMovePuzzle = currentPuzzle.moveLeft();
					AStarList.add(leftMovePuzzle);
					leftMovePuzzle.setPathcost(leftMovePuzzle.getPathcost() + currentPuzzle.getPathcost());
					leftMovePuzzle.setPathString(currentPuzzle.getPathString() + "L");
					selectBestMove.put("L", leftMovePuzzle.manhattanDist() + leftMovePuzzle.getPathcost());
				}
				if (currentPuzzle.canMoveRight()) {
					Puzzle rigthMovePuzzle = currentPuzzle.moveRight();
					AStarList.add(rigthMovePuzzle);
					rigthMovePuzzle.setPathcost(rigthMovePuzzle.getPathcost() + currentPuzzle.getPathcost());
					rigthMovePuzzle.setPathString(currentPuzzle.getPathString() + "R");
					selectBestMove.put("R", rigthMovePuzzle.manhattanDist() + rigthMovePuzzle.getPathcost());
				}
				if (currentPuzzle.canMoveUp()) {
					Puzzle upMovePuzzle = currentPuzzle.moveUp();
					AStarList.add(upMovePuzzle);
					upMovePuzzle.setPathcost(upMovePuzzle.getPathcost() + currentPuzzle.getPathcost());
					upMovePuzzle.setPathString(currentPuzzle.getPathString() + "U");
					selectBestMove.put("U", upMovePuzzle.manhattanDist() + upMovePuzzle.getPathcost());
				}
				if (currentPuzzle.canMoveDown()) {
					Puzzle downMovePuzzle = currentPuzzle.moveDown();
					AStarList.add(downMovePuzzle);
					downMovePuzzle.setPathcost(downMovePuzzle.getPathcost() + currentPuzzle.getPathcost());
					downMovePuzzle.setPathString(currentPuzzle.getPathString() + "D");
					selectBestMove.put("D", downMovePuzzle.manhattanDist() + currentPuzzle.getPathcost());
				}
				String minMoveString = "";
				int minMoveInt = 10000;
				for (Map.Entry<String, Integer> entry : selectBestMove.entrySet()) {
					if (entry.getValue() < minMoveInt) {
						minMoveInt = entry.getValue();
						minMoveString = entry.getKey();
					}
				}
				//currentPuzzle.setPathString(currentPuzzle.getPathString() + minMoveString);
				//path += minMoveString;
				
				if (AStarList.size() > maxQueueSize)
					maxQueueSize = AStarList.size();
				System.out.println("-------------------");
			}

			finalPuzzle = currentPuzzle;
			path = finalPuzzle.getPathString();
			pathLength = path.length();
			if (!solutionFound) {
				pathLength = -1;
				path = null;
			}
			
			Summary manhattanSummary = new Summary(startPuzzle, "AStar", PuzzleSolver.Heuristic.MANHATTAN, detectDouble,
					maxDepth, limit, finalPuzzle, solutionFound, numExpansions, maxDepthReached,
					AStarList.size(), maxQueueSize, pathLength, path);
			return manhattanSummary;

		}
	}

	// hieran bitte nichts veraendern
	public enum Heuristic {
		WRONG_TILES, MANHATTAN
	}
}
