package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Streams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//Klasse Input-Outputstreams für Bytes
//Klasse Reader/Writer für Zeichen(Chars)
		File infile = new File("C:\\Users\\david\\Downloads\\instream.txt");
		File outfile = new File("C:\\Users\\david\\Downloads\\outstream.txt");
		Streams.inFileStream(infile, outfile);
	}

	public static void inFileStream(File in, File out) {
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			inStream = new FileInputStream(in);
			outStream = new FileOutputStream(out);
			int input;
			while ((input = inStream.read()) != -1) { // da das Ende der Datei mit -1 gekennzeichnet / read gibt bytes zurück
				outStream.write(input);

			}
		}

		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

