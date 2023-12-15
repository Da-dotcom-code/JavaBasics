package Threads_Reader_Writer;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		int number = 1;
		
		File file = new File();

		WriterThread[] myWriterThread = new WriterThread[number];
		ReaderThread[] myReaderThread = new ReaderThread[number];

		for (int i = 0; i < number; i++) {
			myWriterThread[i] = new WriterThread(file);
			myReaderThread[i] = new ReaderThread(file);
		}
		for (int i = 0; i < number; i++) {
			myWriterThread[i].start();
			myReaderThread[i].start();
		}
		for (int i = 0; i < number; i++) {
			myWriterThread[i].join();
			myReaderThread[i].join();

		}
		System.out.println("exit");
	}
}
