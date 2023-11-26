package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import model.Card;
import model.Hand;
import model.PokerHand;
import model.Rank;
import model.Suit;

/**
 * Tests the CardHand class, last modified Sep 2015, June 2017, July 23, August
 * 23
 * 
 * I think this a pretty good unit test, if you add any other test cases please
 * send them to me!
 * 
 * I am providing all 52 possible Cars to save you time writing @Tests
 * 
 * @author Rick Mercer and Andrew Dennison
 */
public class PokerHandTest {

	// Set up 52 cards so we can use C2 instead of new Card(Rank.Deuce, Suit.Clubs)
	private final static Card C2 = new Card(Rank.DEUCE, Suit.CLUBS);
	private final static Card C3 = new Card(Rank.THREE, Suit.CLUBS);
	private final static Card C4 = new Card(Rank.FOUR, Suit.CLUBS);
	private final static Card C5 = new Card(Rank.FIVE, Suit.CLUBS);
	private final static Card C6 = new Card(Rank.SIX, Suit.CLUBS);
	private final static Card C7 = new Card(Rank.SEVEN, Suit.CLUBS);
	private final static Card C8 = new Card(Rank.EIGHT, Suit.CLUBS);
	private final static Card C9 = new Card(Rank.NINE, Suit.CLUBS);
	private final static Card C10 = new Card(Rank.TEN, Suit.CLUBS);
	private final static Card CJ = new Card(Rank.JACK, Suit.CLUBS);
	private final static Card CQ = new Card(Rank.QUEEN, Suit.CLUBS);
	private final static Card CK = new Card(Rank.KING, Suit.CLUBS);
	private final static Card CA = new Card(Rank.ACE, Suit.CLUBS);

	private final static Card D2 = new Card(Rank.DEUCE, Suit.DIAMONDS);
	private final static Card D3 = new Card(Rank.THREE, Suit.DIAMONDS);
	private final static Card D4 = new Card(Rank.FOUR, Suit.DIAMONDS);
	private final static Card D5 = new Card(Rank.FIVE, Suit.DIAMONDS);
	private final static Card D6 = new Card(Rank.SIX, Suit.DIAMONDS);
	private final static Card D7 = new Card(Rank.SEVEN, Suit.DIAMONDS);
	private final static Card D8 = new Card(Rank.EIGHT, Suit.DIAMONDS);
	private final static Card D9 = new Card(Rank.NINE, Suit.DIAMONDS);
	private final static Card D10 = new Card(Rank.TEN, Suit.DIAMONDS);
	private final static Card DJ = new Card(Rank.JACK, Suit.DIAMONDS);
	private final static Card DQ = new Card(Rank.QUEEN, Suit.DIAMONDS);
	private final static Card DK = new Card(Rank.KING, Suit.DIAMONDS);
	private final static Card DA = new Card(Rank.ACE, Suit.DIAMONDS);

	private final static Card H2 = new Card(Rank.DEUCE, Suit.HEARTS);
	private final static Card H3 = new Card(Rank.THREE, Suit.HEARTS);
	private final static Card H4 = new Card(Rank.FOUR, Suit.HEARTS);
	private final static Card H5 = new Card(Rank.FIVE, Suit.HEARTS);
	private final static Card H6 = new Card(Rank.SIX, Suit.HEARTS);
	private final static Card H7 = new Card(Rank.SEVEN, Suit.HEARTS);
	private final static Card H8 = new Card(Rank.EIGHT, Suit.HEARTS);
	private final static Card H9 = new Card(Rank.NINE, Suit.HEARTS);
	private final static Card H10 = new Card(Rank.TEN, Suit.HEARTS);
	private final static Card HJ = new Card(Rank.JACK, Suit.HEARTS);
	private final static Card HQ = new Card(Rank.QUEEN, Suit.HEARTS);
	private final static Card HK = new Card(Rank.KING, Suit.HEARTS);
	private final static Card HA = new Card(Rank.ACE, Suit.HEARTS);

