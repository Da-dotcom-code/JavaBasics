package Graph;
import java.util.*;

public class Graph {

	List<Knoten> l;

	Graph() {
		l = new ArrayList<>();
	}

	public void addKnoten(Knoten v) {
		l.add(v);
	}

	public void addKante(Knoten v1, Knoten v2) {
		v1.getK().add(v2);
		v2.getK().add(v1);
	}

	public String print() {
		String s = "";
		for (Knoten knoten : l) {
			s += knoten.print();
			s += "\n";
		}
		return s;
	}

	public static void main(String args[]) {
		Graph g = new Graph();
		Knoten v1 = new Knoten("Laptop");
		Knoten v2 = new Knoten("Handy");
		Knoten v3 = new Knoten("PS5");
		g.addKante(v1, v2);
		g.addKante(v2, v3);
		g.addKante(v2, v3);
		g.addKnoten(v1);
		g.addKnoten(v2);
		g.addKnoten(v3);
		System.out.println(g.print());

	}
}