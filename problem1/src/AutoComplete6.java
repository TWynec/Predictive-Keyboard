

        import java.awt.*;
        import java.awt.event.*;
        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;

        import javax.swing.*;

class AutoComplete6 extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;

    JTextArea output= new JTextArea();
    JTextArea input = new JTextArea();
    String partialWord = "";

    boolean inWord = false;
    String current = "";

    ArrayList<String> popular = null;

    WordItem dict[];
    Trie5 tr;
    WordItem leastFreq = new WordItem("zz", Integer.MAX_VALUE);


    public AutoComplete6(WordItem d[]) {
        JFrame frame = new JFrame("Preditive Application");
        frame.setSize(640,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2,1));

        JPanel inputPanel = new JPanel();
        JPanel outPanel = new JPanel();

        outPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setLayout(new GridLayout(1,1));
        outPanel.setLayout(new GridLayout(1,1));

        outPanel.add(output);
        inputPanel.add(input);
        output.setEditable(true);
        output.addKeyListener(this);
        input.setEditable(false);
        input.addKeyListener(this);
        input.setLineWrap(true);
        output.setLineWrap(true);
        //
        //change the font and the color in the input textArea
        Font font = new Font("Verdana", Font.BOLD, 16);
        input.setFont(font);
        input.setForeground(Color.BLUE);

        frame.add(outPanel);
        frame.add(inputPanel);
        frame.setVisible(true);
        partialWord = "";
        this.dict = d;

        //then create the Trie here
        this.tr = new Trie5(this.dict);
        for(WordItem word : this.dict) {
            tr.insertString(word.getWord(), word.getCount());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(! inWord) {
            inWord = true;
            //temp = new String(current); //save current text in output area
            //System.out.println("current1:" + temp);
        }

        int keyCode = e.getKeyCode();
        char ch = e.getKeyChar();
        int index = parseKeyCode(keyCode);

        // Handle regular alphabetic letter keys
        if ( index < 0 ) {
            output.setEditable(true); //echo what we input

            if ( Character.isAlphabetic(ch) && inWord ) {
                partialWord += ch; //append the current character pressed
                // Search the prefix tree, by sending partialWord to the tree and returns
                // up to ten most popular words start with msg.
                System.out.println("Current Prefix:\"" + partialWord + "\"");

                // search the trie to find the most frequently used words with the prefix saved in partialWord
                // by traversing relevant subtree of the trie
                //System.out.println("=================================== " + partialWord);
                //popular = tr.traverseSubtree(partialWord);
                //System.out.println("===================================" );

                //if the first letter your type in
                if(partialWord.length() == 1) {
                    popular = tr.topKwords2(partialWord, 9, leastFreq);
                }
                else{
                    popular = tr.topKwords(partialWord, 9);
                }
                //display the popular list of words
                //popular.add(0, partialWord);
                input.setText(arrtoString(popular));
            }
        }
        else if( index >= 0 && index <= 10 ){ // if the key pressed is enter or space or numbers
            //System.out.println(index);
            output.setEditable(false);

            if(index == 0) { //this is enter, to add new words into trie on purpose
                current += this.partialWord + " ";
                //delete leastFreq item and add current word
                int count = leastFreq.getCount();
                this.tr.delWord(leastFreq.getWord());
                this.tr.insertString(partialWord, count);
            }
            //if the partialWord does exist in the trie
            else if(popular != null && popular.size() > 0) {
                current += popular.get(index - 1) + " ";
                this.tr.addCount(popular.get(index - 1));  //addcount
            }
            //if prefix does not exist, HH
            else {
                current += this.partialWord + " ";
                //delete leastFreq item and add current word
                int count = leastFreq.getCount();
                this.tr.delWord(leastFreq.getWord());
                this.tr.insertString(partialWord, count + 1);
            }

            //System.out.println("curent2:" + current);
            output.setText(current);
            inWord = false;
            partialWord = "";
        }//end of outer else
        else if( index == 11 || index == 12) {
            output.setEditable(false);
            current = current.substring(0, current.length() - 1); //remove ending space
            if(index == 10) //comma
                current += ", ";
            else
                current += ". "; //period
            //System.out.println("curent2:" + current);
            output.setText(current);
        }

    }

    private int parseKeyCode(int code) {
        int index = -10;
        switch(code) {
            case KeyEvent.VK_ENTER :
                index = 0;
                break;
            case KeyEvent.VK_SPACE :
                index = 1;
                break;
            case KeyEvent.VK_1 :
                index = 2;
                break;
            case KeyEvent.VK_2 :
                index = 3;
                break;
            case KeyEvent.VK_3 :
                index = 4;
                break;
            case KeyEvent.VK_4 :
                index = 5;
                break;
            case KeyEvent.VK_5 :
                index = 6;
                break;
            case KeyEvent.VK_6 :
                index = 7;
                break;
            case KeyEvent.VK_7 :
                index = 8;
                break;
            case KeyEvent.VK_8 :
                index = 9;
                break;
            case KeyEvent.VK_9 :
                index = 10;
                break;
            case KeyEvent.VK_COMMA :
                index = 11;
                break;
            case KeyEvent.VK_PERIOD :
                index = 12;
                break;
            default:
                index = -1;
        }
        return index;

    }
    private String arrtoString(String a[]) {
        String ret = "";
        ret += "-->" + a[0] + "  ";
        for(int i = 1; i < a.length; i ++) {
            ret += i + ":" + a[i] + "  ";
        }
        return ret;
    }

    // this displays the list of most frequently used words in the bottom window
    private String arrtoString(ArrayList<String> a) {
        String ret = "";
        if(a == null || a.size() == 0)  //this is important, sometimes prefix is not in the tree.
            return ret;
        ret += "-->" + a.get(0) + "  ";
        for(int i = 1; i < a.size(); i ++) {
            ret += i + ":" + a.get(i) + "  ";
            if(i == 5)
                ret += "\n          ";
        }
        return ret;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static WordItem[] readDict(String name) throws IOException {
        ArrayList<WordItem> words = new ArrayList<WordItem>();

        FileReader fileReader = new FileReader(name);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String aline = null;
        //readLine() returns striped string, that discards any line-termination chars
        while ((aline = bufferedReader.readLine()) != null) {
            aline = aline.trim();
            //System.out.println("readin:" + aline);
            if(aline.length() > 0){
                String temp[] = aline.split(",");
                //System.out.println("temp0:" + temp[0] + ",temp1:" + temp[1]);
                words.add( new WordItem( temp[0], Integer.parseInt(temp[1]) ) );
            }
        }
        fileReader.close();

        WordItem dict[] = new WordItem[words.size()];
        for(int i = 0; i < dict.length; i ++)
            dict[i] = words.get(i);

        return dict;
    }

    public static String arrToString(WordItem d[]) {
        String ret = "";
        for(int i=0; i < d.length; i ++){
            ret += i + ":" + d[i] + "\n";
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        String fileName2= "problem1/files/dictionary.txt";
        String fileName3="files/dictSmall.txt";
        //String fileName4="files/bigDict.txt";
        //WordProcessor wp = new WordProcessor();
        long then = System.currentTimeMillis();
        System.out.println("Initializing .....");
        WordItem d[] = AutoComplete6.readDict(fileName2);
        //System.out.println(AutoComplete3.arrToString(d));
        new AutoComplete6(d);
        System.out.println("Done Intialization and Ready to type in!");
        long now = System.currentTimeMillis();
        System.out.println("time cost to initialize:" + (now-then) );
    }
}
