package Threads_Consumer_Producer;

//Schreiben Sie ein Programm in Java, welches mit Threads einen Stack befüllt und
//diesen wieder entleert. Nutzen Sie Semaphore, um die Aufgabe zu realisieren.


public class Main {
	
	public static final int THREAD_AMOUNT = 10;
	
	public static void main (String args[]) throws InterruptedException {
		Stack stack = new Stack(1000);
		
		Producer producer [] = new Producer [THREAD_AMOUNT];
		Consumer consumer [] = new Consumer [THREAD_AMOUNT];
				
		for (int i = 0; i < THREAD_AMOUNT; i++) {
			producer[i] = new Producer(stack);
			consumer[i]= new Consumer(stack);
		}
		
		for (int i = 0; i < THREAD_AMOUNT; i++) {
			producer[i].start();
			consumer[i].start();
		}
	
		for (int i = 0; i < THREAD_AMOUNT; i++) {
			producer[i].join();
			consumer[i].join();
		}
		
		for (int i = 0; i < THREAD_AMOUNT; i++) {
			System.out.println("Consumer " + i + " hat einen Count von: " + consumer[i].getCount());
		}
	}
}
