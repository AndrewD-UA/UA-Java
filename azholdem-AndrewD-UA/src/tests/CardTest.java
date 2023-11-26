package tests;

/** 
 * Start of a JUnit test for class Card with enums.
 * 
 * @author Rick Mercer and Andrew Dennison
 */
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import model.Card;
import model.Rank;
import model.Suit;

public class CardTest {
  
  @Test
  public void testGetters() {
    Card c1 = new Card(Rank.DEUCE, Suit.CLUBS);
    assertEquals(Rank.DEUCE, c1.getRank());
    assertEquals(Suit.CLUBS, c1.getSuit());
    assertEquals(2, c1.getValue());
  }

  @Test
  public void testCompareTo() {
    Card c1 = new Card(Rank.DEUCE, Suit.CLUBS);
    Card c2 = new Card(Rank.THREE, Suit.DIAMONDS);
    assertEquals(-1, c1.compareTo(c2));
    assertEquals(1, c2.compareTo(c1));
    assertEquals(0, c1.compareTo(c1));
  }
  
  @Test
  public void testToString() {
    Card c2 = new Card(Rank.DEUCE, Suit.CLUBS);
    assertEquals("2" + '\u2663', c2.toString());
    Card c3 = new Card(Rank.THREE, Suit.DIAMONDS);
    assertEquals("3" + '\u2666', c3.toString());
    Card c4 = new Card(Rank.FOUR, Suit.HEARTS);
    assertEquals("4" + '\u2665', c4.toString());
  }

  @Test
  public void testEquals() {
    Card c1 = new Card(Rank.DEUCE, Suit.CLUBS);
    Card c2 = new Card(Rank.THREE, Suit.DIAMONDS);
    Card c3 = new Card(Rank.DEUCE, Suit.CLUBS);

    assertFalse(c1.equals(c2));
    assertFalse(c3.equals(c2));
    assertTrue(c1.equals(c3));
  }

}