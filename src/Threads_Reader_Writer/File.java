package Threads_Reader_Writer;

public class File {
	int countWriteWait;
	int countWriteActive;
	int countReadWait;
	int countReadActive;
	
	
	
	public File() {
		countWriteWait = 0;
		countWriteActive = 0;
		countReadWait = 0;
		countReadActive = 0;
	}

	public void read() throws InterruptedException {
		countReadWait++;;
		beforeRead();
		countReadWait--;
		countReadActive++;
		Thread.sleep(300);
		afterRead();
		
	}
	
	public void write () throws InterruptedException {
		countWriteWait++;
		beforeWrite();
		countWriteWait--;
		countWriteActive++;
		Thread.sleep(300);
		afterWrite();
		
	}
	
	public  synchronized void beforeRead() {
		while (countWriteActive >= 0) {
			
		}
			
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void beforeWrite()  {
		while ((countReadActive >= 0) || (countReadWait >= 0) || (countReadActive >= 0 )) {
		
		}
			
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  synchronized void afterRead() {
		countReadActive--;
		notifyAll();
		
	}
	
	public synchronized void afterWrite() {
		countWriteActive--;
		notifyAll();
		
		
	}
}
