import java.util.List;

/**
 * An utility that calculates the {@link HandValue} that forms from the 
 * given list of 5 {@link Card}.
 */
public class HandChecker {
	private static boolean isFlush(List<Card> hand){
		if(hand.get(0).getSuit() == hand.get(1).getSuit() &&
			hand.get(1).getSuit() == hand.get(2).getSuit() && 
			hand.get(2).getSuit() == hand.get(3).getSuit() &&
			hand.get(3).getSuit() == hand.get(4).getSuit()){ 
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isStraight(List<Card> hand){
		if(hand.get(0).getValue() == hand.get(1).getValue()-1 &&
			hand.get(1).getValue() == hand.get(2).getValue()-1 && 
			hand.get(2).getValue() == hand.get(3).getValue()-1 &&
			hand.get(3).getValue() == hand.get(4).getValue()-1){ 
			return true;
		} else if(hand.get(0).getValue() == 1 &&
					hand.get(1).getValue() == 10 && 
					hand.get(2).getValue() == 11 &&
					hand.get(3).getValue() == 12 &&
					hand.get(4).getValue() == 13){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isRoyalFlush(List<Card> hand){
		if(isStraightFlush(hand) &&
			hand.get(0).getValue() == 1	&&
			hand.get(1).getValue() == 10 && 
			hand.get(2).getValue() == 11 &&
			hand.get(3).getValue() == 12 &&
			hand.get(4).getValue() == 13){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isStraightFlush(List<Card> hand){
		if(isStraight(hand) && isFlush(hand)){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isFourOfAKind(List<Card> hand){
		if(hand.get(0).getValue() == hand.get(1).getValue() &&
			hand.get(1).getValue() == hand.get(2).getValue() &&
			hand.get(2).getValue() == hand.get(3).getValue()){
			return true;
		} else if(hand.get(1).getValue() == hand.get(2).getValue() &&
				hand.get(2).getValue() == hand.get(3).getValue() &&
				hand.get(3).getValue() == hand.get(4).getValue()){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isFullHouse(List<Card> hand)
	{
		if (hand.get(0).getValue() == hand.get(1).getValue() &&
				hand.get(2).getValue() == hand.get(3).getValue() &&
				hand.get(3).getValue() == hand.get(4).getValue()){
			return true;
		} else if(hand.get(0).getValue() == hand.get(1).getValue() &&
				hand.get(1).getValue() == hand.get(2).getValue() &&
				hand.get(3).getValue() == hand.get(4).getValue()){
			return true;
		} else{
			return false;
		}
		
	}
	
	private static boolean isThreeOfAKind(List<Card> hand){
		if(hand.get(0).getValue() == hand.get(1).getValue() &&
				hand.get(1).getValue() == hand.get(2).getValue()){
				return true;
			} else if(hand.get(1).getValue() == hand.get(2).getValue() &&
					hand.get(2).getValue() == hand.get(3).getValue()){
				return true;
			} else if(hand.get(2).getValue() == hand.get(3).getValue() &&
					hand.get(3).getValue() == hand.get(4).getValue()){
				return true;
			} else {
				return false;
			}
	}
	
	private static boolean isTwoPair(List<Card> hand){
		if(hand.get(0).getValue() == hand.get(1).getValue() &&
				hand.get(2).getValue() == hand.get(3).getValue()){
				return true;
			} 
		if(hand.get(0).getValue() == hand.get(1).getValue() &&
					hand.get(3).getValue() == hand.get(4).getValue()){
				return true;
			} 
		if(hand.get(1).getValue() == hand.get(2).getValue() &&
					hand.get(3).getValue() == hand.get(4).getValue()){
				return true;
			} 
		return false;
			
	}
	
	private static boolean isPair(List<Card> hand){
		if(hand.get(0).getValue() == hand.get(1).getValue())
			return true;
		if(hand.get(1).getValue() == hand.get(2).getValue())
			return true;
		if(hand.get(2).getValue() == hand.get(3).getValue())
			return true;
		if(hand.get(3).getValue() == hand.get(4).getValue())
			return true;
		return false;
	}
	
	public static HandValue score(List<Card> hand){
		if (isRoyalFlush(hand))
			return HandValue.ROYAL_FLUSH;
		else if (isStraightFlush(hand))
			return HandValue.STRAIGHT_FLUSH;
		else if (isFourOfAKind(hand))
			return HandValue.FOUR_OF_A_KIND;
		else if (isFullHouse(hand))
			return HandValue.FULL_HOUSE;
		else if (isFlush(hand))
			return HandValue.FLUSH;
		else if (isStraight(hand))
			return HandValue.STRAIGHT;
		else if (isThreeOfAKind(hand))
			return HandValue.THREE_OF_A_KIND;
		else if (isTwoPair(hand))
			return HandValue.TWO_PAIR;
		else if (isPair(hand))
			return HandValue.PAIR;
		else		
			return HandValue.HIGH_CARD;
	}
}
