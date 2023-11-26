import java.util.EmptyStackException;

/**
 * This class contains one possible implementation of LinkedStack using
 * singly-linked Nodes. No recursion required.
 * 
 * @author Andrew Dennison
 */

// This stack must implement OurStack<Type> using a 
// singly linked data structure

public class LinkedStack<Type> implements OurStack<Type> {

	private class Node {
		private Type data;
		private Node nextNode;

		public Node(Type value) {
			this.data = value;
			nextNode = null;
		}
	}

	private Node root;

	public LinkedStack() {
		root = null;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void push(Type element) {
		Node temp = root;

		root = new Node(element);
		root.nextNode = temp;
	}

	@Override
	public Type peek() throws EmptyStackException {
		if (root == null) {
			throw new EmptyStackException();

		}
		return root.data;
	}

	@Override
	public Type pop() throws EmptyStackException {
		if (root == null) {
			throw new EmptyStackException();
		}

		Type temp = root.data;

		root = root.nextNode;
		return temp;
	}

}
