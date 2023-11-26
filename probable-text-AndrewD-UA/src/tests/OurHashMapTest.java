package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.OurHashMap;
import model.OurMap;

/**
 * Provided test case
 * 
 * @author Rick Mercer
 */
class OurHashMapTest {

	@Test
	void testConstructorAndToString() {
		OurMap<String, ArrayList<Character>> map = new OurHashMap<>();
		ArrayList<Character> followers = new ArrayList<>();
		followers.add('U');
		followers.add('A');
		map.put("Alice", followers);
		assertEquals("[U, A]", map.get("Alice").toString()); // [U, A]
	}
}