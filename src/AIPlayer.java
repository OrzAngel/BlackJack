/**
 * Act as an AI player<br>
 * Strategy : calculate the probability of winning when hit and winning when stand by brute force, then makes the decision based on the probability
 * @author CJC
 *
 */
public class AIPlayer extends Player {
	
	private static final int[] SCORE = {0,0,2,3,4,5,6,7,8,9,10,10,10,10,1};
	
	private int[] deck;
	private int num;
	private int[] hand;
	private int[] dealerHand;
	
	public AIPlayer(String name) {
		super(name);
		deck = new int[15];
		hand= new int[15];
		dealerHand = new int[15];
		shuffle();
	}

	@Override
	public void display(String message) {
				
		if (message.equals("A new Game starts")) {
			shuffle();
		}
		
		int index = message.indexOf("gets a %s.".substring(0, 7));
		if ( index >= 0 ) {
			
			index += 7 + 1;
			
			num--;
			
			int rank = toRank(message.substring(index).split("\\.")[0]);
			deck[rank]--; 
			
			if (message.contains(name)) {
				hand[rank]++;
			}
			if (message.contains("The dealer")) {
				dealerHand[rank]++;
			}
			
		}
	}


	@Override
	public boolean decision(String message) {
		
		if (message.equals("Do you want a new game?(y/n)")) {
			return true;
		}
		
		if (message.contains("Your score is: ")) {
			int index = "Your score is: ".length();
			int score = Integer.parseInt(message.substring(index).split("\\.")[0]);
			
			if (score <= 11) {
				return true;
			}
			
			double prob_h = probWhenHit(score);
			double prob_s = probWhenStand(score);
						
			if (prob_h > prob_s) {
				return true;
			} else {
				return false;
			}

		}
		
		return true;
	}
	
	private void shuffle() {
		for (int i = 2; i <= 14; i++) {
			deck[i] = 4;
			hand[i] = 0;
			dealerHand[i] = 0;
		}
		num = 52;
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
	
	/*
	 * winning prob when ask for a hit
	 */
	private double probWhenHit(int score) {
		
		if (score >= 21) {
			return 0.0;
		}
		
		double prob = 0.0;
		
		for (int i = 2; i <= 14 ; i++) {
			
			if (deck[i] <= 0 ) {
				continue;
			}
			
			double prob_i = (double)deck[i]/num;
			deck[i]--;
			num--;
			hand[i]++;
			
			int t = getScore(hand);
			
			double prob_s = probWhenStand(t);
			double prob_h = probWhenHit(t);	
			
			if (prob_s >= prob_h) {
				prob += prob_i * prob_s;
			} else {
				prob += prob_i * prob_h;
			}
			
			deck[i]++;
			num++;
			hand[i]--;
		}
			
		return prob;
	}
	
	/*
	 * winning prob when choose to stand
	 */
	private double probWhenStand(int score) {
		if (score > 21) {
			return 0.0;
		}
		
		if (score == 21) {
			return 1.0;
		}
		
		double prob = 0.0;
		
		for (int i = 2 ; i <= 14 ; i++) {
			
			if (deck[i] <= 0) {
				continue;
			}
			
			double prob_i = (double) deck[i]/num;
			dealerHand[i]++;
			
			int dealerScore = getScore(dealerHand);

			if (dealerScore > 21) {
				prob += prob_i;
				dealerHand[i]--;
				continue;
			}
			
			if (dealerScore >= 17) {
				if (dealerScore <= score) {	
					prob += prob_i;
				}
				
				dealerHand[i]--;
				continue;
			}
//			System.out.println(dealerScore);
			deck[i]--;
			num--;
			prob += prob_i * probWhenStand(score);
			dealerHand[i]--;
			deck[i]++;
			num++;

		}
		
		return prob;
	}

	
	private int getScore(int[] hand) {
		
		int score = 0;
		for (int i = 2; i <= 14; i++) {
			score += hand[i] * SCORE[i];
		}
		
		if (hand[14] > 0 && score <= 11) {
			score += 10;
		}
		
		return score;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] arfs) {
		
		
		AIPlayer ai = new AIPlayer("AI");
		for (int r = 2 ; r <= 14 ; r ++) {
			ai.deck[r] = 0;
		}
		
		ai.num = 4;
		ai.deck[14] = 1;
		ai.deck[13] = 3;
		
		ai.dealerHand[6] = 1;
		ai.hand[9] = 1;
		ai.hand[3] = 1;
		
		System.out.println(ai.probWhenHit(12));
		System.out.println(ai.probWhenStand(12));
		
		if (true) return;
		
		BlackJack bj = new BlackJack();

		Player aiPlayer1 = new AIPlayerBasic("AI_1");
		Player aiPlayer2 = new AIPlayerBasic("AI_2");
		Player aiPlayer3 = new AIPlayerBasic("AI_3");
		Player aiPlayer4 = new AIPlayerBasic("AI_4");
		Player aiPlayer5 = new AIPlayerBasic("AI_5");
		Player aiPlayer6 = new AIPlayerBasic("AI_6");
		Player aiPlayer7 = new AIPlayerBasic("AI_7");
		Player aiPlayer8 = new AIPlayerBasic("AI_8");

		Player aiPlayer21 = new AIPlayerBasic("AI_21");
		Player aiPlayer22 = new AIPlayerBasic("AI_22");
		Player aiPlayer23 = new AIPlayerBasic("AI_23");
		Player aiPlayer24 = new AIPlayerBasic("AI_24");
		
		AIPlayer aiPlayer9 = new AIPlayer("AI_9");
		
		bj.addPlayer(aiPlayer1);
		bj.addPlayer(aiPlayer2);
		bj.addPlayer(aiPlayer3);
		bj.addPlayer(aiPlayer4);
		bj.addPlayer(aiPlayer5);
		bj.addPlayer(aiPlayer6);
		bj.addPlayer(aiPlayer7);
		bj.addPlayer(aiPlayer8);
		
		bj.addPlayer(aiPlayer21);
		bj.addPlayer(aiPlayer22);
		bj.addPlayer(aiPlayer23);
		bj.addPlayer(aiPlayer24);
		
		bj.addPlayer(aiPlayer9);
		
		int[] total= {0,0,0,0,0,0,0,0,0,0,0,0,0};
		int k = 0;
		for (int i = 0; i< 100; i++) {
			k = -1 ;
			for (int score : bj.oneGame()) {
				k++;
				total[k] += score;
			}
		}
		
		for (int i :total) {
			System.out.println(i);
		}
		
	}
	

}
