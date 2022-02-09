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
			
			
			//Uncomment sorting methods one at a time
			
			//allWords.MergeSortAlpha();
			//allWords.MergeSortOcc();
			//System.out.println("mergeSortOcc Test Case: " + i + "\n" + allWords);
			
		}
		
		System.out.println("Test containWord: " + allWords);
		System.out.println("1. Basically " + allWords.containWord("Basically", 0));
		System.out.println("2. scanning " + allWords.containWord("scanning", 0));
		System.out.println("3. I " + allWords.containWord("I", 0));
		
		System.out.println("Test removeFirst: \n" + allWords);
		allWords.removeFirst();
		System.out.println("Test removeFirst: \n" + allWords);
		allWords.removeFirst();
		System.out.println("Test removeFirst: \n" + allWords);
		allWords.removeFirst();
		System.out.println("Test removeFirst: \n" + allWords);
		
		System.out.println("Test addFirst: \n" + allWords);
		allWords.addFirst("TestCase1");
		allWords.addFirst("TestCase2");
		allWords.addFirst("TestCase3");
		System.out.println(allWords);


		
		System.out.println("mergeSortOcc test Case: 4");
		MyLinkedList extractAll = wp.extractAll(fileName1);
		System.out.println(extractAll);

		
		//allWords.MergeSortOcc();
		//allWords.MergeSortAlpha();
		//System.out.println(allWords);
		//wp.writeToFile(allWords, "allWordsOutSort2");
		
		Queue q = new Queue();
		MyLinkedList queueTestCase1 = new MyLinkedList();
		MyLinkedList queueTestCase2 = new MyLinkedList();
		MyLinkedList queueTestCase3 = new MyLinkedList();

		System.out.println("test enqueue");
		q.enqueue(queueTestCase1);
		q.enqueue(queueTestCase2);
		q.enqueue(queueTestCase3);
		
		System.out.println("test dequeue");
		q.dequeue();
		q.dequeue();
		q.dequeue();
		
		WordItem testWord1 = new WordItem("testCase1", 0, 9999);
		WordItem testWord2 = new WordItem("testCase2", 0, 9999);
		WordItem testWord3 = new WordItem("testCase3", 0, 9999);
		
		System.out.println("testCase1 " + testWord1);
		System.out.println("testCase2 " + testWord2);
		System.out.println("testCase3 " + testWord3 + "\n");
		
		System.out.println("Compare testWord1 to testWord2: " + testWord1.compareTo(testWord2));
		System.out.println("Compare testWord2 to testWord1: " + testWord2.compareTo(testWord1));
		System.out.println("Compare testWord3 to testWord3: " + testWord3.compareTo(testWord3));

		System.out.println("testCase1 " + testWord1);
		System.out.println("testCase2 " + testWord2);
		System.out.println("testCase3 " + testWord3 + "\n");

		
		System.out.println("test updateItem");
		testWord1.updateItem(0);
		System.out.println("testCase1 " + testWord1);
		testWord2.updateItem(10);
		System.out.println("testCase2 " + testWord2);
		testWord3.updateItem(100);
		System.out.println("testCase1 " + testWord3);
		
		System.out.println("testCase1 " + testWord1);
		System.out.println("testCase2 " + testWord2);
		System.out.println("testCase3 " + testWord3 + "\n");
		
		System.out.println("Test equals on testWord1 and testWord2: " + testWord1.equals(testWord2));
		System.out.println("Test equals on testWord2 and testWord1: " + testWord2.equals(testWord1));
		System.out.println("Test equals on testWord3 and testWord3: " + testWord3.equals(testWord3));



		
	}

}
