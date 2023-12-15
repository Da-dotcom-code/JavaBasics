package Threads_Consumer_Producer;

public class Stack {

	private int [] stack;
	private int next;
	private Semaphore semaphore_sync; //Funktion: Schutz des kritischen Abschnittes; nur ein Thread darf eintreten
	private Semaphore semaphore_leer; //Funktion: Achtet darauf, dass Stack nicht leer ist, wenn konsumiert wird (bzw. bei pop)
	private Semaphore semaphore_voll; //Funktion: Achtet darauf, dass Stack nicht voll wird, wenn produziert wird (bzw. bei push)
	
	public Stack (int size) {
		stack = new int[size];
		next = 0; // Starte an Position 0
		semaphore_sync = new Semaphore(1);
		semaphore_leer = new Semaphore(0);
		semaphore_voll = new Semaphore(size);
	}
	
	public void push (int item) {
		semaphore_voll.p();
		semaphore_sync.p();
		stack[next] = item;
		next++;
		semaphore_sync.v();
		semaphore_leer.v();
	}
	
	public int pop () {
		semaphore_leer.p();
		semaphore_sync.p();
		int item = stack[next];
		next--;
		semaphore_sync.v();
		semaphore_voll.v();
		return item;
	}
	
}
