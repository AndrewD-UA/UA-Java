package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Contains framework for implementing a PokerHand object that implements
 * Comparable Thus, two pokerHands can be compared to each to determine which
 * one is more "valuable"
 * 
 * @author Andrew Dennison
 */
public class PokerHand implements Comparable<PokerHand> {

	// ArrayList of all of our cards
	private ArrayList<Card> cards;

	// Variables which store several properties of the current Hand, calculated in
	// constructor
	private ArrayList<Card> twoPairs = new ArrayList<Card>();
	private Card threePairs = null;
	private boolean flush = true;
	private boolean straight = true;
	private Hand heighestHand;

	/**
	 * Constructor for PokerHand takes in five unique cards, then computes the
	 * variables which store any necessary properties of the PokerHand.
	 * Specifically, with regards to the comparesTo method, which requires knowledge
	 * of pairs, three of a kind, flush status, and straight status.
	 * 
	 * @param card1 Card unique card.
	 * @param card2 Card unique card.
	 * @param card3 Card unique card.
	 * @param card4 Card unique card.
	 * @param card5 Card unique card.
	 */
	public PokerHand(Card card1, Card card2, Card card3, Card card4, Card card5) {

		// Properly store our cards from highest card to lowest card
		cards = new ArrayList<Card>(Arrays.asList(card1, card2, card3, card4, card5));
		Collections.sort(cards);
		Collections.reverse(cards);

		// Detect the properties we need to store in twoPairs, threePairs, flush, and
		// straight
		testDuplicates();
		testStraightFlush();
		testThreePairs();
		testTwoPairs();

		// Finally, compute our heighest Hand
		heighestHand = determineHeighestHand();
	}

	/**
	 * Search the available cards at a specific index
	 * 
	 * @param index Integer representation of where to search in the cards ArrayList
	 * @return Card object containing the card at that index
	 */
	public Card getCard(int index) {
		return cards.get(index);
	}

	/**
	 * Ensure we have no duplicate cards in our PokerHand.
	 * 
	 * @throws IllegalArgumentException if any two cards are the same.
	 */
	private void testDuplicates() {
		// For each card, search the remaining cards
		// And throw an error if there's a duplicate (same rank and suit)
		for (int i = 0; i < cards.size(); i++) {
			for (int j = i + 1; j < cards.size(); j++) {
				if (cards.get(i).equals(cards.get(j)))
					throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Executes a for loop with four iterations. If at any point, a card is not the
	 * same suit as the next card, or is not numerically one greater than the next
	 * card, store this result.
	 */
	private void testStraightFlush() {
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getSuit() != cards.get(i + 1).getSuit())
				flush = false;
			if (cards.get(i).getValue() != (cards.get(i + 1).getValue() + 1))
				straight = false;
		}
	}

	/**
	 * Executes a for loop with at most four iterations. If at any point a card is
	 * numerically equivalent to the next card, mark it as a pair and skip the
	 * following iteration.
	 */
	private void testTwoPairs() {
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).compareTo(cards.get(i + 1)) == 0) {
				twoPairs.add(cards.get(i));
				i++;
			}
		}
	}

	/**
	 * Executes a for loop with at most three iterations. If at any point a card is
	 * numerically equivalent to the next two cards, mark it as a three-of-a-kind
	 * and skip the following two iterations.
	 */
	private void testThreePairs() {
		for (int i = 0; i < cards.size() - 2; i++) {
			if (cards.get(i).compareTo(cards.get(i + 1)) == 0 && cards.get(i + 1).compareTo(cards.get(i + 2)) == 0) {

				threePairs = cards.get(i);
				i += 2;
			}
		}
	}

	/**
	 * Get method to access the heighest known hand of the PokerHand externally.
	 * 
	 * @return Hand enum representing the heighest hand.
	 */
	public Hand getHeighestHand() {
		return heighestHand;
	}

	/**
	 * Determine what the heighest known hand of this PokerHand is, and return it to
	 * be stored. Returning the heighest hand allows the PokerHand to be dynamically
	 * updated in future usage. This method acts as a switch statement, starting
	 * with the heighest hand. At each point further down the method, we know the
	 * hand cannot be any of the Hands that appear earlier in the method.
	 * 
	 * For example, when we reach "if(flush)", we already know the Hand is not a
	 * ROYAL_FLUSH, STRAIGHT_FLUSH, 4OAK, and FULL_HOUSE.
	 * 
	 * @return Hand enum representing the heighest hand.
	 */
	private Hand determineHeighestHand() {
		if (cards.get(0).getRank() == Rank.ACE && straight && flush) {
			return Hand.ROYAL_FLUSH;
		}

		if (straight && flush) {
			return Hand.STRAIGHT_FLUSH;
		}

		if (twoPairs.size() == 2) {
			if (twoPairs.get(0).compareTo(twoPairs.get(1)) == 0) {
				return Hand.FOUR_OF_A_KIND;
			}
		}

		if (twoPairs.size() > 1 && threePairs != null) {
			twoPairs.remove(twoPairs.indexOf(threePairs));
			return Hand.FULL_HOUSE;
		}

		if (flush) {
			return Hand.FLUSH;
		}

		if (straight) {
			return Hand.STRAIGHT;
		}

		if (threePairs != null) {
			return Hand.THREE_OF_A_KIND;
		}

		if (twoPairs.size() == 2) {
			return Hand.TWO_PAIR;
		}

		if (twoPairs.size() == 1) {
			return Hand.ONE_PAIR;
		}

		return Hand.HIGH_CARD;
	}

	/**
	 * This method overrides the comparable compareTo method.
	 * 
	 * @param o PokerHand object which this PokerHand should be comapred to
	 * @return Integer representation (1, 0, -1) of how this PokerHand compares to
	 *         another.
	 */
	@Override
	public int compareTo(PokerHand o) {

		// If we can easily determine a heighest hand difference, return appropriately
		if (heighestHand.compareTo(o.getHeighestHand()) == -1)
			return 1;
		if (heighestHand.compareTo(o.getHeighestHand()) == 1)
			return -1;

		// We can now assume this and o have the same heighestHand
		// If our heighestHand requires comparison of pairs, check the pairs first
		if (heighestHand.requiresPairs()) {
			// If this heighestHand factors 3-of-a-kind into it
			if (threePairs != null) {
				int comparison = threePairs.compareTo(o.threePairs);
				if (comparison != 0)
					return comparison;
			}

			// Otherwise, compare each pair in this hand
			// We need not do any comparisons between the size of the twoPairs because
			// we assume both this and other have the same heighestHand
			for (int i = 0; i < twoPairs.size(); i++) {
				int comparison = twoPairs.get(i).compareTo(o.twoPairs.get(i));
				if (comparison != 0)
					return comparison;
			}
		}

		// If all else has returned equal, simply compare the value of all the cards
		// Whichever hand has the heigh card, return
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).compareTo(o.getCard(i)) == 1)
				return 1;
			if (cards.get(i).compareTo(o.getCard(i)) == -1)
				return -1;
		}

		// If all else fails, we have two perfectly logically equivalent PokerHands
		return 0;
	}

	/**
	 * Returns a String representation of this PokerHand
	 * 
	 * @return String representation of PokerHand
	 */
	public String toString() {
		String result = "";
		for (Card card : cards) {
			result += card.toString() + " ";
		}

		return result.trim();
	}
}
