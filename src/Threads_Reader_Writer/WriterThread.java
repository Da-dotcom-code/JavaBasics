package Threads_Reader_Writer;

public class WriterThread extends Thread {
	File file;
	public WriterThread(File file) {
		this.file = file;
	}
	
	public void run() {
		try {
			file.write();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
