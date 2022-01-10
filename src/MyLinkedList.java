import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * MyLinkedList class contains a list of WordItem objects.
 * This Data Structure holds the data that we extract from the provided 
 * text file. In WordProcessor Class, we add a new WordItem object to a MyLinkedList 
 * object each time we find a new English word.
 * 
 * This MyLinkedList Class has to implement iterator, addOrdered, sort and containWord method.
 * You can create the list by calling its addOrdered() method, so that it reserves its order as
 * the list is growing.
 * 
 * Each English word is represented by a WordItem Object. English word is stored as a String in the data field of the ListNode object.
 * MyLinkedList also provides a method containWord() to check whether an English word has been existing in the list or not.
 * If it exists, we have to increment the occurrence of that word and update other fields in that WordItem object.
 * If not exist, we have to create a new WordItem object and add it to the List.
 * NOTE: English word in the list should be unique, NO Duplicates.
 * NOTE: When comparing two words in Java String type, please Ignore their case and use case-insensitive comparison.
 * 
 * You can choose to write either one or two sort() method for the MyLinkedList class, because your addOrdered method would have already
 * created an sorted list, based on either English word or its Occurrence.
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

		public MyLinkedList() 
		{
			this.head = new Node(null); //with Dummy Node
			this.size = 0;
		}

		public boolean isEmpty() {
			return (this.head == null);
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
	

		/*
		 * This method checks whether the passed-in parameter "word" exists or not in
		 * this list. If it exists, the method update number occurrence and its show-up locations (at which line(s) the word appear in the original text file) as well,
		 * then it returns true, Otherwise, it returns false.
		 */
		public boolean containWord(String word, int atLine) {

			return false;
		}
		
		
		@Override
		public String toString() {
			String result = "";
			//walk through the list
			for ( Node cur = this.head.next; cur != null; cur = cur.next ) {   // explain how cur changes, and references and dereferences
				result += cur.data + "\n"; /// (not cur.next != null.) in for statement 
			
			}
			//result += "\n";
			return result;
		}


		@Override
		public Iterator<Object> iterator() {
			return new MyLinkedListIterator(this.head.next); //with dummy node
		}
		
		public class MyLinkedListIterator implements Iterator<Object> {  // or you can use ListIterator without change other code
			private Node cur;
			private int index;
			private Node prev;

			private MyLinkedListIterator ( Node start ) {
				this.cur = start; //with dummy node
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
}
