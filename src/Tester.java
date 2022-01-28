import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class Tester {

	public static void main(String[] args) throws IOException {

		String fileName1="files/testfile1";
		String fileName2="files/testfile2";
		WordProcessor wp = new WordProcessor();

		MyLinkedList allWords = wp.extractAll(fileName1);
		
		
		//USED TO TEST EXTRACTLINE
		ArrayList<String> x = wp.fileRead(fileName1);
        String[] str = new String[x.size()];
        
        // scans each line individually from testfile 1
		for(int i = 0; i < x.size(); i++) {
	        str[i] = x.get(i);
			/*MyLinkedList testExtractLine = wp.extractLine(str[i], i);
			System.out.println(testExtractLine);
			wp.writeToFile(testExtractLine, "TestTest");*/
		}

		MyLinkedList testExtractLine = wp.extractLine(str[0], 0);
		System.out.println(testExtractLine);
		wp.writeToFile(testExtractLine, "TestExtractOneLine");
		
		

		System.out.println("SPACE");
		System.out.println(allWords);
		
		/*wp.writeToFile(allWords, "outSort1");
		//allWords.sortListOcc(); //sort the list according to word occurrence, you have to implement it.
		wp.writeToFile(allWords,"outSort2");
		
		System.out.println(allWords);

		WordItem wItem = new WordItem("car",1,1);//making two wordItems, testing if equals works, it does.
		WordItem wItem1 = new WordItem("cas",1,1);
		System.out.println(wItem.equals(wItem1));*/

		
		
	}

}
