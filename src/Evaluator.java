import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The evaluator that uses Monte Carlo method to simulate the probability
 * of forming the best 5 card hand in 7 cards.
 */
public class Evaluator {
	
	/* The times simulation will run. */
	private int simCount;
	/* The table that records the highest hand formed. */
	private int[] handCount = new int[HandValue.values().length];

	/** 
	 * Constructs the evaluator with the given simulation count.
	 * @param simCount
	 * 				The amount of times simulation will run.
	 */
	public Evaluator(int simCount){
		this.simCount = simCount;
	}
	
	/** 
	 * Evaluates the probability of highest {@link HandValue} formed with the
	 * given list of {@link Card} and save the count in the table.
	 * 
	 * Separated hole cards and community cards so it is easier to
	 * implement probability of opponent's best hand.
	 * 
	 * Both lists does not have to follow rule of amount of cards as long as 
	 * there is less than 7. So pre-flop will have a null list for board.
	 * 
	 * @param hole
	 * 			List of {@link Card} for your hole cards.
	 * @param board
	 * 			List of {@link Card} for known community cards.
	 */
	public void evaluate(List<Card> hole, List<Card> board){

		/* Initialize deck and reset stats. */
		Deck deck = new Deck();
		resetStats();

		/* Simulation of possible hands. */
		for(int i = 0; i < simCount; i ++){
			
			deck.shuffle();
			
			/* Remove the drawn cards from deck. */
			deck.pullCards(hole);
			deck.pullCards(board);
			
			/* Saves the known cards in a list. */
			List<Card> allCards = new ArrayList<>();
			allCards.addAll(hole);
			allCards.addAll(board);
			
			if(allCards.size() > 7){
				throw new IllegalArgumentException("More than 7 cards.");
			}
			
			/* If there are less than 7 known cards, draw random cards from deck. */
			while(allCards.size() != 7){
				allCards.add(deck.pullCard());
			}
						
			/* Sort the known cards in value order. */
			sortCards(allCards);
			
			/* Obtain all the possible combinations of 5 card hand in 7 cards. */
			ArrayList<List<Card>> combinations = generateAllPossibleHands(allCards, 5);	

			/* Evaluate the highest hand in all combinations and add count to table. */
			HandValue highestHand = HandValue.NONE;
			for(List<Card> hand : combinations){
				HandValue handFormed = HandChecker.score(hand);
				if(handFormed.compare(highestHand) == 1){
					highestHand = handFormed;
				}
			}
			handCount[highestHand.getRank()]++;
						
		}
	
		printResult();
	}
		
	/**
	 * Prints the result from simulation in formatted text table.
	 */
	public void printResult(){
		System.out.printf("%-15s %-10s %-5s %n", "Highest Hand", "Percent%", "Count");
		System.out.printf("---------------------------------- %n");

		for(int i = handCount.length-1; i >= 0 ; i--){
			double percent = ((double)handCount[i]/(double)simCount) * 100;
			System.out.printf("%-15s %5.2f %8s %n",HandValue.values()[i], percent, handCount[i]);
		}
		System.out.printf("-%29d %n", simCount);

	}
	
	/**
	 * Sort the given list of {@link Card} in ascending order using
	 * basic selection sort. 
	 * 
	 * Choice of selection sort was the possibility of visual representation,
	 * might be changed for performance optimization.
	 * 
	 * @param cards
	 * 			List of {@link Card} to be sorted.
	 */
	private void sortCards(List<Card> cards){
		int n = cards.size();
		
		for(int i = 0;i < n - 1; i ++){
			int minIndex = i;
			for(int j = i + 1; j < n; j++){
				if(cards.get(j).getValue() < cards.get(minIndex).getValue()){
					minIndex = j;
				}
			}
			Card temp = cards.get(minIndex);
			cards.set(minIndex, cards.get(i));
			cards.set(i, temp);
		}
		
	}
	
	
	/**
	 * The helper function to create combinations recursively and saves it in a list.
	 * 
	 * @param list
	 * 			The list of to save finished combinations.
	 * @param cards
	 * 			The remaining cards to used to form combinations.
	 * @param store
	 * 			The list to store currently formed combination at that recursive stage.
	 * @param start, end
	 * 			The indices determine what remaining cards there are.
	 * @param index
	 * 			The index position in store.
	 * @param r
	 * 			The size of combination.
	 */
	private void combineRecursive(ArrayList<List<Card>> list, Card[] cards, Card[] store, int start, int end, int index, int r){
		
		/* Number of elements in store == size of combination.
		 * The combination formed is now finished and to be added to list */
		if(index == r){
			Card[] cs = store.clone();
			list.add(new ArrayList<Card>(Arrays.asList(cs)));
			return;
		}
		
		/* Loop down possible cards and place in store to form possible 
		 * combinations. The loop stops when all possible combination is
		 * placed into store of that recursive route. */
		for(int i = start; i <= end && end-i+1 >= r - index; i++){
			store[index] = cards[i];
			combineRecursive(list, cards, store, i + 1, end, index + 1, r);
		}
		
	}
	
	/**
	 * Creates a lists of all possible combinations using given list of 
	 * elements. Calls the recursive helper to form a tree from 
	 * each recursive route and store it in final list.
	 * 
	 * Helper: {@link Evaluator#combineRecursive(ArrayList, Card[], Card[], int, int, int, int)}
	 * 
	 * Classic k-combinations code from given elements. Might change a bit
	 * for performance optimization.
	 * 
	 * @param allCards
	 * 			List of {@link Card} to be used to form combinations.
	 * @param r
	 * 			Size of the combination.
	 * @return List of all possible combinations of lists of {@link Card}.
	 */
	private ArrayList<List<Card>> generateAllPossibleHands(List<Card> allCards, int r){
		ArrayList<List<Card>> list = new ArrayList<List<Card>>(21);
		Card[] store = new Card[r];
		Card[] cards = allCards.toArray(new Card[allCards.size()]);
		
		combineRecursive(list, cards, store, 0, cards.length - 1, 0, r);
		
		return list;
    }
	
	/**
	 * Resets the table.
	 */
	public void resetStats(){
		for(int i = 0 ; i < handCount.length; i++){
			handCount[i] = 0;
		}
	}
	
}
