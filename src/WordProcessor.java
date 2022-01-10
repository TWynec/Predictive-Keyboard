import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


/*
 *The WordProcessor class would extract words from the raw text file(a.k.a tokenization).
 *After extracted one word, it either creates a new WordItem object and insert
 *the object into LinkedLis at a proper location, or it calls a method in MyLinkedList to increment 
 *the word occurrence and to update line-number list if a word has already been existing.
 *
 *The class also provides File I/O methods. Write the resultant string or list back to a file.
 *
 */

public class WordProcessor {

	private final String fileName="files/testfile1";
	
	//give you a taste about how to do fileIO
	public ArrayList<String> fileRead(String name) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		
		FileReader fileReader = new FileReader(name);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String aline = null;
        //read in the rest of rows
        //readLine() returns striped string, that discards any line-termination chars
        while ((aline = bufferedReader.readLine()) != null) {
        	aline = aline.trim();
        	if(aline.length() > 0)
        		lines.add(aline); //skip empty lines
        }
        fileReader.close();
		return lines;
	}
	
	public MyLinkedList extractLine(String aline) {

	}//end of extract
	
	public MyLinkedList extractAll(String fileName) throws IOException {
		
	}
	
	public void writeToFile(MyLinkedList alist, String fileName) {
		FileWriter fileWriter = null;
        try {
            String content = alist.toString();
            File newTextFile = new File(fileName);
            fileWriter = new FileWriter(newTextFile);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}//
	
}//end of class
	

