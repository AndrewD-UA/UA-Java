import java.util.EmptyStackException;

/**
 * A specification for a stack collection class
 * 
 * @param <Type> The type of elements stored on the stack
 * 
 * @author Rick Mercer
 */

interface OurStack<Type> {
	/**
	 * Check if stack is empty to help avoid popping an empty stack.
	 * 
	 * @returns true if there are 0 elements on this stack
	 */
	public boolean isEmpty();

	/**
	 * Put element on "top" of this Stack object.
	 * 
	 * @param element to be placed at the top of this stack
	 */
	public void push(Type element);

	/**
	 * Return reference to the element at the top of stack
	 * 
	 * @returns A reference to the top element.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public Type peek() throws EmptyStackException;

	/**
	 * Remove element at top and return reference to it
	 * 
	 * @returns reference to the most recently pushed element
	 * @throws EmptyStackException if the stack is empty.
	 */
	public Type pop() throws EmptyStackException;

}
