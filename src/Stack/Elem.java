package Stack;



public class Elem {
	private Object wert;
	private Elem nextElem;
	
	
	public Elem() {
	}
	
	public Elem(Object wert) {
		this.wert = wert;
	}


	public Elem(Object wert, Elem nextElem) {
		this.wert = wert;
		this.nextElem = nextElem;
	}

	public Object getWert() {
		return wert;
	}

	public void setWert(Object wert) {
		this.wert = wert;
	}

	public Elem getNextElem() {
		return nextElem;
	}

	public void setNextElem(Elem nextElem) {
		this.nextElem = nextElem;
	}
	
	@Override public String toString () { 
		return wert.toString (); 
	 }
	
}

