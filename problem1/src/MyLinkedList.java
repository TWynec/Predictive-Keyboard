
import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Iterator;
        import java.util.NoSuchElementException;

/*
 * MyLinkedList class contains a list of WordItem objects.
 * This Data Structure holds the data that we extracted from the provided
 * text file. In WordProcessor Class, we add a new WordItem object to a MyLinkedList
 * Object when we find one English word.
 *
 * The MyLinkedList Class has to implement iterator, addOrdered, sort and containWord method.
 * You can grow the list by calling its addOrdered method, so that it reserves its order as
 * the list is growing.
 *
 * Each English word is represented by a WordItem Object, which is stored in the data field of the ListNode class.
 * MyLinkedList also provides a method containWord() to check whether an English word has been in the list or not.
 * If it exists, we have to increment the occurrence of that word and update other fields in that WordItem object.
 * If not exist, we have to create a new WordItem object and add it to the List.
 * Note: English word in the list should be unique, NO Duplicates.
 *
 * You can choose write one or two sort method for the MyLinkedList class, since your addOrdered method would
 * create an sorted list, based on either English word or its Occurrence.
 * One sort method would sort the list nodes according to the English word in an dictionary order.
 * The other sorts the list nodes according to the occurrence of words in descending order. I.e.,
 * the more frequently used word is more closer to head node.
 */
public class MyLinkedList implements Iterable<Object> {

    private class Node {
        private Object data;
        private Node next;

        private Node( Object data, Node next )
        {
            this.data=data;
            this.next = next;
        }
        private Node( Object data ) {
            this(data,null);
        }
        private Node() {
        }
    }//end of node

    private Node head;
    private int size;
    private ArrayList copy;

    public MyLinkedList()
    {
        this.head = new Node(null); //with Dummy Node
        this.size = 0;
    }

    public boolean isEmpty() {
        return (this.head == null);
    }

    public void addFirst(Object e) {
        Node anode = new Node(e, this.head.next);
        this.head.next = anode;
    }

    //this method is equivalent to addLast()
    public boolean add(Object e) {
        Node cur;
        for(cur = head; cur.next != null; cur = cur.next){
            //empty loop body
        }
        //make new node
        Node anode = new Node(e);
        cur.next = anode;
        this.size ++; //increment size
        return true;
    }

    public void addOrdered( Comparable<Object> dataToAdd ) {

        //the precondition for the list that should be ordered before
        Node cur, prev;
        for(cur = this.head.next, prev = this.head; cur != null && ((Comparable) cur.data).compareTo(dataToAdd) < 0;
            cur = cur.next){
            prev = cur;
        }
        prev.next = new Node(dataToAdd, cur);
        this.size ++;
    }

    public void sort() { //flavor of insertion sort
        Node cur;
        MyLinkedList newList = new MyLinkedList();
        for(cur = this.head.next; cur != null; cur = cur.next)
            newList.addOrdered((Comparable)cur.data);

        this.head.next = newList.head.next;
        //return newList;
    }

    public MyLinkedList sort2() { //flavor of insertion sort
        Node cur;
        MyLinkedList newList = new MyLinkedList();
        for(cur = this.head.next; cur != null; cur = cur.next)
            newList.addOrdered((Comparable)cur.data);

        //.head.next = newList.head.next;
        return newList;
    }

    public void selectSortArray( Comparable []  array ) {

        int smallest, cur, start;
        Comparable temp;
        for( start = 0; start <= array.length - 1; start ++ ) {
            smallest = start;
            for( cur = start + 1; cur < array.length; cur ++ ) {
                if(array[cur].compareTo(array[smallest]) < 0)
                    smallest = cur;
            }
            //swap smallest and and start;
            temp=array[start];
            array[start] = array[smallest];
            array[smallest] = temp;
        }//end of outer for
    }

    public void selectionSortList() { //with dummy node

        Node start, smallest, cur;
        Comparable temp;

        for(start = this.head.next; start.next != null; start = start.next ) {   //this does not check empty list that uses dummy node. null
            smallest = start;
            for( cur = start.next; cur != null; cur = cur.next) {
                if ( ((Comparable)cur.data).compareTo((Comparable)smallest.data) < 0 )
                    smallest = cur;
            }
            //swap
            temp = (Comparable)start.data;
            start.data = smallest.data;
            smallest.data = temp;
        }
    }

    public void sortListOcc() { //with dummy node

        Node start, smallest, cur;
        Comparable temp;

        for(start = this.head.next; start.next != null; start = start.next ) {   //this does not check empty list that uses dummy node. null
            smallest = start;
            for( cur = start.next; cur != null; cur = cur.next) {
                WordItem curWord = (WordItem)cur.data;
                WordItem smallWord = (WordItem)smallest.data;
                if ( curWord.getCount() > smallWord.getCount() )
                    smallest = cur;
            }
            //swap
            temp = (Comparable)start.data;
            start.data = smallest.data;
            smallest.data = temp;
        }
    }

    public boolean containWord(String word, int atLine) {
        Iterator it = this.iterator();
        while(it.hasNext()) {
            WordItem item = (WordItem)it.next();
            if(item.getWord().equalsIgnoreCase(word)) {
                item.incrCount(atLine);
                return true;
            }
        }
        return false;
    }


    @Override
    //this method uses the build-in sorting algorithm to sort myLinkedList
    public String toString() {
        String result = "";
        this.copy = this.sortMyList();
        //walk through the list
        Iterator it = this.copy.iterator();
        while ( it.hasNext() ) {   // explain how cur changes, and references and dereferences
            WordItem temp = (WordItem)it.next();
            result += temp + "\n"; /// (not cur.next != null.) in for statement

        }
        return result;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new MyLinkedListIterator(this.head );  //no dummy is right now
    }

    public class MyLinkedListIterator implements Iterator<Object> {  // or you can use ListIterator without change other code
        private Node cur;
        private int index;
        private Node prev;

        private MyLinkedListIterator ( Node start ) {
            this.cur = start.next; //with dummy node
            this.index = 0;
            prev = start;
        }

        public boolean hasNext() {
            return cur != null;
        }

        public Object next() {
            if(hasNext()) {
                Object data = cur.data;
                prev = cur;
                cur = cur.next;
                return data;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            //
            throw new UnsupportedOperationException();
        }

    }

    public WordItem[] toArray() {
        WordItem ret[] = new WordItem[this.size];
        Node cur = this.head.next; //with dummy
        int i = 0;
        while(cur != null) {
            WordItem temp = (WordItem)cur.data;
            String word = temp.getWord();
            temp.setWord(word.toLowerCase()); //convert to lowercase
            ret[i++] = temp;
            cur = cur.next;
        }
        return ret;
    }

    public String toString2() {
        String ret = "";
        WordItem arr[] = this.toArray();
        for(int i = 0; i < arr.length; i ++) {
            ret = ret + arr[i].getWord() + "\n";
        }

        return ret;
    }

    private ArrayList<WordItem> sortMyList() {
        ArrayList<WordItem> ret = new ArrayList<WordItem>();
        Node cur = this.head.next;
        while(cur != null) {
            ret.add( (WordItem)cur.data );
            cur = cur.next;
        }
        System.out.println("Java sorting the big list.....");
        Collections.sort(ret, new WordItem.WordComparator(false));
        System.out.println("Java done sorting!");
        return ret;
    }//end of sortMyList
}