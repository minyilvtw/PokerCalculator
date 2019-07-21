

/**
 * A single poker card that has suit and value. Does not include the Jokers.
 */
public class Card {
	
	/* Labels for the cards for printing. */
	public static final char[] LABEL = {'A', '2', '3', '4', '5', '6', '7',
			'8', '9', 'T', 'J', 'Q', 'K'};
	
	/* The value of the card. */
	private final int value;
	/* The suit of the card. */
	private final Suit suit;
	
	/** 
	 * Constructs a new {@link Card} with the given value and suit.
	 * 
	 * @param value
	 *            The value of the card.
	 * @param suit
	 *            The suit of the card.
	 */
	public Card(int value, Suit suit){
		this.value = value;
		this.suit = suit;
	}
	
	
	/**
	 * Obtains the value of the card.
	 * 
	 * @return The value of the card.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Obtains the suit of the card.
	 * 
	 * @return The suit of the card.
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Compares this {@link Card} to another {@link Card}.
	 * 
	 * @param o
	 * 			The other card to compare against
	 * 
	 * @return 1 if this is larger, -1 if this is smaller, 0 if they have the same value.
	 */
	public int compareTo(Card o) {
		if(this.getValue() > o.getValue()){
			return 1;
		} else if(this.getValue() < o.getValue()){
			return -1;
		} else {
			return 0;
		}
    }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (suit != other.suit || value != other.value)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(LABEL[value-1] + " ").append(suit.toString()).toString();
	}
}