	private final static Card S2 = new Card(Rank.DEUCE, Suit.SPADES);
	private final static Card S3 = new Card(Rank.THREE, Suit.SPADES);
	private final static Card S4 = new Card(Rank.FOUR, Suit.SPADES);
	private final static Card S5 = new Card(Rank.FIVE, Suit.SPADES);
	private final static Card S6 = new Card(Rank.SIX, Suit.SPADES);
	private final static Card S7 = new Card(Rank.SEVEN, Suit.SPADES);
	private final static Card S8 = new Card(Rank.EIGHT, Suit.SPADES);
	private final static Card S9 = new Card(Rank.NINE, Suit.SPADES);
	private final static Card S10 = new Card(Rank.TEN, Suit.SPADES);
	private final static Card SJ = new Card(Rank.JACK, Suit.SPADES);
	private final static Card SQ = new Card(Rank.QUEEN, Suit.SPADES);
	private final static Card SK = new Card(Rank.KING, Suit.SPADES);
	private final static Card SA = new Card(Rank.ACE, Suit.SPADES);

	@Test
	public void testCard() {
		CardTest ct = new CardTest();
		ct.testCompareTo();
		ct.testEquals();
		ct.testGetters();
		ct.testToString();
	}

	@Test
	public void testRoyalFlush() {
		PokerHand rf1 = new PokerHand(SK, SA, SQ, SJ, S10);
		PokerHand rf2 = new PokerHand(SJ, SQ, SA, SK, S10);
		PokerHand lesser = new PokerHand(S9, S7, S8, S6, S5);

		assertEquals(0, rf1.compareTo(rf2));
		assertEquals(1, rf1.compareTo(lesser));
	}

	@Test
	public void testStraightFlush() {
		PokerHand rf = new PokerHand(SK, SA, SQ, SJ, S10);
		PokerHand sf1 = new PokerHand(S9, S7, S8, S6, S5);
		PokerHand sf1_h = new PokerHand(H9, H7, H8, H6, H5);
		PokerHand sf2 = new PokerHand(S8, S7, S4, S6, S5);

		PokerHand fourKind = new PokerHand(S4, C4, D4, H4, H10);

		assertEquals(-1, sf1.compareTo(rf));
		assertEquals(0, sf1.compareTo(sf1_h));
		assertEquals(1, sf1.compareTo(sf2));
		assertEquals(1, sf1.compareTo(fourKind));
	}

	@Test
	public void testfourOfAKind() {
		PokerHand fourOAK = new PokerHand(S4, C4, D4, H4, H10);
		PokerHand fourOAKHigh10 = new PokerHand(S4, C4, D4, H4, H10);
		PokerHand fourOAKHighJack = new PokerHand(S4, C4, D4, H4, HJ);
		PokerHand fourOAKLow = new PokerHand(H3, C3, D3, S3, H10);

		PokerHand lesser = new PokerHand(H4, C4, D4, H3, C3);

		assertEquals(1, fourOAK.compareTo(fourOAKLow));
		assertEquals(-1, fourOAK.compareTo(fourOAKHighJack));
		assertEquals(0, fourOAK.compareTo(fourOAKHigh10));
		assertEquals(1, fourOAK.compareTo(lesser));
	}

	@Test
	public void testFullHouse() {
		PokerHand fullHouseHigh3Pair = new PokerHand(H4, C4, D4, H3, C3);
		PokerHand fullHouseLow3Pair = new PokerHand(H3, C3, D3, H2, C2);
		PokerHand fullHouseLow2Pair = new PokerHand(H4, C4, D4, H2, D2);

		PokerHand fullHouseInvert = new PokerHand(H6, C6, D6, H10, C10);
		PokerHand fullHouseHigh = new PokerHand(H9, C9, D9, H6, C6);

		PokerHand lesser = new PokerHand(H2, H4, H6, H8, H10);

		assertEquals(1, fullHouseHigh3Pair.compareTo(lesser));
		assertEquals(1, fullHouseHigh3Pair.compareTo(fullHouseLow2Pair));
		assertEquals(1, fullHouseHigh3Pair.compareTo(fullHouseLow3Pair));

		assertEquals(1, fullHouseHigh.compareTo(fullHouseInvert));
	}

