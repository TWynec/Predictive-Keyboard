import java.util.*;

public class Trie2 {
	
	private class TrieNode {
		Map<Character, TrieNode> children = new TreeMap<>();//TreeMap is java build-in structure, 
		boolean aword = false;		//Basically it acts like a Hashtable or Hashmap, establishing a mapping between Key and Value
		                                //Unlike hash table, keys in TreeMap are sorted!
		int freq = 0;
	}
	
	private TrieNode root;
	private WordItem dict[];
	public Trie2(WordItem d[]) {
		this.root = new TrieNode();
		this.dict = d;
	}

	public void insertString(String s, int f) {
		insertString(root, s, f);
	}

	public void setRoot(TrieNode nRoot){this.root = nRoot;}//set new root to trim down the trie.
	public TrieNode getRoot(){return this.root;}//get the root of the trie.
	
	private void insertString(TrieNode root, String s, int f) {
		TrieNode cur = root;
		for (char ch : s.toCharArray()) {
			TrieNode next = cur.children.get(ch);
			if (next == null)
				cur.children.put(ch, next = new TrieNode());
			cur = next;
		}
		cur.aword = true;
		cur.freq = f;
	}

	public void printSorted() {
		printSorted(root, "");
	}

	private void printSorted(TrieNode node, String s) {
		if (node.aword) {
			System.out.println(s);
		}
		for (Character ch : node.children.keySet()) {
			printSorted(node.children.get(ch), s + ch);
		}
	}

	private void printSorted(TrieNode node, String s, MyLinkedList mylist) {  //the helper
		if (node.aword) {
			mylist.add(s); ///System.out.println(s);  //for example, instead of print out the word, you have to add the word s into a linkedlist object you have defined or passed in.
		}
		for (Character ch : node.children.keySet()) {
			printSorted(node.children.get(ch), s + ch, mylist);
		}
	}

	private void newprintSorted(TrieNode node, String s, MyLinkedList mylist) {
		for (Character ch : node.children.keySet()) {
			newprintSorted(node.children.get(ch), s + ch, mylist);
		}

		int count = 0;

		if (node.aword && count <= 8) {
			WordItem newWord = new WordItem(s, node.freq);
			mylist.add(newWord);
		}
	}

	public ArrayList<String> mostFreqWordsNull(String prefix) {
		ArrayList<String> wordList = new ArrayList<String>();
		ArrayList<Integer> freqList = new ArrayList<Integer>();

		if(prefix == null)
			return wordList;

		//find the root of the subtree
		TrieNode sub = findRoot(this.root, prefix);

		if(sub == null) {
			return wordList;
		}
		mostFreqWordsNull(sub, prefix, wordList, freqList);
		return wordList;
	}

	 private void mostFreqWordsNull(TrieNode node, String prefix, ArrayList<String> wordList,
                           ArrayList<Integer> freqList) {
        for (Character ch : node.children.keySet()) {
			mostFreqWordsNull(node.children.get(ch), prefix + ch, wordList, freqList);
        }
        int insertPos = insertSpot(freqList, node.freq);

        if (node.aword && insertPos <= 9 - 1) {
            wordList.add(insertPos, prefix);
            freqList.add(insertPos, node.freq);

            if(wordList.size() > 9) {
                wordList.remove(wordList.size() - 1);
                freqList.remove(freqList.size() - 1);
            }
        }
    }

	public ArrayList<String> mostFreqWords(String prefix, WordItem leastFreq) {
		ArrayList<String> wordList = new ArrayList<String>();
		ArrayList<Integer> freqList = new ArrayList<Integer>();

		if(prefix == null)
			return wordList;

		//find the root of the subtree
		TrieNode sub = findRoot(this.root, prefix);

		if(sub == null) {
			return wordList;
		}
		mostFreqWords(sub, prefix, wordList, freqList, leastFreq);
		return wordList;
	}

	private void mostFreqWords(TrieNode node, String prefix, ArrayList<String> wordList,
							ArrayList<Integer> freqList, WordItem leastFreq) {
		for (Character ch : node.children.keySet()) {
			mostFreqWords(node.children.get(ch), prefix + ch, wordList, freqList, leastFreq);
		}
		int insertPos = insertSpot(freqList, node.freq);

		//update leastFreq,
		if(node.aword && node.freq < leastFreq.getCount()) {
			leastFreq.setCount(node.freq);
			leastFreq.setWord(prefix);
		}

		if (node.aword && insertPos <= 9 - 1) {
			wordList.add(insertPos, prefix);
			freqList.add(insertPos, node.freq);

			//if wordList is too large, remove
			if(wordList.size() > 9) {
				wordList.remove(wordList.size() - 1);
				freqList.remove(freqList.size() - 1);
			}
		}
	}

