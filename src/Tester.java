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
			//allWords.MergeSortAlpha();
			//allWords.MergeSortOcc();
			//System.out.println("mergeSortOcc Test Case: " + i + "\n" + allWords);
		}
		
		/*System.out.println("Test containWord: " + allWords);
		System.out.println("1. Basically " + allWords.containWord("Basically", 0));
		System.out.println("2. scanning " + allWords.containWord("scanning", 0));
		System.out.println("3. I " + allWords.containWord("I", 0));*/
		
		/*System.out.println("Test removeFirst: \n" + allWords);
		allWords.removeFirst();
		System.out.println("Test removeFirst: \n" + allWords);
		allWords.removeFirst();
		System.out.println("Test removeFirst: \n" + allWords);
		allWords.removeFirst();
		System.out.println("Test removeFirst: \n" + allWords);*/
		
		/*System.out.println("Test addFirst: \n" + allWords);
		allWords.addFirst("TestCase1");
		allWords.addFirst("TestCase2");
		allWords.addFirst("TestCase3");
		System.out.println(allWords);*/


		
		/*System.out.println("mergeSortOcc test Case: 4");
		MyLinkedList extractAll = wp.extractAll(fileName1);
		System.out.println(extractAll);*/

		
		//allWords.MergeSortOcc();
		//allWords.MergeSortAlpha();
		//System.out.println(allWords);
		//wp.writeToFile(allWords, "allWordsOutSort2");


		
	}

}
