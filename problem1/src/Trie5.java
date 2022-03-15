import java.io.IOException;
import java.util.*;


public class Trie5 {

    private class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        boolean word = false;
        //ArrayList<String> mostUsed;
        int freq = 0;
    }

    private TrieNode root;
    private WordItem dict[];

    public Trie5(WordItem d[]) {
        this.root = new TrieNode();
        this.dict = d;
    }

    public void insertString(String s, int f) {
        insertString(root, s, f);
    }

    private void insertString(TrieNode r, String s, int f) {
        TrieNode cur = r;
        for (char ch : s.toCharArray()) {
            TrieNode next = cur.children.get(ch);
            if (next == null) {
                cur.children.put(ch, next = new TrieNode());
            }
            cur = next;
        }
        cur.word = true;
        cur.freq = f;
    }

    //---------------------------------------------good
    public void insertString2(String s) {
        insertString2(this.root, s);
    }

    //---------------------------------------------good
    private void insertString2(TrieNode r, String s) {
        TrieNode cur = r;
        String prefix = "";
        for (char ch : s.toCharArray()) {
            prefix += ch;
            TrieNode next = cur.children.get(ch);
            if (next == null) {
                cur.children.put(ch, next = new TrieNode());
                //next.mostUsed = computeMostUsed(prefix);
            }
            cur = next;
        }
        cur.word = true;
    }


    public void printSorted() {
        printSorted(root, "");
    }
    private void printSorted(TrieNode node, String s) {
        for (Character ch : node.children.keySet()) {
            printSorted(node.children.get(ch), s + ch);
        }
        if (node.word) {
            System.out.println(s + ", " + node.freq);
        }
    }

    private void printSorted(TrieNode node, String s, ArrayList<String> wordList) {
        for (Character ch : node.children.keySet()) {
            printSorted(node.children.get(ch), s + ch, wordList);
        }
        if (node.word) {
            //System.out.println(s);
            wordList.add(s);
        }
    }

    // return wordList as a collection of most frequently used words in the trie with prefix of pre
    // by traversing a subtree in the trie
    public ArrayList<String> topKwords(String pre, int topk) {
        ArrayList<String> wordList = new ArrayList<String>();
        ArrayList<Integer> freqList = new ArrayList<Integer>();

        if(pre == null)
            return wordList;
        //find the trie node for the prefix
        TrieNode sub = findPrefix(this.root, pre);
        if(sub == null) { //if not found
            return wordList;
        }
        topKwords(sub, pre, wordList, freqList, topk);
        return wordList;
    }

    private void topKwords(TrieNode node, String s, ArrayList<String> wordList,
                           ArrayList<Integer> freqList, int topk) {
        for (Character ch : node.children.keySet()) {
            topKwords(node.children.get(ch), s + ch, wordList, freqList, topk);
        }
        int inPos = insertSpot(freqList, node.freq);

        if (node.word && inPos <= topk - 1) {
            wordList.add(inPos, s);
            freqList.add(inPos, node.freq);
            //if wordList size exceed topk, we remove the least frequent item
            if(wordList.size() > topk) {
                wordList.remove(wordList.size() - 1);
                freqList.remove(freqList.size() - 1);
            }
        }
    }

    // return wordList as a collection of most frequently used words in the trie with prefix of pre
    // by traversing a subtree in the trie. Also returns the leastFrequent items
    public ArrayList<String> topKwords2(String pre, int topk, WordItem leastFreq) {
        ArrayList<String> wordList = new ArrayList<String>();
        ArrayList<Integer> freqList = new ArrayList<Integer>();

        if(pre == null)
            return wordList;
        //find the trie node for the prefix
        TrieNode sub = findPrefix(this.root, pre);
        if(sub == null) { //if not found
            return wordList;
        }
        topKwords2(sub, pre, wordList, freqList, topk, leastFreq);
        return wordList;
    }

    // this method returns extra item, the word with smallest frequency in the subtree of s
    // if multiple words have the same least frequency, returns the leftmost(then the longest one if multiple
    // exist in a single path) path in the tree.
    private void topKwords2(TrieNode node, String s, ArrayList<String> wordList,
                            ArrayList<Integer> freqList, int topk, WordItem leastFreq) {
        for (Character ch : node.children.keySet()) {
            topKwords2(node.children.get(ch), s + ch, wordList, freqList, topk, leastFreq);
        }
        int inPos = insertSpot(freqList, node.freq);

        //System.out.println("debug: " + s + "," + node.freq );

        //update leatFreq,
        if(node.word && node.freq < leastFreq.getCount()) {
            leastFreq.setCount(node.freq);
            leastFreq.setWord(s);
            //System.out.println("debug: " + s + "," + node.freq);
        }
        //keep topk words to display in GUI
        if (node.word && inPos <= topk - 1) {
            wordList.add(inPos, s);
            freqList.add(inPos, node.freq);
            //if wordList size exceed topk, we remove the least frequent item
            if(wordList.size() > topk) {
                wordList.remove(wordList.size() - 1);
                freqList.remove(freqList.size() - 1);
            }
        }
    }


    // find an insert spot in freqList, so that frequency is sorted in descending order
    private int insertSpot(ArrayList<Integer> freqList, int toAdd) {
        int len = freqList.size();
        int i;
        for(i = len - 1; i >= 0 && freqList.get(i) < toAdd; i --) { //empty body
        }
        return (i + 1);
    }


    //print all words in the trie with a prefix of pre, by traversing the subtree
    public ArrayList<String> traverseSubtree(String pre) {
        if(pre == null)
            return null;
        ArrayList<String> ret = new ArrayList<String>();

        //find the trie node for the prefix
        TrieNode sub = findPrefix(this.root, pre);
        if(sub == null) { //if not found
            return null;
        }
        else
        {
            printSorted(sub, pre, ret);
        }
        return ret;
    }

    public boolean findWord(String s) {
        return findWord(root, s);
    }

    //find a prefix in the trie, if found, return true, otherwise false
    private boolean findWord(TrieNode node, String s) {
        if(s != null) {
            String rest = s.substring(1);//substring of s, excluding the first char in s.
            char ch = s.charAt(0);
            TrieNode child = node.children.get(ch);
            if(s.length() == 1 && child != null) //not checking child == leaf
                return true;
            if(child == null)
                return false;
            else
                return findWord(child, rest);
        }
        return false;
    }

    // you are sure the word s is in the trie
    public void addCount(String s) {
        addCount(root, s);
    }

    //
    private void addCount(TrieNode node, String s) {
        if(s != null) {
            String rest = s.substring(1);//substring of s, excluding the first char in s.
            char ch = s.charAt(0);
            TrieNode child = node.children.get(ch);
            if(s.length() == 1 && child != null) //not checking child == leaf
                child.freq ++;
            else
                addCount(child, rest);
        }
    }

    public boolean delWord(String s) {
        return delWord(root, s);
    }

    //delete a word in the trie, if the word is found and deleted, return true, otherwise false
    //if the word is a prefix of another word(s), he --> her, remove its flag 'word', without affecting its child.
    private boolean delWord(TrieNode node, String s) {
        LinkedList<TrieNode> pstk = new LinkedList<TrieNode>();
        TrieNode parent = node;
        int i = s.length() - 1;

        for (char ch : s.toCharArray()) {
            pstk.push(parent); //push parent first
            TrieNode next = parent.children.get(ch);
            if (next == null) {
                return false;
            }
            parent = next;
        }
        //the word to be deleted is a prefix of another existing word
        //we only change the word flag, without delete any trie node
        if( ! parent.children.isEmpty() ) {
            parent.word = false;
            return true;
        }

        //perform deletion
        while( !pstk.isEmpty() ) {
            TrieNode cur = pstk.pop();

            //this means the word to be intendedly deleted contains a prefix(word) that is not supposed to be deleted
            //while we deleting 'these', but not 'the', for example.
            if (cur.children.size() == 1 && (i == s.length() - 1 || ! cur.children.get(s.charAt(i)).word)){
                cur.children.remove(s.charAt(i));
            }
            else {
                break; //stop deleting from this level up to the root.
            }
            i --;
        }
        return true;
    }

    // find a prefix s in the trie, if found, return the trie node corresponding to s,
    // otherwise return null
    private TrieNode findPrefix(TrieNode node, String s) {
        if(s != null) {
            String rest = s.substring(1);//substring of s, excluding the first char in s.
            char ch = s.charAt(0);
            TrieNode child = node.children.get(ch);
            if(s.length() == 1 && child != null) //not checking child == leaf
                return child;
            if(child == null)
                return null;
            else
                return findPrefix(child, rest);
        }
        return null;
    }

    /*
     */

    public static void main(String[] args) throws IOException {

        String fileName2="files/dictionary.txt";
        String fileName3="files/dictSmall.txt";
        //WordProcessor wp = new WordProcessor();

        WordItem dict[] = AutoComplete6.readDict(fileName2);//
        //System.out.println(AutoComplete4.arrToString(dict));

        //this creates the trie
        Trie5 tr = new Trie5(dict);

        for(WordItem word : dict) {
            tr.insertString(word.getWord(), word.getCount());
        }

        WordItem leastFreq = new WordItem("zz", Integer.MAX_VALUE);

        ArrayList<String> wlist = tr.topKwords2("b", 9, leastFreq);

        //search the most used words with a specified prefix
        //String pre = "c";
        //System.out.println("end of bb:" + tr.findEndIndex(pre));

        //ArrayList<String> popular = tr.mostUsedList(pre);
		
		/*
		if(popular != null) {
			System.out.println("\n\n\nsize of temp:" + popular.size());
			for(String s: popular)
				System.out.println(s);
		}
		else
			System.out.println("Prefix:\"" + pre + "\" NOT in the trie!");
			
		*/
		/*
		System.out.println("=======================================before deleted basically");
		tr.printSorted();
		
		System.out.println("=======================================after deleted basically");
		if(tr.delWord("my"))
			System.out.println("======================================= deleted basically");
		
		tr.printSorted();
		*/
        for(String s: wlist)
            System.out.println(s);

        System.out.println("Least frequent item is:" + leastFreq.getWord());
    }
}