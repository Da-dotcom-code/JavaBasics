package Threads_Consumer_Producer;

public class Semaphore {

	private int counter;

	
	public Semaphore(int counter) {
		this.counter = counter;
	}
	
	public Semaphore() {
		counter = 1;
	}
	
	public void p() {
		while (counter == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		counter--;
	}
	
	public void v() {
		counter++;
		notify();
	}
}