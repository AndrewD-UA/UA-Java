import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Test cases for Expressions.java
 * 
 * @author Andrew Dennison
 */
class ExpressionTest {

	Expressions e = new Expressions();

	@Test
	void testValueOf() {
		assertEquals(5, e.valueOf("2 3 +"));
		assertEquals(3, e.valueOf("15 4 % 5 %"));
		assertEquals(2, e.valueOf("4 2 /"));
		assertEquals(1, e.valueOf("2 1 -"));
		assertEquals(6, e.valueOf("3 2 *"));
		assertEquals(0, e.valueOf("6 3 % 4 + 5 - 2 * 6 /"));
		System.out.println(e.valueOf("5 2 1 - - 3 1 4 + + *"));
		System.out.println(e.valueOf("9 3 / 5 + 7 2 - *"));
		System.out.println(e.valueOf("3 2 * 2 ^ 5 3 - 8 4 / * -"));

	}

	@Test
	void testEdgeCases() {
		assertThrows(EmptyStackException.class, () -> e.valueOf(""));
		assertEquals(4, e.valueOf("4"));
		assertThrows(EmptyStackException.class, () -> e.valueOf("3 +"));
		assertThrows(EmptyStackException.class, () -> e.valueOf("3 4 + -"));
		assertThrows(IllegalArgumentException.class, () -> e.valueOf("3 4 &"));
	}

	@Test
	void testInToPost() {
		assertEquals("-12 7 +", e.inToPost("-12 + 7"));
		assertEquals("2 3 4 + 2 / * 4 %", e.inToPost("2 * ( ( 3 + 4 ) / 2 ) % 4"));
		assertEquals("1 2 * 3 - 4 +", e.inToPost("1 * 2 - 3 + 4"));
		assertEquals("4 2 + 3 6 * -", e.inToPost("4 + 2 - 3 * 6"));
		assertEquals("4 2 3 6 * - +", e.inToPost("4 + ( 2 - ( 3 * 6 ) )"));
	}

	@Test
	void testInToPostEdge() {
		assertEquals("", e.inToPost(""));
		assertEquals("3", e.inToPost("3"));
		assertThrows(EmptyStackException.class, () -> e.valueOf(e.inToPost("")));
		assertEquals(3, e.valueOf(e.inToPost("3")));
		assertThrows(IllegalArgumentException.class, () -> e.inToPost("3 & 4"));
	}

	@Test
	void testCombo() {
		assertEquals(3, e.valueOf(e.inToPost("1 * 2 - 3 + 4")));
		assertEquals(27, e.valueOf(e.inToPost("3 * ( 4 + 5 )")));
		assertEquals(-12, e.valueOf(e.inToPost("4 + 2 - 3 * 6")));
		assertEquals(-12, e.valueOf(e.inToPost("4 + ( 2 - ( 3 * 6 ) )")));
		assertEquals(-5, e.valueOf(e.inToPost("-12 + 7")));
	}
	
	@Test
	void otherTests() {
		OurStackTest t = new OurStackTest();
		t.testIsEmpty();
	}
}
