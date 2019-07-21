
/**
 * The hand values in regular Texas Hold'em game. 
 */

public enum HandValue {
	/** No qualifying hand. **/
	NONE(0),
	/** A high card. **/
	HIGH_CARD(1), 
	/** A pair. **/
	PAIR(2),
	/** Two-pair. **/
	TWO_PAIR(3), 
	/** Three of a kind. */
	THREE_OF_A_KIND(4),
	/** A straight. **/
	STRAIGHT(5), 
	/** A flush. **/
	FLUSH(6), 
	/** A full house. **/
	FULL_HOUSE(7),
	/** Four of a kind. **/
	FOUR_OF_A_KIND(8), 
	/** A straight-flush. **/
	STRAIGHT_FLUSH(9),
	/** A royal-flush. **/
	ROYAL_FLUSH(10);
	/* The hand rank. */
	private final int rank;

	/**
	 * Constructs a new {@link HandValue} using the specified rank.
	 * 
	 * @param rank
	 *            The rank.
	 */
	private HandValue(int rank) {
		this.rank = rank;
	}

	/**
	 * Returns the value for this {@link HandValue}.
	 * 
	 * @return The value of this {@link HandValue}.
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Compares this {@link HandValue} value to another {@link HandValue} value.
	 * 
	 * @param v
	 *            The {@link HandValue} to compare to.
	 * @return 1 if this is higher in rank, -1 if this is smaller in rank, 
	 * 			and 0 if they have the same rank.
	 */
	public int compare(HandValue v) {
		if (this.rank == v.rank) {
			return 0;
		}
		return this.rank > v.rank ? 1 : -1;
	}
}
