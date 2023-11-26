import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Test cases for LinkedStack. Tests for the two exceptions that should be
 * thrown, as well as edge cases for isEmpty().
 * 
 * Second test cases are for multiple elements in a stack.
 * 
 * @author Andrew Dennison
 */

class OurStackTest {

	@Test
	public void testIsEmpty() {
		OurStack<Integer> s = new LinkedStack<>();
		assertTrue(s.isEmpty());
		assertThrows(EmptyStackException.class, () -> s.peek());
		assertThrows(EmptyStackException.class, () -> s.pop());

		s.push(5);
		assertFalse(s.isEmpty());
		assertEquals(5, s.peek());
		assertEquals(5, s.pop());

		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		assertEquals(4, s.pop());
		s.push(5);
		assertEquals(5, s.pop());
		assertEquals(3, s.pop());
		assertEquals(2, s.pop());
		assertEquals(1, s.pop());
		assertTrue(s.isEmpty());
	}
}
