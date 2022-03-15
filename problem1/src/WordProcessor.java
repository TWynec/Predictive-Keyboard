import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


/*
 *The WordProcessor class would extract words from the raw text file(a.k.a tokenization).
 *After extracted one word, it either creates a new WordItem object and insert
 *the object into a proper location, or it calls a method in MyLinkedList to increment
 *the word occurrence and to update line number list if a word has already been existed.
 *
 *The class also provides File I/O methods.Write the resultant string or list back to a file.
 *
 */

public class WordProcessor {

    private final String fileName="files/testfile1";

    //----------------------------------------------------------good
    public ArrayList<String> fileRead(String name) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();

        FileReader fileReader = new FileReader(name);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String aline = null;
        //read in the rest of rows
        //readLine() returns striped string, that discards any line-termination chars
        while ((aline = bufferedReader.readLine()) != null) {
            aline = aline.trim();
            if(aline.length() > 0)
                lines.add(aline); //skip empty lines
        }
        fileReader.close();
        return lines;
    }

    //-------------------------------------------------------------good
    public LinkedList<String> extractLine(String aline) {
        LinkedList<String> wordList = new LinkedList<String>();
        boolean inWord = false;
        int size = aline.length();
        int i = 0, wordLen = 0, start = 0;
        String aword = "";
        while (i < size)
        {
            if( Character.isAlphabetic(aline.charAt(i)) )
            {
                if (! inWord ) //start of new word
                {
                    start = i;
                    inWord = true;
                }
                wordLen ++;
            }
            else if( inWord ) //in order to throw away multi non-alphabetic chars
            {
                //we find a  word
                aword = aline.substring(start, start + wordLen);
                // throw away word of length one except A and I.
                if( wordLen > 1 || aword.equalsIgnoreCase("a") || aword.equalsIgnoreCase("i") )
                    wordList.add(aword);

                //reset
                inWord = false;
                wordLen = 0;
            }
            i ++;
        }
        if(inWord && (wordLen > 1 || aword.equalsIgnoreCase("a")
                || aword.equalsIgnoreCase("i")) ) {
            aword = aline.substring(start, start + wordLen);
            wordList.add(aword); //last word in the line
        }
        return wordList;
    }//end of extract

    public MyLinkedList extractAll(String name) throws IOException {
        MyLinkedList wordList = new MyLinkedList();

        FileReader fileReader = new FileReader(name);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String aline = null;
        int lineNum = 0;
        LinkedList lineWords = null;
        //readLine() returns striped string, that discards any line-termination chars
        while ((aline = bufferedReader.readLine()) != null) {
            aline = aline.trim();

            if(aline.length() > 0) {
                lineWords = extractLine(aline); //skip empty lines

                Iterator lineItr = lineWords.iterator();
                while(lineItr.hasNext()) {
                    String aword = (String)lineItr.next();
                    if(! wordList.containWord(aword, lineNum)) {
                        WordItem newItem = new WordItem(aword, 1, lineNum);
                        //here basically just added first if the word is not in the list
                        //we will use the build in sort method to sort the array list later
                        //since the addOrdered is too slow for bigger textfiles, tony changed Feb 28th
                        wordList.addFirst(newItem);
                        //System.out.println("We addfirst\n");
                        //wordList.addOrdered(newItem);
                    }
                }//end of inner while
            }//end of if
            lineNum ++;
            if(lineNum % 1000 == 0)
                System.out.println("" + lineNum + " lines has been processed!");
        }//end of out while
        fileReader.close();
        return wordList;
    }

    public void writeToFile(MyLinkedList alist, String fileName) {
        FileWriter fileWriter = null;
        try {
            String content = alist.toString(); //this method will sort alist first using build-in sorting
            File newTextFile = new File(fileName);
            fileWriter = new FileWriter(newTextFile);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//

}//end of class
	