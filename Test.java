/*
 * class to Test The LinkedList and DoublyLinkedList classes
 * @author Steven Kravitz
 * @version 1.0
 * created 4/13/2021
 * last edited 4/13/2021
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.ListIterator;
public class Test {
	/*
	 * Main method
	 * Creates LL and DLL and then adds countries to both
	 * calls print methods
	 */
	public static void main(String[] args) {
		LinkedList<String> countryLL = new LinkedList<>();
		DoublyLinkedList<String> countryDLL = new DoublyLinkedList<>();
		File file = new File("countries.txt");
		Scanner readFile = null;
		try {
			readFile = new Scanner(file);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		while(readFile.hasNextLine()) {
			String country = readFile.nextLine();
			countryLL.addLast(country);
			countryDLL.addLast(country);
		}
		readFile.close();
		System.out.println("Linked List (Forwards)");
		printForward(countryLL);
		System.out.println("Linked List (Backwards)");
		printBackward(countryLL);
		System.out.println("Doubly Linked List (Forwards)");
		printForward(countryDLL);
		System.out.println("Doubly Linked List (Backwards)");
		printBackward(countryDLL);
	}
	/*
	 * Generic Method to print out countryLL in the forwards direction
	 * uses the LinkedListIterator
	 */
	public static <E> void printForward (LinkedList<E> list) { //O(n)
		Iterator<E> iter = list.iterator();
		System.out.print("[ ");
		while(iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println("]");
	}
	/*
	 * Generic Method to print out countryLL in the backwards direction
	 * uses a for loop and the get methods
	 */
	public static <E> void printBackward (LinkedList<E> list) { //(n^2)
		System.out.print("[ ");
		for(int i = list.size() - 1; i >= 0; i--) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("]");
	}
	/*
	 * Method to print out countryDLL in the forwards direction
	 * uses the forwards iterator
	 */
	public static <E> void printForward (DoublyLinkedList<E> list) { //O(n)
		ListIterator<E> iter = list.iterator();
		System.out.print("[ ");
		while(iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println("]");
	}
	/*
	 * Method to print out countryDLL in the backwards direction
	 * uses the backwards iterator
	 */
	public static <E> void printBackward(DoublyLinkedList<E> list) { //O(n)
		ListIterator<E> iter = list.iterator(list.size());
		System.out.print("[ ");
		while(iter.hasPrevious()) {
			System.out.print(iter.previous() + " ");
		}
		System.out.println("]");
	}
	/*
	 * Comparison of the 4 methods:
	 * For printing forwards, the time complexity is equal for the LinkedList and the DoublyLinkedList. They are both O(n).
	 * For printing backwards, the time complexity is significantly greater for the LinkedList. The DoublyLinkedList is O(n) whereas the LinkedList is O(n^2)
	 */
}