	@Test
	public void testFlush() {
		PokerHand flush = new PokerHand(H2, H4, H6, H8, H10);
		PokerHand flushSame = new PokerHand(S2, S4, S6, S8, S10);
		PokerHand flushLess = new PokerHand(H2, H4, H6, H8, H9);

		PokerHand lesser = new PokerHand(H3, S4, C5, D6, H7);
		PokerHand fullHouse = new PokerHand(H9, C9, D9, H6, C6);

		assertEquals(0, flush.compareTo(flushSame));
		assertEquals(1, flush.compareTo(flushLess));
		assertEquals(1, flush.compareTo(lesser));
		assertEquals(-1, flush.compareTo(fullHouse));
	}

	@Test
	public void testStraight() {
		PokerHand straight = new PokerHand(H3, S4, C5, D6, H7);
		PokerHand straightLow = new PokerHand(H2, S3, C4, D5, H6);

		PokerHand lower = new PokerHand(H4, S4, C4, C7, D6);

		assertEquals(1, straight.compareTo(straightLow));
		assertEquals(-1, straightLow.compareTo(straight));
		assertEquals(1, straight.compareTo(lower));
	}

	@Test
	public void testThreeKind() {
		PokerHand threeOAK = new PokerHand(H4, S4, C4, C7, D6);
		PokerHand threeOAKLowCard = new PokerHand(H4, S4, C4, C6, D5);
		PokerHand threeOAKLower = new PokerHand(H3, S3, C3, C10, DA);

		PokerHand lesser = new PokerHand(H4, C4, H6, D6, S10);
		assertEquals(1, threeOAK.compareTo(threeOAKLowCard));
		assertEquals(1, threeOAK.compareTo(threeOAKLower));
		assertEquals(1, threeOAK.compareTo(lesser));
	}

	@Test
	public void testTwoPairs() {
		PokerHand twoPair = new PokerHand(H4, C4, H6, D6, S10);
		PokerHand twoPairLower = new PokerHand(H5, D5, H4, C4, S10);
		PokerHand twoPairLowCard = new PokerHand(H4, C4, H6, D6, S9);
		PokerHand twoPairLowCardHighPair = new PokerHand(H4, C4, H7, D7, S9);

		PokerHand lesser = new PokerHand(H6, C6, D8, S9, SK);

		assertEquals(1, twoPair.compareTo(twoPairLower));
		assertEquals(1, twoPair.compareTo(twoPairLowCard));
		assertEquals(-1, twoPair.compareTo(twoPairLowCardHighPair));
		assertEquals(1, twoPair.compareTo(lesser));
	}

	@Test
	public void testOnePair() {
		PokerHand onePair = new PokerHand(H6, C6, D8, S9, SK);
		PokerHand onePairLower = new PokerHand(H5, C5, D8, S9, SA);

		PokerHand lesser = new PokerHand(C3, D6, C4, D7, DA);

		assertEquals(1, onePair.compareTo(onePairLower));
		assertEquals(1, onePair.compareTo(lesser));
	}

	@Test
	public void testHighCard1() {
		PokerHand a = new PokerHand(C3, D6, C4, S7, DA);
		PokerHand b = new PokerHand(C2, C5, C7, DQ, DK);
		System.out.println(a);
		assertEquals(1, a.compareTo(b));

		assertEquals(Hand.HIGH_CARD, a.getHeighestHand());
		assertThrows(IllegalArgumentException.class, () -> {
			new PokerHand(C3, C3, D6, D6, DA);
		});
		
		PokerHand c = new PokerHand(CA, CK, CQ, CJ, C8);
		assertEquals(Hand.FLUSH, c.getHeighestHand());
		
		PokerHand d = new PokerHand(HA, HK, HQ, DJ, D10);
		assertEquals(Hand.STRAIGHT, d.getHeighestHand());
	}
}