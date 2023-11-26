package model;

/**
 * Enum containing all possible Hands one PokerHand can have, from ROYAL_FLUSH
 * to HIGH_CARD
 * 
 * @author Andrew Dennison
 */
public enum Hand {
	ROYAL_FLUSH(), STRAIGHT_FLUSH(), FOUR_OF_A_KIND(), FULL_HOUSE(), FLUSH(), STRAIGHT(), THREE_OF_A_KIND(),
	TWO_PAIR(), ONE_PAIR(), HIGH_CARD();

	public boolean requiresPairs() {
		return this == FULL_HOUSE || this == THREE_OF_A_KIND || this == TWO_PAIR || this == ONE_PAIR;
	}
}
