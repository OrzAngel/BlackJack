
/**
 * calcuates the BlackJack score of a hand.
 * @author CJC
 *
 */
public class BlackJackScore {
	
	private static final int[] SCORE = {11,2,3,4,5,6,7,8,9,10,10,10,10};
	
	private static final int  BLACKJACK = 21;
	private static final int  SOFT_A_DEDUCTION = 10;
	
	/**
	 * calculates the score of a hand of cards
	 * @param hand the hand of card to be calculated
	 * @return the score
	 */
	public static int getScore(Hand hand) {
		
		int score = 0;
		int rank;
		int numOfSoftA = 0;
		
		for (Card c : hand.toArrayList()) {
			rank = c.getRank();
			score += SCORE[rank];
			if (rank == 0) {
				numOfSoftA ++;
			}
		}
		
		while (score > BLACKJACK && numOfSoftA > 0) {
			score -= SOFT_A_DEDUCTION;
			numOfSoftA -- ;
		}
		
		return score;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hand hand = new Hand(null);
		Card card = new Card(0,0);
		for (int i = 0 ; i<12 ; i++) {
			hand.addCard(card);
			System.out.println(getScore(hand)); 
		}
		
	}

}
