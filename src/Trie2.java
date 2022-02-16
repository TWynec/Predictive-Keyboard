import java.util.*;

public class Trie2 {
	
	private class TrieNode {
		Map<Character, TrieNode> children = new TreeMap<>();//TreeMap is java build-in structure, 
		boolean aword = false;		//Basically it acts like a Hashtable or Hashmap, establishing a mapping between Key and Value
		                                //Unlike hash table, keys in TreeMap are sorted!
	}
	
	private TrieNode root;
	public Trie2() {
		this.root = new TrieNode();
	}

	public void insertString(String s) {
		insertString(root, s);
	}
	
	private void insertString(TrieNode root, String s) {
		TrieNode cur = root;
		for (char ch : s.toCharArray()) {
			TrieNode next = cur.children.get(ch);
			if (next == null)
				cur.children.put(ch, next = new TrieNode());
			cur = next;
		}
		cur.aword = true;
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

	private void printSorted(TrieNode node, String s, LinkedList mylist) {  //the helper
		if (node.aword) {
			mylist.add(s); ///System.out.println(s);  //for example, instead of print out the word, you have to add the word s into a linkedlist object you have defined or passed in.
		}
		for (Character ch : node.children.keySet()) {
			printSorted(node.children.get(ch), s + ch, mylist);
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

	// First, please add this public method into the provided Trie2 class.
	// Then implement the private helper method below this method.
	public LinkedList wordsPrefixedBy(String p) {

		return wordsPrefixedBy(this.root,  p);

	}//end of method

	// The method returns a LinkedList of all words that have a prefix p. For example, if the current prefix
	// tree object stores a set of words {apple, bike, bake, pen, did, ape, child, cat, file, hello, he, hell},
	// the method call wordsPrefixedBy(root, “ap”) returns two words in the tree {“apple”, “ape”}
	// Helper methods are allowed.
	private LinkedList wordsPrefixedBy(TrieNode root, String p) {

		LinkedList RET = new LinkedList();

		if (findWord(root, p)) {
			TrieNode preWord = findRoot(root, p);
			printSorted(preWord, p, RET);
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
		tr.insertString("hello");
		tr.insertString("hell");
		tr.insertString("help");
		tr.insertString("head");
		tr.insertString("bread");
		//System.out.println(tr.findWord("hea"));
		System.out.println(tr.wordsPrefixedBy("hea"));
*/

	}
}
