package model;

/**
 * This class contains the code for a Linked List to be used to resolve
 * collisions in OurHashMap. This code was provided by Rick Mercer, except the
 * toString method.
 * 
 * @author Andrew Dennison and Rick Mercer
 * @param <Type> Generic Type that this LL will contain in its data
 */
public class OurLinkedList<Type> implements OurList<Type> {

	private class Node {
		private Type data; // Reference to one element
		private Node next; // null, or reference to next node

		public Node(Type element, Node nodeRef) {
			data = element;
			next = nodeRef;
		}

		public String toString() {
			return data.toString();
		}
	}

	// front is an external reference to the first element
	private Node front;
	// n is the number of elements in the list
	int n;

	public OurLinkedList() {
		n = 0;
		front = null;
	}

	// Return the number of elements currently in the list.
	public int size() {
		return n;
	}

	// Return the value of the element stored at the given index.
	// Precondition: 0 <= getIndex < size()
	public Type get(int getIndex) {
		Node ref = front;
		for (int c = 0; c < getIndex; c++) {
			ref = ref.next;
		}
		return ref.data;
	}

	// Add element at the front of the list
	public void addFront(Type element) {
		front = new Node(element, front);
		n++;
	}

	/**
	 * Formats each child node for output Each LL is presented similarly to an
	 * Array.
	 */
	public String toString() {
		String result = "Linked List: ";
		Node ref = front;
		for (int i = 0; i < size(); i++) {
			result += "[Node " + i + ": " + ref.toString() + "]";
			ref = ref.next;
		}

		return result;
	}
}