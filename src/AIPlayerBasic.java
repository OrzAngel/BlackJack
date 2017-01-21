/**
 * Act as an AI player<br>
 * Strategy : stick to the <a href=https://en.wikipedia.org/wiki/Blackjack#Basic_strategy> basic strategy from wiki </a>
 * @author CJC
 *
 */
public class AIPlayerBasic extends Player {
	
	private static final int[] SCORE = {0,0,2,3,4,5,6,7,8,9,10,10,10,10,1};
	
	private int dealerFaceUp;
	private int hard;
	
	public AIPlayerBasic(String name) {
		super(name);
		shuffle();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display(String message) {
		
//		System.out.println(message);
		
		if (message.equals("A new Game starts")) {
			shuffle();
		}
		
		if (message.contains(name)) {
			if (message.contains("gets a ")) {
				int index = message.indexOf("gets a ") + 7 + 1;
				int rank = toRank(message.substring(index).split("\\.")[0]);
				hard += SCORE[rank];
			}
		}
		
		if (message.contains("The dealer")) {
			if (message.contains("gets a ")) {
				int index = message.indexOf("gets a ") + 7 + 1;
				int rank = toRank(message.substring(index).split("\\.")[0]);
				dealerFaceUp = rank;
			}
		}

	}


	@Override
	public boolean decision(String message) {
		// TODO Auto-generated method stub
		
		if (message.equals("Do you want a new game?(y/n)")) {
			return true;
		}
		
		if (message.contains("Your score is: ")) {
			int index = "Your score is: ".length();
			int score = Integer.parseInt(message.substring(index).split("\\.")[0]);
			
			if (score == hard) {
				if (score >= 17) {
					return false;
				} 
				if (score > 12 && dealerFaceUp <= 6) {
					return false;
				} 
				if (score == 12 && dealerFaceUp >= 4 && dealerFaceUp <= 6) {
					return false;
				}
			} else {
				if(score >= 19) {
					return false;
				}
				if (score == 18 && dealerFaceUp <=8) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void shuffle() {
		dealerFaceUp = 0;
		hard = 0;
	}
	
	private int toRank(String s) {
		
		switch(s) {
		case "A" :
			return 14;
		case "J" :
			return 11;
		case "Q" :
			return 12;
		case "K" :
			return 13;
		default :
			return Integer.parseInt(s);
				
		}
		
	}
	
	public static void main(String[] arfs) {
		BlackJack bj = new BlackJack();
		Player ai = new AIPlayerBasic("AI");
		bj.addPlayer(ai);
		bj.oneGame();
	}
	

}
