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
		
		ArrayList<String> x = wp.fileRead(fileName1);
        String[] str = new String[x.size()];

		str[0] = x.get(1);
		MyLinkedList testtest = wp.extractLine(str[0]);
		System.out.println(testtest);
		System.out.println("SPACE");
		System.out.println(allWords);

		
		//wp.writeToFile(allWords, "outSort1");
		//allWords.sortListOcc(); //sort the list according to word occurrence, you have to implement it.
		//wp.writeToFile(allWords,"outSort2");
		
		//System.out.println(allWords);
		//System.out.println(testtest.toString());

		//WordItem wItem = new WordItem("car",1,1);//making two wordItems, testing if equals works, it does.
		//WordItem wItem1 = new WordItem("cas",1,1);
		//System.out.println(wItem.equals(wItem1));
		
		
	}

}
