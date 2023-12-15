package Threads_Reader_Writer;

public class ReaderThread extends Thread{
	File file;
	
	public ReaderThread(File file) {
		this.file = file;
	}
	
	public void run() {
		try {
			file.read();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
