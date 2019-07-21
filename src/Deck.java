import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A deck with 52-cards that stores card in an array in the fashion of stacks using cursor.
 */
public class Deck {
	
	/* Array of cards*/
	private Card[] d;
	/* Pointer to the current card Index */
	private int currCardIndex = 0;
	/* Variables for shuffle */
	private Random rand = new Random();

	/* Constructs a new deck and call init() that fill the deck with cards placed in order */
	public Deck() {
		init();
	}

	private void init(){
		currCardIndex = 0;
		d = new Card[52];
		int counter = 0;

		for (Suit suit : Suit.values()) {
			for (int value = 1; value <= 13; value++) {
				d[counter] = new Card(value, suit);
				counter++;
			}
		}
	
	}

	/** 
	 * Pull a set of {@link Card} from the deck. The function loops
	 * through the list of cards and call the function that pulls
	 * specific cards.
	 * 
	 * @param cards
	 * 			List of cards to pull from deck.
	 * @return List of with all the {@link Card} specified in given list.
	 */
	public List<Card> pullCards(List<Card> cards){
		List<Card> pulled = new ArrayList<Card>(cards.size());
		for(Card c : cards){
			pulled.add(c);
			pullCard(c);
		}
		return pulled;
	}
	
	/** 
	 * Pull a specified {@link Card} from the deck, throws exception if
	 * the {@link Card} has already been dealt. The function swaps the
	 * specified card with the next card after currentCardIndex and
	 * calls the regular pullCard() to pop it.
	 * 
	 * @param c
	 * 		The targeted card to pop from the deck.
	 * @return {@link Card} that is chosen.
	 */
	public Card pullCard(Card c){
		int index = 0;
		for(int i = 0; i < d.length; i++){
			if(d[i].getValue() == c.getValue() && d[i].getSuit() == c.getSuit()){
				index = i;
			}
		}
		
		if(index < currCardIndex){
			throw new IllegalArgumentException("Card already dealt.");
		}
		
		if(index > 0){
			swapCards(index, currCardIndex);
		}
		return pullCard();
	}
	
	/** 
	 * Pops the next {@link Card} from the deck.
	 * 
	 * @return The next {@link Card} after currentCardIndex.
	 */
	public Card pullCard() {
		return d[currCardIndex++];
	}
	
	/** 
	 * Peeks the next {@link Card} from the deck without drawing it.
	 * 
	 * @return The next {@link Card} after currentCardIndex.
	 */
	public Card peekCard() {
		return d[currCardIndex];
	}
	/** 
	 * Peeks the {@link Card} from the deck at given index without
	 * drawing it.
	 * 
	 * @param index
	 * 			The index to peek at.
	 * 
	 * @return The {@link Card} in deck at given index.
	 */
	public Card peekCard(int index){
		return d[index];
	}

	/** 
	 * Peeks the next {@link Card} from the deck without drawing it.
	 * 
	 * @return The next {@link Card} after currentCardIndex.
	 */
	private void swapCards(int i, int j) {
		Card tmp = d[i];
		d[i] = d[j];
		d[j] = tmp;
	}

	/** 
	 * Shuffles the deck by swapping cards at random indices for the
	 * given amount of times. 
	 * 
	 * The random indices comes from @link java.util.Random}.
	 * 
	 * @param count
	 * 			The amount to shuffle.
	 */
	public void shuffle(int count) {
		currCardIndex = 0;
		for (int i = 0; i < count; ++i) {
			for (int j = 0; j < 52; ++j) {
				int idx = rand.nextInt(52);
				swapCards(j, idx);
			}
		}
	}

	/** 
	 * Shuffles the deck by swapping cards at random indices for 10 times.
	 * 
	 */
	public void shuffle() {
		this.shuffle(10);
	}
	
	/** 
	 * Returns the remaining amount of cards that has not been drawn.
	 * 
	 * @return The remaining amount of cards.
	 */
	public int getRemainingCardsCount(){
		return d.length - currCardIndex;
	}
	
	/** 
	 * Loops through the deck and print out each card.
	 */
	public void print()
	{
		for(Card c : d){
			System.out.println(c.toString());
		}
	}

}