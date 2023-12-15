package Graph;
import java.util.ArrayList;
import java.util.List;

public class Knoten {
	public String nameString;
	List<Knoten> k;

	public Knoten(String s) {
		nameString = s;
		k = new ArrayList<>();
	}

	public List<Knoten> getK() {
		return k;
	}

	public void setK(List<Knoten> k) {
		this.k = k;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String print() {
		String s = "";
		s += "Knoten " + this.getNameString() + " hat Kante zu folgenden Knoten: ";
		for (Knoten knoten : k) {

			s += knoten.getNameString();
			s += " ";

		}
		return s;
	}
}
