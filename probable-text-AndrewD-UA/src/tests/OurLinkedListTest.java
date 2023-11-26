package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.OurList;
import model.OurLinkedList;

/**
 * Provided test cases
 * 
 * @author Rick Mercer
 */
class OurLinkedListTest {

	@Test
	void testEverythingWithString() {
		OurList<String> strs = new OurLinkedList<>();
		strs.addFront("A");
		strs.addFront("B");
		strs.addFront("C");
		strs.addFront("D");
		assertEquals(4, strs.size());
		assertEquals("D", strs.get(0));
		assertEquals("C", strs.get(1));
		assertEquals("B", strs.get(2));
		assertEquals("A", strs.get(3));
	}

	@Test
	void testEverythingWithInteger() {
		OurList<Integer> strs = new OurLinkedList<>();
		strs.addFront(1);
		strs.addFront(2);
		strs.addFront(3);
		strs.addFront(4);
		assertEquals(4, strs.size());
		assertEquals(4, strs.get(0));
		assertEquals(3, strs.get(1));
		assertEquals(2, strs.get(2));
		assertEquals(1, strs.get(3));
	}
}