	public boolean delWord(String delWord) {
		return delWord(root, delWord);
	}

	private boolean delWord(TrieNode node, String delWord) {

		LinkedList<TrieNode> nodeList = new LinkedList<TrieNode>();
		TrieNode parent = node;
		int i = delWord.length() - 1;

		for (char ch : delWord.toCharArray()) {
			nodeList.push(parent);
			TrieNode next = parent.children.get(ch);
			if (next == null) {
				return false;
			}
			parent = next;
		}

		if(!parent.children.isEmpty() ) {
			parent.aword = false;
			return true;
		}

		// delete node
		while( !nodeList.isEmpty() ) {
			TrieNode cur = nodeList.pop();

			// check if word has common prefix
			if (cur.children.size() == 1 && (i == delWord.length() - 1 || ! cur.children.get(delWord.charAt(i)).aword)){
				cur.children.remove(delWord.charAt(i));
			}
			else {
				break;
			}
			i --;
		}
		return true;
	}

	public void addCount(String prefix) {
		addCount(root, prefix);
	}

	// Helper for addCount
	private void addCount(TrieNode node, String prefix) {
		if(prefix != null) {
			String rest = prefix.substring(1); // follow the substring
			char ch = prefix.charAt(0);
			TrieNode child = node.children.get(ch);
			if(prefix.length() == 1 && child != null)
				child.freq ++;
			else
				addCount(child, rest);
		}
	}

	public boolean findWord(String s) {
		return findWord(root, s);
	}
	
	private boolean findWord(TrieNode node, String s) {
		if(s != null) {
			String rest = s.substring(1); //rest is a substring of s, by excluding the first character in s
			char ch = s.charAt(0);        //ch is the first letter of s
			TrieNode child = node.children.get(ch);	//return the child that ch associated with. 
			if(s.length() == 1 && child != null) //if s contains only one letter, and current node has a child associated with that letter, we find the prefix in Trie!
				return true;	                 //base case
			if(child == null)
				return false;
			else
				return findWord(child, rest);    //recursive, In this way, we follow the path of the trie from root down towards leaf
		}
		return false;
	}

	// find an insert spot in freqList, so that frequency is sorted in descending order
	private int insertSpot(ArrayList<Integer> freqList, int toAdd) {
		int len = freqList.size();
		int i;
		for(i = len - 1; i >= 0 && freqList.get(i) < toAdd; i --) { //empty body
		}
		return (i + 1);
	}

	// First, please add this public method into the provided Trie2 class.
	// Then implement the private helper method below this method.
	public MyLinkedList wordsPrefixedBy(String p) {

		return wordsPrefixedBy(this.root,  p);

	}//end of method

	// The method returns a LinkedList of all words that have a prefix p. For example, if the current prefix
	// tree object stores a set of words {apple, bike, bake, pen, did, ape, child, cat, file, hello, he, hell},
	// the method call wordsPrefixedBy(root, 
	// Helper methods are allowed.
	private MyLinkedList wordsPrefixedBy(TrieNode root, String p) {

		MyLinkedList RET = new MyLinkedList();

		if (findWord(root, p)) {
			TrieNode preWord = findRoot(root, p);
			printSorted(preWord, p, RET);
			//newprintSorted(preWord, p, RET);
			return RET;
		}
		else {
			return RET;
		}


	}//end of method

	public static TrieNode findRoot(TrieNode root, String p) {
		if (p != null) {
			String rest = p.substring(1); //rest is a substring of p, by excluding the first character in p
			char ch = p.charAt(0);        //ch is the first letter of p
			TrieNode child = root.children.get(ch);
			if (p.length() == 1 && child != null) //if p contains only one letter, and current node has a child associated with that letter, we find the prefix in Trie!
				return child;                     //base case
			else
				return findRoot(child, rest);    //recursive, In this way, we follow the path of the trie from root down towards leaf
		}
		return root;
	}





	// Usage example
	public static void main(String[] args) {
		/* default test code
		Trie2 tr = new Trie2();
		
		tr.insertString("hello");
		tr.insertString("world");
		tr.insertString("hi");
		tr.insertString("ant");
		tr.insertString("an");
		
		System.out.println(tr.findWord("ant"));
		System.out.println(tr.findWord("an"));
		System.out.println(tr.findWord("hello"));
		System.out.println(tr.findWord("cant"));
		System.out.println(tr.findWord("hig"));
		System.out.println(tr.findWord("he"));
		
		tr.printSorted();*/

/*

		Trie2 tr = new Trie2();
		tr.insertString("hello",1);
		tr.insertString("hell",1);
		tr.insertString("help",1);
		tr.insertString("head",1);
		tr.insertString("bread",1);
		//System.out.println(tr.findWord("hea"));
		System.out.println(tr.wordsPrefixedBy("hea"));
*/

	}
}
