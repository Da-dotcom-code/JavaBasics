package Threads_Consumer_Producer;

public class Consumer extends Thread {
	Stack stack;
	private int count;
	
	public Consumer(Stack stack) {
		this.stack = stack;
	}
	
	public void run() {
		for (int i = 0; i < 1000; i++) {
			count += this.stack.pop();
		}
	}
	
	public int getCount() {
		return count;
	}

}