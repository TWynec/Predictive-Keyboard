
public class Queue {
		private class Node {
			
			private MyLinkedList data;
			private Node next, prev;
			
			private Node(MyLinkedList data) {
				this.data = data;
				this.next = null;
			}
			
			private Node(MyLinkedList data, Node next) {
				this.data = data;
				this.next = next;
			}	
		}
		
		private Node head, tail;
		private int size;
		
		public Queue() {
			this.head = null;
			this.tail = null;
			this.size = 0;
		}
		
		public void enqueue(MyLinkedList elem) {
			Node nn = new Node(elem);
			if(this.size == 0) {
				head = nn;
				tail = nn;
			}
			
			else {
				tail.next = nn;
				tail = nn;
			}
			this.size++;

		}
		
		public MyLinkedList dequeue(){
			
			MyLinkedList temp = head.data;
			this.head = this.head.next;
			this.size--;
			if(this.size == 0) {
				this.tail = null;
			}
			return temp;
		}
		
		public Object front() throws Exception {
			if(this.size == 0) {
				throw new Exception("The queue is empty!");
			}
			
			return this.head.data;
		}
		
		public int getSize() {
			return this.size;
		}
	
}
