/*
 * class to create the DoublyLinkedList object
 * @author Steven Kravitz
 * @version 1.0
 * created 4/11/2021
 * last edited 4/13/2021
 */
import java.util.ListIterator;
import java.util.NoSuchElementException;
public class DoublyLinkedList<E> {
	// Data members
	private Node head, tail;
	int size;
	// Inner class Node
	private class Node {
		E value;
		Node next;
		Node previous;
		Node(E initialValue) {
			value = initialValue;
			next = null;
			previous = null;
		}
	}
	 /*
	  * Default Constructor
	  * head and tail are set to null
	  * size is set to 0
	  */
	public DoublyLinkedList() {//empty list O(1)
	head = tail = null;
	size = 0;
	}
	/*
	 * Method to insert an element at the begining of the list
	 * @param item for the item to be added
	 * @returns true if element is added
	 */
	public boolean addFirst(E item) {  //O(1)
		Node newNode = new Node(item);
		if(head == null) {//first element to be added
			head = tail = newNode;
		}
		else {
			newNode.previous = null;
			newNode.next = head;
			head = newNode;
			head.previous = newNode;
		}
		size++;
		return true;
	}
	/*
	 * Method to insert an element at the end of the list
	 * @param item for the item to be added
	 * @returns true if element is added
	 */
	public boolean addLast(E item) {  //O(1)
		Node newNode = new Node(item);
		if(head == null) {//first element to be added
			head = tail = newNode;
		}
		else {
			newNode.previous = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return true;
	}
	/*
	 * Method to add an item by calling the addFirst method
	 * @param item for the item to be added
	 * @returns the boolean value from the addFirst method
	 */
	public boolean add(E item) { //O(1)
		return addFirst(item);
	}
	/*
	 * Method for retrieving an item from the begining of the list
	 * @param void
	 * @returns the item at the begining of the list
	 */
	public E getFirst() { //O(1)
		if (head == null) {//if list is empty
			throw new NoSuchElementException();
		}
		return head.value;
	}
	/*
	 * Method to get the ite from the end of th elist
	 * @param void
	 * @returns the item at the begining of the list
	 */
	public E getLast() { //O(1)
		if (head == null) {//if list is empty
			throw new NoSuchElementException();
		}
		return tail.value;
	}
	/*
	 * Method to remove the first item from the list
	 * @param void
	 * @returns true if the item was successfully removed
	 */
	public boolean removeFirst() { //O(1)
		if (head == null) {
			throw new NoSuchElementException();
		}
		head = head.next;
		head.previous = null;
		if(head == null) {//removing one element from the list
			tail = null;
		}
		size--; 
		return true;
	}
	/*
	 * Method to remove the last item from the list
	 * @param void
	 * @returns true if the item was successfully removed
	 */
	public boolean removeLast() { //O(1)
		if (head == null) {
			throw new NoSuchElementException();
		}
		tail = tail.previous;
		tail.next = null;
		if(tail == null) {
			head = null;
		}
		size--;
		return true;
	}
	/*
	 * Method to return the information of the class in a formatted String
	 */
	public String toString() { //O(n)
		String output = "[";
		Node current = head;
		while(current != null) {
			output += current.value + " ";
			current = current.next;//move to the next node
		}
		output += "]";
		return output;
	}
	/*
	 * Method to empty the list
	 */
	public void clear() { //O(1)
		head = tail = null;
		size = 0;
	}
	/*
	 * Method to check if the list is empty
	 */
	public boolean isEmpty() { //O(1)
		return (size == 0); 
	}
	public int size() { //O(1)
		return size;
	}
	/*
	 * Method to create an iterator
	 */
	public ListIterator<E> iterator(){
		return new DoublyLinkedListIterator();
	}
	/*
	 * Method to create an iterator that has its data member current at a specified index
	 */
	public ListIterator<E> iterator(int index){
		return new DoublyLinkedListIterator(index);
	}
	/*
	 * Class to create the iterator for LinkedLists
	 */
	private class DoublyLinkedListIterator implements ListIterator<E>{
		private Node current;
		/*
		 * Default Constructor for the ListIterator
		 * Sets the current value to the head of the list
		 */
		public DoublyLinkedListIterator() { //O(1)
			current = head;
		}
		/*
		 * Constuctor with 1 parameter
		 * Sets current to a designated index 
		 * @throws ArrayIndexOutOfBoundsException if index is <0 or >size
		 * @param index for the index to which the current is set
		 */
		public DoublyLinkedListIterator(int index) { //O(n)
			if (index > size || index < 0) {
				 throw new ArrayIndexOutOfBoundsException();
			}
			if(index == size) {
				current = tail;
			}
			else {
				current = head;
				Node prev = null;
				int i = 0;
				while(i < index) {
					prev = current;
					current = prev.next;
					i++;
				}		 
			}		
		}
		/*
		 * Method to see if the next value in the DoublyLinkedList is null
		 */
		public boolean hasNext() { //O(1)
			return (current != null);
		}
		/*
		 * Method to return the current node and set the current node to the next node
		 */
		public E next() { //O(1)
			if(!hasNext()) {
				throw new ArrayIndexOutOfBoundsException();
			}
			E value = current.value;
			current = current.next;
			return value;
		}
		/*
		 * Method to see if the previous value in the DoublyLinkedList is null
		 */
		public boolean hasPrevious() { //O(1)
			return (current != null);
		}
		/*
		 * Method to return the current node and set the current node to the previous node
		 */
		public E previous() { //O(1)
			if(!hasPrevious()) {
				throw new ArrayIndexOutOfBoundsException();
			}
			E value = current.value;
			current = current.previous;
			return value;
		}
		/*
		 * Empty method that only throws an exception of type UnsupportedOperationException
		 */
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		/*
		 * Empty method that only throws an exception of type UnsupportedOperationException
		 */
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		/*
		 * Empty method that only throws an exception of type UnsupportedOperationException
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
		/*
		 * Empty method that only throws an exception of type UnsupportedOperationException
		 */
		public void set(E e) {
			throw new UnsupportedOperationException();
		}
		/*
		 * Empty method that only throws an exception of type UnsupportedOperationException
		 */
		public void add(E e) {
			throw new UnsupportedOperationException();			
		}
	}
	/*
	 * Method to add an element at a specified index on the list
	 * @param index for the index at which the element will be added
	 * @param item for the element to be added
	 * @returns true if the element is added successfully
	 */
	public boolean add(int index, E item) { //O(n)
		if(index > size || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if(index == 0) {
			return addFirst(item);
		}
		if(index == size) {
			return addLast(item);
		}
		Node current = head;
		Node prev = null;
		int i = 0;
		while(i < index) {
			prev = current;
			current = current.next;
			i++;
		}
		Node newNode = new Node(item);
		prev.next = newNode;
		newNode.previous = prev;
		newNode.next = current;//inserting newNode between previous and current
		current.previous = newNode;
		size++;
		return true;
	}
	/*
	 * Search method
	 * @param item for the item to be searched
	 * @returns the index of the item if found, otherwise returns size of the LinkedList
	 */
	 public int searchComparison(E item) {
		 int iterations = 0;
		 ListIterator<E> iter = iterator();
		 while(iter.hasNext()) {
			 iterations++;
			 if(iter.next().equals(item)) {
				 return iterations;
			 }
		 }
		 return iterations;
	 }
	 /*
	  * Method to return the element at a specified index
	  * @param index for the index of the element to return
	  * @return the element of the position
	  * @throws IndexOutOfBoundsException if the index > size or the index < 0
	  */
	 public E get(int index) { //O(n)
		 if (index > size || index < 0) {
			 throw new ArrayIndexOutOfBoundsException();
		 }
		 else {
			 Node current = head;
			 Node prev = null;
			 int i = 0;
			 while(i < index) {
				 prev = current;
				 current = prev.next;
				 i++;
			 }
			 return current.value;		 
		 }
	 }
}