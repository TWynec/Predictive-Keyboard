import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;


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
	
	public MyLinkedList extractLine(String aline, int lineNumber, MyLinkedList totalList) {

		boolean inWord = false;
		MyLinkedList wordList = new MyLinkedList();
		int i = 0;
		int wordLen = 0;
		int start = 0;
		
		while(i < aline.length()) {
 			if(Character.isLetter(aline.charAt(i))) {
				if(inWord == false) {
					start = i;
					inWord = true;
				}
				wordLen++;
			}
 			
			else if(inWord == true) {
				String newWord = aline.substring(start, start + wordLen);
				
				if(newWord.length() > 1 || Character.toUpperCase(newWord.charAt(0)) == 'A' || Character.toUpperCase(newWord.charAt(0)) == 'I') {
					if(totalList.containWord(newWord, lineNumber) == false) {
						WordItem object = new WordItem(newWord, 1, lineNumber);
						totalList.addOrdered(object);
												
					}

					
				}
				
				wordLen = 0;
				inWord = false;
				
			}
			i++;
				
		}
		
		if(inWord == true) {
			String newWord = aline.substring(start, start + wordLen);
			
			if(newWord.length() > 1 || Character.toUpperCase(newWord.charAt(0)) == 'A' || Character.toUpperCase(newWord.charAt(0)) == 'I') {
				if(totalList.containWord(newWord, lineNumber) == false) {
					WordItem object = new WordItem(newWord, 1, lineNumber);
					totalList.addOrdered(object);
										
				}
				
			}
		}
	
		return wordList;
	
	}//end of extract
	
	public MyLinkedList extractAll(String fileName) throws IOException {
		int i = 0; //i is the total number of lines.
		int cur = 0; //cur is the current line being read.
		FileReader fileReader = new FileReader(fileName);//these lines will scan the file to find the total number of lines, "i", then use that as the incrementer for the repetitive calling of the extractLine method.
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while (bufferedReader.readLine() != null) i++;
		fileReader.close();

		MyLinkedList allLinesList = new MyLinkedList(); //Final linked list, will link end of first line to the first of the last line.

		ArrayList<String> x = fileRead(fileName);//copied from tester, just converting ArrayList to a String Array.
		String[] str = new String[x.size()];

		while (cur != i) {

			str[0] = x.get(cur);
			MyLinkedList listLine = extractLine(str[0], cur, allLinesList); // added a second parameter to extractLine, it needs the current line number now
			//allLinesList.combine(listLine);
			cur++;
		}

		return allLinesList;
		
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
	

