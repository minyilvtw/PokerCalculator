import java.util.ArrayList;
import java.util.List;

public class InputParser {
	
	public List<Card> parseCards(String s){
		List<Card> cards = new ArrayList<Card>();
		char[] text = s.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < text.length; i++){
			if(text[i] != '/'){
				sb.append(text[i]);
			} else {
				Card card = parseCard(sb.toString());
				cards.add(card);

				sb = new StringBuilder();
			}
		}
				
		return cards;
	}
	
	private Card parseCard(String s){
		if(s.length() > 3){
			return null;
		}
		int value = 0;
		Suit suit = Suit.CLUBS;
		
		if(s.length() == 2){
			value = Integer.parseInt(s.substring(0, 1));
			suit = parseSuit(s.charAt(1));
		} else {
			value = Integer.parseInt(s.substring(0, 2));
			suit = parseSuit(s.charAt(2));
		}
		
		return new Card(value, suit);
	}
	
	private Suit parseSuit(char c){
		switch(c){
			case 's':
				return Suit.SPADES;
			case 'h':
				return Suit.HEARTS;
			case 'c':
				return Suit.CLUBS;
			case 'd':
				return Suit.DIAMONDS;
			default:
				return null;
		}
	}
	
	
	
	
}
