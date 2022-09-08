/*
 * class to create the LinkedList object
 * @author Steven Kravitz
 * @version 2.0
 * created 4/6/2021
 * last edited 4/11/2021
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinkedList<E> {
	// Data members
	private Node head, tail;
	int size;
	// Inner class Node
	private class Node {
		E value;
		Node next;
		Node(E initialValue) {
			value = initialValue;
			next = null;
		}
	}
	 /*
	  * Default Constructor
	  * head and tail are set to null
	  * size is set to 0
	  */
	public LinkedList() {//empty list O(1)
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
			newNode.next = head;
			head = newNode;
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
			tail.next = newNode; tail = newNode;
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
		if(head == null) {//removing one element from the list
			tail=null;
		}
		size--; 
		return true;
	}
	/*
	 * Method to remove the last item from the list
	 * @param void
	 * @returns true if the item was successfully removed
	 */
	public boolean removeLast() { //O(n)
		if (head == null) {
			throw new NoSuchElementException();
		}
		if(size == 1) {
			return removeFirst();
		}
		Node current = head;
		Node previous = null;
		while(current.next != null) {//go to the element before the tail
			previous = current; current = current.next;
		}
		//previous becomes the new tail
		previous.next = null;
		tail = previous;
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
	public Iterator<E> iterator(){
		return new LinkedListIterator();
	}
	/*
	 * Class to create the iterator for LinkedLists
	 */
	private class LinkedListIterator implements Iterator<E>{
		private Node current = head;
		/*
		 * Checks if there is another item in the linkedList
		 */
		public boolean hasNext() { //O(1)
			return (current != null);
		}
		/*
		 * Goes to the next item of the LinkedList
		 */
		public E next() { //O(1)
			if(current == null) {
				throw new NoSuchElementException();
			}
			E value = current.value;
			current = current.next;
			return value;
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
		newNode.next = current;//inserting newNode between previous and current
		size++;
		return true;
	}
	/*
	 * Search method
	 * @param item for the item to be searched
	 * @returns the index of the item if found, otherwise returns size of the LinkedList
	 */
	 public int searchComparison(E item) { //O(n)
		 int iterations = 0;
		 Iterator<E> iter = iterator();
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
				 current = current.next;
				 i++;
			 }
			 return current.value;
		 }
	 }
}