import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class Tester {

	public static void main(String[] args) throws IOException {

		String fileName1="files/testfile1";
		String fileName2="files/testfile2";
		WordProcessor wp = new WordProcessor();

		//MyLinkedList allWords = wp.extractAll(fileName1);
		
		
		//Test ExtractLine
		MyLinkedList allWords = new MyLinkedList();
		ArrayList<String> x = wp.fileRead(fileName1);
        String[] str = new String[x.size()];
		
		for(int i = 0; i < 3; i++) {
	        str[i] = x.get(i);
			MyLinkedList testExtractLine = wp.extractLine(str[i], i, allWords);
			allWords.MergeSortAlpha();
			System.out.println("ExtractLine test case: " + i + "\n" + allWords);
		}
		
		//allWords.MergeSortOcc();
		//allWords.MergeSortAlpha();
		//System.out.println(allWords);
		//wp.writeToFile(allWords, "allWordsOutSort2");


		
	}

}
