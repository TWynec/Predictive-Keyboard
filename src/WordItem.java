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
	private  int count;
	private  ArrayList<Integer> atLines;
	
	public WordItem(String word, int c, int atLine) {
		this.word = word;
		this.count = c;
		this.atLines = new ArrayList<Integer>();
		atLines.add(atLine);
	}
	
	public void updateItem(int atLine) {
		this.count ++;
		this.atLines.add(atLine);
	}
	
		
	@Override
	//implement this method
	public int compareTo(Object other) {
			WordItem compared = (WordItem) other;
			return this.word.toLowerCase().compareTo(compared.word.toLowerCase());
	}
	
	//
	public String getWord() {
		return this.word;
	}
	

	//
	public int getCount() {
		return this.count;
	}
	
	//
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof WordItem) {
			WordItem wItem = (WordItem) obj;
			if (this.word == wItem.getWord()) {
				return true;
			}
			else return false;
		}
		if(obj instanceof String) {
			String wordCompare = obj.toString();
			if(this.word.equalsIgnoreCase(wordCompare)) {
				return true;
			}
			else return false;
		}

        return false;
    }
	
	@Override
	public String toString() {
		String ret = "";
		ret += word + ":" + this.count + "->" +//changed from WordItem.
				this.atLines.toString();//changed from WordItem.
		return ret;
	}
	
}//end of class
