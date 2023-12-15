package Stack;


public class Stack {

	Elem top;
	private int elemCount = 0;

	public void enStack(Elem newElem) { // push
		newElem.setNextElem(top);
		top = newElem;
		elemCount++;
	}
	
	public Elem deStack() { // pop
		if (top == null) {
			return null;

		}
		Elem tempElem = top;
		top = top.getNextElem(); 
		elemCount--;
		return tempElem;
	}
	
	public Elem peek() {
		return top;
//		Elem toPeek = deStack();
//		enStack(toPeek);
//		return toPeek;
	}
	
	
	public void append(Object[] newData) {
		for (int i = 0; i < newData.length; i++) {
			Elem tempElem = new Elem(newData[i]);
			enStack(tempElem);
		}
	}

	public int size() {
		return elemCount;
	}

	public Object elementAt(int position) {
		if (position >= size() || position < 0) {
			return -1;
		}
		int currentPos = size()-1;
		Elem tempElem = top;
		while (position != currentPos) {
			tempElem = tempElem.getNextElem();
			currentPos--;
		}
		return tempElem.getWert();
	}

	public int contains(Object toFind) {
		Elem tempElem = top;
		int count = size()-1;
		while (tempElem != null) {
			if (tempElem.getWert().equals(toFind)) {
				return count;
			}
			tempElem = tempElem.getNextElem();
			count--;
		}
		return -1;
	}

	
	public static Stack concat(Stack q, Stack p) {
		Stack rStack = new Stack();
		while (q.size() > 0)
			rStack.enStack(q.deStack());
		while (p.size() > 0)
			rStack.enStack(p.deStack());
		return rStack;
	}

	public Object[] toArray() {
		Object[] myArray = new Object[size()];
		int count = 0;
		while (size() > 0) {
			Elem tempElem = deStack();
			myArray[count] = tempElem.getWert();
			count++;
		}
		return myArray;
	}

	@Override
	public String toString() {
		Elem pos = top;
		String str = "";
		while (pos != null) {
			str += pos.getWert().toString() + " ";
			pos = pos.getNextElem();
		}
		return str;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack Stack = new Stack();
		Elem aElem = new Elem("Apfel");
		Elem bElem = new Elem("Birne");
		Elem cElem = new Elem("Kirsche");
		Stack.enStack(aElem);
		Stack.enStack(bElem);
		Stack.enStack(cElem);
		System.out.println(Stack.size());
		Elem peekedElem = Stack.peek();
		System.out.println(peekedElem.getWert());
		System.out.println(Stack.size());
		
		System.out.println("Element an Stelle 2: " + Stack.elementAt(2));
		System.out.println("Wo ist der Apfel? " + Stack.contains("Apfel"));
		System.out.println(Stack.toString());
		
		Object toAppend [] = {"Melone", "Himbeere"};
		Stack.append(toAppend);
		System.out.println(Stack.size());
		
		Elem peekedElem3 = Stack.peek();
		System.out.println(peekedElem3.getWert());
		
		System.out.println(Stack.toString());
		Object myArrObject [] = Stack.toArray();
		
		for (int i = 0; i < myArrObject.length; i++) {
		System.out.println((String) myArrObject[i]);
		}
		
	}

}
