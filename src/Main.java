import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
			
		Scanner in = new Scanner(System.in);
		String input;
				
		InputParser ip = new InputParser();
		Evaluator ev = new Evaluator(100000);
		
		System.out.printf("**** Enter Hole Cards ****\n");
		System.out.printf("* End Each Card with forward slash('/')\n");
		System.out.printf("* Ex. 1s/5c/ (= Ace of SPACES + 5 of CLUBS)\n");
		System.out.printf("- ");

		input = in.nextLine();
		List<Card> hole = ip.parseCards(input);
		
		ev.evaluate(hole, new ArrayList<Card>());
		
		System.out.printf("**** Enter Flop ****\n");
		System.out.printf("* End Each Card with forward slash('/')\n");
		System.out.printf("* Ex. 2d/11h/13c/ (= 2 of DIAMONDS/Jack of HEARTS/King of CLUBS)\n");
		System.out.printf("- ");
	
		input = in.nextLine();
		List<Card> board = ip.parseCards(input);
		ev.evaluate(hole, board);

		System.out.printf("**** Enter Turn ****\n");
		System.out.printf("* End Each Card with forward slash('/')\n");
		System.out.printf("* Ex. 3d/ (= 2 of DIAMONDS)\n");
		System.out.printf("- ");
		
		input = in.nextLine();
		List<Card> turn = ip.parseCards(input);
		board.addAll(turn);
		ev.evaluate(hole, board);
				
		System.out.printf("**** Enter River ****\n");
		System.out.printf("* End Each Card with forward slash('/')\n");
		System.out.printf("* Ex. 4d/ (= 2 of DIAMONDS)\n");
		System.out.printf("- ");
		
		input = in.nextLine();
		List<Card>river = ip.parseCards(input);
		board.addAll(river);
		ev.evaluate(hole, board);
		
	}

}
