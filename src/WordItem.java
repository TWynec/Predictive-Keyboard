import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;

/*
 * Each WordItem object represents a word extracted from the text file.
 * It has three fields. String word stores the literal English word.
 * int count records the number of occurrence for that word in text file.
 * ArrayList atLines records a list of line numbers at which the word appears in
 * the original text file. 
 * NOTE: line number in this Arraylist 'atLines' should be unique, no duplicates.
 * 
 * 
 *  
 */

public class WordItem implements Comparable {
	private String word;
	private static int count;
	private static ArrayList<Integer> atLines;
	
	public WordItem(String word, int c, int atLine) {
		this.word = word;
		WordItem.count = c;
		WordItem.atLines = new ArrayList<Integer>();
		atLines.add(atLine);
	}
	
	public static void updateItem(int atLine) {
		WordItem.count ++;
		WordItem.atLines.add(atLine);
	}
	
		
	@Override
	//implement this method
	public int compareTo(Object other) {
			return 0;
	}
	
	//
	public String getWord() {
		return this.word;
	}
	

	//
	public int getCount() {
		return WordItem.count;
	}
	
	//
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof WordItem) {
			WordItem wItem = (WordItem) obj;
			if (this.word == wItem.getWord()) {
				return true;
			}
		}

        return false;
    }
	
	@Override
	public String toString() {
		String ret = "";
		ret += word + ":" + WordItem.count + "->" +
				WordItem.atLines.toString();
		return ret;
	}
	
}//end of class
