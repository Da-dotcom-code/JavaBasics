package Threads_Consumer_Producer;

public class Producer extends Thread{

	Stack stack;
	
	public Producer(Stack stack) {
		this.stack = stack;
	}
	
	public void run() {
		for (int i = 0; i < 1000; i++) {
			this.stack.push(1);
		}
	}

}
