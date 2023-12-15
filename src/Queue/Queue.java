package Queue;

// Wenn Modifikatoren auf private sind, dann gehen nur getter und setter-Zugriffe
// Wenn nicht definiert, dann protected
// private, wenn man es nur in der Klasse selbst verwenden darf

public class Queue {

	Elem first;
	Elem last;

	public void enQueue(Elem newElem) {
		if (first == null) {
			first = newElem;	
		}
		else last.setNextElem(newElem);
		last = newElem;
	}
	
	public Elem deQueue() {
		if (first == null) {
			return null;

		}
		Elem tempElem = first;
		first = first.getNextElem();
		if (first == null)
			last = null;
		return tempElem;
	}

	public void append(Object[] newData) {
		for (int i = 0; i < newData.length; i++) {
			Elem tempElem = new Elem();
			tempElem.setWert(newData[i]); 
			last.setNextElem(tempElem); 
			last = last.getNextElem();
		}
	}
	
	public int size() {
		if (first == null) return 0;
		Elem temp = first;
		int count = 1;
		while (temp.getNextElem() != null){
			temp = temp.getNextElem();
			count ++;
		}
		return count;
	}
	
	
	  public Object elementAt(int position) {
		  if (position >= size() || position < 0) {
			  return -1;
		  }
		  Elem tempElem = first;
		  while (position != 0) {
			  tempElem = tempElem.getNextElem();
			  position --;
		  }
		  return tempElem.getWert();
	  }
  
	  public int contains(Object toFind) {
		  Elem tempElem = first;
		  int count = 0;
		  while (tempElem != null) {
			  if (tempElem.getWert().equals(toFind)) {
				  return count;
			  }
			  tempElem = tempElem.getNextElem();
			  count++;
		  }
		  return -1;
	  }
	  
	public static Queue concat(Queue q, Queue p) {
		Queue rQueue = new Queue();
		while (q.size() > 0) rQueue.enQueue(q.deQueue());
		while (p.size() > 0) rQueue.enQueue(p.deQueue());
		return rQueue;
	}
	  
	  public Object[] toArray() {
		  Object [] myArray = new Object[size()];
		  int count = 0;
		  while (size() > 0) {
			  Elem tempElem = deQueue();
			  myArray[count] = tempElem.getWert();
			  count ++;
		  }
		  return myArray;	  
	  }
	 

	@Override
	public String toString() {
		Elem pos = first;
		String str = "";
		while (pos != null) {
			str += pos.getWert().toString() + " ";
			pos = pos.getNextElem();
		}
		return str;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue queue = new Queue();
		Elem aElem = new Elem("Apfel");
		Elem bElem = new Elem("Birne");
		Elem cElem = new Elem("Kirsche");
		queue.enQueue(aElem);
		queue.enQueue(bElem);
		queue.enQueue(cElem);
		System.out.println("Element an Stelle 2: " + queue.elementAt(2));
		System.out.println("Wo ist die Kirsche? " + queue.contains("Kirsche"));
		System.out.println(queue.toString());
		
		Object toAppend [] = {"Melone", "Himbeere"};
		queue.append(toAppend);
		
		System.out.println(queue.toString());
		
		Object myArrObject [] = queue.toArray();
		
		for (int i = 0; i < myArrObject.length; i++) {
			System.out.println((String) myArrObject[i]);
		}
		
	}

}
